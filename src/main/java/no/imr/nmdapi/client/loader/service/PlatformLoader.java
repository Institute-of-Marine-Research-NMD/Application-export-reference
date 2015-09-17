package no.imr.nmdapi.client.loader.service;

import no.imr.nmdapi.client.loader.convert.PlatformConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author sjurl
 */
@Service("platformLoader")
public class PlatformLoader extends ReferenceLoaderServiceImpl {

    @Autowired
    private PlatformConverter platformConverter;

    @Override
    public void loadReferenceToXml() {
        init();
        handle("platform", platformConverter);
        finish();
    }
}
