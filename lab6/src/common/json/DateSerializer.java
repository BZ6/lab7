package json;
import java.lang.reflect.Type;
import java.util.Date;
import com.google.gson.*;
import utils.DateConverter;

/**
 * type adapter for json serialization
 */
public class DateSerializer implements JsonSerializer<Date>{
    @Override
    public JsonElement serialize(final Date date, Type srcType, JsonSerializationContext context) {
        return new JsonPrimitive(DateConverter.dateToString(date));
    }
}