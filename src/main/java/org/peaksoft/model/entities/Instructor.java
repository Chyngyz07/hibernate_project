package org.peaksoft.model.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.peaksoft.model.enums.Gender;

import java.time.LocalDate;
import java.util.List;

// TODO: 07.10.2023  в классе Instructor должен быть поля(Long id,String name, String lastName, int age,
//  Gender gender, LocalDate createDate)
//  Relationship: Instructor and Course.На одном курсе может быть несколько инструкторов,
//  но один инструктор не может работать на нескольких курсах
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "instructors")
@FieldDefaults(level = AccessLevel.PRIVATE)

public class Instructor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    String lastName;
    int age;
    @Enumerated(EnumType.STRING)
    Gender gender;
    LocalDate creatDate;

    public Instructor(String name, String lastName, int age, Gender gender, LocalDate creatDate) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
        this.creatDate = creatDate;
    }
    @ManyToOne(cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH})
            @JoinColumn(name = "course_id")
    Course course;
}
