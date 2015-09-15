package no.imr.nmdapi.client.loader.dao;

import java.sql.Date;
import java.util.List;
import javax.sql.DataSource;
import no.imr.commons.nmdreference.domain.v1.EquipmentElementType;
import no.imr.nmdapi.client.loader.mapper.DateMapper;
import no.imr.nmdapi.client.loader.mapper.EquipmentElementTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Data access object for equipment
 *
 * @author sjurl
 */
public class EquipmentDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /**
     * Returns a list of equpments
     *
     * @return
     */
    public List<EquipmentElementType> getEquipments() {
        return jdbcTemplate.query("select id, code, name, area, description from nmdreference.equipment", new EquipmentElementTypeMapper());
    }

    /**
     * Get last changed for equipment
     *
     * @return
     */
    public Date getLastChanged() {
        return (Date) jdbcTemplate.query("SELECT max(last_edited) as last_edited FROM nmdreference.equipment", new DateMapper()).get(0);
    }
}
