package fr.karspa.hikerthinker.Entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "hikes")
public class Hike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hike_id")
    private Long hikeId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private ApplicationUser user;


    @Column(unique = true, nullable = false)
    private String hikeTitle;

    private float distance;

    private float vertical;

    private LocalDate hikeDate;

    @Column(nullable = false)
    private boolean isModel;

    public Hike() {
    }

    public Hike(ApplicationUser user, String hikeTitle, float distance, float vertical, LocalDate hikeDate, boolean isModel) {
        this.user = user;
        this.hikeTitle = hikeTitle;
        this.distance = distance;
        this.vertical = vertical;
        this.hikeDate = hikeDate;
        this.isModel = isModel;
    }

    public Hike(Long hikeId, ApplicationUser user, String hikeTitle, float distance, float vertical, LocalDate hikeDate, boolean isModel) {
        this.hikeId = hikeId;
        this.user = user;
        this.hikeTitle = hikeTitle;
        this.distance = distance;
        this.vertical = vertical;
        this.hikeDate = hikeDate;
        this.isModel = isModel;
    }

    public Long getHikeId() {
        return hikeId;
    }

    public void setHikeId(Long hikeId) {
        this.hikeId = hikeId;
    }

    public String getHikeTitle() {
        return hikeTitle;
    }

    public void setHikeTitle(String hikeTitle) {
        this.hikeTitle = hikeTitle;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public float getVertical() {
        return vertical;
    }

    public void setVertical(float vertical) {
        this.vertical = vertical;
    }

    public LocalDate getHikeDate() {
        return hikeDate;
    }

    public void setHikeDate(LocalDate hikeDate) {
        this.hikeDate = hikeDate;
    }

    public boolean isModel() {
        return isModel;
    }

    public void setModel(boolean model) {
        isModel = model;
    }
}
