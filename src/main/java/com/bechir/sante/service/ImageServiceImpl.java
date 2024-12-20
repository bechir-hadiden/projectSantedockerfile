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
import com.bechir.sante.repositroy.ImageRepository;
import com.bechir.sante.repositroy.ImageSanteRepository;

import static java.lang.Integer.valueOf;



import jakarta.transaction.Transactional;

@Service
public class ImageServiceImpl implements ImageService {
	
	
	@Autowired
	ImageRepository imageRepository;

	


	
	@Override
	public Image uplaodImage(MultipartFile file ,String message) throws IOException {
	    if (file.isEmpty()) {
	        throw new IOException("Le fichier est vide !");
	    }
	    String fileName = file.getOriginalFilename();
	    if (fileName == null || fileName.isEmpty()) {
	        throw new IOException("Le nom du fichier est vide ou nul !");
	    }

	    return imageRepository.save(
	    	
	  
	        Image.builder()
	            .name(fileName)
	            .type(file.getContentType())
	            .image(file.getBytes())
	            .message(message).build()
	
	            
	    );
	}


	@Override
	public Image getImageDetails(Long id) throws IOException {
		final Optional<Image> dbImage = imageRepository.findById(id);
		return Image.builder().idImage(dbImage.get().getIdImage()).name(dbImage.get().getName())
				.type(dbImage.get().getType()).image(dbImage.get().getImage()).build();
	}

	@Override
	public ResponseEntity<byte[]> getImage(Long id) throws IOException {
		final Optional<Image> dbImage = imageRepository.findById(id);
		return ResponseEntity.ok().contentType(MediaType.valueOf(dbImage.get().getType()))
				.body(dbImage.get().getImage());
	}

	@Override
	public void deleteImage(Long id) {
		imageRepository.deleteById(id);
	}
	
	@Override 
	public List<Image> getImagesById(Long idIma) {
		 Optional<Image> imageOptional = imageRepository.findById(idIma);

		if (imageOptional.isPresent()) {
	        return List.of(imageOptional.get());
	    } else {
	        // Retournez une liste vide ou lancez une exception selon vos besoins
	        return new ArrayList<>();
	    }
		
		
//		return i.getImage();
	}


	@Override
	public List<Image> getAllImages() {
		// TODO Auto-generated method stub
	    return imageRepository.findAll();
	}


	@Override
	public Image saveImage(Image d) {
		return imageRepository.save(d);
	}

	

	
}
