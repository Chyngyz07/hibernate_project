package org.peaksoft.model.entities;

// TODO: 07.10.2023 в классе Course должен быть поля(Long id, String courseName,
//  String durationMonth, LocalDate createDate)

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "courses")
@ToString
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String courseName;
    String durationMonth;
    LocalDate createDate;

    public Course(String courseName, String durationMonth, LocalDate createDate) {
        this.courseName = courseName;
        this.durationMonth = durationMonth;
        this.createDate = createDate;
    }
    @OneToMany(cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.PERSIST},
            mappedBy = "course")
    List<Instructor> instructors;

    @ManyToMany(cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH},
            mappedBy = "courses")
    List<Student> students;
}
