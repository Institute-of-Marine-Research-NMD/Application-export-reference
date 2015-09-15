package no.imr.nmdapi.client.loader.service;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import no.imr.commons.nmdreference.domain.v1.KeyValueElementListType;
import no.imr.nmd.commons.dataset.jaxb.DatasetType;
import no.imr.nmd.commons.dataset.jaxb.DatasetsType;
import no.imr.nmdapi.client.loader.convert.AcousticCategoryConverter;
import no.imr.nmdapi.client.loader.convert.ConvertInterface;
import no.imr.nmdapi.client.loader.convert.EquipmentConverter;
import no.imr.nmdapi.client.loader.convert.InstitutionConverter;
import no.imr.nmdapi.client.loader.convert.LanguageConverter;
import no.imr.nmdapi.client.loader.convert.MissionTypeConverter;
import no.imr.nmdapi.client.loader.convert.NationConverter;
import no.imr.nmdapi.client.loader.convert.PersonConverter;
import no.imr.nmdapi.client.loader.convert.PlatformConverter;
import no.imr.nmdapi.client.loader.convert.SeaAreasConverter;
import no.imr.nmdapi.client.loader.convert.TaxaConverter;
import no.imr.nmdapi.client.loader.convert.UdpListConverter;
import no.imr.nmdapi.client.loader.dao.AcousticCategoryDAO;
import no.imr.nmdapi.client.loader.dao.EquipmentDAO;
import no.imr.nmdapi.client.loader.dao.InstitutionDAO;
import no.imr.nmdapi.client.loader.dao.LanguageDAO;
import no.imr.nmdapi.client.loader.dao.MissionTypeDAO;
import no.imr.nmdapi.client.loader.dao.NationDAO;
import no.imr.nmdapi.client.loader.dao.PersonDAO;
import no.imr.nmdapi.client.loader.dao.PlatformDAO;
import no.imr.nmdapi.client.loader.dao.SeaAreasDAO;
import no.imr.nmdapi.client.loader.dao.TaxaDAO;
import no.imr.nmdapi.client.loader.dao.UDPListDAO;
import org.apache.commons.io.FileUtils;
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
    private PlatformDAO platformDAO;

    @Autowired
    private TaxaConverter taxaConverter;

    @Autowired
    private TaxaDAO taxaDAO;

    @Autowired
    private AcousticCategoryConverter acousticCategoryConverter;

    @Autowired
    private AcousticCategoryDAO acousticCategoryDAO;

    @Autowired
    private EquipmentConverter equipmentConverter;

    @Autowired
    private EquipmentDAO equipmentDAO;

    @Autowired
    private InstitutionConverter institutionConverter;

    @Autowired
    private InstitutionDAO institutionDAO;

    @Autowired
    private LanguageConverter languageConverter;

    @Autowired
    private LanguageDAO languageDAO;

    @Autowired
    private MissionTypeConverter missionTypeConverter;

    @Autowired
    private MissionTypeDAO missionTypeDAO;

    @Autowired
    private NationConverter nationConverter;

    @Autowired
    private NationDAO nationDAO;

    @Autowired
    private SeaAreasConverter seaAreasConverter;

    @Autowired
    private SeaAreasDAO seaAreasDAO;

    @Autowired
    private UdpListConverter udpListConverter;

    @Autowired
    private UDPListDAO udpListDAO;

    @Autowired
    private PersonConverter personConverter;

    @Autowired
    private PersonDAO personDAO;

    @Override
    public void loadReferenceToXml() {
        File baseDirectory = new File(config.getString("file.location"));
        if (!baseDirectory.exists()) {
            baseDirectory.mkdirs();
        }
        DatasetsType datasets = unmarshallDatasets(baseDirectory.getAbsolutePath().concat("/data.xml"));

        handle(baseDirectory, datasets, "platform", platformConverter);

        handle(baseDirectory, datasets, "taxa", taxaConverter);

        handle(baseDirectory, datasets, "acousticcategory", acousticCategoryConverter);

        handle(baseDirectory, datasets, "equipment", equipmentConverter);

        handle(baseDirectory, datasets, "institution", institutionConverter);

        handle(baseDirectory, datasets, "language", languageConverter);

        handle(baseDirectory, datasets, "missiontype", missionTypeConverter);

        handle(baseDirectory, datasets, "nation", nationConverter);

        handle(baseDirectory, datasets, "person", personConverter);

        handle(baseDirectory, datasets, "seaareas", seaAreasConverter);

        List<KeyValueElementListType> udpLists = udpListConverter.convert();
        for (KeyValueElementListType keyValueElementListType : udpLists) {
            String useName = keyValueElementListType.getLookupName();
            if (config.containsKey("diffname.".concat(useName))) {
                keyValueElementListType.setLookupName(config.getString("diffname.".concat(useName)));
            }
            handleUdp(baseDirectory, datasets, keyValueElementListType.getLookupName(), keyValueElementListType);
        }
        LOGGER.info("FINISHED with udp lists!");
        marshallDatasets(baseDirectory.getAbsolutePath().concat("/data.xml"), datasets);
    }

    private void handle(File baseDirectory, DatasetsType datasets, String datasetName, ConvertInterface ci) {
        File newFile = new File(FileUtils.getTempDirectory().getAbsolutePath().concat("/").concat(datasetName));
        File oldFile = new File(baseDirectory.getAbsolutePath().concat("/").concat(datasetName).concat("/").concat(datasetName).concat(".xml"));
        writeToFile(ci.convert(), newFile);
        if (newFile.exists() && oldFile.exists()) {
            try {
                if (FileUtils.checksumCRC32(oldFile) != FileUtils.checksumCRC32(newFile)) {
                    FileUtils.copyFile(newFile, oldFile);
                    updateUpdatedTime(datasets, datasetName);
                }
            } catch (IOException ex) {
                LOGGER.error("Error working on table platform.");
            }
        }
        newFile.delete();
        LOGGER.info("FINISHED with platforms!");
    }

    private void handleUdp(File baseDirectory, DatasetsType datasets, String datasetName, KeyValueElementListType elementListType) {
        File newFile = new File(FileUtils.getTempDirectory().getAbsolutePath().concat("/").concat(datasetName));
        File oldFile = new File(baseDirectory.getAbsolutePath().concat("/").concat(datasetName).concat("/").concat(datasetName).concat(".xml"));
        writeToFile(elementListType, newFile);
        if (newFile.exists() && oldFile.exists()) {
            try {
                if (FileUtils.checksumCRC32(oldFile) != FileUtils.checksumCRC32(newFile)) {
                    FileUtils.copyFile(newFile, oldFile);
                    updateUpdatedTime(datasets, datasetName);
                }
            } catch (IOException ex) {
                LOGGER.error("Error working on table ".concat(datasetName));
            }
        }
        newFile.delete();
        LOGGER.info("FINISHED with ".concat(datasetName));
    }

    private void writeToFile(Object taxaList, File file) {
        try {
            JAXBContext ctx = JAXBContext.newInstance("no.imr.commons.nmdreference.domain.v1");
            Marshaller marshaller = ctx.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(taxaList, file);
        } catch (JAXBException ex) {
            Logger.getLogger(ReferenceLoaderServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private DatasetsType unmarshallDatasets(String file) {
        try {
            JAXBContext ctx = JAXBContext.newInstance("no.imr.nmd.commons.dataset.jaxb");
            Unmarshaller unmarshaller = ctx.createUnmarshaller();
            Object obj = unmarshaller.unmarshal(new File(file));
            if (obj instanceof JAXBElement) {
                return (DatasetsType) ((JAXBElement)obj).getValue();
            } else {
                return (DatasetsType) obj;
            }
        } catch (JAXBException ex) {
            Logger.getLogger(ReferenceLoaderServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private void updateUpdatedTime(DatasetsType datasets, String name) {
        try {
            GregorianCalendar c = new GregorianCalendar();
            c.setTime(Calendar.getInstance().getTime());
            XMLGregorianCalendar date = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
            for (DatasetType datasetType : datasets.getDataset()) {
                if (datasetType.getDatasetName().equalsIgnoreCase(name)) {
                    datasetType.setUpdated(date);
                }
            }
        } catch (DatatypeConfigurationException ex) {
            Logger.getLogger(ReferenceLoaderServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void marshallDatasets(String file, DatasetsType datasets) {
        try {
            JAXBContext ctx = JAXBContext.newInstance("no.imr.nmd.commons.dataset.jaxb");
            Marshaller marshaller = ctx.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(datasets, new File(file));
        } catch (JAXBException ex) {
            Logger.getLogger(ReferenceLoaderServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
