package com.eddicorp.ifaspring.map.controller.form;

import com.eddicorp.ifaspring.map.entity.Location;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
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
    public LocationResForm(Location location) {
        this.d_o = location.getD_o();
        this.si = location.getSi();
        this.gu = location.getGu();
        this.dong = location.getDong();
        this.lat = location.getLat();
        this.lng = location.getLng();
    }
}
