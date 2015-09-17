package no.imr.nmdapi.client.loader.service;

import no.imr.nmdapi.client.loader.convert.EquipmentConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author sjurl
 */
@Service("equipmentLoader")
public class EquipmentLoader extends ReferenceLoaderServiceImpl {

    @Autowired
    private EquipmentConverter equipmentConverter;

    @Override
    public void loadReferenceToXml() {
        init();
        handle("equipment", equipmentConverter);
        finish();
    }

}
