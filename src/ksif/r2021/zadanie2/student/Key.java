package ksif.r2021.zadanie2.student;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Key {
    private ArrayList<String> allKeys;

    public void randomKey(){
        ArrayList<Character> alphabet= new ArrayList<Character>(26);
        for (int i= 0; i< 26; i++){
            alphabet.set(i, (char) (i + 'a'));
        }

        Collections.shuffle(alphabet);
    }

    public void inversePermutation(char[] permut){
        System.out.println(permut);
        System.out.println(Arrays.toString(charToIntArray(permut)));

        int[] intPermut= charToIntArray(permut);
        int[] inversePermut= new int[permut.length];

        for (int i= 0; i< permut.length; i++){
            inversePermut[intPermut[i]] = i;
        }

        System.out.println(Arrays.toString(inversePermut));
        System.out.println(intToCharArray(inversePermut));
    }

    private char[] intToCharArray(int[] intArr){
        char[] charArr= new char[intArr.length];
        for (int i= 0; i< intArr.length; i++){
            int x= intArr[i];
            char y= (char) (x + 'a');
            charArr[i] = y;
        }
        return charArr;
    }

    private int[] charToIntArray(char[] charArr){
        int[] intArr= new int[charArr.length];
        for (int i= 0; i< charArr.length; i++){
            char x= charArr[i];
            int y= x - 'a';
            intArr[i] = y;
        }
        return intArr;
    }

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
