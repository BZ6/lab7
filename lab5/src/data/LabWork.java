package data;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * LabWork class
 */
public class LabWork implements Collectionable{
    private Integer id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.util.Date creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Integer minimalPoint; //Поле не может быть null, Значение поля должно быть больше 0
    private int personalQualitiesMinimum; //Значение поля должно быть больше 0
    private Double averagePoint; //Поле может быть null, Значение поля должно быть больше 0
    private Difficulty difficulty; //Поле не может быть null
    private Discipline discipline; //Поле не может быть null

    /**
     * constructor, just set fields
     * @param name
     * @param coordinates
     * @param minimalPoint
     * @param personalQualitiesMinimum
     * @param averagePoint
     * @param difficulty
     * @param discipline
     */
    public LabWork(String name, Coordinates coordinates, Integer minimalPoint, int personalQualitiesMinimum, Double averagePoint, Difficulty difficulty, Discipline discipline){
        creationDate = new java.util.Date();

        this.name = name;
        this.coordinates = coordinates;
        this.minimalPoint = minimalPoint;
        this.personalQualitiesMinimum = personalQualitiesMinimum;
        this.averagePoint = averagePoint;
        this.difficulty = difficulty;
        this.discipline = discipline;
    }


    /**
     * @return int
     */
    public int getId(){
        return id;
    }


    /**
     * sets ID, usefull for replacing elements in collection
     * @param ID
     */
    public void setId(int ID){
        id = ID;
    }


    /**
     * @return String
     */
    public String getName(){
        return name;
    }


    /**
     * @return Integer
     */
    public Integer getMinimalPoint(){
        return minimalPoint;
    }


    /**
     * @return int
     */
    public int getPersonalQualitiesMinimum(){
        return personalQualitiesMinimum;
    }


    /**
     * @return Double
     */
    public Double getAveragePoint(){
        return averagePoint;
    }

    /**
     * @return Double
     */
    public Discipline getDiscipline(){
        return discipline;
    }

    /**
     * @return String
     */
    @Override
    public String toString(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String strCreationDate = dateFormat.format(creationDate);
        String s = "";

        s += "{\n";
        s += "  \"id\" : " + Integer.toString(id) + ",\n";
        s += "  \"name\" : " + "\"" + name + "\"" + ",\n";
        s += "  \"coordinates\" : " + coordinates.toString() + ",\n";
        s += "  \"creationDate\" : " + "\"" + strCreationDate + "\"" + ",\n";
        s += "  \"minimalPoint\" : " + Integer.toString(minimalPoint) + ",\n";
        s += "  \"personalQualitiesMinimum\" : " + Integer.toString(personalQualitiesMinimum) + ",\n";
        s += "  \"averagePoint\" : " + Double.toString(averagePoint) + ",\n";
        s += "  \"difficulty\" : " + "\"" + difficulty.toString() + "\"" + ",\n";
        s += "  \"discipline\" : " + discipline.toString() + "\n";
        s += "}";
        return s;
    }


    /**
     * @param obj
     * @return boolean
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null || this.getClass()!= obj.getClass()) return false;
        LabWork another = (LabWork)obj;
        return this.getId() == another.getId();
    }


    /**
     * @param labWork
     * @return int
     */
    public int compareTo(Collectionable labWork) {
        int res = Long.compare(this.id, labWork.getId());
        return res;
    }


    /**
     * @return boolean
     */
    public boolean validate(){
        return (
            coordinates!=null && coordinates.validate() &&
            discipline!=null && discipline.validate() &&
            (personalQualitiesMinimum>0) &&
            (averagePoint==null || (averagePoint>0)) &&
            minimalPoint!=null && (minimalPoint>0) &&
            id!=null && (id>0) &&
            name!=null && !name.equals("") &&
            difficulty!=null &&
            creationDate!=null
        );
    }
}
