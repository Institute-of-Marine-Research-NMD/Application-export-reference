package no.imr.nmdapi.client.loader.dao;

import java.sql.Date;
import java.util.List;
import javax.sql.DataSource;
import no.imr.commons.nmdreference.domain.v1_0.LanguageElementType;
import no.imr.nmdapi.client.loader.mapper.DateMapper;
import no.imr.nmdapi.client.loader.mapper.LanguageElementTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Data access object for language
 *
 * @author sjurl
 */
public class LanguageDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /**
     * returns a list of languages
     *
     * @return
     */
    public List<LanguageElementType> getLanguageElementType() {
        return jdbcTemplate.query("SELECT name, id, iso6391 from nmdreference.language", new LanguageElementTypeMapper());
    }

    /**
     * Get last changed for language
     *
     * @return
     */
    public Date getLastChanged() {
        return (Date) jdbcTemplate.query("SELECT max(last_edited) as last_edited FROM nmdreference.language", new DateMapper()).get(0);
    }

}
