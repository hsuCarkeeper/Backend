package com.carKeeper.backend.domain.status.entity;

import com.carKeeper.backend.domain.vehicle.entity.Vehicle;
import com.carKeeper.backend.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(
        name = "vehicle_status",
        indexes = {
                @Index(name = "idx_status_vehicle_id", columnList = "vehicle_id")
        })
public class VehicleStatus extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cumulative_mileage", nullable = false)
    private Long cumulativeMileage;

    @Column(name = "remaining_distance", nullable = false)
    private Long remainingDistance;

    @Column(name = "average_fuel", precision = 6, scale = 3) // DECIMAL(6,3) 대응
    private BigDecimal averageFuel;

    @Column(name = "fuel_warning")
    @Builder.Default
    private Boolean fuelWarning = false;

    @Column(name = "tire_pressure_warning")
    @Builder.Default
    private Boolean tirePressureWarning = false;

    @Column(name = "lamp_wire_warning")
    @Builder.Default
    private Boolean lampWireWarning = false;

    @Column(name = "smart_key_battery_warning")
    @Builder.Default
    private Boolean smartKeyBatteryWarning = false;

    @Column(name = "washer_fluid_warning")
    @Builder.Default
    private Boolean washerFluidWarning = false;

    @Column(name = "brake_oil_warning")
    @Builder.Default
    private Boolean brakeOilWarning = false;

    @Column(name = "engine_oil_warning")
    @Builder.Default
    private Boolean engineOilWarning = false;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "vehicle_id", unique = true, nullable = false)
    private Vehicle vehicle;


}
