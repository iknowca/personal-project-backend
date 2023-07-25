package com.eddicorp.ifaspring.map.controller.form;

import com.eddicorp.ifaspring.map.entity.Location;
import lombok.Getter;

@Getter
public class LocationResForm {
    private Long Id;
    private String dong;
    private String gu;
    private String si;
    private String d_o;

    private Double lat;
    private Double lng;

    public Location toLocation() {
        return Location.builder()
                .dong(dong)
                .gu(gu)
                .si(si)
                .d_o(d_o)
                .lat(lat)
                .lng(lng)
                .build();
    }
}
