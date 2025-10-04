package com.carKeeper.backend.domain.maintenance.entity;

import com.carKeeper.backend.domain.vehicle.entity.Vehicle;
import com.carKeeper.backend.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(
        name = "maintenance_history",
        indexes = {
                @Index(name = "idx_mh_vehicle_id", columnList = "vehicle_id"),
                @Index(name = "idx_mh_serviced_at_date", columnList = "serviced_at_date")
        })
public class MaintenanceHistory extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "service_center", length = 50)
    private String serviceCenter;      // 서비스 센터 이름

    @Column(name = "item_name", length = 300, nullable = false)
    private String itemName; // 정비 받은 아이템 이름

    @Column(name = "cost", nullable = false, precision = 12, scale = 0)
    private BigDecimal cost;  // 비용


    @Column(name = "serviced_at_date", nullable = false)
    private LocalDate servicedAtDate; // 정비 받은 날짜

    @Column(name = "serviced_at_odometer")
    private Long servicedAtOdometer; // 정비 시점 계기판 값으로 권장

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "vehicle_id", nullable = false)
    @Setter
    private Vehicle vehicle;

    @OneToMany(mappedBy = "history", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MaintenancePhoto> photos = new ArrayList<>();
}
