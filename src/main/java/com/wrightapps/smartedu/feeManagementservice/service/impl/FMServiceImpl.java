package com.wrightapps.smartedu.feeManagementservice.service.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.wrightapps.smartedu.feeManagementservice.constants.ApplicationConstants;
import com.wrightapps.smartedu.feeManagementservice.entity.FeesTemplate;
import com.wrightapps.smartedu.feeManagementservice.entity.StudentFeeTemplate;
import com.wrightapps.smartedu.feeManagementservice.model.FeePayDetails;
import com.wrightapps.smartedu.feeManagementservice.repository.FMRepository;
import com.wrightapps.smartedu.feeManagementservice.repository.StudentFMRepository;
import com.wrightapps.smartedu.feeManagementservice.request.dto.FeeTemplateVo;
import com.wrightapps.smartedu.feeManagementservice.response.dto.FMResponse;
import com.wrightapps.smartedu.feeManagementservice.service.FMService;

@Service
public class FMServiceImpl implements FMService {
	
	private static final Logger log = LoggerFactory.getLogger(FMServiceImpl.class);

	private FMRepository fmRepo;
	private StudentFMRepository studentFmRepo;

	@Autowired
	public FMServiceImpl(FMRepository fmRepo, StudentFMRepository studentFmRepo) {
		this.fmRepo = fmRepo;
		this.studentFmRepo = studentFmRepo;
	}

	public ResponseEntity<FMResponse> saveFeeManagementDetails(FeeTemplateVo templateVo) {
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
		FMResponse response = new FMResponse();
		response.setStatus(ApplicationConstants.SUCCESS);
		response.setResults(feeTemplateList);
		return new ResponseEntity<FMResponse>(response,HttpStatus.OK);
	}

	public ResponseEntity<FMResponse> getFeeManagementDetails(Integer studentId) {
		List<FeesTemplate> feeTemplateList = (List<FeesTemplate>) fmRepo.findAll();
		FMResponse response = new FMResponse();
		response.setStatus(ApplicationConstants.SUCCESS);
		response.setResults(feeTemplateList);
		return new ResponseEntity<FMResponse>(response,HttpStatus.OK);
	}

	String updateStatus = "";

	public ResponseEntity<FMResponse> updateFeeManagementDetails(FeeTemplateVo templateVo) {
		FeesTemplate feesTemplate = fmRepo.findByFeesId(templateVo.getFeesId());
		templateVo.getPayTerm().forEach(template -> {
			log.info("template.getToPay():: "+ template.getToPay() +":: fee.getToPay():: "+ feesTemplate.getToPay());
			if (template.getToPay() != feesTemplate.getToPay()) {
				updateStatus = "PayChange";
			}
			if (!(template.getPayFrequency().equalsIgnoreCase(feesTemplate.getPayFrequency())
					&& template.getTerm().equalsIgnoreCase(feesTemplate.getTerm())
					//&& template.getDueDate() == fee.getDueDate() 
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
				FeesTemplate feesTemplate2 = this.convertTemplateVotoTemplate(templateVo, template, feesTemplate);
				fmRepo.save(feesTemplate2);
				studentFmRepo.deleteByFeesId(templateVo.getFeesId());
				studentFeeTemplate = this.convertTemplateVotoStudentTemplate(templateVo, template, studentFeeTemplate);
				studentFmRepo.save(studentFeeTemplate);
			}
		});
		
		FMResponse response = new FMResponse();
		response.setStatus(ApplicationConstants.SUCCESS);
		response.setResult(feesTemplate);
		
		return new ResponseEntity<FMResponse>(response,HttpStatus.OK);
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
		String date =convertLocalDateToDDMMMFormat(fee.getDueDate()); 
		//saving UUIDs in Postgres DB
		//feesTemplate.setTenantId(tenantId);
		//feesTemplate.setSchoolId(schoolId);
		feesTemplate.setFeesCategory(templateVo.getFeesCategory());
		feesTemplate.setFeesDescription(templateVo.getFeesDescription());
		feesTemplate.setApplicableTo(templateVo.getApplicableTo());
		feesTemplate.setGradeIds(templateVo.getGradeIds());
		//feesTemplate.setCreatedBy((UUID)createdBy);
		feesTemplate.setAllowPartialPay(templateVo.isAllowPartialPay());
		feesTemplate.setValidity(convertLocalDateToYYYYFormat(templateVo.getValidity()));
		feesTemplate.setCurrency(templateVo.getCurrency());
		feesTemplate.setNotifyBefore(templateVo.getNotifyBefore());

		feesTemplate.setTerm(fee.getTerm());
		//feesTemplate.setTermId(termId);
		feesTemplate.setDueDate(date);
		feesTemplate.setToPay(fee.getToPay());
		feesTemplate.setPayFrequency(fee.getPayFrequency());
		return feesTemplate;
	}
	
	private StudentFeeTemplate convertTemplateVotoStudentTemplate(FeeTemplateVo templateVo, FeePayDetails fee,
			StudentFeeTemplate studentFeeTemplate) {
		
		String date =convertLocalDateToDDMMMFormat(fee.getDueDate()); 
		studentFeeTemplate.setAcademicYear(templateVo.getTenantId());
		studentFeeTemplate.setFeesCategory(templateVo.getFeesCategory());
		//studentFeeTemplate.setStudentId(null);
		studentFeeTemplate.setPaid(false);
		studentFeeTemplate.setPaidDate(null);
		studentFeeTemplate.setPaymentMode(null);
		studentFeeTemplate.setReceiptNumber(null);
		studentFeeTemplate.setReferenceNo(null);
		studentFeeTemplate.setNotificationSent(false);
		studentFeeTemplate.setPayTerm(fee.getTerm());
		studentFeeTemplate.setDueDate(date);
		studentFeeTemplate.setToPay(fee.getToPay());
	
		return studentFeeTemplate;
	}
	
	public String convertLocalDateToDDMMMFormat(LocalDate date){
		DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .appendPattern("dd MMM")
                .toFormatter(Locale.US);
        System.out.println(formatter.format(date));
		return formatter.format(date);
	}
	
	public String convertLocalDateToYYYYFormat(LocalDate date){
		DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .appendPattern("YYYY")
                .toFormatter(Locale.US);
        System.out.println(formatter.format(date));
		return formatter.format(date);
	}



}
