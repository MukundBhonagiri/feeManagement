package com.wrightapps.smartedu.feeManagementservice.controller;

import java.util.List;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wrightapps.smartedu.feeManagementservice.port.FMPort;
import com.wrightapps.smartedu.feeManagementservice.request.dto.FeeTemplateVo;
import com.wrightapps.smartedu.feeManagementservice.response.dto.FMResponse;
import com.wrightapps.smartedu.feeManagementservice.service.FMService;

@RestController
@RequestMapping("/api/v1/feeManagement")
public class FMController implements FMPort {

	private static final Logger log = LogManager.getLogger(FMController.class);

	private FMService fmService;

	@Autowired
	public FMController(FMService fmService) {
		this.fmService = fmService;
	}

	@Override
	public ResponseEntity<FMResponse> saveFeeManagementDetails(FeeTemplateVo templateVo) {
		ResponseEntity<FMResponse> feeTemp = null;
		try {
			feeTemp = fmService.saveFeeManagementDetails(templateVo);
		} catch (Exception e) {
			log.error("Exception occurred while creating Fee Template" + e.getMessage());
		}
		return feeTemp;
	}

	@Override
	public ResponseEntity<FMResponse> getFeeManagementDetails(Integer studentId) {
		ResponseEntity<FMResponse> feeTemp = null;
		try {
			log.info("entered to controller"+ studentId);
			feeTemp = fmService.getFeeManagementDetails(studentId);
		} catch (Exception e) {
			log.error("Exception occurred while getting Fee Template by student" + e.getMessage());
		}
		return feeTemp;
	}

	@Override
	public ResponseEntity<FMResponse> updateFeeManagementDetails(FeeTemplateVo templateVo) {
		ResponseEntity<FMResponse> feeTemp = null;
		try {
			feeTemp = fmService.updateFeeManagementDetails(templateVo);
		} catch (Exception e) {
			log.error("Exception occurred while updating Fee Template by student" + e.getMessage());
		}

		return feeTemp;
	}

	@Override
	public void deleteFeeManagementDetails(Integer feesId) {
		fmService.deleteFeeManagementDetails(feesId);
	}

}
