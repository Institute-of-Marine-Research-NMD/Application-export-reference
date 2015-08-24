package no.imr.nmdapi.client.loader.mapper;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import no.imr.nmdapi.generic.nmdreference.domain.v1.InstitutionElementType;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author sjurl
 */
public class InstitutionElementTypeMapper implements RowMapper<InstitutionElementType> {

    @Override
    public InstitutionElementType mapRow(ResultSet rs, int rowNum) throws SQLException {
        InstitutionElementType element = new InstitutionElementType();
        element.setAdress(rs.getString("instadress").trim());
        element.setDeprecated(Boolean.FALSE);
        element.setEmail(rs.getString("instemail").trim());
        element.setId(rs.getString("id"));
        element.setInstitution(BigInteger.valueOf(rs.getInt("institution")));
        element.setName(rs.getString("instname").trim());
        element.setNation(BigInteger.valueOf(rs.getInt("nation")));
        element.setPhone(rs.getString("instphone").trim());
        return element;
    }

}
