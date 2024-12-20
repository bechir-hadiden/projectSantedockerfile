package com.bechir.sante.repositroy;


import org.springframework.data.jpa.repository.JpaRepository;

import com.bechir.sante.ImageSante;

public interface ImageSanteRepository  extends JpaRepository<ImageSante , Long> {
}
