package com.feeManagement.fm.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.feeManagement.fm.entity.StudentFeeTemplate;

public interface StudentFMRepository extends CrudRepository<StudentFeeTemplate, Integer> {

	List<StudentFeeTemplate> findByFeesId(Integer feesId);

	void deleteByFeesId(Integer feesId);

}
