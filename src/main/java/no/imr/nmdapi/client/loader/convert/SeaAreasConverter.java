package no.imr.nmdapi.client.loader.convert;

import java.sql.Date;
import no.imr.commons.nmdreference.domain.v1.SeaAreasElementListType;
import no.imr.nmdapi.client.loader.dao.SeaAreasDAO;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * convert sea areas into SeaAreasElementListType
 *
 * @author sjurl
 */
public class SeaAreasConverter implements ConvertInterface {

    @Autowired
    private SeaAreasDAO seaAreasDAO;

    /**
     * Returns all sea areas from the database
     *
     * @return
     */
    @Override
    public SeaAreasElementListType convert() {
        SeaAreasElementListType elementList = new SeaAreasElementListType();
        elementList.getElement().addAll(seaAreasDAO.getSeaAreas());
        elementList.setLastChanged(DateConverter.convertDate((Date) seaAreasDAO.getLastChanged()));
        return elementList;
    }
}
