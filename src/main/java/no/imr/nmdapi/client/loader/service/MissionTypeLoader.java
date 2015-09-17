package no.imr.nmdapi.client.loader.service;

import no.imr.nmdapi.client.loader.convert.MissionTypeConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author sjurl
 */
@Service("missiontypeLoader")
public class MissionTypeLoader extends ReferenceLoaderServiceImpl {

    @Autowired
    private MissionTypeConverter missionTypeConverter;

    @Override
    public void loadReferenceToXml() {
        init();
        handle("missiontype", missionTypeConverter);
        finish();
    }

}
