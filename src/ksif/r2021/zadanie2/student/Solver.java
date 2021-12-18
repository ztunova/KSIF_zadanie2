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
        String message, bestCandidate = null;
        TextEvaluation evaluation= new TextEvaluation();
        double bestBigramScore= Double.MAX_VALUE;
        double actBigramScore;

        for (String candidate : allCandidates){
            int candidateLength= candidate.length();
            int breakIndex= candidateLength -20;
            message= candidate.substring(0, breakIndex);
            // transpKey= candidate.substring(breakIndex, candidate.length());

            actBigramScore= evaluation.l1BigramDistance(message);
            if (actBigramScore < bestBigramScore){
                bestBigramScore= actBigramScore;
                bestCandidate= candidate;
            }
        }

        retVal= bestCandidate;
        System.out.println("Solved Vigenere: " + retVal);
        return retVal;
    }

    public Integer[] getPermutation(String key) {
        Integer[] perm = new Integer[key.length()];

        char[] sortedKeyArray = key.toCharArray();
        Arrays.sort(sortedKeyArray);
        //System.out.println("sorted key: " + Arrays.toString(sortedKeyArray));
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

        int columns= key.length;
        int rows= ct2.length() / columns;
        int incompleteRows= ct2.length() % columns;
        if (incompleteRows > 0){
            rows++;
        }

        ArrayList<Integer> permList= new ArrayList<Integer>(Arrays.asList(key));
        char[][] table= new char[rows][columns];

        int actualColumn, actualRow, orderInPerm, lengtfOfColumn, indexCt2= 0;

        for(actualColumn= 0; actualColumn< columns; actualColumn++){
            orderInPerm= permList.indexOf(actualColumn);
            lengtfOfColumn= rows;
            if(incompleteRows > 0 && orderInPerm >= incompleteRows){
                lengtfOfColumn= lengtfOfColumn -1;
            }

            for (actualRow= 0; actualRow< lengtfOfColumn; actualRow++){
                table[actualRow][orderInPerm] = ct2.charAt(indexCt2);
                indexCt2++;
            }
        }

        StringBuilder sb= new StringBuilder();
        for (actualRow= 0; actualRow< rows; actualRow++){
            for (actualColumn= 0; actualColumn< columns; actualColumn++){
                if (table[actualRow][actualColumn] != '\u0000'){
                    sb.append(table[actualRow][actualColumn]);
                }
            }
        }

        retVal= sb.toString();
        System.out.println("Solved transpozition: " + retVal);
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
