package com.filipcygan.buidlingsOntology.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
public class Type {

    @Id
    @GeneratedValue
    private Long typeId;
    @Column(unique = true)
    private String typeName;
    @ManyToMany (mappedBy = "typeList")
    private Collection<Building> buildingList = new ArrayList<Building>();

    public Type() {

    }

    public Type(String typeName) {
        this.typeName = typeName;
    }

    @Override
    public String toString() {
        return "Type{" +
               "typeName='" + typeName + '\'' +
               '}';
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Collection<Building> getBuildingList() {
        return buildingList;
    }

    public void setBuildingList(Collection<Building> buildingList) {
        this.buildingList = buildingList;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }


}
