package io;

import command.CommandWrapper;
import data.Coordinates;
import data.Difficulty;
import data.Discipline;
import data.LabWork;
import exceptions.EmptyStringException;
import exceptions.InvalidDataException;
import exceptions.InvalidEnumException;
import exceptions.InvalidNumberException;

import java.util.Scanner;

/**
 * basic implementation of InputManager
 */
public abstract class InputManagerImpl implements InputManager{
    private Scanner scanner;

    public InputManagerImpl(Scanner sc){
        scanner = sc;
        scanner.useDelimiter("\n");
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
        if (Double.isInfinite(x) || Double.isNaN(x)) throw new InvalidNumberException("invalid float value");
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
        if (y<=-545) throw new InvalidNumberException("must be greater than -545");
        return y;
    }
    public Coordinates readCoords() throws InvalidNumberException{
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
        if (minimalPoint<=0) throw new InvalidNumberException("must be greater than 0");
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
        if (personalQualitiesMinimum<=0) throw new InvalidNumberException("must be greater than 0");
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
        if (averagePoint<=0) throw new InvalidNumberException("must be greater than 0");
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
        if (lectureHours<0) throw new InvalidNumberException("must be greater than 0(ahtung!)");
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
            String arr [] = cmd.split(" ",2);
            cmd = arr[0];
            String arg = arr[1];
            return new CommandWrapper(cmd,arg);
        } else {
            return new CommandWrapper(cmd);
        }
    }
}