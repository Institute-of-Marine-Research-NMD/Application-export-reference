package no.imr.nmdapi.client.loader.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import no.imr.commons.nmdreference.domain.v1.PersonElementType;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author sjurl
 */
public class PersonElementTypeMapper implements RowMapper<PersonElementType> {

    @Override
    public PersonElementType mapRow(ResultSet rs, int rowNum) throws SQLException {
        PersonElementType elementType = new PersonElementType();
        elementType.setFamilyname(rs.getString("familyname"));
        elementType.setFirstname(rs.getString("firstname"));
        return elementType;
    }

}
