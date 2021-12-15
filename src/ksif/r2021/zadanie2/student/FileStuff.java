package ksif.r2021.zadanie2.student;

import java.io.*;

public class FileStuff {
    private String ZTShort;
    private String ZTLong;

    public static String readFile(File file){
        StringBuilder stringBuilder= new StringBuilder();
        if (file.exists()){
            BufferedReader bufferedReader= null;
            try {
                bufferedReader= new BufferedReader(new FileReader(file));
                String line;
                while ((line = bufferedReader.readLine()) != null){
                    stringBuilder.append(line);
                }
            }
            catch (IOException e) {
                stringBuilder= null;
                e.printStackTrace();
            }
            finally {
                try {
                    assert bufferedReader != null;
                    bufferedReader.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        assert stringBuilder != null;
        return stringBuilder.toString();
    }

    public String getZTShort() {
        return ZTShort;
    }

    public String getZTLong() {
        return ZTLong;
    }

    public FileStuff(){
        File fileShort= new File("msg_short.txt");
        File fileLong= new File("msg_long.txt");

        this.ZTShort= readFile(fileShort);
        this.ZTLong= readFile(fileLong);
    }
}
