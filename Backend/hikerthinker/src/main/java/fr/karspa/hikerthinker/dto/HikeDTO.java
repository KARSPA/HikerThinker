package fr.karspa.hikerthinker.dto;

import fr.karspa.hikerthinker.Entity.ApplicationUser;
import fr.karspa.hikerthinker.Entity.Equipment;
import fr.karspa.hikerthinker.Entity.Hike;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class HikeDTO {

    private Long id;
    private Long userId;
    private String title;
    private float distance;
    private float negative;
    private float positive;
    private LocalDate date;
    private float duration;
    private boolean isModel;
    private List<Equipment> equipments;


    public HikeDTO() {
    }

    public HikeDTO(String title, Long userId, float distance, float negative, float positive, LocalDate date, float duration, boolean isModel) {
        this.title = title;
        this.userId = userId;
        this.distance = distance;
        this.negative = negative;
        this.positive = positive;
        this.date = date;
        this.duration = duration;
        this.isModel = isModel;
        this.equipments = new ArrayList<>();
    }

    public HikeDTO(Long id, String title, Long userId, float distance, float negative, float positive, LocalDate date, float duration, boolean isModel) {
        this.id = id;
        this.title = title;
        this.userId = userId;
        this.distance = distance;
        this.negative = negative;
        this.positive = positive;
        this.date = date;
        this.duration = duration;
        this.isModel = isModel;
        this.equipments = new ArrayList<>();
    }

    public HikeDTO(String title, Long userId, float distance, float negative, float positive, LocalDate date, float duration, boolean isModel, List<Equipment> equipments) {
        this.title = title;
        this.userId = userId;
        this.distance = distance;
        this.negative = negative;
        this.positive = positive;
        this.date = date;
        this.duration = duration;
        this.isModel = isModel;
        this.equipments = equipments;
    }

    public HikeDTO(Long id, String title, Long userId, float distance, float negative, float positive, LocalDate date, float duration, boolean isModel, List<Equipment> equipments) {
        this.id = id;
        this.title = title;
        this.userId = userId;
        this.distance = distance;
        this.negative = negative;
        this.positive = positive;
        this.date = date;
        this.duration = duration;
        this.isModel = isModel;
        this.equipments = equipments;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public float getNegative() {
        return negative;
    }

    public void setNegative(float negative) {
        this.negative = negative;
    }

    public float getPositive() {
        return positive;
    }

    public void setPositive(float positive) {
        this.positive = positive;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public float getDuration() {
        return duration;
    }

    public void setDuration(float duration) {
        this.duration = duration;
    }

    public boolean isModel() {
        return isModel;
    }

    public void setModel(boolean model) {
        isModel = model;
    }

    public List<Equipment> getEquipments() {
        return equipments;
    }

    public void setEquipments(List<Equipment> equipments) {
        this.equipments = equipments;
    }

    public Hike toHike(ApplicationUser user){
        return new Hike(user,this.title,this.distance,this.positive,this.negative,this.date,this.isModel,this.duration,this.equipments);
    }

    @Override
    public String toString() {
        return "HikeDTO{" +
                "id=" + id +
                ", userId=" + userId +
                ", title='" + title + '\'' +
                ", distance=" + distance +
                ", negative=" + negative +
                ", positive=" + positive +
                ", date=" + date +
                ", duration=" + duration +
                ", isModel=" + isModel +
                ", equipments=" + equipments +
                '}';
    }
}
