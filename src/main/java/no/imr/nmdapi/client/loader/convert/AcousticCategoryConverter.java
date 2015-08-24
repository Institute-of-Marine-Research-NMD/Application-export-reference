package no.imr.nmdapi.client.loader.convert;

import java.util.List;
import no.imr.nmdapi.client.loader.dao.AcousticCategoryDAO;
import no.imr.nmdapi.generic.nmdreference.domain.v1.AcousticCategoryElementListType;
import no.imr.nmdapi.generic.nmdreference.domain.v1.AcousticCategoryElementType;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * converts acoustic categories to a list of AcousticCategoryElementListType
 *
 * @author sjurl
 */
public class AcousticCategoryConverter {

    @Autowired
    private AcousticCategoryDAO acousticDAO;

    public AcousticCategoryElementListType generateAcousticCategoryListType() {
        AcousticCategoryElementListType acListType = new AcousticCategoryElementListType();
        List<AcousticCategoryElementType> acList = acousticDAO.getAcousticCategories();
        acListType.getElement().addAll(acList);
        return acListType;
    }

}
