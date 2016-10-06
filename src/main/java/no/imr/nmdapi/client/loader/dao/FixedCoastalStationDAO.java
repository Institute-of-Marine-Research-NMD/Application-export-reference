/*
 */
package no.imr.nmdapi.client.loader.dao;

import java.sql.Date;
import java.util.List;
import javax.sql.DataSource;
import no.imr.commons.nmdreference.domain.v1_0.FixedCoastalstationType;
import no.imr.nmdapi.client.loader.mapper.DateMapper;
import no.imr.nmdapi.client.loader.mapper.FixedCoastalStationElementTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author sjurl
 */
public class FixedCoastalStationDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<FixedCoastalstationType> getFixedCoastalStations() {
        return jdbcTemplate.query("select a.id, a.station, a.name, a.description, a.latitude, a.longitude, a.maxdepth, a.gein1, a.gein2, a.startyear, a.endyear"
                + " , ca.name as coastaareaname, ca.description as coastalareadesc "
                + " , su.name as substratename, su.description as substratedesc"
                + " , top.name as topographyname, top.description as topographydescription"
                + " , exp.name as exposurename, exp.description as exposuredescription"
                + " from nmdreference.fixedcoastalstation a, nmdreference.u_udplist ca ,nmdreference.u_udplist  su"
                + " ,nmdreference.u_udplist top"
                + " ,nmdreference.u_udplist exp"
                + " where a.id_r_udplist_coastalarea = ca.id and a.id_r_udplist_substrate = su.id"
                + " and a.id_r_udplist_topography = top.id"
                + " and a.id_r_udplist_exposure = exp.id", new FixedCoastalStationElementTypeMapper());
    }

    /**
     * Get last changed for acoustic category
     *
     * @return
     */
    public Date getLastChanged() {
        return (Date) jdbcTemplate.query("SELECT max(last_edited) as last_edited FROM nmdreference.fixedcoastalstation", new DateMapper()).get(0);
    }
}
