package no.imr.nmdapi.client.loader.convert;

import no.imr.nmdapi.client.loader.dao.EquipmentDAO;
import no.imr.nmdapi.generic.nmdreference.domain.v1.EquipmentElementListType;
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
        return equipmentList;
    }
}
