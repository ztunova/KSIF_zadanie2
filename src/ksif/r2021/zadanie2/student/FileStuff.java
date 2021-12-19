package ksif.r2021.zadanie2.student;

import java.io.*;

/**
 * Trieda ktorá obsahuje metódy na čítanie súborov
 */

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

    // využité pri načítaní súboru _bigrams
    public static Object readFile(String path){
        try {
            FileInputStream fis= new FileInputStream(path);
            ObjectInputStream ois= new ObjectInputStream(fis);
            return ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
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
