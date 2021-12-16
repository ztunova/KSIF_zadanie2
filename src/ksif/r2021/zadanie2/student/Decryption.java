package ksif.r2021.zadanie2.student;

public class Decryption {

    public String decryptVigenere(String key, String zt){
        String cyclicKey= cyclicKeyVigenere(key, zt);
        StringBuilder decryptedText= new StringBuilder();
        int x;

        for(int i= 0; i< zt.length(); i++){
            x= (zt.charAt(i) - cyclicKey.charAt(i) + 26) %26;
            x= x + 'a';
            decryptedText.append((char) (x));
        }
        System.out.println("decrypted text: " + decryptedText.toString());
        return decryptedText.toString();
    }

    public String cyclicKeyVigenere(String key, String zt){
        int ztLength= zt.length();
        int keyLength= key.length();
        StringBuilder cyclicKey= new StringBuilder();

        for(int i= 0; i< ztLength; i++){
            cyclicKey.append(key.charAt(i % keyLength));
        }
        System.out.println("cyclic key: " + cyclicKey.toString());
        return cyclicKey.toString();
    }
}
