package com.bechir.sante;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImageSante {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idImage;
	private String name;
	private String type;
	private String message; 
	private String imagePath;


	@Column(name = "IMAGE", length = 4048576)
	@Lob
	private byte[] image;
}
	

