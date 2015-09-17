package no.imr.nmdapi.client.loader.service;

import no.imr.nmdapi.client.loader.convert.AcousticCategoryConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author sjurl
 */
@Service("acousticcategoryLoader")
public class AcousticCategoryLoader extends ReferenceLoaderServiceImpl {

    @Autowired
    private AcousticCategoryConverter acousticCategoryConverter;

    @Override
    public void loadReferenceToXml() {
        init();
        handle("acousticcategory", acousticCategoryConverter);
        finish();
    }
}
