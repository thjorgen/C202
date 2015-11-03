import java.io.*;
import java.util.*;
/**
 *
 * @author Thomas
 */
public class Spellchecker {
    private final MyLinkedList [] dictionary = new MyLinkedList [26];
    private long notFoundWords;
    private long wordsFoundComparison;
    private int [] stringComparisons = new int[1];
    private long foundWords;
    private long wordsNotFoundComparison;
    
    /**
     * Default constructor of SpellChecker objects.  Putting all counters at 0
     */
    public Spellchecker(){
        stringComparisons[0] = 0;
        notFoundWords = 0;
        wordsFoundComparison = 0;
        foundWords = 0;
        wordsNotFoundComparison = 0;
        for (int i =0; i < dictionary.length; i++){
            dictionary[i] = new MyLinkedList<String>();
        }
    }
    
    /**
     * This method reads the dictionary.txt file and creates an string array
     * with every entry being lower case so the case doesn't matter.  
     */
    public void readDictionary(){
        File f = new File("random_dictionary.txt");
        try{
            Scanner inf = new Scanner(f);
            while (inf.hasNext ()){
                String word = (inf.nextLine().toLowerCase());
                dictionary [(int)word.charAt(0) - 97].addFirst(word);
            }
            inf.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }// End read dictionary
    
    /**
     * This method creates words without different cases such as , or - or !.  
     * Once the word has been created it is then passed into the binary search 
     * array.  If the word is incorrect it prints out the word and increments 
     * the misspelled word counter and the misspelled recursive steps, if the 
     * word is correct it increments the correct word counter and the correct 
     * word recursive steps. 
     */
    public void writeStringParser(){
        try {
            FileInputStream inf = new FileInputStream(new File("oliver.txt"));
            char let;
            String str=""; //word to be processed
            
            int n = 0;
            while ((n = inf.read()) != -1){
                let = (char)n;
                if (Character.isLetter(let))
                    str += Character.toLowerCase(let);
                if ((Character.isWhitespace(let) || let =='-') && !str.isEmpty()){
                    stringComparisons[0]=0;
                    if(dictionary[(int)str.charAt(0)-97].contains(str, stringComparisons)){
                        foundWords++;
                        wordsFoundComparison += stringComparisons[0];
                    }
                    else{
                        notFoundWords++;
                        wordsNotFoundComparison += stringComparisons[0];
                    }
                    str="";
                }
            }
            inf.close(); 
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    /**
     * Tests the methods created above.  
     * @param args
     */
    public static void main(String[] args){
        Spellchecker ex = new Spellchecker();
        ex.readDictionary();
        ex.writeStringParser();
        System.out.println("Number of misspelled words: " + ex.notFoundWords);
        System.out.println("Number of correct spelled words: " + 
                ex.foundWords);
        System.out.println("Number of misspelled words comparisons: " +
                ex.wordsNotFoundComparison);
        System.out.println("Number of correct spelled words comparisons: " + 
                ex.wordsFoundComparison);
        System.out.println("The average number of comparisons for words "
                + "found: " + (double)ex.wordsFoundComparison/ex.foundWords);
        System.out.println("The average number of comparisons for words not "
                + "found: " + (double)ex.wordsNotFoundComparison/ex.notFoundWords);
    }
}

/**
 * Output: 
 * run:
 * Number of misspelled words: 54648
 * Number of correct spelled words: 937492
 * Number of misspelled words comparisons: 403377564
 * Number of correct spelled words comparisons: 3049431067
 * The average number of comparisons for words found: 3252.7542283027483
 * The average number of comparisons for words not found: 7381.378348704436
 * BUILD SUCCESSFUL (total time: 1 minute 0 seconds)
 */