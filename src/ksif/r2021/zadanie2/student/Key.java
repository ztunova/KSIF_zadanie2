package ksif.r2021.zadanie2.student;

import java.util.ArrayList;

public class Key {
    private ArrayList<String> allKeys;

    public void generateAllKeysLength4(){
        for(char firstChar= 'a'; firstChar<= 'z'; firstChar++){
            for (char secondChar= 'a'; secondChar<='z'; secondChar++){
                for (char thirdChar= 'a'; thirdChar<= 'z'; thirdChar++){
                    for (char fourthChar= 'a'; fourthChar<= 'z'; fourthChar++){
                        String key= "" + firstChar + secondChar + thirdChar + fourthChar;
                        allKeys.add(key);
                        //System.out.println(key);
                    }
                }
            }
        }
    }

    public ArrayList<String> getAllKeys() {
        return allKeys;
    }

    public Key(){
        this.allKeys= new ArrayList<String>();
    }
}
