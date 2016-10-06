package no.imr.nmdapi.client.loader.dao;

import java.sql.Date;
import java.util.List;
import javax.sql.DataSource;
import no.imr.commons.nmdreference.domain.v1_0.AcousticCategoryElementType;
import no.imr.nmdapi.client.loader.mapper.AcousticCategoryElementTypeMapper;
import no.imr.nmdapi.client.loader.mapper.DateMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Data access object for acoustic categories
 *
 * @author sjurl
 */
public class AcousticCategoryDAO {

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
    public List<AcousticCategoryElementType> getAcousticCategories() {
        return jdbcTemplate.query("SELECT id, acousticcategory, initials, englishinitials, commonname, englishname, pgnapescode FROM nmdreference.acousticcategory", new AcousticCategoryElementTypeMapper());
    }

    /**
     * Get last changed for acoustic category
     *
     * @return
     */
    public Date getLastChanged() {
        return (Date) jdbcTemplate.query("SELECT max(last_edited) as last_edited FROM nmdreference.acousticcategory", new DateMapper()).get(0);
    }
}
