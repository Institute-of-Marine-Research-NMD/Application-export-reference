package no.imr.nmdapi.client.loader.convert;

import no.imr.commons.nmdreference.domain.v1_0.PersonsElementListType;
import no.imr.nmdapi.client.loader.dao.PersonDAO;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author sjurl
 */
public class PersonConverter implements ConvertInterface {

    @Autowired
    private PersonDAO personDAO;

    @Override
    public PersonsElementListType convert() {
        PersonsElementListType elementListType = new PersonsElementListType();
        elementListType.getElement().addAll(personDAO.getPersons());
        return elementListType;
    }
}
