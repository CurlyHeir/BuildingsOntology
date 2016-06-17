package com.przemekwosko.verbs.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Entity class
 */
@Entity
public class Type {

    @Id
    @GeneratedValue
    private Long typeId;
    @Column(unique = true)
    private String typeName;
    @ManyToMany (mappedBy = "typeList")
    private Collection<Building> buildingList = new ArrayList<>();

    public Type() {

    }

    public Type(String typeName) {
        this.typeName = typeName;
    }

    @Override
    public String toString() {
        return typeName;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Type)) return false;

        Type type = (Type) o;

        if (typeId != null ? !typeId.equals(type.typeId) : type.typeId != null) return false;
        return typeName != null ? typeName.equals(type.typeName) : type.typeName == null;

    }

    @Override
    public int hashCode() {
        int result = typeId != null ? typeId.hashCode() : 0;
        result = 31 * result + (typeName != null ? typeName.hashCode() : 0);
        return result;
    }
}
