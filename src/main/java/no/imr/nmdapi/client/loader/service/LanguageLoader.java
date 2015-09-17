package no.imr.nmdapi.client.loader.service;

import no.imr.nmdapi.client.loader.convert.LanguageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author sjurl
 */
@Service("languageLoader")
public class LanguageLoader extends ReferenceLoaderServiceImpl {

    @Autowired
    private LanguageConverter languageConverter;

    @Override
    public void loadReferenceToXml() {
        init();
        handle("language", languageConverter);
        finish();
    }

}
