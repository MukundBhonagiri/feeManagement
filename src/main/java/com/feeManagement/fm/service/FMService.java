package com.feeManagement.fm.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.feeManagement.fm.entity.FeesTemplate;
import com.feeManagement.fm.model.FeeTemplateVo;
import com.feeManagement.fm.repository.FMRepository;

@Service
public class FMService {
	
	private static final Logger log = LogManager.getLogger(FMService.class);
	
	@Autowired
	FMRepository fmRepo;

	public FeesTemplate saveFeeManagementDetails(FeeTemplateVo templateVo) {
		 log.info("payterm size ::" + templateVo.getPayTerm().size());
		 
		  
		 templateVo.getPayTerm().forEach( fee -> {
			 FeesTemplate feesTemplate = new FeesTemplate();
			 
			 feesTemplate.setTenantId(templateVo.getTenantId());
			 feesTemplate.setSchoolId(templateVo.getSchoolId());
			 feesTemplate.setFeesCategory(templateVo.getFeesCategory());
			 feesTemplate.setFeesDescription(templateVo.getFeesDescription());
			 feesTemplate.setApplicableTo(templateVo.getApplicableTo());
			 feesTemplate.setGradeIds(templateVo.getGradeIds());
			 feesTemplate.setCreatedBy(templateVo.getCreatedBy());
			 feesTemplate.setAllowPartialPay(templateVo.isAllowPartialPay());
			 feesTemplate.setValidity(templateVo.getValidity());
			 feesTemplate.setCurrency(templateVo.getCurrency());
			 feesTemplate.setNotifyBefore(templateVo.getNotifyBefore());
			
			 feesTemplate.setTerm(fee.getTerm());
			 feesTemplate.setTermId(fee.getTermId());
			 feesTemplate.setDueDate(fee.getDueDate());
			 feesTemplate.setToPay(fee.getToPay());
			 feesTemplate.setPayFrequency(fee.getPayFrequency());
			 fmRepo.save(feesTemplate);
		 });
		 return null;
	}

	public List<FeesTemplate> getFeeManagementDetails() {
		return  (List<FeesTemplate>) fmRepo.findAll();
	}

	public void deleteFeeManagementDetails(Integer feesId) {
		
		fmRepo.deleteById(feesId);
	}

	
}
