package no.imr.nmdapi.client.loader.convert;

import no.imr.nmdapi.client.loader.dao.InstitutionDAO;
import no.imr.nmdapi.generic.nmdreference.domain.v1.InstitutionElementListType;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * converts intitutions into InstitutionElementListType
 *
 * @author sjurl
 */
public class InstitutionConverter {

    @Autowired
    private InstitutionDAO institutionDAO;

    public InstitutionElementListType getInstitutionElementListType() {
        InstitutionElementListType institutionList = new InstitutionElementListType();
        institutionList.getElement().addAll(institutionDAO.getInstitutions());
        return institutionList;
    }
}
