package no.imr.nmdapi.client.loader.convert;

import no.imr.nmdapi.client.loader.dao.LanguageDAO;
import no.imr.nmdapi.generic.nmdreference.domain.v1.LanguageElementListType;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * converts language into LanguageElementListType
 *
 * @author sjurl
 */
public class LanguageConverter {

    @Autowired
    private LanguageDAO languageDAO;

    public LanguageElementListType getLanguageElementList() {
        LanguageElementListType langList = new LanguageElementListType();
        langList.getElement().addAll(languageDAO.getLanguageElementType());
        return langList;
    }
}
