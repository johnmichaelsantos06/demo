package com.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.demo.dto.MemberInformationDTO;
import com.demo.mappers.MemberInformationMapper;
import com.demo.model.Gender;
import com.demo.model.MemberInformation;
import com.demo.repository.MemberInformationRepository;
import com.demo.service.MemberInformationService;
import com.demo.service.impl.MemberInformationServiceImpl;

@ExtendWith(MockitoExtension.class)
public class DemoApplicationTests {
	
	@Mock
	private MemberInformationRepository repository;
	
	private MemberInformationService service;
	
	@BeforeEach
	public void setUp() {
		service = new MemberInformationServiceImpl(repository);
	}
	
	@Test
	public void testFindById() {
		MemberInformation obj = new MemberInformation();
		obj.setId(1);
		obj.setFullName("Test Name");
		obj.setEmail("test@gmail.com");
		obj.setGender(Gender.MALE);
		obj.setIpAddress("0.0.0.1");
		
		Optional<MemberInformation> memberInfo = Optional.of(obj);
		
		when(repository.findById(1)).thenReturn(memberInfo);
		
		MemberInformationDTO dto = MemberInformationMapper.INSTANCE.toDTO(obj);
		MemberInformationDTO data = service.findById(1).getData();
		
		assertEquals(dto, data);
	}
	
	@Test
	public void testFindByFullName() {
		List<MemberInformation> objs = new ArrayList<>();
		
		MemberInformation obj1 = new MemberInformation();
		obj1.setId(1);
		obj1.setFullName("Test Name");
		obj1.setEmail("test@gmail.com");
		obj1.setGender(Gender.MALE);
		obj1.setIpAddress("0.0.0.1");
		
		MemberInformation obj2 = new MemberInformation();
		obj2.setId(2);
		obj2.setFullName("Test Name2");
		obj2.setEmail("test2@gmail.com");
		obj2.setGender(Gender.FEMALE);
		obj2.setIpAddress("0.0.0.2");
		
		objs.add(obj1);
		objs.add(obj2);
		
		when(repository.findByFullNameIgnoreCaseContaining("Test")).thenReturn(objs);
		List<MemberInformationDTO> list = service.findByFullName("Test").getList();
		
		assertEquals(objs.size(), list.size());
	}
	
	@Test
	public void testSave() {
		MemberInformation objToBeSaved = new MemberInformation();
		objToBeSaved.setFullName("Test Name");
		objToBeSaved.setEmail("test@gmail.com");
		objToBeSaved.setGender(Gender.MALE);
		objToBeSaved.setIpAddress("0.0.0.1");
		
		MemberInformation objSaved = new MemberInformation();
		objSaved.setId(1);
		objSaved.setFullName("Test Name");
		objSaved.setEmail("test@gmail.com");
		objSaved.setGender(Gender.MALE);
		objSaved.setIpAddress("0.0.0.1");
		
		when(repository.save(objToBeSaved)).thenReturn(objSaved);
		
		MemberInformationDTO dto = MemberInformationMapper.INSTANCE.toDTO(objToBeSaved);
		MemberInformationDTO saved = service.save(dto).getData();
		
		assertEquals(objSaved.getId(), saved.getId());
	}
	
	@Test
	public void testDelete() {
		lenient().doNothing().when(repository).deleteById(1);
		Integer deleted = service.delete(1).getData();
		assertEquals(1, deleted);
	}
}
