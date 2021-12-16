package ksif.r2021.zadanie2.student;

import java.util.ArrayList;
import java.util.Arrays;

public class Solver {

    public String solveVigenere(String ct1) {
        String retVal = null;

        Key k= new Key();
        Decryption d= new Decryption();
        k.generateAllKeysLength4();
        ArrayList<String> allKeys= k.getAllKeys();
        ArrayList<String> allCandidates= new ArrayList<String>();

        for (String key : allKeys) {
            String candidate = d.decryptVigenere(key, ct1);
            allCandidates.add(candidate);
        }


        // hint: hodnotit bez konca, t.j. kluca
        //   ...     
        //
        //return "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        return retVal;
    }

    public Integer[] getPermutation(String key) {
        Integer[] perm = new Integer[key.length()];

        char[] sortedKeyArray = key.toCharArray();
        Arrays.sort(sortedKeyArray);
        System.out.println("sorted key: " + Arrays.toString(sortedKeyArray));
        int keyLength= key.length();

        for(int i= 0; i< keyLength; i++){
            char keyChar= key.charAt(i);
            for(int j= 0; j< keyLength; j++){
                char sortedChar= sortedKeyArray[j];
                if(keyChar == sortedChar){
                    perm[i]= j;
                    sortedKeyArray[j]= '_';
                    break;
                }
            }
        }

        System.out.println("perm: " + Arrays.toString(perm));
        return perm;
    }

    public String solveTransposition(String ct2, Integer[] key) {
        String retVal = null;
        //
        //   ...     
        //
        return retVal;
    }

    public String solveSubstitution(String ct2WithoutT) {
        String retVal = null;
        //
        //   ...     
        //
        return retVal;
    }

    
}
