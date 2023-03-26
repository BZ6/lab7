package io;

import command.CommandWrapper;
import data.Coordinates;
import data.Difficulty;
import data.Discipline;
import data.LabWork;
import exceptions.*;

import java.util.Scanner;

/**
 * basic implementation of InputManager
 */
public abstract class InputManagerImpl implements InputManager{
    private static final int TWICE = 2;
    private static final int THE_FIRST_PART = 0;
    private static final int THE_SECOND_PART = 1;
    private Scanner scanner;

    public InputManagerImpl(Scanner sc){
        scanner = sc;
    }

    public Scanner getScanner(){
        return scanner;
    }

    public void setScanner(Scanner sc){
        scanner = sc;
    }
    public String readName() throws EmptyStringException{
        String s = scanner.nextLine().trim();
        if (s.equals("")){
            throw new EmptyStringException();
        }
        return s;
    }

    public double readXCoord() throws InvalidNumberException{
        double x;
        try{
            x = Double.parseDouble(scanner.nextLine());
        }
        catch(NumberFormatException e){
            throw new InvalidNumberException();
        }
        return x;
    }

    public Integer readYCoord() throws InvalidNumberException{
        Integer y;
        try{
            y = Integer.parseInt(scanner.nextLine());
        }
        catch(NumberFormatException e){
            throw new InvalidNumberException();
        }
        return y;
    }
    public Coordinates readCoords() throws InvalidNumberException {
        double x = readXCoord();
        Integer y = readYCoord();
        Coordinates coord = new Coordinates(x,y);
        return coord;
    }

    public Integer readMinimalPoint() throws InvalidNumberException{
        Integer minimalPoint;
        try{
            minimalPoint = Integer.parseInt(scanner.nextLine());
        }
        catch(NumberFormatException e){
            throw new InvalidNumberException();
        }
        return minimalPoint;
    }

    /**
     * reads personal qualities minimum from input
     * @return
     * @throws InvalidNumberException
     */
    public int readPersonalQualitiesMinimum() throws InvalidNumberException{
        int personalQualitiesMinimum;
        try{
            personalQualitiesMinimum = Integer.parseInt(scanner.nextLine());
        }
        catch(NumberFormatException e){
            throw new InvalidNumberException();
        }
        return personalQualitiesMinimum;
    }

    /**
     * reads average point from input
     * @return
     * @throws InvalidNumberException
     */
    public Double readAveragePoint() throws InvalidNumberException{
        Double averagePoint;
        try{
            averagePoint = Double.parseDouble(scanner.nextLine());
        }
        catch(NumberFormatException e){
            throw new InvalidNumberException();
        }
        return averagePoint;
    }

    public Difficulty readDifficulty() throws InvalidEnumException{
        String s = scanner.nextLine().trim();
        try{
            return Difficulty.valueOf(s);
        } catch(IllegalArgumentException e){
            throw new InvalidEnumException();
        }
    }

    public Integer readLectureHours() throws InvalidNumberException{
        Integer lectureHours;
        try{
            lectureHours = Integer.parseInt(scanner.nextLine());
        }
        catch(NumberFormatException e){
            throw new InvalidNumberException();
        }
        return lectureHours;
    }

    public Discipline readDiscipline() throws InvalidDataException{
        String name = readName();
        Integer lectureHours = readLectureHours();
        return new Discipline(name, lectureHours);
    }

    public LabWork readLabWork() throws InvalidDataException{
        LabWork labWork = null;

        String name = readName();
        Coordinates coords = readCoords();
        Integer minimalPoint = readMinimalPoint();
        int personalQualitiesMinimum = readPersonalQualitiesMinimum();
        Double averagePoint = readAveragePoint();
        Difficulty difficulty = readDifficulty();
        Discipline discipline = readDiscipline();
        labWork = new LabWork(name, coords, minimalPoint, personalQualitiesMinimum, averagePoint, difficulty, discipline);

        return labWork;
    }

    public CommandWrapper readCommand(){
        String cmd = scanner.nextLine();
        if (cmd.contains(" ")){ //if command has argument
            String commandLine [] = cmd.split(" ",TWICE);
            cmd = commandLine[THE_FIRST_PART];
            String arg = commandLine[THE_SECOND_PART];
            return new CommandWrapper(cmd,arg);
        } else {
            return new CommandWrapper(cmd);
        }
    }
}