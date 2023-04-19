package io;
import static io.OutputManager.*;

import exceptions.*;

/**
 * user input wrapper
 * @param <T>
 */
public class Question<T>{
    private final Askable<T> askable;
    private T answer;
    public Question(String msg, Askable<T> askable) throws InvalidInputCharacterException{
        this.askable = askable;
        while (true){
            try{
                System.out.print(msg + " ");
                T ans = this.askable.ask();
                answer = ans;
                break;
            }
            catch(InvalidDataException e){
                printErr(e.getMessage());
            }
        }
    }
    public T getAnswer(){
        return answer;
    }
}