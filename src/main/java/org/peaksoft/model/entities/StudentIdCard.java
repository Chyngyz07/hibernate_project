package org.peaksoft.model.entities;

// TODO: 07.10.2023 в классе StudentIdCard должен быть поля(Long id, String identityNumber,
//  LocalDate createDate)

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "Student_id_cards")

public class StudentIdCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String identityNumber;
    LocalDate creatDate;

    public StudentIdCard(String identityNumber, LocalDate creatDate) {
        this.identityNumber = identityNumber;
        this.creatDate = creatDate;
    }

    @OneToOne(cascade = CascadeType.ALL,mappedBy = "studentIdCard")
    Student student;
}
