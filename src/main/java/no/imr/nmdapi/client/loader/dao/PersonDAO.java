package no.imr.nmdapi.client.loader.dao;

import java.sql.Date;
import java.util.List;
import javax.sql.DataSource;
import no.imr.commons.nmdreference.domain.v1_0.PersonElementType;
import no.imr.nmdapi.client.loader.mapper.DateMapper;
import no.imr.nmdapi.client.loader.mapper.PersonElementTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Data access object for acoustic categories
 *
 * @author sjurl
 */
public class PersonDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /**
     * Returns a list of acoustic categories
     *
     * @return
     */
    public List<PersonElementType> getPersons() {
        return jdbcTemplate.query("SELECT firstname, familyname FROM nmdreference.person", new PersonElementTypeMapper());
    }

    /**
     * Get the last changed date for persom
     *
     * @return
     */
    public Date getLastChanged() {
        return (Date) jdbcTemplate.query("SELECT max(last_edited) as last_edited FROM nmdreference.person", new DateMapper()).get(0);
    }
}
