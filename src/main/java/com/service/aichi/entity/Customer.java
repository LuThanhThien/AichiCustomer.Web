package com.service.aichi.entity;

import com.service.aichi.entity.enums.CertificateType;
import com.service.aichi.entity.enums.CustomerStatus;
import com.service.aichi.entity.enums.Gender;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "customer_sequence"
    )
    @SequenceGenerator(
            name = "customer_sequence",
            sequenceName = "customer_sequence",
            allocationSize = 1
    )
    @Column(name = "customer_id")
    private Long id;

    @Column(name = "users")
    @ManyToMany(mappedBy = "customers", cascade = CascadeType.ALL)
    private List<User> users = new ArrayList<>();;

    @Column(name = "customer_first_name")
    private String firstName;

    @Column(name = "customer_last_name")
    private String lastName;

    @Column(name = "customer_birth_date")
    private LocalDate birthDate;

    @Column(name = "customer_gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "customer_phone_number")
    private Long phoneNumber;

    @Column(name = "customer_avatar")
    private String avatar;

    @Column(name = "customer_facebook")
    private String facebook;

    @Column(name = "customer_certification_type")
    @Enumerated(EnumType.STRING)
    private CertificateType certificationType;

    @Column(name = "customer_document_id")
    private String documentId;

    @Column(name = "customer_register_date")
    private LocalDateTime registerDate;

    @Column(name = "customer_theory_test_dates")
    private List<LocalDateTime> theoryTestDates = new ArrayList<>();;

    @Column(name = "customer_practice_test_dates")
    private List<LocalDateTime> practiceTestDates = new ArrayList<>();;

    @Column(name = "customer_note", columnDefinition = "TEXT")
    private String note;

    @Column(name = "customer_images")
    private List<String> images = new ArrayList<>();;

    //=== Meta ===
    @Column(name = "created_date")
    private LocalDateTime createdDate = LocalDateTime.now();

    @Column(name = "updated_date")
    private LocalDateTime updatedDate;

    @Column(name = "customer_status")
    private CustomerStatus status;

    @PrimaryKeyJoinColumn(name = "recycle_bin")
    @OneToOne(mappedBy = "customer")
    private RecycleBin recycleBin;
}
