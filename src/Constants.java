import java.util.*;

public class Constants {
    private static final Character[] ALPHABET = {
            'а','б','в','г','д','е','ж','з','и','й','к','л',
            'м','н','о','п','р','с','т','у','ф','х','ц','ч',
            'ш','щ','ъ','ы','ь','э','ю','я','!','"','@','#',
            '№',';','$','%',':','^','&','?','*','(',')','_',
            '-','=','+',',','.','/','\\','|',' ','0','1','2',
            '3','4','5','6','7','8','9'
    };

    private final List<Character> alphabet;
    private final Map<Character, Integer> charIndexes;

    public Constants(){
        List<Character> temporaryAlphabet = new ArrayList<>();

        temporaryAlphabet.addAll(Arrays.asList(ALPHABET));

        alphabet = List.copyOf(temporaryAlphabet);

        charIndexes = new HashMap<>();
        for (int i = 0; i< alphabet.size();i++){
            charIndexes.put(alphabet.get(i),i);
        }
    }

    public Character getCharByIndex(int index){
        if (index < 0 || index >= alphabet.size()){
            throw new IllegalArgumentException("Invalid index. Index:" + index + ". Valid is from 0 to" + alphabet.size());
        }
        return alphabet.get(index);
    }

    public int getCharIndex(Character character){
        if (!charIndexes.containsKey(character)){
            throw new IllegalArgumentException("Invalid index. Char:" + character + ". ");
        }
        return charIndexes.get(character);
    }

    public int getSize() {
        return alphabet.size();
    }
}
