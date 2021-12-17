package ksif.r2021.zadanie2.student;

public class TextEvaluation {
    private double referenceValues[][];

    public double l1BigramDistance(String candidate){
        double [][] measuredFrequency= new double[26][26];
        int i, j, indexRow, indexColumn;
        char firstChar, secondChar;

        for (i= 0; i< candidate.length() -1; i++){
            firstChar= candidate.charAt(i);
            secondChar= candidate.charAt(i+1);
            indexRow= firstChar - 'a';
            indexColumn= secondChar - 'a';

            measuredFrequency[indexRow][indexColumn]++;
        }

        double sum= 0, relativeFrequencyCand, distance;

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
        //System.out.println("texteval");
    }
}
