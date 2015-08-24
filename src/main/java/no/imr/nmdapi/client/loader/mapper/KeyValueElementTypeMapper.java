package no.imr.nmdapi.client.loader.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import no.imr.nmdapi.client.loader.convert.DateConverter;
import no.imr.nmdapi.generic.nmdreference.domain.v1.KeyValueElementType;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author sjurl
 */
public class KeyValueElementTypeMapper implements RowMapper<KeyValueElementType> {

    @Override
    public KeyValueElementType mapRow(ResultSet rs, int rowNum) throws SQLException {
        KeyValueElementType kvet = new KeyValueElementType();
        kvet.setDeprecated(Boolean.FALSE);
        kvet.setDescription(rs.getString("description"));
        kvet.setId(rs.getString("id"));
        kvet.setName(rs.getString("name"));
        kvet.setShortname(rs.getString("shortname"));
        kvet.setValidFrom(DateConverter.convertDate(rs.getDate("validfrom")));
        kvet.setValidTo(DateConverter.convertDate(rs.getDate("validto")));
        return kvet;
    }

}
