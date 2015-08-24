package no.imr.nmdapi.client.loader.convert;

import no.imr.nmdapi.client.loader.dao.SeaAreasDAO;
import no.imr.nmdapi.generic.nmdreference.domain.v1.SeaAreasElementListType;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * convert sea areas into SeaAreasElementListType
 *
 * @author sjurl
 */
public class SeaAreasConverter {

    @Autowired
    private SeaAreasDAO seaAreasDAO;

    public SeaAreasElementListType getSeaAreasElementListType() {
        SeaAreasElementListType elementList = new SeaAreasElementListType();
        elementList.getElement().addAll(seaAreasDAO.getSeaAreas());
        return elementList;
    }
}
