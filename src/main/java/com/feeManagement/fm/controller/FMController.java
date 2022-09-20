package com.feeManagement.fm.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.feeManagement.fm.entity.FeesTemplate;
import com.feeManagement.fm.model.FeePayDetails;
import com.feeManagement.fm.model.FeeTemplateVo;
import com.feeManagement.fm.service.FMService;



@RestController
@RequestMapping("/fmController")
public class FMController  {
	
	private static final Logger log = LogManager.getLogger(FMController.class);
	
	@Autowired
	FMService fmService;	
	
	@PostMapping("/createFeeDetails")
	public ResponseEntity<FeeTemplateVo> saveFeeManagementDetails(@Valid @RequestBody FeeTemplateVo templateVo) {
		log.info("entered to controller"+templateVo.getTenantId());
		FeesTemplate feeTemp = fmService.saveFeeManagementDetails(templateVo);
		return  new ResponseEntity<>(templateVo, HttpStatus.CREATED);
	} 
	
	@GetMapping
	public List<FeesTemplate> getFeeManagementDetails() {
		
		return fmService.getFeeManagementDetails();
	}
	
	@PutMapping
	public ResponseEntity<FeesTemplate> updateFeeManagementDetails(@RequestBody FeeTemplateVo templateVo ) {
		FeesTemplate feeTemp =  fmService.saveFeeManagementDetails(templateVo);
		return  new ResponseEntity<>(feeTemp, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{feesId}")
	public void deleteFeeManagementDetails(@PathVariable("feesId") Integer feesId) {
		fmService.deleteFeeManagementDetails(feesId);
	}

}
