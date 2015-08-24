package no.imr.nmdapi.client.loader.convert;

import java.util.List;
import no.imr.nmdapi.client.loader.dao.PlatformDAO;
import no.imr.nmdapi.generic.nmdreference.domain.v1.PlatformElementListType;
import no.imr.nmdapi.generic.nmdreference.domain.v1.PlatformElementType;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * convert platforms into PlatformElementListType
 *
 * @author sjurl
 */
public class PlatformConverter {

    @Autowired
    private PlatformDAO platformDAO;

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
        return platformList;
    }
}
