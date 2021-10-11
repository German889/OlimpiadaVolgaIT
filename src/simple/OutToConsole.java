package simple;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class OutToConsole {
    private String resultSorted = "resources\\resultSorted.txt";
    public void printResultToConsole(){
        FileInputStream inputStream = null;
        Scanner sc = null;
        try{
            inputStream = new FileInputStream(resultSorted);
            sc = new Scanner(inputStream, "UTF-8");
            while(sc.hasNextLine()){
                String line = sc.nextLine();
                System.out.println(line);
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
    }
}
