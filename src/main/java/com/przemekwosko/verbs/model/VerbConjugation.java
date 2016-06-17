package com.przemekwosko.verbs.model;


import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by kudlaty on 12/06/16.
 */

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public abstract class VerbConjugation implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "conjugation_id")
    private Long id;
    private String singular1;
    private String singular2;
    private String singular3;
    private String plural1;
    private String plural2;
    private String plural3;

    public VerbConjugation() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSingular1() {
        return singular1;
    }

    public void setSingular1(String singular1) {
        this.singular1 = singular1;
    }

    public String getSingular2() {
        return singular2;
    }

    public void setSingular2(String singular2) {
        this.singular2 = singular2;
    }

    public String getSingular3() {
        return singular3;
    }

    public void setSingular3(String singular3) {
        this.singular3 = singular3;
    }

    public String getPlural1() {
        return plural1;
    }

    public void setPlural1(String plural1) {
        this.plural1 = plural1;
    }

    public String getPlural2() {
        return plural2;
    }

    public void setPlural2(String plural2) {
        this.plural2 = plural2;
    }

    public String getPlural3() {
        return plural3;
    }

    public void setPlural3(String plural3) {
        this.plural3 = plural3;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
