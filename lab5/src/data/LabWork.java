package data;

import exceptions.InvalidFieldException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

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
    public LabWork(String name, Coordinates coordinates, Integer minimalPoint, int personalQualitiesMinimum, Double averagePoint, Difficulty difficulty, Discipline discipline) throws InvalidFieldException {
        if (
                coordinates == null || !coordinates.validate() ||
                discipline == null || !discipline.validate() ||
                (personalQualitiesMinimum <= 0) ||
                        (averagePoint != null && (averagePoint <= 0)) ||
                minimalPoint == null || (minimalPoint <= 0) ||
                name == null || name.equals("") ||
                difficulty == null
        ){
            throw new InvalidFieldException();
        }
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
     * sets id, usefull for replacing elements in collection
     * @param id
     */
    public void setId(int id){
        if (id > 0) {
            this.id = id;
        }
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
        s += String.format("  \"id\" : %s,\n", Integer.toString(id));
        s += String.format("  \"name\" : %s,\n", name);
        s += String.format("  \"coordinates\" : %s,\n", coordinates.toString());
        s += String.format("  \"creationDate\" : %s,\n", strCreationDate);
        s += String.format("  \"minimalPoint\" : %s,\n", Integer.toString(minimalPoint));
        s += String.format("  \"personalQualitiesMinimum\" : %s,\n", Integer.toString(personalQualitiesMinimum));
        s += String.format("  \"averagePoint\" : %s,\n", Double.toString(averagePoint));
        s += String.format("  \"difficulty\" : %s,\n", difficulty.toString());
        s += String.format("  \"discipline\" : %s\n", discipline.toString());
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
