package fr.karspa.hikerthinker.Entity;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "equipment")
public class Equipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long equipmentId;

    @Column(unique = true, nullable = false)
    private String name;

    private String description;

    @Column(nullable = false)
    private float weight;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToMany(mappedBy = "equipments")
    private List<Hike> hikes;

    public Equipment() {
    }

    public Equipment(String name, String description, float weight, Category category) {
        this.name = name;
        this.description = description;
        this.weight = weight;
        this.category = category;
    }

    public Equipment(Long equipmentId, String name, String description, float weight, Category category) {
        this.equipmentId = equipmentId;
        this.name = name;
        this.description = description;
        this.weight = weight;
        this.category = category;
    }

    public Long getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(Long equipmentId) {
        this.equipmentId = equipmentId;
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

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Equipment{" +
                "equipmentId=" + equipmentId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", weight=" + weight +
                ", category=" + category.toString() +
                '}';
    }
}
