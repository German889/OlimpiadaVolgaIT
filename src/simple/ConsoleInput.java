package simple;

import java.util.Scanner;

public class ConsoleInput {
    private String pathToFile;
    private String dataBaseUsername;
    private String dataBasePassword;
    public void getInputFromConsole(){
        System.out.println("Enter a path to file:");
        Scanner readConsole = new Scanner(System.in);
        pathToFile = readConsole.nextLine();
        System.out.println("Enter DataBase username:");
        dataBaseUsername = readConsole.nextLine();
        System.out.println("Enter a password");
        dataBasePassword = readConsole.nextLine();
    }
    public String getDataBaseUsername(){
        return dataBaseUsername;
    }
    public String getDataBasePassword(){
        return dataBasePassword;
    }
    public String getPathToFile(){
        return pathToFile;
    }
}
