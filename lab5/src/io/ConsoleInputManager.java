package io;

import java.util.Scanner;

import java.time.LocalDate;

import data.*;
import exceptions.InvalidEnumException;
import exceptions.InvalidNumberException;

import static io.OutputManager.*;
public class ConsoleInputManager extends InputManagerImpl{

    public ConsoleInputManager(){
        super(new Scanner(System.in));
        getScanner().useDelimiter("\n");
    }

    @Override
    public String readName(){
        return new Question<String>("enter name:", super::readName).getAnswer();
    }


    @Override
    public double readXCoord(){
        return new Question<Double>("enter x:", super::readXCoord).getAnswer();
    }

    @Override
    public Integer readYCoord(){
        return new Question<Integer>("enter y:", super::readYCoord).getAnswer();
    }

    @Override
    public Coordinates readCoords(){
        OutputManager.print("enter coordinates");
        double x = readXCoord();
        Integer y = readYCoord();
        Coordinates coord = new Coordinates(x,y);
        return coord;
    }

    @Override
    public Integer readMinimalPoint(){
        return new Question<Integer>("enter minimal point:",super::readMinimalPoint).getAnswer();
    }

    @Override
    public int readPersonalQualitiesMinimum(){
        return new Question<Integer>("enter personal qualities minimum:",super::readPersonalQualitiesMinimum).getAnswer();
    }

    @Override
    public Double readAveragePoint(){
        return new Question<Double>("enter average point:",super::readAveragePoint).getAnswer();
    }

    @Override
    public Difficulty readDifficulty(){
        return new Question<Difficulty>("enter difficulty(VERY_HARD, IMPOSSIBLE, HOPELESS, TERRIBLE):", super::readDifficulty).getAnswer();
    }

    @Override
    public Integer readLectureHours(){
        return new Question<Integer>("enter lecture hours:",super::readLectureHours).getAnswer();
    }

    @Override
    public Discipline readDiscipline(){
        OutputManager.print("enter organization");
        String name = readName();
        Integer lectureHours = readLectureHours();
        return new Discipline(name, lectureHours);
    }

    @Override
    public LabWork readLabWork(){
        String name = readName();
        Coordinates coords = readCoords();
        Integer minimalPoint = readMinimalPoint();
        int personalQualitiesMinimum = readPersonalQualitiesMinimum();
        Double averagePoint = readAveragePoint();
        Difficulty difficulty = readDifficulty();
        Discipline discipline = readDiscipline();
        return new LabWork(name, coords, minimalPoint, personalQualitiesMinimum, averagePoint, difficulty, discipline);
    }
}