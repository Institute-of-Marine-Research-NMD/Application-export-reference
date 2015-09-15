package no.imr.nmdapi.client.loader.convert;

import java.sql.Date;
import java.util.GregorianCalendar;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import org.slf4j.LoggerFactory;

/**
 * Utility class for converting date objects into XMLGregorianCalendar objects
 *
 * @author sjurl
 */
public class DateConverter {

    private DateConverter() {
    }

    public static XMLGregorianCalendar convertDate(Date date) {
        XMLGregorianCalendar result = null;
        if (date != null) {

            GregorianCalendar cal = new GregorianCalendar();
            cal.setTime(date);

            try {
                result = DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
            } catch (DatatypeConfigurationException ex) {
                LoggerFactory.getLogger(DateConverter.class).error("Unable to convert date: " + date.toString(), ex);
            }

        }
        return result;

    }
}
