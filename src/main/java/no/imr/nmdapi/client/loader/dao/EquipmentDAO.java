package no.imr.nmdapi.client.loader.dao;

import java.util.List;
import javax.sql.DataSource;
import no.imr.nmdapi.client.loader.mapper.EquipmentElementTypeMapper;
import no.imr.nmdapi.generic.nmdreference.domain.v1.EquipmentElementType;
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
}
