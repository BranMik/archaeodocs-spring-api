package com.branko.portfolio.archaeodocs.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "sites")
public class Site {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private String comment;

    @Column
    private String description;

    @OneToOne(mappedBy = "site",
            cascade = CascadeType.ALL, orphanRemoval = true)
    private SiteGeoLocation geoLocation;

    @OneToMany(mappedBy = "site", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ImageSite> images = new HashSet<>();

    @OneToMany(mappedBy = "site", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Find> finds = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "sites_periods_joint",
            joinColumns = @JoinColumn(name = "site_id_fk"),
            inverseJoinColumns = @JoinColumn(name = "period_id_fk")
    )
    private Set<Period> periods;
}