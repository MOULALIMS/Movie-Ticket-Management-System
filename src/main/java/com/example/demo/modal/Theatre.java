package com.example.demo.modal;

import javax.persistence.*;

@Entity
@Table(name = "theatre")
public class Theatre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "theatreid")
    private Integer theatreId;

    @Column(name = "theatrename", nullable = false)
    private String theatreName;

    @Column(name = "totalscreens", nullable = false)
    private Integer totalScreens;

    @ManyToOne
    @JoinColumn(name = "zipcode", referencedColumnName = "zipcode")
    private City city;

    // Constructors
    public Theatre() {}

    public Theatre(String theatreName, Integer totalScreens, City city) {
        this.theatreName = theatreName;
        this.totalScreens = totalScreens;
        this.city = city;
    }

    public Integer getTheatreId() { return theatreId; }
    public void setTheatreId(Integer theatreId) { this.theatreId = theatreId; }

    public String getTheatreName() { return theatreName; }
    public void setTheatreName(String theatreName) { this.theatreName = theatreName; }

    public Integer getTotalScreens() { return totalScreens; }
    public void setTotalScreens(Integer totalScreens) { this.totalScreens = totalScreens; }

    public City getCity() { return city; }
    public void setCity(City city) { this.city = city; }

    @Override
    public String toString() {
        return "Theatre [theatreId=" + theatreId + ", theatreName=" + theatreName + ", totalScreens=" + totalScreens
                + ", city=" + city + "]";
    }
}
