public class Cipher {
    private final Constants alphabet;

    public Cipher(Constants alphabet) {
        this.alphabet = alphabet;
    }

    public String encrypt(String text, int key) {
        return process(text, key);
    }

    public String decrypt(String text, int key) {
        return process(text, - key);
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
    private Character tolowerCase(Character character){
        return (character + "").toLowerCase().charAt(0);
    }
}
