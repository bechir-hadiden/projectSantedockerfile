package com.bechir.sante.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bechir.sante.Image;
import com.bechir.sante.ImageSante;
import com.bechir.sante.repositroy.ImageSanteRepository;

@Service
public class ImagesanteSeviceImpl  implements  ImageSanteService{

	@Autowired
	ImageSanteRepository imageSanteRepository;

	@Override
	public ImageSante uplaodImageSante(MultipartFile file, String message) throws IOException {
	    if (file.isEmpty()) {
	        throw new IOException("Le fichier est vide !");
	    }
	    String fileName = file.getOriginalFilename();
	    if (fileName == null || fileName.isEmpty()) {
	        throw new IOException("Le nom du fichier est vide ou nul !");
	    }


	    ImageSante imageSante = ImageSante.builder()
	        .name(fileName)
	        .type(file.getContentType())
	        .image(file.getBytes())
	        .message(message)
	        .build();


	    return imageSanteRepository.save(imageSante);
	}





	@Override
	public ResponseEntity<byte[]> getImageSante(Long id) throws IOException {
		final Optional<ImageSante> dbImage = imageSanteRepository.findById(id);
		return ResponseEntity.ok().contentType(MediaType.valueOf(dbImage.get().getType()))
				.body(dbImage.get().getImage());
	}

	@Override
	public void deleteImageSante(Long id) {
		imageSanteRepository.deleteById(id);
	}
	
	@Override 
	public List<ImageSante> getImagesSanteById(Long idIma) {
		 Optional<ImageSante> imageOptional = imageSanteRepository.findById(idIma);

		if (imageOptional.isPresent()) {
	        return List.of(imageOptional.get());
	    } else {

	        return new ArrayList<>();
	    }
		
//		return i.getImage();
	}
	@Override
	public List<ImageSante> getAllImagesSante() {
		// TODO Auto-generated method stub
	    return imageSanteRepository.findAll();
	}
	
	
	@Override
	public ImageSante getImageDetailsSante(Long id) throws IOException {
		final Optional<ImageSante> dbImage = imageSanteRepository.findById(id);
		return ImageSante.builder().idImage(dbImage.get().getIdImage()).name(dbImage.get().getName())
				.type(dbImage.get().getType()).image(dbImage.get().getImage()).build();
	}





	@Override
	public ImageSante saveImageSante(ImageSante i) {
		// TODO Auto-generated method stub
		return imageSanteRepository.save(i);
	}

//
//	@Override
//	public Image saveImage(Image d) {
//		return imageRepository.save(d);
//	}


}
