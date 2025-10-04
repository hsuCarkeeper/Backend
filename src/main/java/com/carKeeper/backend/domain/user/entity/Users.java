package com.carKeeper.backend.domain.user.entity;

import com.carKeeper.backend.domain.user.enums.SocialType;
import com.carKeeper.backend.domain.vehicle.entity.Vehicle;
import com.carKeeper.backend.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Setter
@Table(
        name = "users",
        indexes = {
                @Index(name = "idx_users_email", columnList = "email"),
                @Index(name = "idx_users_hyundai_oauth_id", columnList = "hyundai_oauth_id")
        })
public class Users extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 10, nullable = false)
    private String name;

    @Column(name = "email", length = 50, nullable = false, unique = true)
    private String email;

    @Column(name = "mobile_num", length = 15, nullable = false)
    private String mobileNumber;

    @Column(name = "birth", nullable = false)
    private LocalDate birth;

    @Column(name = "social_type")
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private SocialType socialType = SocialType.NONE;

    @Column(name = "hyundai_oauth_id", length = 50, unique = true)
    private String hyundaiOauthId;

    @Column(name = "access_token")
    private String accessToken;

    @Column(name = "refresh_token")
    private String refreshToken;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Vehicle> vehicles = new ArrayList<>();

    // 양방향 헬퍼
    public void addVehicle(Vehicle v) {
        if (v == null) return;
        if (!vehicles.contains(v)) vehicles.add(v);
        v.assignUser(this);
    }
}
