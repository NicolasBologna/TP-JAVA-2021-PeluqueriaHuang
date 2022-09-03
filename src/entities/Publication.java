package entities;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

public class Publication {
	
	private int publicationId;
	private int barberId;
	private String title;
	private String text;
	private String date;
	private InputStream image;
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
	
	public int getPublicationId() {
		return publicationId;
	}
	public void setPublicationId(int publicationId) {
		this.publicationId = publicationId;
	}
	public int getBarberId() {
		return barberId;
	}
	public void setBarberId(int barberId) {
		this.barberId = barberId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public InputStream getImage() {
		return this.image;
	}
	public void setImage(InputStream image) {
		this.image = image;
	}
}
