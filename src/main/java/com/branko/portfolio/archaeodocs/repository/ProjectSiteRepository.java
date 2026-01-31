package com.branko.portfolio.archaeodocs.repository;

import com.branko.portfolio.archaeodocs.dto.ProjectSiteRowDTO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProjectSiteRepository {

    private final JdbcTemplate jdbc;

    public ProjectSiteRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<ProjectSiteRowDTO> findProjectSitesFlat() {
        String sql = """
            SELECT
              p.id   AS proj_id,
              p.name AS proj_name,
              s.id   AS site_id,
              s.name AS site_name
            FROM projects p
            LEFT JOIN projects_sites_joint psj ON p.id = psj.project_id_fk
            LEFT JOIN sites s ON psj.site_id_fk = s.id
            ORDER BY p.id
        """;

        return jdbc.query(sql, (rs, i) -> new ProjectSiteRowDTO(
                rs.getLong("proj_id"),
                rs.getString("proj_name"),
                (Long) rs.getObject("site_id"),   // čita NULL ispravno
                rs.getString("site_name")
        ));
    }
}
