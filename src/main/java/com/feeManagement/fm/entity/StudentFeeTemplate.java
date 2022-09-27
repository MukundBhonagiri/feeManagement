package com.feeManagement.fm.entity;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Entity
@Table(name = "student_feepayment_details")
@Data
public class StudentFeeTemplate {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@Column
	@NotNull
	private String academicYear;
	@Column
    private Integer feesId;
	@Column
    private String feesCategory;
	@Column
    private double toPay;
	@Transient
    private Integer studentId;
	@Column
    private String payTerm;
	@Column
    private String dueDate;
	@Column
    private boolean paid;
	@Column
    private Date paidDate;
	@Column
    private String paymentMode;
	@Column
    private String receiptNumber;
	@Column
    private String referenceNo;
	@Column
    private boolean notificationSent;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAcademicYear() {
		return academicYear;
	}
	public void setAcademicYear(String academicYear) {
		this.academicYear = academicYear;
	}
	public Integer getFeesId() {
		return feesId;
	}
	public void setFeesId(Integer feesId) {
		this.feesId = feesId;
	}
	public String getFeesCategory() {
		return feesCategory;
	}
	public void setFeesCategory(String feesCategory) {
		this.feesCategory = feesCategory;
	}
	public double getToPay() {
		return toPay;
	}
	public void setToPay(double toPay) {
		this.toPay = toPay;
	}
//	public Integer getStudentId() {
//		return studentId;
//	}
//	public void setStudentId(Integer studentId) {
//		this.studentId = studentId;
//	}
	public String getPayTerm() {
		return payTerm;
	}
	public void setPayTerm(String payTerm) {
		this.payTerm = payTerm;
	}
	public String getDueDate() {
		return dueDate;
	}
	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}
	public boolean isPaid() {
		return paid;
	}
	public void setPaid(boolean paid) {
		this.paid = paid;
	}
	public Date getPaidDate() {
		return paidDate;
	}
	public void setPaidDate(Date paidDate) {
		this.paidDate = paidDate;
	}
	public String getPaymentMode() {
		return paymentMode;
	}
	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}
	public String getReceiptNumber() {
		return receiptNumber;
	}
	public void setReceiptNumber(String receiptNumber) {
		this.receiptNumber = receiptNumber;
	}
	public String getReferenceNo() {
		return referenceNo;
	}
	public void setReferenceNo(String referenceNo) {
		this.referenceNo = referenceNo;
	}
	public boolean isNotificationSent() {
		return notificationSent;
	}
	public void setNotificationSent(boolean notificationSent) {
		this.notificationSent = notificationSent;
	}

	
}
