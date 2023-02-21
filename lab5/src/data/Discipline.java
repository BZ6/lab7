package data;
/**
 * Discipline class
 */
public class Discipline  implements Validateable{
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Integer lectureHours; //Поле не может быть null

    public Discipline(String name, Integer lectureHours){
        this.name = name;
        this.lectureHours = lectureHours;
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
    public Integer getLectureHours(){
        return lectureHours;
    }

    /**
     * @return String
     */
    @Override
    public String toString(){
        String s = "";
        s += "{";
        if (name!=null) s += "\"name\" : " +  "\"" + getName() + "\"" + ", ";
        s += "\"lectureHours\" : " + "\"" + getLectureHours() + "\"" + "}";
        return s;
    }

    public boolean validate(){
        return !(name==null || name.equals("") || lectureHours==null);
    }
}