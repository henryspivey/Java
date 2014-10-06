// Look for lines that begin with two slashes! These are "comments" and 
// we will use them to let you know what you should do on each programming
// exercise. Have fun!
/*
public class TwoLineHelloWorld
{
    public static void main(String args[])
    {
        System.out.println("Hello World!");
        // Hint2: You want to use System.out.println twice. Once to print "Hello" and a second time to print "World" 
    }
}
*/
public class Hi {

    public static void main(String[] args) {
        System.out.print("Hi, ");
        System.out.print(args[0]);
        System.out.println(". How are you?");
    }

}
