package no.imr.nmdapi.client.loader.convert;

import java.sql.Date;
import no.imr.commons.nmdreference.domain.v1_0.LanguageElementListType;
import no.imr.nmdapi.client.loader.dao.LanguageDAO;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * converts language into LanguageElementListType
 *
 * @author sjurl
 */
public class LanguageConverter implements ConvertInterface {

    @Autowired
    private LanguageDAO languageDAO;

    /**
     * converts language into LanguageElementListType
     *
     * @return
     */
    @Override
    public LanguageElementListType convert() {
        LanguageElementListType langList = new LanguageElementListType();
        langList.getElement().addAll(languageDAO.getLanguageElementType());
        langList.setLastChanged(DateConverter.convertDate((Date) languageDAO.getLastChanged()));
        return langList;
    }
}
