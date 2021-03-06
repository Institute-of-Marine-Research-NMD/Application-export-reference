package no.imr.nmdapi.client.loader.dao;

import java.sql.Date;
import java.util.List;
import javax.sql.DataSource;
import no.imr.commons.nmdreference.domain.v1_0.KeyValueElementType;
import no.imr.commons.nmdreference.domain.v1_0.RestrictionElementType;
import no.imr.commons.nmdreference.domain.v1_0.StockElementType;
import no.imr.commons.nmdreference.domain.v1_0.TaxaElementType;
import no.imr.commons.nmdreference.domain.v1_0.TaxaElementType.TaxaSynonyms.Synonym;
import no.imr.nmdapi.client.loader.mapper.DateMapper;
import no.imr.nmdapi.client.loader.mapper.KeyValueElementTypeMapper;
import no.imr.nmdapi.client.loader.mapper.RestrictionElementTypeMapper;
import no.imr.nmdapi.client.loader.mapper.SpesialstadieListsMapper;
import no.imr.nmdapi.client.loader.mapper.StockElementTypeMapper;
import no.imr.nmdapi.client.loader.mapper.SynonymMapper;
import no.imr.nmdapi.client.loader.mapper.TaxaElementTypeMapper;
import no.imr.nmdapi.client.loader.pojo.SpesialstadieLists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Data access object for taxa
 *
 * @author sjurl
 */
public class TaxaDAO {

    private JdbcTemplate jdbcTemplate;
    private static final String GET_ALL_TAXA = "select id, tsn, imr, path, nodc, validstatus, fao, information, pgnapes, aphiaid, ru_code from "
            + "nmdreference.taxa";
    private static final String GET_TAXA_SYNONYM = "SELECT ts.name, ts.preferred, lan.name as language FROM "
            + "nmdreference.taxa_synonym ts, nmdreference.language lan WHERE ts.id_language = lan.id AND"
            + " ts.id_taxa = ?";
    private static final String GET_TAXA_LISTS = "select pt.sexdependent, udp.name, udp.udp_name, udp.id, pt.validto,pt.validfrom,uop.propertyname  from nmdreference.u_u_vobject_property_taxa pt, nmdreference.u_udp udp, nmdreference.u_vobject_property uop where id_taxa = ? and pt.id_u_udp = udp.id and uop.id= pt.id_u_vobject_property";

    private static final String GET_UDP_LIST_ELEMENTS = "select shortname, name, description, validfrom, validto, id from nmdreference.u_udplist where id_u_udp = ?";

    private static final String GET_RESTRICTION = "select rev.double_value, r.name, r.description from nmdreference.u_restriction_value rev, nmdreference.u_restriction r where rev.id_u_restriction = r.id and id_taxa = ?";

    private static final String GET_STOCK = "select description, id, code from nmdreference.stock where id_taxa = ?";

    private static final String LAST_EDITED = "select max(a) as last_edited from "
            + "(select max(tax.last_edited) as a from nmdreference.taxa tax "
            + "union select max(syn.last_edited) as b from nmdreference.taxa_synonym syn "
            + "union select max(rest.last_edited) as c from nmdreference.u_restriction rest "
            + "union select max(restval.last_edited) as d from nmdreference.u_restriction_value restval "
            + "union select max(stock.last_edited) as e from nmdreference.stock stock) n";

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /**
     * returns a list of all taxas
     *
     * @return
     */
    public List<TaxaElementType> getAllTaxa() {
        return jdbcTemplate.query(GET_ALL_TAXA, new TaxaElementTypeMapper());
    }

    /**
     * returns a list of synonyms for the taxa matching the provided id
     *
     * @param id
     * @return
     */
    public List<Synonym> processPlatforms(String id) {
        return jdbcTemplate.query(GET_TAXA_SYNONYM, new SynonymMapper(), id);
    }

    /**
     * returns a list of special stages for the taxa matching the provided id
     *
     * @param id
     * @return
     */
    public List<SpesialstadieLists> getListsForTaxa(String id) {
        return jdbcTemplate.query(GET_TAXA_LISTS, new SpesialstadieListsMapper(), id);
    }

    /**
     * Returns a list of key value elements for the spesicalstage matching the
     * provided id
     *
     * @param id
     * @return
     */
    public List<KeyValueElementType> getKeyValueElements(String id) {
        return jdbcTemplate.query(GET_UDP_LIST_ELEMENTS, new KeyValueElementTypeMapper(), id);
    }

    /**
     * returns a list of restrictions for the taxa matching the provided id
     *
     * @param id
     * @return
     */
    public List<RestrictionElementType> getRestrictionsForTaxa(String id) {
        return jdbcTemplate.query(GET_RESTRICTION, new RestrictionElementTypeMapper(), id);
    }

    /**
     * returns a list of stocks for the taxa matching the provided id
     *
     * @param id
     * @return
     */
    public List<StockElementType> getStock(String id) {
        return jdbcTemplate.query(GET_STOCK, new StockElementTypeMapper(), id);
    }

    /**
     * Get the last edited date for taxa
     *
     * @return
     */
    public Date getLastChanged() {
        return (Date) jdbcTemplate.query(LAST_EDITED, new DateMapper()).get(0);
    }
}
