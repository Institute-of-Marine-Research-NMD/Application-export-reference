package no.imr.nmdapi.client.loader.convert;

import java.sql.Date;
import no.imr.commons.nmdreference.domain.v1_0.NationElementListType;
import no.imr.nmdapi.client.loader.dao.NationDAO;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * convert nations into NationElementListType
 *
 * @author sjurl
 */
public class NationConverter implements ConvertInterface {

    @Autowired
    private NationDAO nationDAO;

    /**
     * convert nations into NationElementListType
     *
     * @return
     */
    @Override
    public NationElementListType convert() {
        NationElementListType nations = new NationElementListType();
        nations.getElement().addAll(nationDAO.getNations());
        nations.setLastChanged(DateConverter.convertDate((Date) nationDAO.getLastChanged()));
        return nations;
    }
}
