/*
 */
package no.imr.nmdapi.client.loader.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import no.imr.nmdapi.client.loader.pojo.SpesialstadieLists;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author sjurl
 */
public class SpesialstadieListsMapper implements RowMapper<SpesialstadieLists> {

    @Override
    public SpesialstadieLists mapRow(ResultSet rs, int rowNum) throws SQLException {
        SpesialstadieLists slist = new SpesialstadieLists();
        slist.setSexdependent(rs.getInt("sexdependent"));
        slist.setName(rs.getString("name"));
        slist.setId(rs.getString("id"));
        return slist;
    }

}
