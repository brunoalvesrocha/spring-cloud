import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @author brunorocha
 */
public class Question02 {

    public static void main(String[] args) throws IOException{
        Scanner in = new Scanner(System.in);
        int _lengths_size = 0;
        int[] res;
        _lengths_size = Integer.parseInt(in.nextLine().trim());
        int[] _lengths = new int[_lengths_size];
        int _lengths_item;
        for(int _lengths_i = 0; _lengths_i < _lengths_size; _lengths_i++) {
            _lengths_item = Integer.parseInt(in.nextLine().trim());
            _lengths[_lengths_i] = _lengths_item;
        }

        res = cutSticks(_lengths);

        for(int res_i=0; res_i < res.length; res_i++) {
            System.out.println(String.valueOf(res[res_i]));
        }

    }

    static int[] cutSticks(int[] lenght) {
        int[] clone = lenght.clone();
        Arrays.sort(clone);
        int[] returned = new int[lenght.length];
        int minorNumber = clone[0];
        if(minorNumber == 0) {
            minorNumber = clone[1];
        }

        for(int i = 0; i < lenght.length; i ++) {
            if(lenght[i] != 0) {
                returned[i] = lenght[i] - minorNumber;
            }
        }

        return returned;
    }

}

