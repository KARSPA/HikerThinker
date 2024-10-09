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

    private float distanceInKm;

    private float positiveVerticalInMeters;
    private float negativeVerticalInMeters;

    private LocalDate date;

    private float durationInDays;

    @Column(nullable = false)
    private boolean isModel;

    public Hike() {
    }

    public Hike(ApplicationUser user, String hikeTitle, float distanceInKm, float positiveVerticalInMeters,float negativeVerticalInMeters, LocalDate date, boolean isModel, float durationInDays) {
        this.user = user;
        this.hikeTitle = hikeTitle;
        this.distanceInKm = distanceInKm;
        this.positiveVerticalInMeters = positiveVerticalInMeters;
        this.negativeVerticalInMeters = negativeVerticalInMeters;
        this.date = date;
        this.durationInDays = durationInDays;
        this.isModel = isModel;
    }

    public Hike(Long hikeId, ApplicationUser user, String hikeTitle, float distanceInKm, float positiveVerticalInMeters,float negativeVerticalInMeters, LocalDate date, boolean isModel, float durationInDays) {
        this.hikeId = hikeId;
        this.user = user;
        this.hikeTitle = hikeTitle;
        this.distanceInKm = distanceInKm;
        this.positiveVerticalInMeters = positiveVerticalInMeters;
        this.negativeVerticalInMeters = negativeVerticalInMeters;
        this.date = date;
        this.durationInDays = durationInDays;
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

    public float getDistanceInKm() {
        return distanceInKm;
    }

    public void setDistanceInKm(float distance) {
        this.distanceInKm = distance;
    }

    public float getPositiveVerticalInMeters() {
        return positiveVerticalInMeters;
    }

    public void setPositiveVerticalInMeters(float vertical) {
        this.positiveVerticalInMeters = vertical;
    }

    public ApplicationUser getUser() {
        return user;
    }

    public void setUser(ApplicationUser user) {
        this.user = user;
    }

    public float getNegativeVerticalInMeters() {
        return negativeVerticalInMeters;
    }

    public void setNegativeVerticalInMeters(float negativeVerticalInMeters) {
        this.negativeVerticalInMeters = negativeVerticalInMeters;
    }

    public float getDurationInDays() {
        return durationInDays;
    }

    public void setDurationInDays(float durationInDays) {
        this.durationInDays = durationInDays;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate hikeDate) {
        this.date = hikeDate;
    }

    public boolean isModel() {
        return isModel;
    }

    public void setModel(boolean model) {
        isModel = model;
    }
}
