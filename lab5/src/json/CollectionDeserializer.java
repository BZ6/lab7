package json;

import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;
import data.*;
import com.google.gson.*;
import static io.OutputManager.*;

/**
 * type adapter for json deserialization
 */
public class CollectionDeserializer implements JsonDeserializer<Stack<LabWork>>{
    private HashSet<Integer> uniqueIds;

    /**
     * constructor
     * @param uniqueIds set of ids. useful for generating new id
     */
    public CollectionDeserializer(HashSet<Integer> uniqueIds){
        this.uniqueIds = uniqueIds;
    }
    @Override
    public Stack<LabWork> deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
        Stack<LabWork> collection = new Stack<>();
        JsonArray labWorks = json.getAsJsonArray();
        int damagedElements = 0;
        for (JsonElement jsonLabWork: labWorks){
            LabWork labWork = null;
            try{
                if(jsonLabWork.getAsJsonObject().entrySet().isEmpty()){
                    printErr("empty labWork found");
                    throw new JsonParseException("empty LabWork");
                }
                if(!jsonLabWork.getAsJsonObject().has("id")) {
                    printErr("found labWork without id");
                    throw new JsonParseException("no id");
                }
                labWork = (LabWork) context.deserialize(jsonLabWork, LabWork.class);

                Integer id = labWork.getId();

                if(uniqueIds.contains(id)) {
                    printErr("database already contains LabWork with id #" + Integer.toString(id));
                    throw new JsonParseException("id isnt unique");
                }
                if(!labWork.validate()) {
                    printErr("labWork #"+Integer.toString(id) + " doesnt match specific conditions");
                    throw new JsonParseException("invalid labWork data");
                }
                uniqueIds.add(id);
                collection.add(labWork);
            } catch (JsonParseException e){
                damagedElements += 1;
            }
        }
        if(collection.size()==0){
            if(damagedElements == 0) printErr("database is empty");
            else  printErr("all elements in database are damaged");
            throw new JsonParseException("no data");
        }
        if (damagedElements != 0) printErr(Integer.toString(damagedElements) + " elements in database are damaged");
        return collection;
    }
}