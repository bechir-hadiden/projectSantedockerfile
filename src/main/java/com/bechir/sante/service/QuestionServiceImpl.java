package com.bechir.sante.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.bechir.sante.Question;
import com.bechir.sante.repositroy.QuestionnaireResponseRepository;

@Service
public class QuestionServiceImpl implements QuestionService {
	
	@Autowired
		QuestionnaireResponseRepository repository;

	@Override
	public Question saveQuestion(Question Q) {
		return repository.save(Q);

	}
	}

	 
		
