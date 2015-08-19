// Bluej project: plurals
public class Word
{
    

    public Word(String letters)
    {
        this.letters = letters.toLowerCase();
    }

    /**
        Forms the plural of this word.
        @return the plural, using the rules for regular nouns
    */
    public String getPluralForm()
    {
        // TODO: Complete this method
        // If the word ends in y preceded by a consonant you take away the y and add ies.
        // If the word ends in y preceded by a vowel, you just add an s.
        // You add an es when a word ends in o, or s, or sh, or ch.
        // In all the other case just add an s.
        // you can use the
        //  isVowel
        //  isConsonant
        //  is
        // methods from below.
        String last = letters.substring(letters.length() - 1);
        String penultimate_letter = letters.substring(letters.length() - 2);
        
        String last_two  = letters.substring(letters.length() - 2,letters.length() - 1);
        
        int index = letters.indexOf(last);
        int penultimate_index = letters.indexOf(penultimate_letter);
        int index_of_last_two = letters.indexOf(last_two);
        
        if (is(index,"y") && isConsonant(penultimate_index))
        {
            String last_letter = "ies";
            letters.replace(last,last_letter);
            
        }
        else if(is(index,"y") && isVowel(penultimate_index))
        {
            String last_letter = "s";
            letters+=last_letter;
            
        }
       
        else if( is(index,"o") || is(index,"s") || (is(index,"h") && isConsonant(penultimate_index)) )
        {
            String last_letter = "es";
            letters+=last_letter;
        }
        else
        {
            String last_letter = "s";
            letters+=last_letter;
        }
        
        return letters;
        
       
        
     
    }

    /**
       Tests whether the ith letter is a vowel.
       @param i the index of the letter to test
       @return true if the ith letter is a vowel
    */
    public boolean isVowel(int i)
    {
        return is(i, "a")
               || is(i, "e")
               || is(i, "i")
               || is(i, "o")
               || is(i, "u");
    }

    /**
       Tests whether the ith letter is a consonant.
       @param i the index of the letter to test
       @return true if the ith letter is a consonant
    */
    public boolean isConsonant(int i)
    {
        return !isVowel(i);
    }

    /**
     * Checks what letter is in position i
     * @return true when the the letter of letters is the given letter.
     *         false otherwise.
     * @param i index in letters
     * @param letter the letter to match with. must be one character long.
     */
    public boolean is(int i, String letter)
    {
        return letters.substring(i, i + 1).equals(letter);
    }
}
