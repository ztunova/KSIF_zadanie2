package ksif.r2021.zadanie2.student;

/**
 * Trieda ktorá obsahuje metódy na lúštenie jednotlivých šifier. V triede Solver sú tieto metódy volané v cykle,
 * vykonávajú teda len dešifrovanie textu jedným konkrétnym klúčom, ktorý dostanú ako vstupný argument
 *
 * Klúčom monoalfabetickej substitúcie je určitá permutácia telegrafnej abecedy. Vstupom do funkcie je zašifrovaný text
 * a pole znakov predstavujúce substitúciu. Index v poli reprezentuje pôvodný znak (a= 0, b= 1, c= 2, ....) a znak v
 * poli na danom indexe reprezentuje na aký znak sa zmení po zašifrovaní. Metóda teda prechádza jednotlivými znakmi
 * zašifrovaného textu, určí ich poradové číslo (0 -25 -> index do pola), vyberie znak na danom indexe v klúči ktorý
 * zapíše do postupne sa vytvárajúceho otvoreného textu
 *
 * Pre Vigenerovu šifru sa najskôr cyklicky zopakuje klúč (metóda cyclicKeyVigenere), čím sa získa klúč rovnako dlhý
 * ako je zašifrovaný text. Následne metóda decryptVigenere postupne prechádza znaky zašifrovaného textu a posúva ich
 * podla znaku na rovnakej pozícií v klúči.
 */

public class Decryption {

    public String decryptMonoalfSubs(String zt, char[] inverseKey){
        StringBuilder ot= new StringBuilder();
        char[] ztArr= zt.toCharArray();
        for (char actChar : ztArr){
            int index= actChar - 'a';
            char invChar= inverseKey[index];
            ot.append(invChar);
        }
        return ot.toString();
    }

    public String decryptVigenere(String key, String zt){
        String cyclicKey= cyclicKeyVigenere(key, zt);
        StringBuilder decryptedText= new StringBuilder();
        int x;

        for(int i= 0; i< zt.length(); i++){
            x= (zt.charAt(i) - cyclicKey.charAt(i) + 26) %26;
            x= x + 'a';
            decryptedText.append((char) (x));
        }

        return decryptedText.toString();
    }

    private String cyclicKeyVigenere(String key, String zt){
        int ztLength= zt.length();
        int keyLength= key.length();
        StringBuilder cyclicKey= new StringBuilder();

        for(int i= 0; i< ztLength; i++){
            cyclicKey.append(key.charAt(i % keyLength));
        }

        return cyclicKey.toString();
    }
}
