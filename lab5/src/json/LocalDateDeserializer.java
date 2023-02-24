package json;
import java.lang.reflect.Type;
import java.time.LocalDate;
import com.google.gson.*;

import exceptions.InvalidDateFormatException;
import utils.DateConverter;

/**
 * type adapter for json deserialization
 */
public class LocalDateDeserializer implements JsonDeserializer<LocalDate> {
    @Override
    public LocalDate deserialize(JsonElement json, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        try{
            return DateConverter.parseLocalDate(json.getAsJsonPrimitive().getAsString());
        }
        catch (InvalidDateFormatException e){
            throw new JsonParseException("invalid date format");
        }
    }
}