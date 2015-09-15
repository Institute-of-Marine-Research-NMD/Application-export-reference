package no.imr.nmdapi.client.loader.convert;

import java.sql.Date;
import java.util.List;
import no.imr.commons.nmdreference.domain.v1.AcousticCategoryElementListType;
import no.imr.commons.nmdreference.domain.v1.AcousticCategoryElementType;
import no.imr.nmdapi.client.loader.dao.AcousticCategoryDAO;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * converts acoustic categories to a list of AcousticCategoryElementListType
 *
 * @author sjurl
 */
public class AcousticCategoryConverter implements ConvertInterface{

    @Autowired
    private AcousticCategoryDAO acousticDAO;

    @Override
    public AcousticCategoryElementListType convert() {
        AcousticCategoryElementListType acListType = new AcousticCategoryElementListType();
        List<AcousticCategoryElementType> acList = acousticDAO.getAcousticCategories();
        acListType.getElement().addAll(acList);
        acListType.setLastChanged(DateConverter.convertDate((Date) acousticDAO.getLastChanged()));
        return acListType;
    }

}
