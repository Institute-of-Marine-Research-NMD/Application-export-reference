/*
 */
package no.imr.nmdapi.client.loader.mapper;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import no.imr.commons.nmdreference.domain.v1.FixedCoastalstationType;
import no.imr.commons.nmdreference.domain.v1.StringDescriptionType;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author sjurl
 */
public class FixedCoastalStationElementTypeMapper implements RowMapper<FixedCoastalstationType> {

    @Override
    public FixedCoastalstationType mapRow(ResultSet rs, int rowNum) throws SQLException {
        FixedCoastalstationType station = new FixedCoastalstationType();
        station.setId(rs.getString("id"));
        station.setStation(BigInteger.valueOf(rs.getInt("station")));
        station.setName(rs.getString("name"));
        station.setDescription(rs.getString("description"));      
        station.setLatitude(rs.getBigDecimal("latitude"));
        station.setLongitude(rs.getBigDecimal("longitude"));
        station.setMaxdepth(rs.getBigDecimal("maxdepth"));
        station.setGein1(rs.getBigDecimal("gein1"));
        station.setGein2(rs.getBigDecimal("gein2"));
        station.setStartyear(BigInteger.valueOf(rs.getInt("startyear")));
        station.setEndyear(BigInteger.valueOf(rs.getInt("endyear")));
        station.setDeprecated(Boolean.FALSE);
        StringDescriptionType coastalarea = new StringDescriptionType();
        coastalarea.setDescription(rs.getString("coastalareadesc"));
        coastalarea.setValue(rs.getString("coastaareaname"));
        station.setCoastalarea(coastalarea);

        StringDescriptionType substrate = new StringDescriptionType();
        substrate.setDescription(rs.getString("substratedesc"));
        substrate.setValue(rs.getString("substratename"));
        station.setSubstrate(substrate);

        StringDescriptionType topography = new StringDescriptionType();
        topography.setDescription(rs.getString("topographydescription"));
        topography.setValue(rs.getString("topographyname"));
        station.setTopography(topography);

        StringDescriptionType exposure = new StringDescriptionType();
        exposure.setDescription(rs.getString("exposuredescription"));
        exposure.setValue(rs.getString("exposurename"));
        station.setExposure(exposure);
        return station;

    }

}
