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
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Utility configuration for providing converter beans
 *
 * @author sjurl
 */
@Configuration
public class ConverterConfig {

    @Bean
    public AcousticCategoryConverter acousticCategoryConverter() {
        return new AcousticCategoryConverter();
    }

    @Bean
    public PlatformConverter platformConverter() {
        return new PlatformConverter();
    }

    @Bean
    public TaxaConverter taxaConverter() {
        return new TaxaConverter();
    }

    @Bean
    public EquipmentConverter equipmentConverter() {
        return new EquipmentConverter();
    }

    @Bean
    public InstitutionConverter institutionConverter() {
        return new InstitutionConverter();
    }

    @Bean
    public LanguageConverter languageConverter() {
        return new LanguageConverter();
    }

    @Bean
    public MissionTypeConverter missionTypeConverter() {
        return new MissionTypeConverter();
    }

    @Bean
    public NationConverter nationConverter() {
        return new NationConverter();
    }

    @Bean
    public SeaAreasConverter seaAreasConverter() {
        return new SeaAreasConverter();
    }
}
