package no.imr.nmdapi.client.loader.config;

import javax.sql.DataSource;
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
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class containing the datasources
 *
 * @author sjurl
 */
@Configuration
public class PersistenceConfig {

    @Autowired
    @Qualifier("persistanceConfig")
    private org.apache.commons.configuration.Configuration configuration;

    /**
     * Datasource bean
     *
     * @return
     */
    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();

        dataSource.setDriverClassName(configuration.getString("jdbc.driver"));
        dataSource.setUrl(configuration.getString("jdbc.url"));
        dataSource.setUsername(configuration.getString("jdbc.user"));
        dataSource.setPassword(configuration.getString("jdbc.password"));
        dataSource.setPassword(configuration.getString("jdbc.password"));

        return dataSource;
    }

    /**
     * Platform data access object
     *
     * @return
     */
    @Bean
    public PlatformDAO platformDAO() {
        return new PlatformDAO();
    }

    /**
     * Taxa data access object
     *
     * @return
     */
    @Bean
    public TaxaDAO taxaDAO() {
        return new TaxaDAO();
    }

    /**
     * Acoustic category data access object
     *
     * @return
     */
    @Bean
    public AcousticCategoryDAO acousticCategoryDAO() {
        return new AcousticCategoryDAO();
    }

    /**
     * Equipment data access object
     *
     * @return
     */
    @Bean
    public EquipmentDAO equipmentDAO() {
        return new EquipmentDAO();
    }

    /**
     * Institution data access object
     *
     * @return
     */
    @Bean
    public InstitutionDAO institutionDAO() {
        return new InstitutionDAO();
    }

    /**
     * Language data access object
     *
     * @return
     */
    @Bean
    public LanguageDAO languageDAO() {
        return new LanguageDAO();
    }

    /**
     * Mission type data access object
     *
     * @return
     */
    @Bean
    public MissionTypeDAO missionTypeDAO() {
        return new MissionTypeDAO();
    }

    /**
     * Nation data access object
     *
     * @return
     */
    @Bean
    public NationDAO nationDAO() {
        return new NationDAO();
    }

    /**
     * Sea areas data access object
     *
     * @return
     */
    @Bean
    public SeaAreasDAO seaAreasDAO() {
        return new SeaAreasDAO();
    }

    /**
     * UDP list data access object
     *
     * @return
     */
    @Bean
    public UDPListDAO udpListDAO() {
        return new UDPListDAO();
    }

    /**
     * Person data access object
     *
     * @return
     */
    @Bean
    public PersonDAO personDAO() {
        return new PersonDAO();
    }
}
