package com.wrightapps.smartedu.feeManagementservice.repository;

import org.springframework.data.repository.CrudRepository;

import com.wrightapps.smartedu.feeManagementservice.entity.FeesTemplate;

public interface FMRepository extends CrudRepository<FeesTemplate, Integer> {

	FeesTemplate findByFeesId(Integer feesId);

}
