package com.carKeeper.backend.domain.ocr.entity;

import com.carKeeper.backend.domain.vehicle.entity.Vehicle;
import com.carKeeper.backend.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(
        name = "ocr_history",
        indexes = @Index(name = "idx_ocr_vehicle_id", columnList = "vehicle_id")
)
public class OcrHistory extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "image_url", length = 255, nullable = false)
    private String imageUrl;

    @Column(name = "recognized_mileage")
    private Long recognizedMileage;

    @Column(name = "is_reflected")  // 인식된 주행 거리를 실제 차량 정보(VehicleStatus)에 반영했는지 여부
    @Builder.Default
    private Boolean isReflected = false;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "vehicle_id", nullable = false)
    @Setter
    private Vehicle vehicle;

// recognizedMileage 가 확인되면 VehicleStatus.cumulativeMileage 갱신 트리거
}
