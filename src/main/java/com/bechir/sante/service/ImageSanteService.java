package com.bechir.sante.service;

import java.io.IOException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.bechir.sante.ImageSante;


public interface ImageSanteService {
	
	 ImageSante getImageDetailsSante(Long id) throws IOException;
	 ResponseEntity<byte[]> getImageSante(Long id) throws IOException;
	 void deleteImageSante(Long id) ;
	 List<ImageSante> getAllImagesSante();
	 ImageSante uplaodImageSante(MultipartFile file , String message) throws IOException;
	 List<ImageSante> getImagesSanteById(Long idMess);
	 ImageSante saveImageSante(ImageSante i);

}
