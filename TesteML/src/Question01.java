import java.util.Scanner;

/**
 * @author brunorocha
 */
public class Question01 {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        int n;
        n = Integer.parseInt(in.nextLine().trim());

        fizzBuzz(n);

    }

    static void fizzBuzz(int n) {

        for(int i = 1; i <= n; i ++) {

            Boolean fizz = Boolean.FALSE;
            Boolean buzz = Boolean.FALSE;

            if(i % 3 == 0) {
                fizz = Boolean.TRUE;
            }

            if(i % 5 == 0) {
                buzz = Boolean.TRUE;
            }

            if(fizz && buzz) {
                System.out.println("FizzBuzz");
            }

            if(fizz && !buzz) {
                System.out.println("Fizz");
            }

            if(!fizz && buzz) {
                System.out.println("Buzz");
            }

            if(!fizz && !buzz) {
                System.out.println(i);
            }
        }



    }
}
