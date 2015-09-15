package no.imr.nmdapi.client.loader.config;

import no.imr.nmdapi.client.loader.convert.AcousticCategoryConverter;
import no.imr.nmdapi.client.loader.convert.EquipmentConverter;
import no.imr.nmdapi.client.loader.convert.InstitutionConverter;
import no.imr.nmdapi.client.loader.convert.LanguageConverter;
import no.imr.nmdapi.client.loader.convert.MissionTypeConverter;
import no.imr.nmdapi.client.loader.convert.NationConverter;
import no.imr.nmdapi.client.loader.convert.PlatformConverter;
import no.imr.nmdapi.client.loader.convert.SeaAreasConverter;
import no.imr.nmdapi.client.loader.convert.TaxaConverter;
import no.imr.nmdapi.client.loader.convert.UdpListConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Utility configuration for providing converter beans
 *
 * @author sjurl
 */
@Configuration
public class ConverterConfig {

    /**
     * AcousticCategoryConverter
     *
     * @return
     */
    @Bean
    public AcousticCategoryConverter acousticCategoryConverter() {
        return new AcousticCategoryConverter();
    }

    /**
     * PlatformConverter
     *
     * @return
     */
    @Bean
    public PlatformConverter platformConverter() {
        return new PlatformConverter();
    }

    /**
     * TaxaConverter
     *
     * @return
     */
    @Bean
    public TaxaConverter taxaConverter() {
        return new TaxaConverter();
    }

    /**
     * EquipmentConverter
     *
     * @return
     */
    @Bean
    public EquipmentConverter equipmentConverter() {
        return new EquipmentConverter();
    }

    /**
     * InstitutionConverter
     *
     * @return
     */
    @Bean
    public InstitutionConverter institutionConverter() {
        return new InstitutionConverter();
    }

    /**
     * LanguageConverter
     *
     * @return
     */
    @Bean
    public LanguageConverter languageConverter() {
        return new LanguageConverter();
    }

    /**
     * MissionTypeConverter
     *
     * @return
     */
    @Bean
    public MissionTypeConverter missionTypeConverter() {
        return new MissionTypeConverter();
    }

    /**
     * NationConverter
     *
     * @return
     */
    @Bean
    public NationConverter nationConverter() {
        return new NationConverter();
    }

    /**
     * SeaAreasConverter
     *
     * @return
     */
    @Bean
    public SeaAreasConverter seaAreasConverter() {
        return new SeaAreasConverter();
    }

    /**
     * UdpListConverter
     *
     * @return
     */
    @Bean
    public UdpListConverter udpListConverter() {
        return new UdpListConverter();
    }
}
