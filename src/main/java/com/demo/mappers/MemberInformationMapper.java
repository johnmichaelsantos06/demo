package com.demo.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.demo.dto.MemberInformationDTO;
import com.demo.model.MemberInformation;

@Mapper
public interface MemberInformationMapper {
	
	MemberInformationMapper INSTANCE = Mappers.getMapper(MemberInformationMapper.class);
	
	MemberInformation toModel(MemberInformationDTO dto);

	MemberInformationDTO toDTO(MemberInformation model);
	
	List<MemberInformationDTO> toDTOList(List<MemberInformation> modelList);
	
}
