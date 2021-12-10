package entities;

public class Local {
	
	private int localId;
	private String name;
	private String address;
	private String coordenates;
	
	public int getLocalId() {
		return localId;
	}
	public void setLocalId(int localId) {
		this.localId = localId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCoordenates() {
		return coordenates;
	}
	public void setCoordenates(String coordenates) {
		this.coordenates = coordenates;
	}
}
