package no.imr.nmdapi.client.loader.pojo;

import java.sql.Date;

/**
 *
 * @author sjurl
 */
public class SpesialstadieLists {

    private Integer sexdependent;
    private String name;
    private String udpname;
    private String id;
    private Date validFrom;
    private Date validTo;

    /**
     * @return the sexdependent
     */
    public Integer getSexdependent() {
        return sexdependent;
    }

    /**
     * @param sexdependent the sexdependent to set
     */
    public void setSexdependent(Integer sexdependent) {
        this.sexdependent = sexdependent;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    public String getUdpname() {
        return udpname;
    }

    public void setUdpname(String udpname) {
        this.udpname = udpname;
    }

    public void setValidFrom(Date validFrom) {
        this.validFrom = validFrom;
    }

    public void setValidTo(Date validTo) {
        this.validTo = validTo;
    }

    public Date getValidFrom() {
        return validFrom;
    }

    public Date getValidTo() {
        return validTo;
    }

    
}
