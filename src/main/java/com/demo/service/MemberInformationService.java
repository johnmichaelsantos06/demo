package com.demo.service;

import com.demo.model.MemberInformation;
import com.demo.response.BaseResponse;

public interface MemberInformationService {
	BaseResponse<MemberInformation> findById(Integer id);
	
	BaseResponse<MemberInformation> findByFullName(String fullName);

	BaseResponse<MemberInformation> save(MemberInformation memberInfo);

	BaseResponse<Integer> delete(Integer id);

}
