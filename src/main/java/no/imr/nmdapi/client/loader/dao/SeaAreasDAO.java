package no.imr.nmdapi.client.loader.dao;

import java.sql.Date;
import java.util.List;
import javax.sql.DataSource;
import no.imr.commons.nmdreference.domain.v1_0.SeaAreasElementType;
import no.imr.nmdapi.client.loader.mapper.DateMapper;
import no.imr.nmdapi.client.loader.mapper.SeaAreasElementTypeMapper;
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

    /**
     * Get the last edited date for sea areas
     *
     * @return
     */
    public Date getLastChanged() {
        return (Date) jdbcTemplate.query("SELECT max(last_edited) as last_edited FROM nmdreference.seaarea", new DateMapper()).get(0);
    }
}
