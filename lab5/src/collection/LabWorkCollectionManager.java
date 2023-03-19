package collection;


import java.util.*;
import java.lang.reflect.Type;
import java.time.LocalDate;

import data.LabWork;
import json.*;

import static io.OutputManager.*;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;


/**
 * Operates collection.
 */
public class LabWorkCollectionManager implements CollectionManager<LabWork>{
    private Stack<LabWork> collection;
    private final java.time.LocalDateTime initDate;
    private final HashSet<Integer> uniqueIds;
    private static final Integer THE_FIRST_ID = 1;

    /**
     * Constructor, set start values
     */
    public LabWorkCollectionManager()
    {
        uniqueIds = new HashSet<>();
        collection = new Stack<>();
        initDate = java.time.LocalDateTime.now();
    }

    public Integer generateNextId(){

        if (collection.isEmpty())
            return THE_FIRST_ID;
        else {
            Integer id = collection.peek().getId() == Integer.MAX_VALUE ?
                    THE_FIRST_ID :
                    collection.peek().getId() + 1;
            if (uniqueIds.contains(id)){
                while (uniqueIds.contains(id)) id+=1;
            }
            uniqueIds.add(id);
            return id;
        }
    }

    public void sort(){
        Collections.sort(collection);
    }

    /**
     * Return collection
     * @return Collection
     */
    public Stack<LabWork> getCollection()
    {
        return collection;
    }

    /**
     * Add element to collection
     * @param labWork Element of collection
     */
    public void add(LabWork labWork){
        labWork.setId(generateNextId());
        collection.push(labWork);
        print("Added element:");
        print(labWork.toString());
    }


    /**
     * Get information about collection
     * @return Information
     */
    public String getInfo(){
        return "Stack of LabWork, size: " + Integer.toString(collection.size()) + ", initialization date: " + initDate.toString();
    }

    /**
     * Give info about is this ID used
     * @param id
     * @return is it used or not
     */
    public boolean checkId(Integer id){
        for (LabWork labWork: collection)
            if (labWork.getId() == id)
                return true;
        return false;
    }

    /**
     * Delete element by ID
     * @param id
     */
    public void removeByID(Integer id){
        if (checkId(id))
            for (LabWork labWork : collection){
                if (labWork.getId() == id){
                    collection.remove(labWork);
                    uniqueIds.remove(id);
                    print("element #"+Integer.toString(id)+" successfully deleted");
                    return;
                }
            }
    }

    /**
     * Delete element by ID
     * @param id
     */
    public void updateByID(Integer id, LabWork newLabWork){
        int currentId = THE_FIRST_ID;
        for (LabWork labWork : collection){
            if (labWork.getId() == id){
                newLabWork.setId(id);
                collection.set(currentId, newLabWork);
                print("element #"+Integer.toString(id)+" successfully updated");
                return;
            }
            currentId += 1;
        }
    }

    /**
     * Get size of collection
     * @return Size of collection
     */
    public int getSize(){
        return collection.size();
    }


    public void clear(){
        collection.clear();
        uniqueIds.clear();
    }

    /**
     * Add if ID of element bigger than max in collection
     * @param newLabWork Element
     */
    public void addIfMax(LabWork newLabWork){
        for (LabWork labWork : collection){
            if (newLabWork.compareTo(labWork) < 0){
                printErr("unable to add");
                return;
            }
        }
        add(newLabWork);
    }

    /**
     * Remove elements in collection if ID of elements smaller then that
     * @param newLabWork Element
     */
    public void removeLower(LabWork newLabWork){
        for (LabWork labWork : collection){
            if (newLabWork.compareTo(labWork) > 1){
                collection.remove(labWork);
            }
        }
    }

    /**
     * Print if field personalQualitiesMinimum is min in collection
     */
    public void minByPersonalQualitiesMinimum(){
        print(collection.stream()
                .min(Comparator.comparing(LabWork::getPersonalQualitiesMinimum))
                .get());
    }

    /**
     * Print if field discipline is max in collection
     */
    public void maxByDiscipline(){
        print(collection.stream()
                .max(Comparator.comparingInt(value -> value.getDiscipline().getLectureHours()))
                .get());
    }

    public void printStartsWithName(String start){
        LinkedList<LabWork> list = new LinkedList<>();
        boolean isEmpty = true;
        for (LabWork labWork : collection){
            if (labWork.getName().startsWith(start.trim())){
                isEmpty = false;
                print(labWork.toString());
            }
        }
        if (isEmpty) print("none of elements have name which starts with " + start);
    }


    public boolean deserializeCollection(String jsonData){
        boolean success = true;
        try {
            if (jsonData == null || jsonData.equals("")){
                collection =  new Stack<LabWork>();
            } else {
                Type collectionType = new TypeToken<Stack<LabWork>>(){}.getType();
                Gson collectionData = new GsonBuilder()
                        .registerTypeAdapter(Date.class, new DateDeserializer())
                        .registerTypeAdapter(collectionType, new CollectionDeserializer(uniqueIds))
                        .create();
                collection = collectionData.fromJson(jsonData.trim(), collectionType);
            }
        } catch (JsonParseException e){
            success = false;
            printErr("wrong json data");
        }
        return success;
    }

    public String serializeCollection(){
        if (collection == null || collection.isEmpty()) return "";
        Gson collectionData = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateSerializer())
                .registerTypeAdapter(Date.class, new DateSerializer())
                .setPrettyPrinting().create();
        String jsonData = collectionData.toJson(collection);
        return jsonData;
    }
}
