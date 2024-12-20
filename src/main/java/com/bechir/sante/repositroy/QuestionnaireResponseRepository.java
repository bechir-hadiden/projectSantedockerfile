package com.bechir.sante.repositroy;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bechir.sante.Question;

public interface QuestionnaireResponseRepository extends JpaRepository<Question, Long> {
}

