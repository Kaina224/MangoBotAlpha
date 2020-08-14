public enum PropertiesFileNames {
    MANGADEX_LOGIN_CREDENTIALS("mangadexlogincredentials.properties");

    private String fileName;

    PropertiesFileNames(String fileName){
        this.fileName = fileName;
    }

    public String getFileName(){
        return fileName;
    }
}
