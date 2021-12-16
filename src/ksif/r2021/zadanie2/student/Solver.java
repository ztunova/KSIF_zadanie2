package ksif.r2021.zadanie2.student;

import java.util.ArrayList;

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
        //
        //   ...     
        //
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
