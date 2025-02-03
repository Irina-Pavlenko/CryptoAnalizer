public class Cipher {
    private final Constants alphabet;
     String text;
     int key;

    public Cipher(Constants alphabet) {
        this.alphabet = alphabet;
    }

    public String encrypt(String text, int key) {
        validateInput(text, key);
        return process(text, key);
    }

    private void validateInput(String text, int key) {
        this.text = text;
        this.key = key;
    }

    public String decrypt(String text, int key) {
        validateInput(text, key);
        return process(text, - key);
    }

    public void bruteForceDecrypt(String text){
        for (int key = 0; key < alphabet.getSize(); key++) {
            String decryptedText = decrypt(text,key);
            System.out.println("Key: " + key + " -> Decrypted Text: " + decryptedText);
        }
    }
    private String process(String text, int key) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < text.length();i++){
            Character originalChar = Character.toLowerCase(text.charAt(i));
            int originalCharIndex = alphabet.getCharIndex(originalChar);
            int newCharIndex = (alphabet.getSize()+(originalCharIndex + key))%alphabet.getSize();

            result.append(alphabet.getCharByIndex(newCharIndex));
        }
        return result.toString();
    }
}
