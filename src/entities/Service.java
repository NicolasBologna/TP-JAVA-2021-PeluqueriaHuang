package entities;

import java.sql.Time;

public class Service {
  private int serviceId;
  private String name;
  private String description;
  private float price;
  private Time duration;
  private Boolean isEnable;

  public int getServiceId() {
    return serviceId;
  }
  public void setServiceId(int serviceId) {
    this.serviceId = serviceId;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getDescription() {
    return description;
  }
  public void setDescription(String description) {
    this.description = description;
  }
  public float getPrice() {
    return price;
  }
  public void setPrice(float price) {
    this.price = price;
  }
  public Time getDuration() {
    return duration;
  }
  public void setDuration(Time duration) {
    this.duration = duration;
  }
  public Boolean getIsEnable() {
	    return this.isEnable;}}