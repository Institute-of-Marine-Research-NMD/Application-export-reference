package no.imr.nmdapi.client.loader.dao;

import java.util.List;
import javax.sql.DataSource;
import no.imr.nmdapi.client.loader.mapper.LanguageElementTypeMapper;
import no.imr.nmdapi.generic.nmdreference.domain.v1.LanguageElementType;
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
}
