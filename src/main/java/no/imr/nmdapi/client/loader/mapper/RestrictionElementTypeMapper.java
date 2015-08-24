package no.imr.nmdapi.client.loader.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import no.imr.nmdapi.generic.nmdreference.domain.v1.RestrictionElementType;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author sjurl
 */
public class RestrictionElementTypeMapper implements RowMapper<RestrictionElementType> {

    @Override
    public RestrictionElementType mapRow(ResultSet rs, int rowNum) throws SQLException {
        RestrictionElementType ret = new RestrictionElementType();
        ret.setDescription(rs.getString("description"));
        ret.setName(rs.getString("name"));
        ret.setValue(rs.getString("double_value"));
        return ret;
    }

}
