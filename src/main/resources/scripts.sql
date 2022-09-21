# Fee Template details
CREATE TABLE IF NOT EXISTS fees.fees_template
(
    fees_id integer NOT NULL,
    tenant_id character varying(25) COLLATE pg_catalog."default",
    school_id character varying(25) COLLATE pg_catalog."default",
    grade_ids character varying(200) COLLATE pg_catalog."default",
    fees_category character varying(200) COLLATE pg_catalog."default",
    fees_description character varying(200) COLLATE pg_catalog."default",
    term character varying(200) COLLATE pg_catalog."default",
    term_id character varying(200) COLLATE pg_catalog."default",
    due_date date,
    to_pay double precision,
    notify_before integer,
    pay_frequency character varying(200) COLLATE pg_catalog."default",
    applicable_to character varying(200) COLLATE pg_catalog."default",
    created_by integer,
    updated_by character varying(200) COLLATE pg_catalog."default",
    allow_partial_pay boolean,
    validity date,
    currency character varying(200) COLLATE pg_catalog."default",
    is_active boolean,
    CONSTRAINT fees_template_pkey PRIMARY KEY (fees_id)
)

TABLESPACE pg_default; 

ALTER TABLE IF EXISTS fees.fees_template
    OWNER to postgres;

# Student Fee Payment Details

CREATE TABLE IF NOT EXISTS fees.student_feepayment_details
(
    id integer NOT NULL,
    academic_year character varying COLLATE pg_catalog."default",
    fees_id integer,
    fees_category character varying COLLATE pg_catalog."default",
    to_pay double precision,
    student_id integer,
    pay_term character varying COLLATE pg_catalog."default",
    due_date date,
    paid boolean,
    paid_date date,
    payment_mode character varying COLLATE pg_catalog."default",
    receipt_number character varying COLLATE pg_catalog."default",
    reference_no character varying COLLATE pg_catalog."default",
    notification_sent boolean,
    CONSTRAINT student_feepayment_details_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS fees.student_feepayment_details
    OWNER to postgres;