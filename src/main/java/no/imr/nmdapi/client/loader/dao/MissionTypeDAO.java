package no.imr.nmdapi.client.loader.dao;

import java.util.List;
import javax.sql.DataSource;
import no.imr.nmdapi.client.loader.mapper.MissionTypeElementTypeMapper;
import no.imr.nmdapi.generic.nmdreference.domain.v1.MissionTypeElementType;
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
}
