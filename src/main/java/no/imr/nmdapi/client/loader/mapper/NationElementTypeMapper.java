package no.imr.nmdapi.client.loader.mapper;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import no.imr.nmdapi.generic.nmdreference.domain.v1.NationElementType;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author sjurl
 */
public class NationElementTypeMapper implements RowMapper<NationElementType> {

    @Override
    public NationElementType mapRow(ResultSet rs, int rowNum) throws SQLException {
        NationElementType element = new NationElementType();
        element.setDeprecated(Boolean.FALSE);
        element.setId(rs.getString("id"));
        element.setNation(BigInteger.valueOf(rs.getInt("nation")));
        element.setNationioc(rs.getString("nationioc"));
        element.setNationname(rs.getString("nationname").trim());
        element.setPgnapescode(rs.getString("pgnapescode"));
        return element;
    }

}
