package no.imr.nmdapi.client.loader.dao;

import java.util.List;
import javax.sql.DataSource;
import no.imr.nmdapi.client.loader.mapper.SeaAreasElementTypeMapper;
import no.imr.nmdapi.generic.nmdreference.domain.v1.SeaAreasElementType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Data access object for sea areas
 *
 * @author sjurl
 */
public class SeaAreasDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /**
     * Returns a list of sea areas
     *
     * @return
     */
    public List<SeaAreasElementType> getSeaAreas() {
        return jdbcTemplate.query("select id, name, description from nmdreference.seaarea", new SeaAreasElementTypeMapper());
    }
}
