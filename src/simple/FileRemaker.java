package simple;

import java.io.*;

public class FileRemaker{
    public void createCacheFiles(){
        PrintWriter writer = null;
        try {
            writer = new PrintWriter("resources\\out.txt", "UTF-8");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } finally {
            writer.close();
        }
        try {
            writer = new PrintWriter("resources\\result.txt", "UTF-8");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } finally {
            writer.close();
        }
        try {
            writer = new PrintWriter("resources\\resultSorted.txt", "UTF-8");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } finally {
            writer.close();
        }
    }
    public void deleteCacheFiles(){
        File fileToDelete = null;
        fileToDelete = new File("resources\\out.txt");
        if(fileToDelete.delete()){
            //System.out.println("File deleted");
        } else {
            //System.out.println("Operation failed");
        }
        fileToDelete = new File("resources\\result.txt");
        if(fileToDelete.delete()){
            //System.out.println("File deleted");
        } else {
            //System.out.println("Operation failed");
        }
        fileToDelete = new File("resources\\resultSorted.txt");
        if(fileToDelete.delete()){
            //System.out.println("File deleted");
        } else {
            //System.out.println("Operation failed");
        }
    }
}
