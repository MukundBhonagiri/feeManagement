package com.feeManagement.fm.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.feeManagement.fm.entity.FeesTemplate;
import com.feeManagement.fm.entity.StudentFeeTemplate;
import com.feeManagement.fm.model.FeePayDetails;
import com.feeManagement.fm.model.FeeTemplateVo;
import com.feeManagement.fm.repository.FMRepository;
import com.feeManagement.fm.repository.StudentFMRepository;

@Service
public class FMService {
	private static final Logger log = LogManager.getLogger(FMService.class);

	FMRepository fmRepo;
	StudentFMRepository studentFmRepo;

	@Autowired
	public FMService(FMRepository fmRepo, StudentFMRepository studentFmRepo) {
		this.fmRepo = fmRepo;
		this.studentFmRepo = studentFmRepo;
	}

	public List<FeesTemplate> saveFeeManagementDetails(FeeTemplateVo templateVo) {
		List<FeesTemplate> feeTemplateList = new ArrayList<>();
		templateVo.getPayTerm().forEach(fee -> {
			FeesTemplate feesTemplate = new FeesTemplate();
			StudentFeeTemplate studentFeeTemplate = new StudentFeeTemplate();

			feesTemplate = convertTemplateVotoTemplate(templateVo, fee, feesTemplate);
			fmRepo.save(feesTemplate);
			studentFeeTemplate = convertTemplateVotoStudentTemplate(templateVo,fee,studentFeeTemplate);
			studentFeeTemplate.setFeesId(feesTemplate.getFeesId());
			studentFmRepo.save(studentFeeTemplate);
			
			feeTemplateList.add(feesTemplate);
		});
		return feeTemplateList;
	}

	public List<FeesTemplate> getFeeManagementDetails(Integer studentId) {
		return (List<FeesTemplate>) fmRepo.findAll();
	}

	String updateStatus = "";

	public void updateFeeManagementDetails(FeeTemplateVo templateVo) {
		FeesTemplate feesTemplate = fmRepo.findByFeesId(templateVo.getFeesId());
		templateVo.getPayTerm().forEach(template -> {
			log.info("template.getToPay():: "+ template.getToPay() +":: fee.getToPay():: "+ feesTemplate.getToPay());
			if (template.getToPay() != feesTemplate.getToPay()) {
				updateStatus = "PayChange";
			}
			if (!(template.getPayFrequency().equalsIgnoreCase(feesTemplate.getPayFrequency())
					&& template.getTerm().equalsIgnoreCase(feesTemplate.getTerm())
					//&& template.getDueDate() == fee.getDueDate() 
					&& template.getTermId().equalsIgnoreCase(feesTemplate.getTermId())
					&& templateVo.getFeesCategory().equalsIgnoreCase(feesTemplate.getFeesCategory())
					&& templateVo.getFeesDescription().equalsIgnoreCase(feesTemplate.getFeesDescription())
					&& templateVo.getGradeIds().equalsIgnoreCase(feesTemplate.getGradeIds())
					)) {
				log.info("template.getPayFrequency():: "+ template.getPayFrequency() +":: fee.getPayFrequency():: "+ feesTemplate.getPayFrequency());
				log.info("template.getTerm():: "+ template.getTerm() +":: fee.getTerm():: "+ feesTemplate.getTerm());
				log.info("template.getDueDate():: "+ template.getDueDate() +":: fee.getDueDate():: "+ feesTemplate.getDueDate());
				log.info("template.getFeesCategory():: "+ templateVo.getFeesCategory() +":: fee.getFeesCategory():: "+ feesTemplate.getFeesCategory());
				log.info("template.getGradeIds():: "+ templateVo.getGradeIds() +":: fee.getGradeIds():: "+ feesTemplate.getGradeIds());
				log.info("template.getToPay():: "+ templateVo.getNotifyBefore() +":: fee.getToPay():: "+ feesTemplate.getNotifyBefore());
				updateStatus = "OtherChange";
			}
			if ("PayChange".equalsIgnoreCase(updateStatus)) {
				// update the fee template and student fee template
				feesTemplate.setToPay(template.getToPay());
				fmRepo.save(feesTemplate);
				List<StudentFeeTemplate> studentFeeTemplates = studentFmRepo.findByFeesId(templateVo.getFeesId());
				studentFeeTemplates.forEach(student -> {
					student.setToPay(template.getToPay());
					studentFmRepo.save(student);
				});
				log.info("entered to if block");
			} else if("OtherChange".equalsIgnoreCase(updateStatus)) {
				log.info("entered to else block");
				StudentFeeTemplate studentFeeTemplate = new StudentFeeTemplate();
				// update the template and delete all records in student template and recreate
				FeesTemplate feesTemplate2 = this.convertTemplateVotoTemplate(templateVo, template, feesTemplate);
				fmRepo.save(feesTemplate2);
				studentFmRepo.deleteByFeesId(templateVo.getFeesId());
				studentFeeTemplate = this.convertTemplateVotoStudentTemplate(templateVo, template, studentFeeTemplate);
				studentFmRepo.save(studentFeeTemplate);
			}
		});
	}

	public void deleteFeeManagementDetails(Integer feesId) {
		FeesTemplate fee = fmRepo.findByFeesId(feesId);
		fee.setActive(false);
		fmRepo.save(fee);

		List<StudentFeeTemplate> studentFees = studentFmRepo.findByFeesId(feesId);
		studentFees.forEach(student -> {
			if (!student.isPaid()) {
				studentFmRepo.deleteById(student.getId());
			}
		});
	}
	
	private FeesTemplate convertTemplateVotoTemplate(FeeTemplateVo templateVo, FeePayDetails fee, FeesTemplate feesTemplate) {
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
		return feesTemplate;
	}
	
	private StudentFeeTemplate convertTemplateVotoStudentTemplate(FeeTemplateVo templateVo, FeePayDetails fee,
			StudentFeeTemplate studentFeeTemplate) {
		
		studentFeeTemplate.setAcademicYear(templateVo.getTenantId());
		studentFeeTemplate.setFeesCategory(templateVo.getFeesCategory());
		studentFeeTemplate.setStudentId(null);
		studentFeeTemplate.setPaid(false);
		studentFeeTemplate.setPaidDate(null);
		studentFeeTemplate.setPaymentMode(null);
		studentFeeTemplate.setReceiptNumber(null);
		studentFeeTemplate.setReferenceNo(null);
		studentFeeTemplate.setNotificationSent(false);
		studentFeeTemplate.setPayTerm(fee.getTerm());
		studentFeeTemplate.setDueDate(fee.getDueDate());
		studentFeeTemplate.setToPay(fee.getToPay());
	
		return studentFeeTemplate;
	}


}
