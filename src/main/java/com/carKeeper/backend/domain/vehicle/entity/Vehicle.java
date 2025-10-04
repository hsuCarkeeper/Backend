package com.carKeeper.backend.domain.vehicle.entity;

import com.carKeeper.backend.domain.maintenance.entity.MaintenanceCycle;
import com.carKeeper.backend.domain.maintenance.entity.MaintenanceHistory;
import com.carKeeper.backend.domain.ocr.entity.OcrHistory;
import com.carKeeper.backend.domain.status.entity.VehicleStatus;
import com.carKeeper.backend.domain.user.entity.Users;
import com.carKeeper.backend.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(
        name = "vehicle",
        indexes = {
                @Index(name = "idx_vehicle_user_id", columnList = "user_id")
        })
public class Vehicle extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "vin", length = 50, nullable = false, unique = true) // VIN 유니크 권장
    private String vin;

    @Column(name = "vehicle_nickname", length = 50, nullable = false)
    private String vehicleNickname;

    @Column(name = "vehicle_type", length = 10, nullable = false)
    private String vehicleType;

    @Column(name = "vehicle_name", length = 25)
    private String vehicleName;

    @Column(name = "vehicle_year", nullable = false)
    private Integer vehicleYear;

    @Column(name = "registration_number", length = 25)
    private String registrationNumber;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    @OneToOne(mappedBy = "vehicle", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private VehicleStatus status;

    @OneToMany(mappedBy = "vehicle", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MaintenanceHistory> maintenanceHistories = new ArrayList<>();

    @OneToMany(mappedBy = "vehicle", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MaintenanceCycle> maintenanceCycles = new ArrayList<>();

    @OneToMany(mappedBy = "vehicle", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OcrHistory> ocrHistories = new ArrayList<>();

    // 헬퍼
    public void addMaintenanceHistory(MaintenanceHistory h) {
        if (h == null) return;
        if (!maintenanceHistories.contains(h)) maintenanceHistories.add(h);
        h.setVehicle(this);
    }
    public void addMaintenanceCycle(MaintenanceCycle c) {
        if (c == null) return;
        if (!maintenanceCycles.contains(c)) maintenanceCycles.add(c);
        c.setVehicle(this);
    }
    public void addOcrHistory(OcrHistory o) {
        if (o == null) return;
        if (!ocrHistories.contains(o)) ocrHistories.add(o);
        o.setVehicle(this);
    }

    public void assignUser(Users user) {
        this.user = user;
    }

}
