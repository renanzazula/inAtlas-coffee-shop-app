package com.standard.coffeeShop.controller.user;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.standard.coffeeShop.controller.domain.security.VerifyCode;
import com.standard.coffeeShop.repository.entity.security.UserEntity;
import com.standard.coffeeShop.repository.security.UserRepository;
import com.standard.coffeeShop.utils.ConstantsApi;
import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorQRGenerator;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Api(value = "Manage User", tags = "user")
@Slf4j
@RestController
@RequestMapping(UserController.USER_CONTROLLER_BASE_URL)
@RequiredArgsConstructor
public class UserController {
    public static final String USER_CONTROLLER_BASE_URL = ConstantsApi.USER;

    private final GoogleAuthenticator googleAuthenticator;
    private final UserRepository userRepository; //change to the service layer


    @GetMapping("/register")
    public void register2fa(HttpServletResponse response){
        UserEntity user = getUser();
        String url = GoogleAuthenticatorQRGenerator.getOtpAuthTotpURL("SCSA", user.getUsername(), googleAuthenticator.createCredentials(user.getUsername()));
        log.debug("Google URL: " + url);
        try {
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(url, BarcodeFormat.QR_CODE, 200, 200);
            ServletOutputStream outputStream = null;
            outputStream = response.getOutputStream();
            MatrixToImageWriter.writeToStream(bitMatrix, "PNG", outputStream);
            outputStream.close();
        } catch (WriterException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/confirm")
    public ResponseEntity<Boolean> confirm2Fa(@RequestBody VerifyCode verifyCode){
        log.debug("Entered code is: " + verifyCode);
        UserEntity user = getUser();
        if(googleAuthenticator.authorizeUser(user.getUsername(), verifyCode.getCode())){
            UserEntity savedUser = userRepository.findById(user.getId()).orElseThrow();
            savedUser.setUseGoogle2fa(true);
            userRepository.save(savedUser);
            return new ResponseEntity<Boolean>(true, HttpStatus.OK);
        } else {
            return new ResponseEntity<Boolean>(false, HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping("/verify")
    public ResponseEntity<Boolean> verify2Fa(@RequestBody VerifyCode verifyCode){
        log.debug("Entered code is: " + verifyCode);
        UserEntity user = getUser();
        if(googleAuthenticator.authorizeUser(user.getUsername(), verifyCode.getCode())){
            ((UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).setUserGoogle2FaRequired(false);
           return new ResponseEntity<Boolean>(true, HttpStatus.OK);
        } else {
            return new ResponseEntity<Boolean>(false, HttpStatus.FORBIDDEN);
        }
    }

    private static UserEntity getUser() {
        return (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal(); // change to domain obj
    }
}
