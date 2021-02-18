package com.demo.util.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.util.service.UtilityService;
import com.us.models.util.Utility;



@RestController
@RequestMapping("/v1")
public class UtilityResourceController {

	
	@Autowired
	private UtilityService utilityService;
	
	Logger log=LoggerFactory.getLogger(UtilityResourceController.class);
	
		
	@PostMapping(value="/utilities")
	public ResponseEntity<String> saveUtility(@RequestBody Utility utility ) {
		
		utilityService.save(utility);
		return new ResponseEntity<String>(HttpStatus.CREATED);
		
	}
	@PutMapping(value="/utilities/{utilityId}", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Utility> updateUtility(@RequestBody Utility utility ,@PathVariable ("utilityId") int utilityId) {
		
		Utility s = utilityService.update(utilityId,utility).get();
		HttpHeaders headers=new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		return new ResponseEntity<Utility>(s,headers,HttpStatus.ACCEPTED);
		
	}
	
	@DeleteMapping(value="/utilities/{utilityId}")
	public ResponseEntity<Utility> deleteUtility(@PathVariable ("utilityId") int utilityId) {
		
		utilityService.delete(utilityId);
		
		return new ResponseEntity<Utility>(HttpStatus.NO_CONTENT);
		
	}
	
	@GetMapping(value="/utilities", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Utility>> getAllUtilitys(Authentication auth) {
		
		System.out.println("auth "+auth.getName());
		return new ResponseEntity<List<Utility>>(utilityService.getUtilities(), HttpStatus.FOUND);
		
	}
	
	@GetMapping(value="/utilities/{utilityId}")
	public ResponseEntity<Utility> getUtilityById(@PathVariable ("utilityId") int utilityId) {
		
		log.info("inside getbyid");
		Utility utility=utilityService.getUtilityById(utilityId).get();
			
		if(utility==null) {
			return new ResponseEntity<Utility>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Utility>(utility,HttpStatus.OK);
	}
	
//	@GetMapping(value="/Utilitys/me")
//	public ResponseEntity<Utility> getUtilityById(Authentication authentication) {
//		
//		log.info("inside getbyid");
//		Utility utility=utilityService.getUtilityByUtilityName(authentication.getName());
//			
//		if(utility==null) {
//			return new ResponseEntity<Utility>(HttpStatus.NOT_FOUND);
//		}
//		return new ResponseEntity<Utility>(utility,HttpStatus.OK);
//	}
//	
//	@GetMapping(value="/utilities/search/{utilityName}")
//	public ResponseEntity<Utility> getUtilityByUtilityName(@PathVariable ("utilityName") String utilityName) {
//		
//		log.info("inside getbyUtilityname");
//		Utility utility=utilityService.getUtilityByUtilityName(utilityName);
//		
//		
//		if(utility==null) {
//			return new ResponseEntity<Utility>(HttpStatus.NOT_FOUND);
//		}
//		return new ResponseEntity<Utility>(utility,HttpStatus.OK);
//	}
	
	
	
	
}
