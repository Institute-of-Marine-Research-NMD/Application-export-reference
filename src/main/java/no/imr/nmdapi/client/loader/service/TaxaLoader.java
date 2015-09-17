package no.imr.nmdapi.client.loader.service;

import no.imr.nmdapi.client.loader.convert.TaxaConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author sjurl
 */
@Service("taxaLoader")
public class TaxaLoader extends ReferenceLoaderServiceImpl {

    @Autowired
    private TaxaConverter taxaConverter;

    @Override
    public void loadReferenceToXml() {
        init();
        handle("taxa", taxaConverter);
        finish();
    }

}
