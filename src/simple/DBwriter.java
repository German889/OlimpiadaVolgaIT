package simple;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

public class DBwriter{
    private String username = null;
    private String password = null;
    public void setLogin(String usr,String psw){username = usr; password = psw;}
    public void writeToDatabase(String filePath){
        System.out.println("MySQL JDBC Connection Testing");
        Connection conn = null;
        Statement statement;
        try
        {
            Class.forName ("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/mysql";
            conn = DriverManager.getConnection (url, username, password);
            System.out.println ("Database Connection Established");
//====================================================
            FileInputStream inputStream = null;
            Scanner sc = null;
            try{
                inputStream = new FileInputStream(filePath);
                sc = new Scanner(inputStream, "UTF-8");
                while(sc.hasNextLine()){
                    String line = sc.nextLine();
                    String[] arr = line.split(":");
                    String word = arr[0];
                    String count = arr[1];
                    statement = conn.createStatement();
                    String SQL = "INSERT INTO mydbtest.words(word, count) VALUES('"+word+"', "+count+");";
                    statement.executeUpdate(SQL);
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
            //===============================================

        }
        catch (Exception ex)
        {
            System.err.println ("Cannot connect to database server");
            ex.printStackTrace();
        }
        finally
        {
            if (conn != null)
            {
                try
                {
                    System.out.println("Let terminate the Connection");
                    conn.close ();
                    System.out.println ("Database connection terminated");
                }
                catch (Exception ex)
                {
                    System.out.println ("Error in connection termination!");
                }
            }
        }
    }
}