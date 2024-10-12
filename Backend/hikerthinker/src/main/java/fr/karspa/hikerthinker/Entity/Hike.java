package fr.karspa.hikerthinker.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fr.karspa.hikerthinker.dto.HikeDTO;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "hikes")
public class Hike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hike_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private ApplicationUser user;


    @Column(unique = true, nullable = false)
    private String title;

    private float distanceInKm;

    private float positiveVerticalInMeters;
    private float negativeVerticalInMeters;

    private LocalDate date;

    private float durationInDays;

    @Column(nullable = false)
    private boolean isModel;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "hike_equipment_junction",
            joinColumns = @JoinColumn(name = "hike_id"),
            inverseJoinColumns = @JoinColumn(name = "equipment_id")
    )
    private List<Equipment> equipments;

    public Hike() {
    }

    public Hike(ApplicationUser user, String title, float distanceInKm, float positiveVerticalInMeters, float negativeVerticalInMeters, LocalDate date, boolean isModel, float durationInDays, List<Equipment> equipments) {
        this.user = user;
        this.title = title;
        this.distanceInKm = distanceInKm;
        this.positiveVerticalInMeters = positiveVerticalInMeters;
        this.negativeVerticalInMeters = negativeVerticalInMeters;
        this.date = date;
        this.durationInDays = durationInDays;
        this.isModel = isModel;
        this.equipments = equipments;
    }

    public Hike(Long id, ApplicationUser user, String title, float distanceInKm, float positiveVerticalInMeters, float negativeVerticalInMeters, LocalDate date, boolean isModel, float durationInDays, List<Equipment> equipments) {
        this.id = id;
        this.user = user;
        this.title = title;
        this.distanceInKm = distanceInKm;
        this.positiveVerticalInMeters = positiveVerticalInMeters;
        this.negativeVerticalInMeters = negativeVerticalInMeters;
        this.date = date;
        this.durationInDays = durationInDays;
        this.isModel = isModel;
        this.equipments = equipments;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long hikeId) {
        this.id = hikeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String hikeTitle) {
        this.title = hikeTitle;
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


    public HikeDTO toDTO(boolean withEquipment) {

        if(withEquipment) {
            if(this.id != null){
                return new HikeDTO(this.id,this.title,this.user.getUserId(),this.distanceInKm, this.negativeVerticalInMeters, this.positiveVerticalInMeters,this.date,this.durationInDays,this.isModel, this.equipments);
            }else{
                return new HikeDTO(this.title,this.user.getUserId(),this.distanceInKm, this.negativeVerticalInMeters, this.positiveVerticalInMeters,this.date,this.durationInDays,this.isModel, this.equipments);
            }
        }else{
            if(this.id != null){
                return new HikeDTO(this.id,this.title,this.user.getUserId(),this.distanceInKm, this.negativeVerticalInMeters, this.positiveVerticalInMeters,this.date,this.durationInDays,this.isModel);
            }else{
                return new HikeDTO(this.title,this.user.getUserId(),this.distanceInKm, this.negativeVerticalInMeters, this.positiveVerticalInMeters,this.date,this.durationInDays,this.isModel);
            }
        }

    }

    @Override
    public String toString() {
        return "Hike{" +
                "id=" + id +
                ", user=" + user +
                ", title='" + title + '\'' +
                ", distanceInKm=" + distanceInKm +
                ", positiveVerticalInMeters=" + positiveVerticalInMeters +
                ", negativeVerticalInMeters=" + negativeVerticalInMeters +
                ", date=" + date +
                ", durationInDays=" + durationInDays +
                ", isModel=" + isModel +
                ", equipments=" + equipments.toString() +
                '}';
    }
}
