package ksif.r2021.zadanie2.student;

/**
 * Trieda ktorá slúži na ohodnotenie textu na základe vzdialenosti bigramov v danom texte (kandidátovi na riešenie)
 * od referenčných hodnôt jazyka. Referenčné hodnoty sa nachádzajú  v súbore _bigrams a pri volaní konštruktora
 * tejto triedy sú uložené do atribútu referenceValues.
 *
 * Výpočet vzdialenosti sa vykonáva v metóde l1BigramDistance. V prvej časti sa do tabuľky measuredFrequency uloží
 * počet jednotlivých bigramov nájdených v kandidátovi na riešenie. V druhej časti sú tieto počty najskôr prevedené
 * na relatívne (vzhladom na dlzku textu). Následne je určená vzdialenosť nameraných hodnôt od referenčných hodnôt jazyka
 * a tieto vzdialenosti sú nasčítavané. Lepší kandidát má nižšiu sumu vzdialeností.
 */

public class TextEvaluation {
    private double referenceValues[][];

    public double l1BigramDistance(String candidate){
        double [][] measuredFrequency= new double[26][26];
        int i, j, indexRow, indexColumn;
        char firstChar, secondChar;

        // určenie počtu jednotlivých bigramov v texte (kandidátovi na riešenie)
        for (i= 0; i< candidate.length() -1; i++){
            firstChar= candidate.charAt(i);
            secondChar= candidate.charAt(i+1);
            indexRow= firstChar - 'a';
            indexColumn= secondChar - 'a';

            measuredFrequency[indexRow][indexColumn]++;
        }

        double sum= 0, relativeFrequencyCand, distance;

        // výpočet vzdialenosti bigramov v texte od referenčných hodnôt angličtiny
        // výsledné ohodnotenie je suma týchto vzdialeností
        for(i= 0; i< 26; i++){
            for (j= 0; j< 26; j++){
                relativeFrequencyCand= measuredFrequency[i][j] / (candidate.length() -1);
                distance= referenceValues[i][j] - relativeFrequencyCand;
                sum= sum + java.lang.Math.abs(distance);
            }
        }

        return sum;
    }

    public void dictionaryEvaluation(){}

    public TextEvaluation(){
        this.referenceValues= (double[][]) FileStuff.readFile("_bigrams");
    }
}
