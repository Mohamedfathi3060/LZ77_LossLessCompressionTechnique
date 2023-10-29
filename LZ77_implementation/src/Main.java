import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static int longestMatch(String data, int i , int j){
        int res = -1 ;
        // i=3  j = 5 ABCABC
        for (int k = 0; k <= i-1; k++) {
             int win = k ;
             int my = i;
             while (win < i && my  <=  j){
                 if (data.charAt(win) != data.charAt(my)){
                     break;
                 }
                 win++;
                 my++;
             }
            if(my  ==  j+1) res = k;


        }
        return res ;
    }
    public static void comp(File file){
        try{
            Scanner read  = new Scanner(file);
            String data ="";
            while (read.hasNextLine()){
                data = data.concat(read.nextLine());
            }
            // ABCDEF   DEF
            // A B A A B A B A A B B B B B B B B B B B B A
            int  i = 0 ;
            int  j = i ;
            String result = "";
            while( i  < data.length() ){

                int prev  = -1 ;
                int x = -1;
                do{
                    prev = x;
                    x = longestMatch(data,i,j++);
                }while (x != -1 && j  < data.length());



                result += "<"+ (prev ==-1? 0 :(i-prev)) + "," + (j-i-1) + ","+ data.charAt(j-1) +">" +'\n';
                i = j ;

            }
            System.out.println(result);


        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }
    public void deComp(){

    }
    public  static void main(String[] args) {
        File f = new File("test.txt");
        comp(f);
        //  comp or deComp ?
        // take file name
        // validate file name
        // pass to comp or deComp
        // output result
        // Repeat


    }
}