package no.imr.nmdapi.client.loader.dao;

import java.util.Date;
import java.util.List;
import javax.sql.DataSource;
import no.imr.commons.nmdreference.domain.v1.KeyValueElementType;
import no.imr.nmdapi.client.loader.mapper.DateMapper;
import no.imr.nmdapi.client.loader.mapper.KeyValueElementTypeMapper;
import no.imr.nmdapi.client.loader.mapper.UDPListMapper;
import no.imr.nmdapi.client.loader.pojo.UDPList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Data access object for udp lists
 *
 * @author sjurl
 */
public class UDPListDAO {

    private JdbcTemplate jdbcTemplate;
    private static final String GET_UDP_LIST_ELEMENTS = "select shortname, name, description, validfrom, validto, id from nmdreference.u_udplist where id_u_udp = ?";

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<UDPList> getUDPLists() {
        return jdbcTemplate.query("select id, name, description, udp_name FROM nmdreference.u_udp", new UDPListMapper());
    }

    /**
     * Returns a list of key value elements for the udplist matching the
     * provided id
     *
     * @param id
     * @return
     */
    public List<KeyValueElementType> getKeyValueElements(String id) {
        return jdbcTemplate.query(GET_UDP_LIST_ELEMENTS, new KeyValueElementTypeMapper(), id);
    }

    public Date getLastEdited(String id) {
        return jdbcTemplate.query("select max(last_edited) as last_edited from nmdreference.u_udplist where id_u_udp = ?", new DateMapper(), id).get(0);
    }
}
