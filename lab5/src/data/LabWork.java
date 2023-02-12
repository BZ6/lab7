package data;

public class LabWork {
    private Integer id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.util.Date creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Integer minimalPoint; //Поле не может быть null, Значение поля должно быть больше 0
    private int personalQualitiesMinimum; //Значение поля должно быть больше 0
    private Double averagePoint; //Поле может быть null, Значение поля должно быть больше 0
    private Difficulty difficulty; //Поле не может быть null
    private Discipline discipline; //Поле не может быть null
}
