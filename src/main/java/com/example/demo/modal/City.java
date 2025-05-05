package com.example.demo.modal;

import javax.persistence.*;

@Entity
@Table(name = "city")
public class City {
    @Id
    @Column(name = "zipcode", nullable = false, unique = true)
    private Integer zipcode;

    @Column(name = "cityname", unique = true, nullable = false, length = 50)
    private String cityname;

    @Column(name = "state", nullable = false)
    private String state;

    // Constructors
    public City() {}

    public City(Integer zipcode, String cityname, String state) {
        this.zipcode = zipcode;
        this.cityname = cityname;
        this.state = state;
    }

    // Getters and Setters
    public Integer getZipcode() { return zipcode; }
    public void setZipcode(Integer zipcode) { this.zipcode = zipcode; }

    public String getCityname() { return cityname; }
    public void setCityname(String cityname) { this.cityname = cityname; }

    public String getState() { return state; }
    public void setState(String state) { this.state = state; }

    @Override
    public String toString() {
        return "City [zipcode=" + zipcode + ", cityname=" + cityname + ", state=" + state + "]";
    }
}
