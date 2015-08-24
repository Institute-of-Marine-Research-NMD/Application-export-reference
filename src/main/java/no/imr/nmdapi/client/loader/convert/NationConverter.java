package no.imr.nmdapi.client.loader.convert;

import no.imr.nmdapi.client.loader.dao.NationDAO;
import no.imr.nmdapi.generic.nmdreference.domain.v1.NationElementListType;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * convert nations into NationElementListType
 *
 * @author sjurl
 */
public class NationConverter {

    @Autowired
    private NationDAO nationDAO;

    public NationElementListType getNationElementListType() {
        NationElementListType nations = new NationElementListType();
        nations.getElement().addAll(nationDAO.getNations());
        return nations;
    }
}
