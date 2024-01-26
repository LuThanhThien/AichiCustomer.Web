package com.service.aichi.dto;

import com.service.aichi.entity.enums.CertificateType;
import com.service.aichi.entity.enums.Gender;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {

    @Column(name = "customer_first_name")
    private String customerFirstName;

    @Column(name = "customer_last_name")
    private String customerLastName;

    @Column(name = "customer_birth_date")
    private LocalDate customerBirthDate;

    @Column(name = "customer_gender")
    @Enumerated(EnumType.STRING)
    private Gender customerGender;

    @Column(name = "customer_phone_number")
    private Long userPhoneNumber;

    @Column(name = "customer_avatar")
    private String customerAvatar;

    @Column(name = "customer_facebook")
    private String customerFacebook;

    @Column(name = "customer_certification_type")
    @Enumerated(EnumType.STRING)
    private CertificateType customerCertificationType;

    @Column(name = "customer_document_id")
    private String customerDocumentId;

    @Column(name = "customer_register_date")
    private LocalDateTime customerRegisterDate;

    @Column(name = "customer_theory_test_dates")
    private List<LocalDateTime> customerTheoryTestDates;

    @Column(name = "customer_practice_test_dates")
    private List<LocalDateTime> customerPracticeTestDates;

    @Column(name = "customer_note", columnDefinition = "TEXT")
    private String customerNote;

    @Column(name = "customer_images")
    private List<String> customerImages;
}
