package com.branko.portfolio.archaeodocs.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "sites_images",
        uniqueConstraints = {
                @UniqueConstraint(name = "uq_site_filename", columnNames = {"site_id_fk", "filename"})
        })
@Getter
@Setter
public class ImageSite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String filename;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "site_id_fk", nullable = false)
    private Site site;
}
