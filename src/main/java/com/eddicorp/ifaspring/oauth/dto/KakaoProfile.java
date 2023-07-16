package com.eddicorp.ifaspring.oauth.dto;

import lombok.Data;

@Data
public class KakaoProfile {
    public Long id;
    public String connected_at;
    public Properties properties;

    @Data
    public class Properties {
        public String profile_image;
        public String thumbnail_image;
    }
}
