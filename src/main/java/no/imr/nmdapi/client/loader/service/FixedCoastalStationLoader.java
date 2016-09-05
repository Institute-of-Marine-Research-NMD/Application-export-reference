/*
 */
package no.imr.nmdapi.client.loader.service;

import no.imr.nmdapi.client.loader.convert.FixedCoastalStationConverter;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author sjurl
 */
@Service("fixedCoastalStationLoader")
public class FixedCoastalStationLoader extends ReferenceLoaderServiceImpl {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(FixedCoastalStationLoader.class);
    @Autowired
    private FixedCoastalStationConverter fixedStationConverter;

    @Override
    public void loadReferenceToXml() {
        LOGGER.info("Starting fixed coastal station");
        init();
        handle("fixedcoastalstation", fixedStationConverter);
        finish();
    }

}
