package no.imr.nmdapi.client.loader.dao;

import java.util.List;
import javax.sql.DataSource;
import no.imr.nmdapi.client.loader.mapper.InstitutionElementTypeMapper;
import no.imr.nmdapi.generic.nmdreference.domain.v1.InstitutionElementType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Data access object for institutions
 *
 * @author sjurl
 */
public class InstitutionDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /**
     * returns a list of institutions
     *
     * @return
     */
    public List<InstitutionElementType> getInstitutions() {
        return jdbcTemplate.query("select id, nation, institution, instname, instadress, instphone, instemail from nmdreference.institution", new InstitutionElementTypeMapper());
    }
}
