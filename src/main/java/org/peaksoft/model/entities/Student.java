package org.peaksoft.model.entities;

// TODO: 07.10.2023 в классе Student должен быть поля (Long id, String name, int age,
//  StudyFormat studyFormat(enum),Gender gender, LocalDate createDate)
//  Relationships:
//  1. Student and StudentIdCard. У каждого Студента должна быть только одна карта студента
//  2. Student and Course. Студент может обучаться на нескольких курсах, и на одном курсе может быть несколько студентов

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.peaksoft.model.enums.Gender;
import org.peaksoft.model.enums.StudyFormat;

import java.time.LocalDate;
import java.util.List;

@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "students")

public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    int age;
    @Enumerated(value = EnumType.STRING)
    StudyFormat studyFormat;
    @Enumerated(value = EnumType.STRING)
    Gender gender;
    LocalDate creatDate;

    public Student(String name, int age, StudyFormat studyFormat, Gender gender, LocalDate creatDate) {
        this.name = name;
        this.age = age;
        this.studyFormat = studyFormat;
        this.gender = gender;
        this.creatDate = creatDate;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id_cards")
    StudentIdCard studentIdCard;

    @ManyToMany(cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH})
    @JoinTable(name = "courses_and_students",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "students_id"))
    List<Course> courses;
}
