package com.bechir.sante.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bechir.sante.Question;
import com.bechir.sante.service.QuestionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@CrossOrigin
@RequiredArgsConstructor
public class QuestionnaireController {


	@Autowired
	
	QuestionService questionService ;
	
	 public QuestionnaireController(Question service) {
	    }

	    @PostMapping
	    public ResponseEntity<Question> saveResponses(@RequestBody Question response) {
	    	Question savedResponse = questionService.saveQuestion(response);
	        return ResponseEntity.ok(savedResponse);
	    }
	
}
