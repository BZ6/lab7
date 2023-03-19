package utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import exceptions.InvalidDateFormatException;
/**
 * Provides methods to convenient conversion between String and Date
 */
public class DateConverter {
    private static DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * convert Date to String
     * @param date
     * @return
     */
    public static String dateToString(Date date){
        return dateFormatter.format(date);
    }

    /**
     * convert Date to String
     * @param s
     * @return String
     * @throws InvalidDateFormatException
     */
    public static Date parseDate(String s) throws InvalidDateFormatException{
        try{
            return dateFormatter.parse(s);
        }
        catch (ParseException e){
            throw new InvalidDateFormatException();
        }
    }
}