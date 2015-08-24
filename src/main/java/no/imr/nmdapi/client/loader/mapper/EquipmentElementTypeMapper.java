/*
 */
package no.imr.nmdapi.client.loader.mapper;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import no.imr.nmdapi.generic.nmdreference.domain.v1.EquipmentElementType;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author sjurl
 */
public class EquipmentElementTypeMapper implements RowMapper<EquipmentElementType> {

    @Override
    public EquipmentElementType mapRow(ResultSet rs, int rowNum) throws SQLException {
        EquipmentElementType element = new EquipmentElementType();
        element.setArea(BigDecimal.valueOf(rs.getDouble("area")));
        element.setCode(BigInteger.valueOf(rs.getInt("code")));
        element.setDeprecated(Boolean.FALSE);
        element.setDescription(rs.getString("description"));
        element.setId(rs.getString("id"));
        element.setName(rs.getString("name"));
        return element;
    }

}
