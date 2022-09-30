package com.wrightapps.smartedu.feeManagementservice.service;

import org.springframework.http.ResponseEntity;

import com.wrightapps.smartedu.feeManagementservice.request.dto.FeeTemplateVo;
import com.wrightapps.smartedu.feeManagementservice.response.dto.FMResponse;

public interface FMService {

	ResponseEntity<FMResponse> saveFeeManagementDetails(FeeTemplateVo templateVo);

	ResponseEntity<FMResponse> getFeeManagementDetails(Integer studentId);

	ResponseEntity<FMResponse> updateFeeManagementDetails(FeeTemplateVo templateVo);

	void deleteFeeManagementDetails(Integer feesId);
	
}
