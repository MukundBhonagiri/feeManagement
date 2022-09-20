package com.feeManagement.fm.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "student_feepayment_details")
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
	@Column
    private Integer studentId;
	@Column
    private String payTerm;
	@Column
    private Date dueDate;
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

}
