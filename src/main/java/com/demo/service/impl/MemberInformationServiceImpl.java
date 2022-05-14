package com.demo.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dto.MemberInformationMapper;
import com.demo.mappers.MemberInformationDTO;
import com.demo.model.MemberInformation;
import com.demo.repository.MemberInformationRepository;
import com.demo.response.BaseResponse;
import com.demo.service.MemberInformationService;

@Service
public class MemberInformationServiceImpl implements MemberInformationService {

	private MemberInformationRepository repository;
	
	@Autowired
	public MemberInformationServiceImpl(MemberInformationRepository repository) {
		this.repository = repository;
	}

	@Override
	public BaseResponse<MemberInformationDTO> findById(Integer id) {
		BaseResponse<MemberInformationDTO> response = new BaseResponse<>();
		
		Optional<MemberInformation> memberInformationOpt = repository.findById(id);
		MemberInformation memberInfo = memberInformationOpt.orElse(new MemberInformation());
		
		MemberInformationDTO dto = MemberInformationMapper.INSTANCE.toDTO(memberInfo);
		
		response.setSuccess(1);
		response.setData(dto);
		
		return response;
	}

	@Override
	public BaseResponse<MemberInformationDTO> findByFullName(String fullName) {
		BaseResponse<MemberInformationDTO> response = new BaseResponse<>();
		
		List<MemberInformation> list = new ArrayList<>();
		
		Iterable<MemberInformation> memberInfos = Collections.emptyList();
		if (fullName == null || fullName.isEmpty()) {
			memberInfos = repository.findAll();
		} else {
			memberInfos = repository.findByFullNameIgnoreCaseContaining(fullName);
		}
		
		memberInfos.forEach(list::add);
		
		List<MemberInformationDTO> listDTO = new ArrayList<>();
		if (!list.isEmpty() || list != null) {
			listDTO = MemberInformationMapper.INSTANCE.toDTOList(list);
		}
		
		response.setSuccess(1);
		response.setList(listDTO);
		
		return response;
	}

	@Override
	public BaseResponse<MemberInformationDTO> save(MemberInformationDTO memberInfoDTO) {
		MemberInformation memberInfo = MemberInformationMapper.INSTANCE.toModel(memberInfoDTO);
		
		BaseResponse<MemberInformationDTO> response = new BaseResponse<>();
		
		MemberInformation memberInfoSaved = repository.save(memberInfo);
		
		MemberInformationDTO memberInfoSavedDTO = MemberInformationMapper.INSTANCE.toDTO(memberInfoSaved);
		
		response.setSuccess(1);
		response.setData(memberInfoSavedDTO);
		
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
