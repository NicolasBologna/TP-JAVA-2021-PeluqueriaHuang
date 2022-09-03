package dtos;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

import entities.Publication;
import entities.User;

public class PublicationsIndexDto {
	private Publication publication;
	private User createdBy;
	
	public PublicationsIndexDto(Publication publication, User createdBy) {
		this.publication = publication;
		this.createdBy = createdBy;		
	}
	
	
	public User getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}
	public Publication getPublication() {
		return publication;
	}
	public void setPublication(Publication publication) {
		this.publication = publication;
	}
	
	private String base64Image;
	
    public String getBase64Image() {
		return this.base64Image;
    }
    
    public void setBase64Image(InputStream image) throws IOException {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[4096];
		int bytesRead = -1;
		
		while ((bytesRead = image.read(buffer)) != -1) {
			outputStream.write(buffer, 0, bytesRead);
		}
		
		byte[] imageBytes = outputStream.toByteArray();
		
		String base64Image = Base64.getEncoder().encodeToString(imageBytes);
		
		outputStream.close();
		this.base64Image = base64Image;
    }
}
