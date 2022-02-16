package entities;

public class Publication {
	
	private int publicationId;
	private int barberId;
	private String title;
	private String text;
	private String date;
	private String image;
	
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
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
}
