package no.imr.nmdapi.client.loader.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import no.imr.nmdapi.generic.nmdreference.domain.v1.SeaAreasElementType;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author sjurl
 */
public class SeaAreasElementTypeMapper implements RowMapper<SeaAreasElementType> {

    @Override
    public SeaAreasElementType mapRow(ResultSet rs, int rowNum) throws SQLException {
        SeaAreasElementType element = new SeaAreasElementType();
        element.setDeprecated(Boolean.FALSE);
        element.setDescription(rs.getString("description"));
        element.setId(rs.getString("id"));
        element.setName(rs.getString("name"));
        return element;
    }

}
