package no.imr.nmdapi.client.loader.mapper;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import no.imr.nmdapi.generic.nmdreference.domain.v1.AcousticCategoryElementType;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author sjurl
 */
public class AcousticCategoryElementTypeMapper implements RowMapper<AcousticCategoryElementType> {

    @Override
    public AcousticCategoryElementType mapRow(ResultSet rs, int rowNum) throws SQLException {
        AcousticCategoryElementType actype = new AcousticCategoryElementType();
        actype.setAcousticcategory(BigInteger.valueOf(rs.getInt("acousticcategory")));
        actype.setCommonName(rs.getString("commonname"));
        actype.setDeprecated(Boolean.FALSE);
        actype.setEnglishInitials(rs.getString("englishinitials"));
        actype.setEnglishName(rs.getString("englishname"));
        actype.setId(rs.getString("id"));
        actype.setInitials(rs.getString("initials"));
        actype.setPgnapescode(rs.getString("pgnapescode"));
        return actype;
    }

}
