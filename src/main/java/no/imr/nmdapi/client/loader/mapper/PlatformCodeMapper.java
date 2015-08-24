package no.imr.nmdapi.client.loader.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import no.imr.nmdapi.client.loader.convert.DateConverter;
import no.imr.nmdapi.generic.nmdreference.domain.v1.PlatformElementType;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author sjurl
 */
public class PlatformCodeMapper implements RowMapper<PlatformElementType.PlatformCodes.PlatformCode> {

    @Override
    public PlatformElementType.PlatformCodes.PlatformCode mapRow(ResultSet rs, int rowNum) throws SQLException {
        PlatformElementType.PlatformCodes.PlatformCode platformcode = new PlatformElementType.PlatformCodes.PlatformCode();
        platformcode.setPlatformCode(rs.getString("platformcode"));
        platformcode.setSysCode(rs.getInt("platformcodesys"));
        platformcode.setSysName(rs.getString("platformcodesysname"));
        platformcode.setValidFrom(DateConverter.convertDate(rs.getDate("firstvaliddate")));
        platformcode.setValidTo(DateConverter.convertDate(rs.getDate("lastvaliddate")));
        return platformcode;
    }

}
