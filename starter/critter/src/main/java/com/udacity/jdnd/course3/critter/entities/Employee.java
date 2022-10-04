package com.udacity.jdnd.course3.critter.entities;

import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import org.hibernate.annotations.Nationalized;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.DayOfWeek;
import java.util.Set;

@Entity
public class Employee {
    @Id
    @GeneratedValue
    private long id;
    @Nationalized // should use @Nationalized instead of @Type=nstring to support international language characters
    private String name;
    @ElementCollection
    private Set<EmployeeSkill> skills;
    @ElementCollection
    private Set<DayOfWeek> daysAvailable;

    public Employee() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public Set<EmployeeSkill> getSkills() {
//        return skills;
//    }
//
//    public void setSkills(Set<EmployeeSkill> skills) {
//        this.skills = skills;
//    }
//
//    public Set<DayOfWeek> getDaysAvailable() {
//        return daysAvailable;
//    }
//
//    public void setDaysAvailable(Set<DayOfWeek> daysAvailable) {
//        this.daysAvailable = daysAvailable;
//    }
}
