package org.example.demo4.model;

import net.bytebuddy.implementation.bind.annotation.Default;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank(message = "vui long nhap ten")
    private String name;
    @NotBlank(message = "Vui long nhap ngay sinh")
    private String birthDay;
    @NotBlank(message = "Vui long nhap que")
    private String homeTown;
    @Min(value = 0, message = "Vui long nhap diem > 0")
    @Max(value = 10, message = "Vui long nhap diem < 10")
    private int score;
    @ManyToOne
    @JoinColumn(name = "class_id")
    private Class className;
    @Column(columnDefinition = "varchar(255) default 'img.png'")
    private String avatar;
    public Student(int id, String name, String birthDay, String homeTown, int score, Class className, String avatar){
        this.id = id;
        this.name = name;
        this.birthDay = birthDay;
        this.homeTown = homeTown;
        this.score = score;
        this.className = className;
        this.avatar = avatar;
    }
    public Student(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public String getHomeTown() {
        return homeTown;
    }

    public void setHomeTown(String homeTown) {
        this.homeTown = homeTown;
    }

    public Class getClassName() {
        return className;
    }

    public void setClassName(Class className) {
        this.className = className;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
