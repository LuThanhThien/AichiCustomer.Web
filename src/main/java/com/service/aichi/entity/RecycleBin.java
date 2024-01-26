package com.service.aichi.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "recycle_bin")
public class RecycleBin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bin_id")
    private Long id;

    @PrimaryKeyJoinColumn(name = "customer")
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Customer customer;

    @Column(name = "deleted_date")
    private LocalDateTime deleteDate = LocalDateTime.now();

}
