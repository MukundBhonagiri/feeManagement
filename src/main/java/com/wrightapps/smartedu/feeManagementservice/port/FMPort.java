package com.wrightapps.smartedu.feeManagementservice.port;


import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.wrightapps.smartedu.feeManagementservice.request.dto.FeeTemplateVo;
import com.wrightapps.smartedu.feeManagementservice.response.dto.FMResponse;

import io.swagger.v3.oas.annotations.Operation;

public interface FMPort {
	
	@PostMapping(path = {"/create"},consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Create a Fee Template")
	public ResponseEntity<FMResponse> saveFeeManagementDetails(@RequestBody @Valid FeeTemplateVo feeTemplateVo);
	
	@GetMapping(path = {"/{studentId}"} ,consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Get a Fee Template")
	public ResponseEntity<FMResponse> getFeeManagementDetails(@PathVariable("studentId") Integer studentId);
	
	@PostMapping(path = {"/update"},consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Update a Fee Template")
	public ResponseEntity<FMResponse> updateFeeManagementDetails(@RequestBody @Valid FeeTemplateVo templateVo );
		
	@DeleteMapping(path = {"/{feesId}"},consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Delete a Fee Template")
	public void deleteFeeManagementDetails(@PathVariable("feesId") Integer feesId);
	
}
