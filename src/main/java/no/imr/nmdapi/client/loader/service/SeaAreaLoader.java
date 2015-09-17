package no.imr.nmdapi.client.loader.service;

import no.imr.nmdapi.client.loader.convert.SeaAreasConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author sjurl
 */
@Service("seaareaLoader")
public class SeaAreaLoader extends ReferenceLoaderServiceImpl {

    @Autowired
    private SeaAreasConverter seaAreasConverter;

    @Override
    public void loadReferenceToXml() {
        init();
        handle("seaareas", seaAreasConverter);
        finish();
    }

}
