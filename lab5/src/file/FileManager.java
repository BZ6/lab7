package file;

import static io.OutputManager.*;
import exceptions.*;

import java.io.*;

import java.util.Scanner;

/**
 * Operates the main collection file for saving/loading.
 */
public class FileManager implements ReaderWriter{
    private String path;
    /**
     * Constructor, just save variable for all class.
     * @param pth Path to collection file.
     */
    public FileManager(String pth){
        path = pth;
    }
    public void setPath(String pth){
        path = pth;
    }

    public FileManager(){
        path = null;
    }
    public String read()
    {
        String str = "";
        try {
            if (path == null) throw new EmptyPathException();
            File file = new File(path);
            Scanner scanner = new Scanner(file);
            if (!file.exists()) throw new FileNotExistsException();
            if (!file.canRead()) throw new FileWrongPermissionsException("cannot read file");
            while (scanner.hasNextLine())
                str += scanner.nextLine();
            scanner.close();
        }
        catch(FileException e){
            printErr(e.getMessage());
        } catch (IOException e) {
            printErr("cannot access file");
        }
        return str;
    }

    private void create(File file) throws CannotCreateFileException{
        try{
            file.createNewFile();
        } catch(IOException e){
            throw new CannotCreateFileException();
        }
    }
    public boolean write(String text){
        boolean res = true;
        try{
            if (path == null) throw new EmptyPathException();
            File file = new File(path);
            if(!file.exists()) {
                print("src/file " + path +" does not exist, trying to create new file");
                create(file);
            };
            if(!file.canWrite()) throw new FileWrongPermissionsException("cannot write file");
            FileOutputStream out = new FileOutputStream("notes.txt");
            BufferedOutputStream bufout = new BufferedOutputStream(out);
            byte[] buffer = text.getBytes();
            bufout.write(buffer, 0, buffer.length);
            bufout.close();
            out.close();
        } catch(FileException e){
            printErr(e.getMessage());
            res = false;
        } catch (IOException e) {
            res = false;
            printErr("cannot access file");
        }
        return res;
    }
}