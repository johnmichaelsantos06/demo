package com.demo.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.demo.model.MemberInformation;

public interface MemberInformationRepository extends PagingAndSortingRepository<MemberInformation, Integer> {
	Iterable<MemberInformation> findByFullNameIgnoreCaseContaining(@Param("fullName") String fullName);
}
