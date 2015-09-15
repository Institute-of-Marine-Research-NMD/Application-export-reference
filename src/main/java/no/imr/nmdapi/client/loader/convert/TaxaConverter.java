package no.imr.nmdapi.client.loader.convert;

import java.sql.Date;
import java.util.List;
import no.imr.commons.nmdreference.domain.v1.KeyValueElementType;
import no.imr.commons.nmdreference.domain.v1.RestrictionElementType;
import no.imr.commons.nmdreference.domain.v1.RestrictionsElementType;
import no.imr.commons.nmdreference.domain.v1.SexEnum;
import no.imr.commons.nmdreference.domain.v1.StockElementType;
import no.imr.commons.nmdreference.domain.v1.TaxaElementListType;
import no.imr.commons.nmdreference.domain.v1.TaxaElementType;
import no.imr.commons.nmdreference.domain.v1.TaxaListElementType;
import no.imr.commons.nmdreference.domain.v1.TaxaListsElementType;
import no.imr.nmdapi.client.loader.dao.TaxaDAO;
import no.imr.nmdapi.client.loader.pojo.SpesialstadieLists;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * convert taxas into TaxaElementListType
 *
 * @author sjurl
 */
public class TaxaConverter implements ConvertInterface {

    @Autowired
    private TaxaDAO taxaDAO;
    private static final int MALE_SEX = 2;
    private static final int FEMALE_SEX = 1;
    private static final int BOTH_SEXES = 0;

    /**
     * convert taxas into TaxaElementListType
     *
     * @return
     */
    @Override
    public TaxaElementListType convert() {
        TaxaElementListType taxaList = new TaxaElementListType();
        List<TaxaElementType> taxas = taxaDAO.getAllTaxa();
        for (TaxaElementType taxaElementType : taxas) {
            List<TaxaElementType.TaxaSynonyms.Synonym> synonyms = taxaDAO.processPlatforms(taxaElementType.getId());
            TaxaElementType.TaxaSynonyms tsyn = new TaxaElementType.TaxaSynonyms();
            tsyn.getSynonym().addAll(synonyms);
            taxaElementType.setTaxaSynonyms(tsyn);

            List<SpesialstadieLists> spesialstadier = taxaDAO.getListsForTaxa(taxaElementType.getId());
            TaxaListsElementType lists = new TaxaListsElementType();
            for (SpesialstadieLists spesialstadieLists : spesialstadier) {
                TaxaListElementType tlet = new TaxaListElementType();
                tlet.setName(spesialstadieLists.getName());
                switch (spesialstadieLists.getSexdependent()) {
                    case BOTH_SEXES:
                        tlet.setSex(SexEnum.BOTH);
                        break;
                    case FEMALE_SEX:
                        tlet.setSex(SexEnum.FEMALE);
                        break;
                    case MALE_SEX:
                        tlet.setSex(SexEnum.MALE);
                        break;
                    default:
                        break;
                }

                List<KeyValueElementType> keyvalues = taxaDAO.getKeyValueElements(spesialstadieLists.getId());
                tlet.getElement().addAll(keyvalues);
                lists.getList().add(tlet);
            }
            if (!lists.getList().isEmpty()) {
                taxaElementType.setLists(lists);
            }

            RestrictionsElementType ret = new RestrictionsElementType();
            List<RestrictionElementType> restrictions = taxaDAO.getRestrictionsForTaxa(taxaElementType.getId());
            ret.getRestriction().addAll(restrictions);
            if (!ret.getRestriction().isEmpty()) {
                taxaElementType.setRestrictions(ret);
            }

            TaxaElementType.Stocks st = new TaxaElementType.Stocks();
            List<StockElementType> stocks = taxaDAO.getStock(taxaElementType.getId());
            st.getStock().addAll(stocks);
            if (!st.getStock().isEmpty()) {
                taxaElementType.setStocks(st);
            }

            taxaList.getTaxa().add(taxaElementType);

            taxaList.setLastChanged(DateConverter.convertDate((Date) taxaDAO.getLastChanged()));
        }
        return taxaList;
    }

}
