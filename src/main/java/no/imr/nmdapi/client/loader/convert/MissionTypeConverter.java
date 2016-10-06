package no.imr.nmdapi.client.loader.convert;

import java.sql.Date;
import no.imr.commons.nmdreference.domain.v1_0.MissionTypeElementListType;
import no.imr.nmdapi.client.loader.dao.MissionTypeDAO;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * convert mission types into MissionTypeElementListType
 *
 * @author sjurl
 */
public class MissionTypeConverter implements ConvertInterface {

    @Autowired
    private MissionTypeDAO missionTypeDAO;

    /**
     * convert mission types into MissionTypeElementListType
     *
     * @return
     */
    @Override
    public MissionTypeElementListType convert() {
        MissionTypeElementListType elementList = new MissionTypeElementListType();
        elementList.getElement().addAll(missionTypeDAO.getMissionTypes());
        elementList.setLastChanged(DateConverter.convertDate((Date) missionTypeDAO.getLastChanged()));
        return elementList;
    }
}
