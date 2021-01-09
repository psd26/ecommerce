package com.example.ecommerce.shop.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerce.shop.model.Phone;
import com.example.ecommerce.shop.repo.PhoneRepository;

@RestController
@RequestMapping("/api/v1")
public class PhoneController {
	
	@Autowired
	private PhoneRepository phoneRepository;
	
	@GetMapping("/phones")
	public List<Phone> getAllPhones()
	{
		return phoneRepository.findAll();
	}
	
	@GetMapping("/phones/{id}")
	public ResponseEntity<Phone> getPhoneById(@PathVariable(value = "id") Long phoneId) throws Exception
	{
		Phone phone = phoneRepository.findById(phoneId).orElseThrow(() -> new Exception("Phone " + phoneId + "not found"));
		return ResponseEntity.ok().body(phone);
	}
	
	@PostMapping("/phones")
	public Phone createPhone(@RequestBody Phone phone)
	{
		return phoneRepository.save(phone);
	}
	
	@PutMapping("/phones/{id}")
	public ResponseEntity<Phone> updatePhone(@PathVariable(value="id") Long phoneId, @RequestBody Phone phoneDetails) throws Exception
	{
		Phone phone = phoneRepository.findById(phoneId).orElseThrow(() -> new Exception("Phone " + phoneId + "not found"));
		phone.setPhoneName(phoneDetails.getPhoneName());
		phone.setOs(phoneDetails.getOs());
		
		final Phone updatedPhone = phoneRepository.save(phone);
		return ResponseEntity.ok(updatedPhone);
	}
	
	@DeleteMapping("/phone/{id}")
	public Map<String, Boolean> deletePhone(@PathVariable(value="id") Long phoneId) throws Exception
	{
		phoneRepository.deleteById(phoneId);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	

}
