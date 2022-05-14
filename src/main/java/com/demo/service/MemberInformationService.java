package com.demo.service;

import com.demo.mappers.MemberInformationDTO;
import com.demo.response.BaseResponse;

public interface MemberInformationService {
	BaseResponse<MemberInformationDTO> findById(Integer id);
	
	BaseResponse<MemberInformationDTO> findByFullName(String fullName);

	BaseResponse<MemberInformationDTO> save(MemberInformationDTO memberInfo);

	BaseResponse<Integer> delete(Integer id);

}
