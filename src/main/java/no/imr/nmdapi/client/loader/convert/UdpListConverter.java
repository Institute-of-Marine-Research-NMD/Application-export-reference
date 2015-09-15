package no.imr.nmdapi.client.loader.convert;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import no.imr.commons.nmdreference.domain.v1.KeyValueElementListType;
import no.imr.commons.nmdreference.domain.v1.KeyValueElementType;
import no.imr.nmdapi.client.loader.dao.UDPListDAO;
import no.imr.nmdapi.client.loader.pojo.UDPList;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author sjurl
 */
public class UdpListConverter implements ConvertInterface {

    @Autowired
    private UDPListDAO udplistDAO;

    @Override
    public List<KeyValueElementListType> convert() {
        List<KeyValueElementListType> udplists = new ArrayList<>();

        List<UDPList> lists = udplistDAO.getUDPLists();
        for (UDPList uDPList : lists) {
            KeyValueElementListType keyValueList = new KeyValueElementListType();
            keyValueList.setDescription(uDPList.getDescription());
            keyValueList.setLookupName(uDPList.getUdpName());
            List<KeyValueElementType> values = udplistDAO.getKeyValueElements(uDPList.getId());
            keyValueList.getElement().addAll(values);
            keyValueList.setLastChanged(DateConverter.convertDate((Date) udplistDAO.getLastEdited(uDPList.getId())));
            udplists.add(keyValueList);
        }

        return udplists;
    }
}
