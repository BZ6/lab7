package json;
import java.lang.reflect.Type;
import java.time.LocalDate;
import com.google.gson.*;
import utils.DateConverter;

/**
 * type adapter for json serialization
 */
public class LocalDateSerializer implements JsonSerializer<LocalDate>{
    @Override
    public JsonElement serialize(LocalDate localDate, Type srcType, JsonSerializationContext context) {
        return new JsonPrimitive(DateConverter.dateToString(localDate));
    }
}