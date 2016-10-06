package no.imr.nmdapi.client.loader.service;

import java.util.List;
import no.imr.commons.nmdreference.domain.v1_0.KeyValueElementListType;
import no.imr.nmdapi.client.loader.convert.UdpListConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author sjurl
 */
@Service("udpLoader")
public class UDPLoader extends ReferenceLoaderServiceImpl {

    @Autowired
    private UdpListConverter udpListConverter;

    @Override
    public void loadReferenceToXml() {
        init();
        List<KeyValueElementListType> udpLists = udpListConverter.convert();
        for (KeyValueElementListType keyValueElementListType : udpLists) {
            String useName = keyValueElementListType.getLookupName();
            if (configuration.containsKey("diffname.".concat(useName))) {
                keyValueElementListType.setLookupName(configuration.getString("diffname.".concat(useName)));
            }
            handleUdp(keyValueElementListType.getLookupName(), keyValueElementListType);
        }
        finish();
    }

}
