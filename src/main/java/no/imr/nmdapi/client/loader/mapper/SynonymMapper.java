package no.imr.nmdapi.client.loader.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import no.imr.nmdapi.generic.nmdreference.domain.v1.TaxaElementType;
import no.imr.nmdapi.generic.nmdreference.domain.v1.TaxaElementType.TaxaSynonyms.Synonym;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author sjurl
 */
public class SynonymMapper implements RowMapper<Synonym> {

    @Override
    public Synonym mapRow(ResultSet rs, int rowNum) throws SQLException {
        Synonym syn = new TaxaElementType.TaxaSynonyms.Synonym();
        syn.setLanguage(rs.getString("language"));
        syn.setName(rs.getString("name"));
        syn.setPreferred(rs.getInt("preferred"));
        return syn;
    }

}
