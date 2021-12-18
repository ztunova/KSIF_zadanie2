package ksif.r2021.zadanie2.student;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class Key {
    private ArrayList<String> allKeys;

    public String changeKey(String originalKey){
        Random rand= new Random();
        char[] changedKey= originalKey.toCharArray();
        int i= rand.nextInt(originalKey.length());
        int j= rand.nextInt(originalKey.length());
        char help= changedKey[i];
        changedKey[i] = changedKey[j];
        changedKey[j]= help;

        StringBuilder result= new StringBuilder();
        for (Character character : changedKey) {
            result.append(character);
        }
        return result.toString();
    }

    public String randomKey(){
        ArrayList<Character> alphabet= new ArrayList<Character>(26);
        for (int i= 0; i< 26; i++){
            alphabet.add((char) (i + 'a'));
        }

        Collections.shuffle(alphabet);

        StringBuilder result= new StringBuilder();
        for (Character character : alphabet) {
            result.append(character);
        }

        return result.toString();
    }

    public String inversePermutation(String permut){
        //System.out.println(permut);
        char[] permutArr= permut.toCharArray();
        //System.out.println(Arrays.toString(charToIntArray(permut)));

        int[] intPermut= charToIntArray(permut);
        int[] inversePermut= new int[permutArr.length];

        for (int i= 0; i< permutArr.length; i++){
            inversePermut[intPermut[i]] = i;
        }

        //System.out.println(Arrays.toString(inversePermut));
        //System.out.println(intToCharArray(inversePermut));
        return intToCharArray(inversePermut);
    }

    private String intToCharArray(int[] intArr){
        char[] charArr= new char[intArr.length];
        for (int i= 0; i< intArr.length; i++){
            int x= intArr[i];
            char y= (char) (x + 'a');
            charArr[i] = y;
        }

        StringBuilder result= new StringBuilder();
        for (Character character : charArr) {
            result.append(character);
        }
        return result.toString();
    }

    private int[] charToIntArray(String orig){
        char[] charArr= orig.toCharArray();
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
