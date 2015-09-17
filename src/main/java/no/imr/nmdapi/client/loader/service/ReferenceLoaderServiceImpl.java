package no.imr.nmdapi.client.loader.service;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
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
import no.imr.nmd.commons.dataset.jaxb.QualityEnum;
import no.imr.nmd.commons.dataset.jaxb.RestrictionsType;
import no.imr.nmdapi.client.loader.convert.ConvertInterface;
import org.apache.commons.io.FileUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Implementation of the reference loader service. This implementation generates
 * the reference xml files based on data currently in the database
 *
 * @author sjurl
 */
@Service(value = "referenceLoaderService")
public abstract class ReferenceLoaderServiceImpl {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(ReferenceLoaderServiceImpl.class);

    @Autowired
    @Qualifier("referenceConfig")
    protected org.apache.commons.configuration.Configuration configuration;

    private File baseDirectory;
    private DatasetsType datasets;
    private static final String DATASET_FILE_NAME = File.separator.concat("data.xml");

    protected void init() {
        baseDirectory = new File(configuration.getString("file.location"));
        if (!baseDirectory.exists()) {
            baseDirectory.mkdirs();
        }
        File datsetFile = new File(baseDirectory.getAbsolutePath().concat(DATASET_FILE_NAME));
        if (!datsetFile.exists()) {
            DatasetsType dtype = new DatasetsType();
            marshallDatasets(datsetFile.getAbsolutePath(), dtype);
        }
        datasets = unmarshallDatasets(baseDirectory.getAbsolutePath().concat(DATASET_FILE_NAME));
    }

    protected void finish() {
        marshallDatasets(baseDirectory.getAbsolutePath().concat(DATASET_FILE_NAME), datasets);
    }

    /**
     * Method that must be implemented that should load data from database to
     * xml
     */
    public abstract void loadReferenceToXml();

    protected synchronized void handle(String datasetName, ConvertInterface ci) {
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
                LOGGER.error("Error working on table " + datasetName, ex);
            }
        } else if (newFile.exists() && !oldFile.exists()) {
            try {
                FileUtils.copyFile(newFile, oldFile);
                updateUpdatedTime(datasets, datasetName);
            } catch (IOException ex) {
                LOGGER.error("Unable to write file " + oldFile.getAbsolutePath(), ex);
            }
        }
        newFile.delete();
        LOGGER.info("FINISHED with " + datasetName);
    }

    protected void handleUdp(String datasetName, KeyValueElementListType elementListType) {
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
                LOGGER.error("Error working on table ".concat(datasetName), ex);
            }
        } else if (newFile.exists() && !oldFile.exists()) {
            try {
                FileUtils.copyFile(newFile, oldFile);
                updateUpdatedTime(datasets, datasetName);
            } catch (IOException ex) {
                LOGGER.error("Unable to write file " + oldFile.getAbsolutePath(), ex);
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
                return (DatasetsType) ((JAXBElement) obj).getValue();
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
            boolean exists = false;
            for (DatasetType datasetType : datasets.getDataset()) {
                if (datasetType.getDatasetName().equalsIgnoreCase(name)) {
                    exists = true;
                    datasetType.setUpdated(date);
                }
            }
            if (!exists) {
                DatasetType dataset = new DatasetType();
                dataset.setId("no:imr:reference:".concat(java.util.UUID.randomUUID().toString()));
                dataset.setDataType("reference");
                dataset.setDatasetName(name);
                dataset.setOwner("imr");
                RestrictionsType restrictionsType = new RestrictionsType();
                restrictionsType.setRead("unrestricted");
                restrictionsType.setWrite("SG-NMDREFERENCE-WRITE");
                dataset.setRestrictions(restrictionsType);
                dataset.setQualityAssured(QualityEnum.NONE);
                dataset.setUpdated(date);
                dataset.setCreated(date);
                datasets.getDataset().add(dataset);
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
