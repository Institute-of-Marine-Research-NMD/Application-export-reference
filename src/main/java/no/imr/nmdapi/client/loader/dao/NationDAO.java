package no.imr.nmdapi.client.loader.dao;

import java.sql.Date;
import java.util.List;
import javax.sql.DataSource;
import no.imr.nmdapi.client.loader.mapper.DateMapper;
import no.imr.nmdapi.client.loader.mapper.NationElementTypeMapper;
import no.imr.nmdapi.generic.nmdreference.domain.v1.NationElementType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Data access object for nations
 *
 * @author sjurl
 */
public class NationDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /**
     * returns a list of nations
     *
     * @return
     */
    public List<NationElementType> getNations() {
        return jdbcTemplate.query("select id, nation, nationioc, nationname, pgnapescode FROM nmdreference.nation", new NationElementTypeMapper());
    }

    public Date getLastChanged() {
        return (Date) jdbcTemplate.query("SELECT max(last_edited) as last_edited FROM nmdreference.nation", new DateMapper()).get(0);
    }
}
