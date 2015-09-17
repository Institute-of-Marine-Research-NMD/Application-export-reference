package no.imr.nmdapi.client.loader.service;

import no.imr.nmdapi.client.loader.convert.NationConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author sjurl
 */
@Service("nationLoader")
public class NationLoader extends ReferenceLoaderServiceImpl {

    @Autowired
    private NationConverter nationConverter;

    @Override
    public void loadReferenceToXml() {
        init();
        handle("nation", nationConverter);
        finish();
    }

}
