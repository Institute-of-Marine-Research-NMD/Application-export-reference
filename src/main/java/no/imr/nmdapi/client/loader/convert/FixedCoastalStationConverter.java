/*
 */
package no.imr.nmdapi.client.loader.convert;

import java.sql.Date;
import no.imr.commons.nmdreference.domain.v1_0.FixedCoastalstationElementListType;
import no.imr.nmdapi.client.loader.dao.FixedCoastalStationDAO;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author sjurl
 */
public class FixedCoastalStationConverter implements ConvertInterface {

    @Autowired
    private FixedCoastalStationDAO fixedStationDAO;

    @Override
    public Object convert() {
        FixedCoastalstationElementListType list = new FixedCoastalstationElementListType();
        list.getFixedCoastalstation().addAll(fixedStationDAO.getFixedCoastalStations());
        list.setLastChanged(DateConverter.convertDate((Date) fixedStationDAO.getLastChanged()));
        return list;
    }

}
