package no.imr.nmdapi.client.loader.convert;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import no.imr.nmdapi.client.loader.dao.UDPListDAO;
import no.imr.nmdapi.client.loader.pojo.UDPList;
import no.imr.nmdapi.generic.nmdreference.domain.v1.KeyValueElementListType;
import no.imr.nmdapi.generic.nmdreference.domain.v1.KeyValueElementType;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author sjurl
 */
public class UdpListConverter {

    @Autowired
    private UDPListDAO udplistDAO;

    public List<KeyValueElementListType> getUdpLists() {
        List<KeyValueElementListType> udplists = new ArrayList<>();

        List<UDPList> lists = udplistDAO.getUDPLists();
        for (UDPList uDPList : lists) {
            KeyValueElementListType keyValueList = new KeyValueElementListType();
            keyValueList.setDescription(uDPList.getDescription());
            keyValueList.setTableName(uDPList.getName());
            keyValueList.setLookupName(uDPList.getUdpName());
            List<KeyValueElementType> values = udplistDAO.getKeyValueElements(uDPList.getId());
            keyValueList.getElement().addAll(values);
            keyValueList.setLastChanged(DateConverter.convertDate((Date) udplistDAO.getLastEdited(uDPList.getId())));
            udplists.add(keyValueList);
        }

        return udplists;
    }
}
