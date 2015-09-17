package no.imr.nmdapi.client.loader.service;

import no.imr.nmdapi.client.loader.convert.PersonConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author sjurl
 */
@Service("personLoader")
public class PersonLoader extends ReferenceLoaderServiceImpl {

    @Autowired
    private PersonConverter personConverter;

    @Override
    public void loadReferenceToXml() {
        init();
        handle("person", personConverter);
        finish();
    }

}
