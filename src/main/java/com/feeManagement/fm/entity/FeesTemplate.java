package com.feeManagement.fm.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Entity
@Table(name = "fees_template")
@Data
public class FeesTemplate {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer feesId;
	
	@Column
	@Valid
	@NotNull(message = "Tenant Id required")
	private String tenantId;
	@Column
	private String schoolId;
	@Column
	private String gradeIds;
	@Column
	private String feesCategory;
	@Column
	private String feesDescription;
	@Column
	private String term;
	@Column
	private String termId;
	@Column
	private Date dueDate;
	@Column
	private double toPay;
	@Column
	private Integer notifyBefore;
	@Column
	private String payFrequency;
	@Column
	private String applicableTo;
	@Column
	private Integer createdBy;
	@Column
	private String updatedBy;
	@Column
	private boolean allowPartialPay;
	@Column
	private Date validity;
	@Column
	private String currency;
	@Column
	private boolean isActive;
	
	
	public Integer getFeesId() {
		return feesId;
	}


	public void setFeesId(Integer feesId) {
		this.feesId = feesId;
	}


	public String getTenantId() {
		return tenantId;
	}


	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}


	public String getSchoolId() {
		return schoolId;
	}


	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}


	public String getGradeIds() {
		return gradeIds;
	}


	public void setGradeIds(String gradeIds) {
		this.gradeIds = gradeIds;
	}


	public String getFeesCategory() {
		return feesCategory;
	}


	public void setFeesCategory(String feesCategory) {
		this.feesCategory = feesCategory;
	}


	public String getFeesDescription() {
		return feesDescription;
	}


	public void setFeesDescription(String feesDescription) {
		this.feesDescription = feesDescription;
	}


	public String getTerm() {
		return term;
	}


	public void setTerm(String term) {
		this.term = term;
	}


	public String getTermId() {
		return termId;
	}


	public void setTermId(String termId) {
		this.termId = termId;
	}


	public Date getDueDate() {
		return dueDate;
	}


	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}


	public double getToPay() {
		return toPay;
	}


	public void setToPay(double toPay) {
		this.toPay = toPay;
	}


	public Integer getNotifyBefore() {
		return notifyBefore;
	}


	public void setNotifyBefore(Integer notifyBefore) {
		this.notifyBefore = notifyBefore;
	}


	public String getPayFrequency() {
		return payFrequency;
	}


	public void setPayFrequency(String payFrequency) {
		this.payFrequency = payFrequency;
	}


	public String getApplicableTo() {
		return applicableTo;
	}


	public void setApplicableTo(String applicableTo) {
		this.applicableTo = applicableTo;
	}


	public Integer getCreatedBy() {
		return createdBy;
	}


	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}


	public String getUpdatedBy() {
		return updatedBy;
	}


	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}


	public boolean isAllowPartialPay() {
		return allowPartialPay;
	}


	public void setAllowPartialPay(boolean allowPartialPay) {
		this.allowPartialPay = allowPartialPay;
	}


	public Date getValidity() {
		return validity;
	}


	public void setValidity(Date validity) {
		this.validity = validity;
	}


	public String getCurrency() {
		return currency;
	}


	public void setCurrency(String currency) {
		this.currency = currency;
	}


	public boolean isActive() {
		return isActive;
	}


	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}


	@Override
	public String toString() {
		return "FeesTemplate [feesId=" + feesId + ", tenantId=" + tenantId + ", schoolId=" + schoolId + ", gradeIds="
				+ gradeIds + ", feesCategory=" + feesCategory + ", feesDescription=" + feesDescription + ", term="
				+ term + ", termId=" + termId + ", dueDate=" + dueDate + ", toPay=" + toPay + ", notifyBefore="
				+ notifyBefore + ", payFrequency=" + payFrequency + ", applicableTo=" + applicableTo + ", createdBy="
				+ createdBy + ", updatedBy=" + updatedBy + ", allowPartialPay=" + allowPartialPay + ", validity="
				+ validity + ", currency=" + currency + ", isActive=" + isActive + "]";
	}
	
}
