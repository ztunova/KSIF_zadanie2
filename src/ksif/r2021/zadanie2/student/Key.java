package ksif.r2021.zadanie2.student;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

/**
 * Trieda ktorá "spravuje" klúče k šifrám. Obsahuje metódu na vygenerovanie všetkých možných klúčov dĺžky 4 pre Vigenerovu
 * šifru. Metódu na vygenerovanie náhodného klúča (alias náhodnej permutácie) pre horolezecký algoritmus, metódu na
 * určenie inverznej permutácie s pomocnými funkciami intToCharArray a charToIntArray a metódu changeKey, ktorá vymiena
 * poradie dvoch náhodne zvolených písmen v klúči (pre horolezecký algo.), všetky tieto sú použité pre lúštenie substitúcie.
 */

public class Key {
    private ArrayList<String> allKeys;

    // náhodne zvolí 2 prvky v klúči a vymení ich poradie
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

    //vygeneruje náhodnú permutáciu telegrafnej abecedy
    public String randomKey(){
        ArrayList<Character> alphabet= new ArrayList<Character>(26);
        for (int i= 0; i< 26; i++){
            alphabet.add((char) (i + 'a'));
        }

        //náhodné preusporiadanie abecedy je zabezpečené funkciou shuffle
        Collections.shuffle(alphabet);

        StringBuilder result= new StringBuilder();
        for (Character character : alphabet) {
            result.append(character);
        }

        return result.toString();
    }

    //určenie inverznej permutácie z klúča
    // pre (aspon pre mna) zrozumitelnejsie riesenie metóda využíva pomocnú funkciu charToIntArray ktorá klúč
    // (pole znakov) najskor prevedie na ich číselnú reprezentáciu (0 -25). Inverzná permutácia je určená z tejto
    //číselnej reprezentácie a na konci prevedená späť na znaky pomocnou funkciou intToCharArray
    public String inversePermutation(String permut){
        char[] permutArr= permut.toCharArray();

        int[] intPermut= charToIntArray(permut);
        int[] inversePermut= new int[permutArr.length];

        for (int i= 0; i< permutArr.length; i++){
            inversePermut[intPermut[i]] = i;
        }

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

    //vygenerovanie vštkých klúčov dĺžky 4
    public void generateAllKeysLength4(){
        for(char firstChar= 'a'; firstChar<= 'z'; firstChar++){
            for (char secondChar= 'a'; secondChar<='z'; secondChar++){
                for (char thirdChar= 'a'; thirdChar<= 'z'; thirdChar++){
                    for (char fourthChar= 'a'; fourthChar<= 'z'; fourthChar++){
                        String key= "" + firstChar + secondChar + thirdChar + fourthChar;
                        allKeys.add(key);
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
