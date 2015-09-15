package no.imr.nmdapi.client.loader.convert;

import java.sql.Date;
import no.imr.commons.nmdreference.domain.v1.InstitutionElementListType;
import no.imr.nmdapi.client.loader.dao.InstitutionDAO;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * converts intitutions into InstitutionElementListType
 *
 * @author sjurl
 */
public class InstitutionConverter implements ConvertInterface {

    @Autowired
    private InstitutionDAO institutionDAO;

    /**
     * converts intitutions into InstitutionElementListType
     *
     * @return
     */
    @Override
    public InstitutionElementListType convert() {
        InstitutionElementListType institutionList = new InstitutionElementListType();
        institutionList.getElement().addAll(institutionDAO.getInstitutions());
        institutionList.setLastChanged(DateConverter.convertDate((Date) institutionDAO.getLastChanged()));
        return institutionList;
    }
}
