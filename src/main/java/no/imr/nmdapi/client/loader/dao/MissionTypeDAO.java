package no.imr.nmdapi.client.loader.dao;

import java.sql.Date;
import java.util.List;
import javax.sql.DataSource;
import no.imr.commons.nmdreference.domain.v1_0.MissionTypeElementType;
import no.imr.nmdapi.client.loader.mapper.DateMapper;
import no.imr.nmdapi.client.loader.mapper.MissionTypeElementTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Data access object for mission types
 *
 * @author sjurl
 */
public class MissionTypeDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /**
     * returns a list of mission types
     *
     * @return
     */
    public List<MissionTypeElementType> getMissionTypes() {
        return jdbcTemplate.query("select id, code, description from nmdreference.missiontype", new MissionTypeElementTypeMapper());
    }

    /**
     * Get last changed for mission type
     *
     * @return
     */
    public Date getLastChanged() {
        return (Date) jdbcTemplate.query("SELECT max(last_edited) as last_edited FROM nmdreference.missiontype", new DateMapper()).get(0);
    }
}
