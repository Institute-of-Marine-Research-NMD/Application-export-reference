package no.imr.nmdapi.client.loader.dao;

import java.sql.Date;
import java.util.List;
import javax.sql.DataSource;
import no.imr.commons.nmdreference.domain.v1_0.PlatformElementType;
import no.imr.nmdapi.client.loader.mapper.DateMapper;
import no.imr.nmdapi.client.loader.mapper.PlatformCodeMapper;
import no.imr.nmdapi.client.loader.mapper.PlatformElementTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Data access object for Platforms
 *
 * @author sjurl
 */
public class PlatformDAO {

    private JdbcTemplate jdbcTemplate;

    private static final String QUERY = "select p.id, p.platform, pt.platformtype, na.nation, na.nationname, ty.platformtype, ty.platformsubtype, ty.platformtypename, ty.platformsubtypename\n"
            + "from nmdreference.platform p, nmdreference.platformtype pt, nmdreference.nation na, nmdreference.platformtype ty\n"
            + "where p.id_platformtype = pt.id and p.id_nation = na.id and p.id_platformtype = ty.id";

    private static final String GET_PLATFORM_CODES_FOR_PLATFORM = "Select pc.firstvaliddate, pc.lastvaliddate, pc.platformcode, pcs.platformcodesys, pcs.platformcodesysname"
            + " from nmdreference.platformcode pc, nmdreference.platformcodesys pcs"
            + " where pc.id_platformcodesys = pcs.id and pc.id_platform = ?";

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /**
     * returns a list of platforms
     *
     * @return
     */
    public List<PlatformElementType> processPlatforms() {
        return jdbcTemplate.query(QUERY, new PlatformElementTypeMapper());
    }

    /**
     * returns a list of platform codes for the platform matching the provided
     * id
     *
     * @param id
     * @return
     */
    public List<PlatformElementType.PlatformCodes.PlatformCode> getPlatformCodesForPlatform(String id) {
        return jdbcTemplate.query(GET_PLATFORM_CODES_FOR_PLATFORM, new PlatformCodeMapper(), id);
    }

    /**
     * Get the last changed date for all platforms
     *
     * @return
     */
    public Date getLastChanged() {
        return (Date) jdbcTemplate.query("select max(a) as last_edited from (select max(plat.last_edited) as a from nmdreference.platform plat union select max(code.last_edited) as b from nmdreference.platformcode code) n", new DateMapper()).get(0);
    }

}
