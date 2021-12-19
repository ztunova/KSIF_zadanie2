package ksif.r2021.zadanie2.student;

import java.util.ArrayList;
import java.util.Arrays;

public class Solver {

    // lúštenie Vigenerovej sifry: vygenerujú sa všetky možné klúče dĺžky 4, pre každý klúč sa dešifruje zašifrovaný text.
    // Následne sa každý kandidát na riešenie ohodnotí funkciou na určenie vzdialenosti bigramov od referenčných hodnôt
    // ak má práve hodnotený kandidát lepšie (menšie je lepšie) skóre ako zatial uložený najlepší kandidát tak sa
    // práve hodnotený uloží ako najlepší
    public String solveVigenere(String ct1) {
        String retVal = null;

        Key k= new Key();
        Decryption d= new Decryption();

        //vygenerovanie všetkých klúčov
        k.generateAllKeysLength4();
        ArrayList<String> allKeys= k.getAllKeys();
        ArrayList<String> allCandidates= new ArrayList<String>();

        //dešifrovanie textu podla každého klúča
        for (String key : allKeys) {
            String candidate = d.decryptVigenere(key, ct1);
            allCandidates.add(candidate);
        }

        // hint: hodnotit bez konca, t.j. kluca
        String message, bestCandidate = null;
        TextEvaluation evaluation= new TextEvaluation();

        //inicializácia skóre
        double bestBigramScore= Double.MAX_VALUE;
        double actBigramScore;

        //každý kandidát na riešenie je ohodnotený, je mu určené skóre
        for (String candidate : allCandidates){
            int candidateLength= candidate.length();
            int breakIndex= candidateLength -20;
            message= candidate.substring(0, breakIndex);
            // transpKey= candidate.substring(breakIndex, candidate.length());

            actBigramScore= evaluation.l1BigramDistance(message);

            //porovnáva sa či sa nenašiel kandidát s lepším skóre ako doteraz najlepšie
            // ak áno kandidát sa uloží ako najlepší
            if (actBigramScore < bestBigramScore){
                bestBigramScore= actBigramScore;
                bestCandidate= candidate;
            }
        }

        retVal= bestCandidate;
        System.out.println("Solved Vigenere: " + retVal);
        return retVal;
    }

