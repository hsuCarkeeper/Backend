package com.carKeeper.backend.domain.maintenance.entity;

import com.carKeeper.backend.domain.vehicle.entity.Vehicle;
import com.carKeeper.backend.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(
        name = "maintenance_cycle",
        indexes = @Index(name = "idx_mc_vehicle_id", columnList = "vehicle_id")
)
public class MaintenanceCycle extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "item_name", length = 100, nullable = false)
    private String itemName;    // 정비 받은 아이템

    @Column(name = "last_serviced_date", nullable = false)
    private LocalDate lastServicedDate; // 마지막으로 서비스 받은 날짜

    @Column(name = "last_serviced_odometer")
    private Long lastServicedOdometer;  // 정비 시점의 누적 주행거리(계기판 값)

    @Column(name = "cycle_months")
    private Long cycleMonths;   // 개월 수마다 정비 주기

    @Column(name = "cycle_mileage")
    private Long cycleMileage;  // 주행 거리마다 정비 주기

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "vehicle_id", nullable = false)
    @Setter
    private Vehicle vehicle;
}
