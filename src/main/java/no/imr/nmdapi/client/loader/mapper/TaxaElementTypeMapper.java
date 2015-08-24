package no.imr.nmdapi.client.loader.mapper;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import no.imr.nmdapi.generic.nmdreference.domain.v1.TaxaElementType;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author sjurl
 */
public class TaxaElementTypeMapper implements RowMapper<TaxaElementType> {

    @Override
    public TaxaElementType mapRow(ResultSet rs, int rowNum) throws SQLException {
        TaxaElementType taxaElement = new TaxaElementType();
        taxaElement.setAphiaid(rs.getString("aphiaid"));
        taxaElement.setDeprecated(Boolean.FALSE);
        taxaElement.setFao(rs.getString("fao"));
        taxaElement.setId(rs.getString("id"));
        taxaElement.setImr(rs.getInt("imr"));
        taxaElement.setInformation(rs.getString("information"));
        taxaElement.setNodc(rs.getString("nodc"));
        taxaElement.setPgnapes(rs.getString("pgnapes"));
        taxaElement.setRuCode(rs.getInt("ru_code"));
        taxaElement.setTsn(BigInteger.valueOf(rs.getInt("tsn")));
        return taxaElement;
    }

}
