package am.basic.jdbc.util;

public class Encryption {


    private static final String privateKey = "AAAAA";
    //barev

    public static String encrypt(String clean) {
        char[] encryptedChars = new char[clean.length()];
        int encryptionKeyIndex = 0;
        for (int i = 0; i < clean.length(); i++) {
            encryptedChars[i] = (char) (clean.charAt(i) + privateKey.charAt(encryptionKeyIndex));
            encryptionKeyIndex++;
            if (encryptionKeyIndex == privateKey.length()) {
                encryptionKeyIndex = 0;
            }
        }

        return new String(encryptedChars);
    }


    public static String decrypt(String encrypted) {
        if (encrypted == null){
            return null;
        }
        char[] decryptedChars = new char[encrypted.length()];
        int encryptionKeyIndex = 0;
        for (int i = 0; i < encrypted.length(); i++) {
            decryptedChars[i] = (char) (encrypted.charAt(i) - privateKey.charAt(encryptionKeyIndex));
            encryptionKeyIndex++;
            if (encryptionKeyIndex == privateKey.length()) {
                encryptionKeyIndex = 0;
            }
        }

        return new String(decryptedChars);
    }

    public static void main(String[] args) {
        String encrypted = encrypt("hello");
        System.out.println(encrypted);
        String clean = decrypt(encrypted);
        System.out.println(clean);
    }

}
