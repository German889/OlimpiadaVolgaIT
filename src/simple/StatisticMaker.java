package simple;

import java.io.*;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class StatisticMaker{
    private int maxValue = 0;
    private int lengthFile = 0;
    private String path = "resources\\out.txt";
    private String resultPath = "resources\\result.txt";
    private String resultSorted = "resources\\resultSorted.txt";

    public int countInResult=0;
    public void setReadPath(String read){
        path = read;
    }
    public void setResultPath(String result){ resultPath = result; }
    public void setResultSortedPath(String resultSort){ resultSorted = resultSort;}
    public void setAll(String a,String b,String c){
        setReadPath(a);
        setResultPath(b);
        setResultSortedPath(c);
    }
    public String doAll() {
        countWordsInFile();
        countRepeatWordsInStr();
        stripDuplicatesFromFile();
        countLines(resultPath);
        sortStatInFile();
        return resultSorted;
    }
    public void countWordsInFile(){
        //System.out.println("count word started");
        FileInputStream inputStream = null;
        Scanner sc = null;
        try{
            inputStream = new FileInputStream(path);
            sc = new Scanner(inputStream, "UTF-8");
            while(sc.hasNext()){
                sc.next();
                lengthFile++;
                //System.out.println("iterate "+lengthFile);
            }
            if(sc.ioException() != null){
                throw sc.ioException();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if( inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(sc != null){
                sc.close();
            }
        }
        //System.out.println("lengthFile is "+lengthFile);
    }
    public void countRepeatWordsInStr() {
        //System.out.println("count repeat started");
        FileInputStream inputStream = null;
        Scanner sc = null;
        String[] arr;
        int iterate = 1;
        for(int now=0; now < lengthFile; now++){
            String word = "";
            int repeat = 0;
            try {
                inputStream = new FileInputStream(path);
                sc = new Scanner(inputStream, "UTF-8");
                for(int i=0; i< iterate; i++){
                    word = sc.next();
                }
                iterate++;
                inputStream = new FileInputStream(path);
                sc = new Scanner(inputStream, "UTF-8");
                //System.out.println("word = " + word);
                while (sc.hasNextLine()) {
                    String line = sc.nextLine();
                    arr = line.split(" ");
                    for (String j : arr) {
                        if (j.trim().equals(word)) {repeat++;}
                        //System.out.println("repeat counted");
                    }
                    //System.out.println(line);
                }
                if (sc.ioException() != null) {
                    throw sc.ioException();
                }
                if(repeat > maxValue) maxValue = repeat;
                writeToTxtFile(word+":"+repeat, resultPath);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (sc != null) {
                    sc.close();
                }
            }
        }


    }

    public void stripDuplicatesFromFile() {
        BufferedReader reader = null;
        Set<String> lines = new HashSet<>(100);
        try{
            reader = new BufferedReader(new FileReader(resultPath));
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        }
        catch(FileNotFoundException f){
            //System.out.println("file for reading does not exist");
        }
        catch(IOException e){
            e.printStackTrace();
        }
        finally{
            if(reader!= null){
                try{
                    reader.close();
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
        }

        BufferedWriter writer = null;
        try{
            writer = new BufferedWriter(new FileWriter(resultPath));
            for (String unique : lines) {
                writer.write(unique);
                writer.newLine();
            }
        }
        catch(FileNotFoundException f){
            //System.out.println("file for writing does not exist");
        }
        catch(IOException e){
            e.printStackTrace();
        }
        finally{
            if(writer != null){
                try{
                    writer.close();
                } catch(IOException e){
                    e.printStackTrace();
                }
            }
        }

    }
    public void countLines(String filePath){
        int count = 0;
        FileInputStream inputStream = null;
        Scanner sc = null;
        try{
            inputStream = new FileInputStream(filePath);
            sc = new Scanner(inputStream, "UTF-8");
            while(sc.hasNextLine()){
                String line = sc.nextLine();
                count++;
            }
            if(sc.ioException() != null){
                throw sc.ioException();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if( inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(sc != null){
                sc.close();
            }
        }
        countInResult = count;
    }
    public void sortStatInFile(){
        int max = maxValue;
        for(int u=0; u<countInResult; u++){
            FileInputStream inputStream = null;
            Scanner sc = null;
            try {
                inputStream = new FileInputStream(resultPath);
                sc = new Scanner(inputStream, "UTF-8");
                while (sc.hasNextLine()) {
                    String line = sc.nextLine();
                    String[] arr = line.split(":");
                    int repNum = Integer.parseInt(arr[1]);
                    if (repNum == max) {
                        writeToTxtFile(line, resultSorted);
                    }
                }
                max--;
                if (sc.ioException() != null) {
                    throw sc.ioException();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (sc != null) {
                    sc.close();
                }
            }
        }
    }


    public void writeToTxtFile(String textLine, String pathToFile) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(pathToFile, true));
        writer.append(textLine);
        writer.append('\n');

        writer.close();
    }

}
