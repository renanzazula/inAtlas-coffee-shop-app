package com.standard.coffeeshop.service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.standard.coffeeshop.utils.Constants;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

public @Data
class BaseDto implements Serializable {

    private String id;

    @JsonFormat(pattern= Constants.PATTERN_DATE_FORMAT)
    private Date dateCreate;

    @JsonFormat(pattern= Constants.PATTERN_TIME_FORMAT)
    private Date timeCreate;

    @JsonFormat(pattern= Constants.PATTERN_DATE_FORMAT)
    private Date dateUpdate;

    @JsonFormat(pattern= Constants.PATTERN_TIME_FORMAT)
    private Date timeUpdate;

    private Long whoCreate;
    private Long whoUpdate;

}
