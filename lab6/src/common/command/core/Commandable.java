package command.core;

public interface Commandable {

    /**
     * executes command with argument
     * @param key command name
     * @param arg
     */
    public void runCommand(String key, String arg);

    /**
     * executes command without argument
     * @param key
     */
    public void runCommand(String key);

    /**
     * runs in command interpreter in console
     */
    public void consoleMode();

    /**
     * executes script from file
     * @param path
     */
    public boolean fileMode(String path);
}