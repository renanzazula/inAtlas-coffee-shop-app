package com.standard.coffeeshop.repository.configparam;

import com.standard.coffeeshop.repository.entity.ConfigParamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfigParamRepository extends JpaRepository<ConfigParamEntity, String> {

}
