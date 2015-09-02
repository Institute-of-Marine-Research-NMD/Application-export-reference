package no.imr.nmdapi.client.loader.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author sjurl
 */
public class DateMapper implements RowMapper<java.util.Date> {

    @Override
    public Date mapRow(ResultSet rs, int rowNum) throws SQLException {
        return rs.getDate("last_edited");
    }

}
