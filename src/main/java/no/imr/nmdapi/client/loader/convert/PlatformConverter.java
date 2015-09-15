package no.imr.nmdapi.client.loader.convert;

import java.sql.Date;
import java.util.List;
import no.imr.commons.nmdreference.domain.v1.PlatformElementListType;
import no.imr.commons.nmdreference.domain.v1.PlatformElementType;
import no.imr.nmdapi.client.loader.dao.PlatformDAO;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * convert platforms into PlatformElementListType
 *
 * @author sjurl
 */
public class PlatformConverter {

    @Autowired
    private PlatformDAO platformDAO;

    /**
     * convert platforms into PlatformElementListType
     *
     * @return
     */
    public PlatformElementListType getPlatformList() {
        PlatformElementListType platformList = new PlatformElementListType();
        List<PlatformElementType> platformElementTypes = platformDAO.processPlatforms();
        for (PlatformElementType platformElementType : platformElementTypes) {
            List<PlatformElementType.PlatformCodes.PlatformCode> platformCodes = platformDAO.getPlatformCodesForPlatform(platformElementType.getId());
            PlatformElementType.PlatformCodes pc = new PlatformElementType.PlatformCodes();
            pc.getPlatformCode().addAll(platformCodes);
            platformElementType.setPlatformCodes(pc);
            platformList.getPlatform().add(platformElementType);
        }

        platformList.setLastChanged(DateConverter.convertDate((Date) platformDAO.getLastChanged()));
        return platformList;
    }
}
