package simple;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class DocumentReader {
    String writeFilePath = "resources\\out.txt";
    public void readDocument(String filePath){
        FileInputStream inputStream = null;
        Scanner sc = null;
        try{
            inputStream = new FileInputStream(filePath);
            sc = new Scanner(inputStream, "UTF-8");
            while(sc.hasNextLine()){
                String line = sc.nextLine();
                perform(line);
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
    private void writeToTxtFile(String textLine, String pathToFile) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(pathToFile, true));
        writer.append(textLine);
        writer.append('\n');

        writer.close();
    }
    private void perform(String line) throws IOException {
        line = HTMLDocumentReader.perform(line);
        writeToTxtFile(line, writeFilePath);
    }
}
