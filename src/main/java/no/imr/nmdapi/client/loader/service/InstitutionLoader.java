package no.imr.nmdapi.client.loader.service;

import no.imr.nmdapi.client.loader.convert.InstitutionConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author sjurl
 */
@Service("institutionLoader")
public class InstitutionLoader extends ReferenceLoaderServiceImpl {

    @Autowired
    private InstitutionConverter institutionConverter;

    @Override
    public void loadReferenceToXml() {
        init();
        handle("institution", institutionConverter);
        finish();
    }

}
