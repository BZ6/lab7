package io;

import java.util.Scanner;

import command.CommandWrapper;
import data.*;
import exceptions.*;

public interface InputManager {
    /**
     * reads name from input
     * @return
     * @throws EmptyStringException
     */
    public String readName() throws EmptyStringException;

    /**
     * reads x from input
     * @return
     * @throws InvalidNumberException
     */
    public double readXCoord() throws InvalidNumberException;

    /**
     * reads y from input
     * @return
     * @throws InvalidNumberException
     */
    public Integer readYCoord() throws InvalidNumberException;

    /**
     * reads coordinates from input
     * @return
     * @throws InvalidNumberException
     */
    public Coordinates readCoords() throws InvalidNumberException;

    /**
     * reads minimal point from input
     * @return
     * @throws InvalidNumberException
     */
    public Integer readMinimalPoint() throws InvalidNumberException;

    /**
     * reads personal qualities minimum from input
     * @return
     * @throws InvalidNumberException
     */
    public int readPersonalQualitiesMinimum() throws InvalidNumberException;

    /**
     * reads average point from input
     * @return
     * @throws InvalidNumberException
     */
    public Double readAveragePoint() throws InvalidNumberException;

    /**
     * reads position from input
     * @return
     * @throws InvalidEnumException
     */
    public Difficulty readDifficulty() throws InvalidEnumException;

    /**
     * reads lecture hours from input
     * @return
     * @throws InvalidNumberException
     */
    public Integer readLectureHours() throws InvalidNumberException;

    /**
     * reads organization from input
     * @return
     * @throws InvalidDataException
     */
    public Discipline readDiscipline() throws InvalidDataException;

    /**
     * reads LabWork from input
     * @return
     * @throws InvalidDataException
     */
    public LabWork readLabWork() throws InvalidDataException;

    /**
     * reads command-argument pair from input
     * @return
     */
    public CommandWrapper readCommand();

    /**
     * gets input scanner
     * @return
     */
    public Scanner getScanner();
}