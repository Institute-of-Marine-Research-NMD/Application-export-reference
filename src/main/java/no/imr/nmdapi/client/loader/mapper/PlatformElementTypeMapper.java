package no.imr.nmdapi.client.loader.mapper;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import no.imr.nmdapi.generic.nmdreference.domain.v1.NationType;
import no.imr.nmdapi.generic.nmdreference.domain.v1.PlatformElementType;
import no.imr.nmdapi.generic.nmdreference.domain.v1.PlatformType;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author sjurl
 */
public class PlatformElementTypeMapper implements RowMapper<PlatformElementType> {

    @Override
    public PlatformElementType mapRow(ResultSet rs, int rowNum) throws SQLException {
        PlatformElementType platform = new PlatformElementType();
        platform.setId(rs.getString("id"));
        platform.setPlatformNumber(rs.getInt("platform"));
        PlatformType pt = new PlatformType();
        pt.setValue(new BigInteger(rs.getString("platformtype").concat(rs.getString("platformsubtype"))));
        pt.setDescription(rs.getString("platformtypename").concat(": ").concat(rs.getString("platformsubtypename")));
        platform.setPlatformType(pt);
        platform.setDeprecated(Boolean.FALSE);
        NationType nation = new NationType();
        nation.setValue(BigInteger.valueOf(rs.getInt("nation")));
        nation.setName(rs.getString("nationname").trim());
        platform.setNation(nation);
        PlatformType platformType = new PlatformType();
        platformType.setValue(BigInteger.valueOf(rs.getInt("platformtype")));
        return platform;
//        platformCodeDAO.addPlatformCodes(platform);
        //        platformList.getPlatform().add(platform);
    }

}
