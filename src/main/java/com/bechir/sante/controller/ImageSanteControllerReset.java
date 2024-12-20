package com.bechir.sante.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.bechir.sante.ImageSante;
import com.bechir.sante.service.ImageSanteService;

@RestController
@RequestMapping("/api/image/sante")
@CrossOrigin(origins = "*")
public class ImageSanteControllerReset {

	 @Autowired
	 ImageSanteService  imageSanteService ;	
	
	@RequestMapping(value = "/uploadSante", method = RequestMethod.POST)
	public ImageSante uploadImage(@RequestParam("image") MultipartFile file, String message) throws IOException {

		System.out.println("Nom de l'image : " + file.getOriginalFilename());
		System.out.println("Message : " + message);
		return imageSanteService.uplaodImageSante(file, message);

	}

	@RequestMapping(value = "/getImagesSante/{idImg}", method = RequestMethod.GET)
	public List<ImageSante> getImagesById(@PathVariable("idImg") Long idImg) throws IOException {
		return imageSanteService.getImagesSanteById(idImg);
	}

	@RequestMapping(value = "/loadfromFSSante/{id}", method = RequestMethod.GET, produces = org.springframework.http.MediaType.IMAGE_JPEG_VALUE)
	public byte[] getImageFS(@PathVariable("id") Long id) throws IOException {

		ImageSante p = imageSanteService.getImageDetailsSante(id);
		return Files.readAllBytes(Paths.get(System.getProperty("user.home") + "/images/" + p.getImagePath()));
	}

	@RequestMapping(value = "/getAllImagesSante", method = RequestMethod.GET)
	public List<ImageSante> getAllImageSantes() {
		return imageSanteService.getAllImagesSante();
	}
	
	@RequestMapping(value = "/uploadFSSante/{id}", method = RequestMethod.POST)
	public void uploadImageFS(@RequestParam("image") MultipartFile file, @PathVariable("id") Long id)
			throws IOException {
		ImageSante d = imageSanteService.getImageDetailsSante(id);
		d.setImagePath(id + ".jpg");
		Files.write(Paths.get(System.getProperty("user.home") + "/images/" + d.getImagePath()), file.getBytes());
		imageSanteService.saveImageSante(d);

	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public void deleteImage(@PathVariable("id") Long id) {
		imageSanteService.deleteImageSante(id);

	}
}
