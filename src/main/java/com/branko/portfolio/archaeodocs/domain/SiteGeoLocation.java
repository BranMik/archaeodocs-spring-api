package com.branko.portfolio.archaeodocs.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "site_geo_locations")
public class SiteGeoLocation {

    @Id
    @Column(name = "site_id_fkpk")
    private Long siteId;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "site_id_fkpk", insertable = false, updatable = false)
    private Site site;

    @Column(nullable = false)
    private float lat;

    @Column(nullable = false)
    private float lng;

}