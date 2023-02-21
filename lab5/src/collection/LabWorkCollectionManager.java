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
    private java.time.LocalDateTime initDate;
    private HashSet<Integer> uniqueIds;
    /**
     * Constructor, set start values
     */
    public LabWorkCollectionManager()
    {
        uniqueIds = new HashSet<>();
        collection = new Stack<>();
        initDate = java.time.LocalDateTime.now();
    }

    public int generateNextId(){
        if (collection.isEmpty())
            return 1;
        else {
            Integer id = collection.peek().getId() + 1;
            if(uniqueIds.contains(id)){
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
     * @param ID ID
     * @return is it used or not
     */
    public boolean checkID(Integer ID){
        for (LabWork labWork: collection)
            if (labWork.getId() == ID)
                return true;
        return false;
    }

    /**
     * Delete element by ID
     * @param id ID
     */
    public void removeByID(Integer id){
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
     * @param id ID
     */
    public void updateByID(Integer id, LabWork newLabWork){
        int idx = 0;
        for (LabWork labWork : collection){
            if (labWork.getId() == id){
                newLabWork.setId(id);
                collection.set(idx, newLabWork);
                print("element #"+Integer.toString(id)+" successfully updated");
                return;
            }
            idx += 1;
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
     * Add if ID of element bigger then max in collection
     * @param newLabWork Element
     */
    public void addIfMax(LabWork newLabWork){
        for (LabWork labWork : collection){
            if (newLabWork.compareTo(labWork)==-1){
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
            if (newLabWork.compareTo(labWork)==1){
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
        for (LabWork labWork : collection){
            if (labWork.getName().startsWith(start.trim())){
                list.add(labWork);
            }
        }
        if (list.isEmpty()) print("none of elements have name which starts with " + start);
        else{
            print("starts with: " + start);
            for (LabWork labWork: list){
                print(labWork.toString());
            }
        }
    }


    public boolean deserializeCollection(String json){
        boolean success = true;
        try {
            if (json == null || json.equals("")){
                collection =  new Stack<LabWork>();
            } else {
                Type collectionType = new TypeToken<Stack<LabWork>>(){}.getType();
                Gson gson = new GsonBuilder()
                        .registerTypeAdapter(LocalDate.class, new LocalDateDeserializer())
                        .registerTypeAdapter(Date.class, new DateDeserializer())
                        .registerTypeAdapter(collectionType, new CollectionDeserializer(uniqueIds))
                        .create();
                collection = gson.fromJson(json.trim(), collectionType);
            }
        } catch (JsonParseException e){
            success = false;
            printErr("wrong json data");
        }
        return success;

    }

    public String serializeCollection(){
        if (collection == null || collection.isEmpty()) return "";
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateSerializer())
                .registerTypeAdapter(Date.class, new DateSerializer())
                .setPrettyPrinting().create();
        String json = gson.toJson(collection);
        return json;
    }
}