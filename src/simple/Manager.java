package simple;

public final class Manager {
    private static String pathToFile;
    private static String dataBaseUsername;
    private static String dataBasePassword;
    private static String resultSortedFilePath;
    public void createCache(){
        FileRemaker f = new FileRemaker();
        f.createCacheFiles();
    }
    public void callInput(){
        ConsoleInput user = new ConsoleInput();
        user.getInputFromConsole();
        pathToFile = user.getPathToFile();
        dataBaseUsername = user.getDataBaseUsername();
        dataBasePassword = user.getDataBasePassword();
    }
    public void readFile(){
        DocumentReader htm = new DocumentReader();
        htm.readDocument(pathToFile);
    }
    public void changeFile() {

        StatisticMaker stat = new StatisticMaker();
        resultSortedFilePath = stat.doAll();
    }
    public void sendToDB(){
        DBwriter send = new DBwriter();
        send.setLogin(dataBaseUsername,dataBasePassword);
        send.writeToDatabase(resultSortedFilePath);
    }
    public void writeToConsole(){
        OutToConsole out = new OutToConsole();
        out.printResultToConsole();
    }
    public void deleteCache(){
        FileRemaker fileRemaker = new FileRemaker();
        fileRemaker.deleteCacheFiles();
    }
    public void fileToDBmove(){
        createCache();
        callInput();
        readFile();
        changeFile();
        writeToConsole();
        sendToDB();
        deleteCache();
    }
}
