package com.demo.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.model.MemberInformation;
import com.demo.repository.MemberInformationRepository;
import com.demo.response.BaseResponse;
import com.demo.service.MemberInformationService;

@Service
public class MemberInformationServiceImpl implements MemberInformationService {

	@Autowired
	private MemberInformationRepository repository;

	@Override
	public BaseResponse<MemberInformation> findById(Integer id) {
		BaseResponse<MemberInformation> response = new BaseResponse<>();
		
		Optional<MemberInformation> memberInformationOpt = repository.findById(id);
		MemberInformation memberInfo = memberInformationOpt.orElse(new MemberInformation());
		
		response.setSuccess(1);
		response.setData(memberInfo);
		
		return response;
	}

	@Override
	public BaseResponse<MemberInformation> findByFullName(String fullName) {
		BaseResponse<MemberInformation> response = new BaseResponse<>();
		
		List<MemberInformation> list = new ArrayList<>();
		
		Iterable<MemberInformation> memberInfos = Collections.emptyList();
		if (fullName == null || fullName.isEmpty()) {
			memberInfos = repository.findAll();
		} else {
			memberInfos = repository.findByFullNameIgnoreCaseContaining(fullName);
		}
		
		memberInfos.forEach(list::add);
		
		response.setSuccess(1);
		response.setList(list);
		
		return response;
	}

	@Override
	public BaseResponse<MemberInformation> save(MemberInformation memberInfo) {
		BaseResponse<MemberInformation> response = new BaseResponse<>();
		
		MemberInformation memberInfoSaved = repository.save(memberInfo);
		
		response.setSuccess(1);
		response.setData(memberInfoSaved);
		
		return response;
	}

	@Override
	public BaseResponse<Integer> delete(Integer id) {
		BaseResponse<Integer> response = new BaseResponse<>();
		
		Optional<MemberInformation> memberInformationOpt = repository.findById(id);
		if (!memberInformationOpt.isPresent()) {
			response.setErrorMessages(Collections.singletonMap("id", "member not existing"));
			response.setData(id);
			
			return response;
		}
		
		repository.deleteById(id);
		
		response.setSuccess(1);
		response.setData(id);
		
		return response;
	}

}
