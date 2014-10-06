// BlueJ Project: lesson4/book7
// Video: Working with the Book Text
import java.util.Scanner;
import java.io.File;
public class LookUpAnyWord
{
    public static void main(String[] args)
    {
        System.out.print("Type a word and I'll tell you how many times it appears: ");
        // TODO: Create an scanner object to read the user input
        Scanner read_word = new Scanner(System.in);
        // Read a word from the scanner and assign it to a String variable named word
        String word = read_word.nextLine();
        // TODO: Create a book object that reads from aliceInWonderland.txt
        Book alice = new Book("aliceInWonderland.txt");

        // TODO: Find the number of occurences of that word and assign it to a variable named occurences
        int occurrences = occurrencesOf(word);
        System.out.println(word + " occurs " + occurrences + " times!");
    }
}
