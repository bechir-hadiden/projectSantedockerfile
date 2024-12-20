package com.bechir.sante.repositroy;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bechir.sante.Image;



public interface ImageRepository extends JpaRepository<Image , Long> {
}
