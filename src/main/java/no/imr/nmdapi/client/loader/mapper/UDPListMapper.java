package no.imr.nmdapi.client.loader.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import no.imr.nmdapi.client.loader.pojo.UDPList;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author sjurl
 */
public class UDPListMapper implements RowMapper<UDPList> {

    @Override
    public UDPList mapRow(ResultSet rs, int rowNum) throws SQLException {
        UDPList list = new UDPList();
        list.setDescription(rs.getString("description"));
        list.setId(rs.getString("id"));
        list.setName(rs.getString("name"));
        list.setUdpName(rs.getString("udp_name"));
        return list;
    }

}
