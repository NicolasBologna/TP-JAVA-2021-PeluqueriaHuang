package dtos;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.LinkedList;

import entities.Comment;
import entities.Publication;
import entities.User;

public class PublicationDetailsDto {
	public PublicationDetailsDto(Publication publication, LinkedList<Comment> comments, User createdBy, boolean hasComments) {
		super();
		this.publication = publication;
		this.comments = comments;
		this.createdBy = createdBy;
		this.hasComments = hasComments;
	}
	private Publication publication;
	private LinkedList<Comment> comments;
	private User createdBy;
	private String base64Image;
	private boolean hasComments;
	
	public Publication getPublication() {
		return publication;
	}
	public void setPublication(Publication publication) {
		this.publication = publication;
	}
	public LinkedList<Comment> getComments() {
		return comments;
	}
	public void setComments(LinkedList<Comment> comments) {
		this.comments = comments;
	}

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

	public boolean hasComments() {
		return hasComments;
	}

	public void setHasComments(boolean hasComments) {
		this.hasComments = hasComments;
	}
	
	public User getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}
}
