package collection;
import data.LabWork;

import java.util.Stack;

/**
 * interface for storing elements
 * @param <T> type of elements
 */
public interface CollectionManager<T> {
    /**
     * generates new unique ID for collection
     * @return
     */
    public Integer generateNextId();

    public void sort();

    public Stack<T> getCollection();

    /**
     * adds new element
     * @param element
     */
    public void add(T element);

    /**
     * get information about collection
     * @return info
     */
    public String getInfo();

    /**
     * checks if collection contains element with particular id
     * @param ID
     * @return
     */
    public boolean checkID(Integer ID);

    /**
     * removes element by id
     * @param id
     */
    public void removeByID(Integer id);

    /**
     * updates element by id
     * @param id
     * @param newElement
     */
    public void updateByID(Integer id, T newElement);

    /**
     * get collection size
     * @return
     */
    public int getSize();

    public void clear();

    /**
     * adds element if it is greater than max
     * @param element
     */
    public void addIfMax(T element);

    /**
     * Remove elements in collection if ID of elements smaller then that
     * @param newLabWork Element
     */
    public void removeLower(LabWork newLabWork);

    /**
     * Print if field personalQualitiesMinimum is min in collection
     */
    public void minByPersonalQualitiesMinimum();

    /**
     * Print if field discipline is max in collection
     */
    public void maxByDiscipline();

    /**
     * print all elements which name starts with substring
     * @param start
     */
    public void printStartsWithName(String start);

    /**
     * convert collection to json
     * @param json
     * @return true if success, false if not
     */
    public boolean deserializeCollection(String json);

    /**
     * parse collection from json
     * @return
     */
    public String serializeCollection();
}