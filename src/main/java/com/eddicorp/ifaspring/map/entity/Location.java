package com.eddicorp.ifaspring.map.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String dong;
    private String gu;
    private String si;
    private String d_o;

    @Builder
    public Location(String dong, String gu, String si, String d_o, Double lat, Double lng) {
        this.dong = dong;
        this.gu = gu;
        this.si = si;
        this.d_o = d_o;
        this.lat = lat;
        this.lng = lng;
    }

    private Double lat;
    private Double lng;
}
