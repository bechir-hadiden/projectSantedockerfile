package com.bechir.sante.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bechir.sante.Image;
import com.bechir.sante.service.ImageService;


@RestController
@RequestMapping("/api/image")
@CrossOrigin(origins = "*")
public class ImageRestController {

	

	
	
	 @Autowired
	 ImageService imageService ;
	 
	
	 
	 

		@RequestMapping(value = "/upload", method = RequestMethod.POST)
		public Image uploadImage(@RequestParam("image") MultipartFile file , String message ) throws IOException {
			
			
			System.out.println("Nom de l'image : " + file.getOriginalFilename());
			System.out.println("Message : " + message);

			return imageService.uplaodImage(file , message);
			
		
		}
		
		
	 @RequestMapping(value = "/getImages/{idImg}" , method = RequestMethod.GET)
	 public List<Image> getImagesById(@PathVariable("idImg") Long idImg) 
			 throws IOException {
		 return imageService.getImagesById(idImg);
	 } 
	 
	 @RequestMapping(value = "/loadfromFS/{id}" ,method = RequestMethod.GET,produces = org.springframework.http.MediaType.IMAGE_JPEG_VALUE)
	 public byte[] getImageFS(@PathVariable("id") Long id) throws IOException {

	 Image p = imageService.getImageDetails(id);
	 return
	Files.readAllBytes(Paths.get(System.getProperty("user.home")+"/images/"+p.getImagePath()));
	 }
	 
	 
	 
	 @RequestMapping(value = "/getAllImages", method = RequestMethod.GET)
	 public List<Image> getAllImages() {
	     return imageService.getAllImages();
	 }

	 
	 
		@RequestMapping(value = "/uploadFS/{id}", method = RequestMethod.POST)
		public void uploadImageFS(@RequestParam("image") MultipartFile file, @PathVariable("id") Long id)throws IOException {
			Image d = imageService.getImageDetails(id);
			d.setImagePath(id + ".jpg");
			Files.write(Paths.get(System.getProperty("user.home") + "/images/" + d.getImagePath()), file.getBytes());
			imageService.saveImage(d);

		}
		
		
		 @RequestMapping(value = "/delete/{id}" , method = RequestMethod.DELETE)
		 public void deleteImage(@PathVariable("id") Long id){
			 imageService.deleteImage(id);
		
		 }
}
