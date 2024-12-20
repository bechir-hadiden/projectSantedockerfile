package com.bechir.sante.service;

import java.io.IOException;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.bechir.sante.Image;
import com.bechir.sante.ImageSante;


public interface ImageService {
	
	 Image getImageDetails(Long id) throws IOException;
	 ResponseEntity<byte[]> getImage(Long id) throws IOException;
	 void deleteImage(Long id) ;
	 List<Image> getAllImages();
	 Image uplaodImage(MultipartFile file , String message) throws IOException;
	 List<Image> getImagesById(Long idMess);
	 Image saveImage(Image d);


	 
	 //image sante 
	
}
