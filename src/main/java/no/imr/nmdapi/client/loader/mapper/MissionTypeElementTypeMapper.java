package no.imr.nmdapi.client.loader.mapper;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import no.imr.commons.nmdreference.domain.v1_0.MissionTypeElementType;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author sjurl
 */
public class MissionTypeElementTypeMapper implements RowMapper<MissionTypeElementType> {

    @Override
    public MissionTypeElementType mapRow(ResultSet rs, int rowNum) throws SQLException {
        MissionTypeElementType element = new MissionTypeElementType();
        element.setCode(BigInteger.valueOf(rs.getInt("code")));
        element.setDeprecated(Boolean.FALSE);
        element.setDescription(rs.getString("description"));
        element.setId(rs.getString("id"));
        return element;
    }

}
