package no.imr.nmdapi.client.loader.convert;

import java.sql.Date;
import no.imr.nmdapi.client.loader.dao.MissionTypeDAO;
import no.imr.nmdapi.generic.nmdreference.domain.v1.MissionTypeElementListType;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * convert mission types into MissionTypeElementListType
 *
 * @author sjurl
 */
public class MissionTypeConverter {

    @Autowired
    private MissionTypeDAO missionTypeDAO;

    public MissionTypeElementListType generateMissionTypeElementList() {
        MissionTypeElementListType elementList = new MissionTypeElementListType();
        elementList.getElement().addAll(missionTypeDAO.getMissionTypes());
        elementList.setLastChanged(DateConverter.convertDate((Date) missionTypeDAO.getLastChanged()));
        return elementList;
    }
}
