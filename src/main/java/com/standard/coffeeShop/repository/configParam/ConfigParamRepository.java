package com.standard.coffeeShop.repository.configParam;

import com.standard.coffeeShop.repository.entity.ConfigParamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfigParamRepository extends JpaRepository<ConfigParamEntity, String> {

}
