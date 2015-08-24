package no.imr.nmdapi.client.loader.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import no.imr.nmdapi.generic.nmdreference.domain.v1.StockElementType;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author sjurl
 */
public class StockElementTypeMapper implements RowMapper<StockElementType> {

    @Override
    public StockElementType mapRow(ResultSet rs, int rowNum) throws SQLException {
        StockElementType ste = new StockElementType();
        ste.setDeprecated(Boolean.FALSE);
        ste.setId(rs.getString("id"));
        ste.setName(rs.getString("code"));
        ste.setDescription(rs.getString("description"));
        return ste;
    }

}
