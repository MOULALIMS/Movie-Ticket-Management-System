package com.example.demo.modal;

import javax.persistence.*;

@Entity
@Table(name = "screen")
public class Screen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "screenid")
    private Integer screenId;

    @Column(name = "screenname", nullable = false)
    private String screenName;

    @Column(name = "totalnoofseats", nullable = false)
    private Integer totalNoOfSeats;

    @ManyToOne
    @JoinColumn(name = "theatreid", referencedColumnName = "theatreid")
    private Theatre theatre;

    // Constructors
    public Screen() {}

    public Screen(String screenName, Integer totalNoOfSeats, Theatre theatre) {
        this.screenName = screenName;
        this.totalNoOfSeats = totalNoOfSeats;
        this.theatre = theatre;
    }

    // Getters and Setters
    public Integer getScreenId() { return screenId; }
    public void setScreenId(Integer screenId) { this.screenId = screenId; }

    public String getScreenName() { return screenName; }
    public void setScreenName(String screenName) { this.screenName = screenName; }

    public Integer getTotalNoOfSeats() { return totalNoOfSeats; }
    public void setTotalNoOfSeats(Integer totalNoOfSeats) { this.totalNoOfSeats = totalNoOfSeats; }

    public Theatre getTheatre() { return theatre; }
    public void setTheatre(Theatre theatre) { this.theatre = theatre; }

    @Override
    public String toString() {
        return "Screen [screenId=" + screenId + ", screenName=" + screenName + ", totalNoOfSeats=" + totalNoOfSeats
                + ", theatre=" + theatre + "]";
    }
}
