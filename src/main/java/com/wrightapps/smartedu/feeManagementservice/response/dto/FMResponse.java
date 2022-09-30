package com.wrightapps.smartedu.feeManagementservice.response.dto;

import java.util.List;

import com.wrightapps.smartedu.feeManagementservice.entity.FeesTemplate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FMResponse {
	
	private String status;
	
	private FeesTemplate result;
	
	private List<FeesTemplate> results;
	
	private String error;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public FeesTemplate getResult() {
		return result;
	}

	public void setResult(FeesTemplate result) {
		this.result = result;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public List<FeesTemplate> getResults() {
		return results;
	}

	public void setResults(List<FeesTemplate> results) {
		this.results = results;
	}
	
	
	
}
