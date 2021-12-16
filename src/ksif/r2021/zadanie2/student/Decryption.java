package ksif.r2021.zadanie2.student;

public class Decryption {

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
