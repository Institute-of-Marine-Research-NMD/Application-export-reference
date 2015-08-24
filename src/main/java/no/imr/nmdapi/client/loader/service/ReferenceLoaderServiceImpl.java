package no.imr.nmdapi.client.loader.service;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import no.imr.nmdapi.client.loader.convert.AcousticCategoryConverter;
import no.imr.nmdapi.client.loader.convert.EquipmentConverter;
import no.imr.nmdapi.client.loader.convert.InstitutionConverter;
import no.imr.nmdapi.client.loader.convert.LanguageConverter;
import no.imr.nmdapi.client.loader.convert.MissionTypeConverter;
import no.imr.nmdapi.client.loader.convert.NationConverter;
import no.imr.nmdapi.client.loader.convert.PlatformConverter;
import no.imr.nmdapi.client.loader.convert.SeaAreasConverter;
import no.imr.nmdapi.client.loader.convert.TaxaConverter;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementation of the reference loader service. This implementation generates
 * the reference xml files based on data currently in the database
 *
 * @author sjurl
 */
@Service(value = "referenceLoaderService")
public class ReferenceLoaderServiceImpl implements ReferenceLoaderServiceInterface {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(ReferenceLoaderServiceImpl.class);

    @Autowired
    private org.apache.commons.configuration.Configuration config;

    @Autowired
    private PlatformConverter platformConverter;

    @Autowired
    private TaxaConverter taxaConverter;

    @Autowired
    private AcousticCategoryConverter acousticCategoryConverter;

    @Autowired
    private EquipmentConverter equipmentConverter;

    @Autowired
    private InstitutionConverter institutionConverter;

    @Autowired
    private LanguageConverter languageConverter;

    @Autowired
    private MissionTypeConverter missionTypeConverter;

    @Autowired
    private NationConverter nationConverter;

    @Autowired
    private SeaAreasConverter seaAreasConverter;

    @Override
    public void loadReferenceToXml() {
        File baseDirectory = new File(config.getString("file.location"));
        if (!baseDirectory.exists()) {
            baseDirectory.mkdirs();
        }

        writeToFile(platformConverter.getPlatformList(), new File(baseDirectory.getAbsolutePath().concat("/platform.xml")));
        LOGGER.info("FINISHED with platforms!");

        writeToFile(taxaConverter.generateTaxaJaxBData(), new File(baseDirectory.getAbsolutePath().concat("/taxa.xml")));
        LOGGER.info("FINISHED with taxa!");

        writeToFile(acousticCategoryConverter.generateAcousticCategoryListType(), new File(baseDirectory.getAbsolutePath().concat("/acousticCategory.xml")));
        LOGGER.info("FINISHED with acoustic category!");

        writeToFile(equipmentConverter.generateEquipmentElementListType(), new File(baseDirectory.getAbsolutePath().concat("/equipment.xml")));
        LOGGER.info("FINISHED with equipment!");

        writeToFile(institutionConverter.getInstitutionElementListType(), new File(baseDirectory.getAbsolutePath().concat("/institution.xml")));
        LOGGER.info("FINISHED with institution!");

        writeToFile(languageConverter.getLanguageElementList(), new File(baseDirectory.getAbsolutePath().concat("/language.xml")));
        LOGGER.info("FINISHED with language!");

        writeToFile(missionTypeConverter.generateMissionTypeElementList(), new File(baseDirectory.getAbsolutePath().concat("/missiontype.xml")));
        LOGGER.info("FINISHED with mission types!");

        writeToFile(nationConverter.getNationElementListType(), new File(baseDirectory.getAbsolutePath().concat("/nation.xml")));
        LOGGER.info("FINISHED with nation!");

        writeToFile(seaAreasConverter.getSeaAreasElementListType(), new File(baseDirectory.getAbsolutePath().concat("/seaareas.xml")));
        LOGGER.info("FINISHED with nation!");
    }

    private void writeToFile(Object taxaList, File file) {
        try {
            JAXBContext ctx = JAXBContext.newInstance("no.imr.nmdapi.generic.nmdreference.domain.v1");
            Marshaller marshaller = ctx.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(taxaList, file);
        } catch (JAXBException ex) {
            Logger.getLogger(ReferenceLoaderServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
