package com.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.dto.MemberInformationDTO;
import com.demo.response.BaseResponse;
import com.demo.service.MemberInformationService;

@RestController
@RequestMapping("/member")
public class MemberInformationController {
	
	private MemberInformationService service;
	
	@Autowired
	public MemberInformationController(MemberInformationService service) {
		this.service = service;
	}
	
	@GetMapping("/{id}")
	public BaseResponse<MemberInformationDTO> findById(@PathVariable("id") Integer id) {
		return service.findById(id);
	}
	
	@GetMapping("list")
	public BaseResponse<MemberInformationDTO> findByFullName(@RequestParam(name = "fullName", required = false) String fullName) {
		return service.findByFullName(fullName);
	}
	
	@PostMapping
	public BaseResponse<MemberInformationDTO> save(@RequestBody @Validated MemberInformationDTO memberInfo) {
		return service.save(memberInfo);
	}
	
	@DeleteMapping("/{id}")
	public BaseResponse<Integer> save(@PathVariable("id") Integer id) {
		return service.delete(id);
	}
	
}
