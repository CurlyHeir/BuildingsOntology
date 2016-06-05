package com.filipcygan.buidlingsOntology.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Entity class
 */
@Entity
public class Building {

    @Id
    @GeneratedValue
    private Long buildingId;
    @Column(unique = true)
    private String buildingName;
    @ManyToMany(cascade = CascadeType.ALL)
    @Fetch(FetchMode.JOIN)
    private Set<Type> typeList = new HashSet<>();

    public Building() {
    }
    public Set<Type> getTypeList() {
        return typeList;
    }

    public void setTypeList(Set<Type> typeList) {
        this.typeList = typeList;
    }

    public void addType(Type type) {
        this.typeList.add(type);
    }

    public Long getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Long buildingId) {
        this.buildingId = buildingId;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    @Override
    public String toString() {
        return "Building{" +
               "buildingId=" + buildingId +
               ", buildingName='" + buildingName + '\'' +
               ", typeList=" + typeList +
               '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Building))
            return false;

        Building building = (Building) o;

        return buildingName != null ? buildingName.equals(building.buildingName) : building.buildingName == null;

    }

    @Override
    public int hashCode() {
        return buildingName != null ? buildingName.hashCode() : 0;
    }
}