    //získanie číselnej permutácie z hesla
    //najskôr je heslo prevedené na pole znakov ktoré je utriedené funkciou sort
    //metóda potom berie postupne znaky klúča (v poradí v akom sú v klúči), následne postupne prechádza utriedeným
    //polom a hladá kým daný znak nenájde. Keď tento znak nájde, do výslednej číselnej permutácie
    //uloží index na ktorom znak našla v utriedenom poli
    public Integer[] getPermutation(String key) {
        Integer[] perm = new Integer[key.length()];

        char[] sortedKeyArray = key.toCharArray();
        Arrays.sort(sortedKeyArray);
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

    //lúštenie transpozičnej šifry. Počet stĺpcov v tabuľke je rovnaký ako je dĺžka klúča. Z toho sa dá určiť aj
    //počet riadkov v tabulke ako dĺžka_zašifrovaného_textu / počet_stlpcov. Ak bude zvyšok po delení nenulový
    //v tabulke sa nachádza aj nekompletný riadok
    //Vytvorí sa pomocná tabulka do ktorej sa bude zapisovať šifrovaná správa. Potom funkcia prechhádza jednotlivými
    //stĺpcami v poradí určenom permutáciou do ktorých zapisuje znaky zašifrovaného textu. Ci je stlpec naplneny
    //znakmi až do konca alebo posledny riadok sa nezapisuje (lebo je nekompletny a prazdne bunky zasahuju do daneho stlpcu)
    //sa určí podla toho, či je poradie stlpcu vacsie ako počet zaplnenych buniek v nekompletnom riadku (vysledok modula)
    //Zasifrovany text sa zapisuje po stlpcoch a cita po riadkoch.
    public String solveTransposition(String ct2, Integer[] key) {
        String retVal = null;

        //určenie počtu riadkov a stlpcov a pripadny nekompletny riadok
        int columns= key.length;
        int rows= ct2.length() / columns;
        int incompleteRows= ct2.length() % columns;
        if (incompleteRows > 0){
            rows++;
        }

        ArrayList<Integer> permList= new ArrayList<Integer>(Arrays.asList(key));
        //pomocna tabulka na zapisovanie šifrovanej správy
        char[][] table= new char[rows][columns];

        int actualColumn, actualRow, orderInPerm, lengtfOfColumn, indexCt2= 0;

        for(actualColumn= 0; actualColumn< columns; actualColumn++){
            //zistenie poradia z permutácie
            orderInPerm= permList.indexOf(actualColumn);
            lengtfOfColumn= rows;
            //zistenie ci do stlpca zasahuju prazdne bunky nekompletneho riadku
            if(incompleteRows > 0 && orderInPerm >= incompleteRows){
                lengtfOfColumn= lengtfOfColumn -1;
            }

            //zapisovanie sifrovanej spravy po stlpcoch
            for (actualRow= 0; actualRow< lengtfOfColumn; actualRow++){
                table[actualRow][orderInPerm] = ct2.charAt(indexCt2);
                indexCt2++;
            }
        }

        //citanie spravy z tabulky po riadkoch vytvori desifrovanu spravu
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

    //Desifrovanie substitucie pomocou horolezeckeho algoritmu
    //Vnutri algoritmu sa inicializuje kluc najskor ako nejaky nahodny, urci sa mu inverzny, ktory sa pouzije pre desifrovanie
    //substitucie. Desifrovana sprava sa ohodnoti podla vzdialenosti bigramov od referencnych hodnot.
    //Nasledne sa vykona drobna zmena v kluci, ktora pozostava z vymeny 2 znakov na nahodnych poziciach. Urci sa
    //kluc inverzny k zmenenemu ktorym sa sprava desifruje a urci jej skore. Skore sa porovnaju, zapamatava sa mensie
    //(to je lepsie) aj s prisluchajucim klucom na ktorom sa dalej vykonavaju zmeny a cely postup sa opakuje kym nie je
    //dosiajnuty dany pocet iteracii.
    //Cely algoritmus je spustany viackrat, podla poctu restartov (150), pricom sa po kazdom behu horolezeckeho algritmu
    //kontroluje, ci kandidat na riesenie nema lepsie skore ako doteraz najlepsi kandidat z behov predtym
    //(premenne ...BestTotal su najlepsi kandidat z jednotlivych spusteni HC)
    public String solveSubstitution(String ct2WithoutT) {
        String retVal = null;
        String decryptedBest = "";

        TextEvaluation evaluation = new TextEvaluation();
        Key k = new Key();
        Decryption d = new Decryption();

        String initKey = k.randomKey();
        String initInverseKey = k.inversePermutation(initKey);

        String decryptedBestTotal = d.decryptMonoalfSubs(ct2WithoutT, initInverseKey.toCharArray());
        double bestScoreTotal = evaluation.l1BigramDistance(decryptedBestTotal);

        for (int restart= 0; restart< 150; restart++) {
            String parentKey = k.randomKey();
            String actInverseKey = k.inversePermutation(parentKey);

            decryptedBest = d.decryptMonoalfSubs(ct2WithoutT, actInverseKey.toCharArray());
            double bestScore = evaluation.l1BigramDistance(decryptedBest);

            for (int iteration = 0; iteration < 5000; iteration++) {
                String childKey = parentKey;
                childKey = k.changeKey(childKey);
                actInverseKey = k.inversePermutation(childKey);
                String actDecrypted = d.decryptMonoalfSubs(ct2WithoutT, actInverseKey.toCharArray());
                double actScore = evaluation.l1BigramDistance(actDecrypted);

                if (actScore < bestScore) {
                    decryptedBest = actDecrypted;
                    parentKey = childKey;
                    bestScore = actScore;
                }

            }

            if(bestScore < bestScoreTotal){
                decryptedBestTotal= decryptedBest;
                bestScoreTotal= bestScore;
            }
        }

        System.out.println("Solved substitution: " + decryptedBestTotal);
        retVal= decryptedBestTotal;

        return retVal;
    }

    
}
