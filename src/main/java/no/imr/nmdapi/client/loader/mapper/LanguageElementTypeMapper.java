package no.imr.nmdapi.client.loader.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import no.imr.nmdapi.generic.nmdreference.domain.v1.LanguageElementType;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author sjurl
 */
public class LanguageElementTypeMapper implements RowMapper<LanguageElementType> {

    @Override
    public LanguageElementType mapRow(ResultSet rs, int rowNum) throws SQLException {
        LanguageElementType element = new LanguageElementType();
        element.setDeprecated(Boolean.FALSE);
        element.setId(rs.getString("id"));
        element.setIso6391(rs.getString("iso6391"));
        element.setName(rs.getString("name"));
        return element;
    }

}
