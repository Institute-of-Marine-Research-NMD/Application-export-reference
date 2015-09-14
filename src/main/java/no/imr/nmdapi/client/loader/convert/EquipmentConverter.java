package no.imr.nmdapi.client.loader.convert;

import java.sql.Date;
import no.imr.commons.nmdreference.domain.v1.EquipmentElementListType;
import no.imr.nmdapi.client.loader.dao.EquipmentDAO;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * converts equipments into EquipmentElementListType
 *
 * @author sjurl
 */
public class EquipmentConverter {

    @Autowired
    private EquipmentDAO equipmentDAO;

    public EquipmentElementListType generateEquipmentElementListType() {
        EquipmentElementListType equipmentList = new EquipmentElementListType();
        equipmentList.getElement().addAll(equipmentDAO.getEquipments());
        equipmentList.setLastChanged(DateConverter.convertDate((Date) equipmentDAO.getLastChanged()));
        return equipmentList;
    }
}
