import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;
import java.io.FileWriter;

public class Main {

    public static int longestMatch(String data, int i , int j){
        int res = -1 ;
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
        String result = "";
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
            //System.out.println(result);
        }
        catch (FileNotFoundException e){
            System.out.println("File Not Found");
        }
        try{
            FileWriter f = new FileWriter("Compress_Result.txt",false);
            f.write(result);
            f.close();

        }
        catch (IOException e){
            System.out.println("File Not Found");
        }
    }
    public static void deComp(File file){
        //System.out.println(arr[0].substring(1) +"---" +arr[1]+"---" +arr[2].substring(0,arr[2].length()-1));
        String data ="";
        try{
            Scanner read  = new Scanner(file);
            while (read.hasNextLine()){
                String[] arr = read.nextLine().split(",");
                int start = Integer.parseInt(arr[0].substring(1));
                int count = Integer.parseInt(arr[1]);

                if (start == 0 && count == 0){
                    data += arr[2].substring(0,arr[2].length()-1);
                }
                else {
                    int len =data.length();
                    for (int i = 0; i < count ; i++) {
                        //System.out.println( "next = "+arr[2].substring(0,arr[2].length()-1)+"  start="+start+ "  count = "+ count + "  datalen = "+data.length() + "  =>>>"+data);
                        data += data.charAt(len-start);
                        start--;
                    }
                    data += arr[2].substring(0,arr[2].length()-1);
                }

            }

        }
        catch (FileNotFoundException e ){
            System.out.println("File Not Found");
        }
        try{
            FileWriter f = new FileWriter("Decompress_Result.txt",false);
            f.write(data);
            f.close();

        }
        catch (IOException e){
            System.out.println("File Not Found");
        }



    }
    public  static void main(String[] args) {
        String answer = "exit",fileName = "test.txt";
        System.out.println("☼ Welcome:) ☼");
        System.out.println("☼ What you want to do ? ☼");
        while (true) {
            System.out.println("☼ 1-Compression");
            System.out.println("☼ 2-Decompression");
            System.out.println("☼ 0-exit");
            System.out.print(">>>>>>>>>>  ");
            Scanner read = new Scanner(System.in);
            answer = read.nextLine();
            if (answer.equalsIgnoreCase("1") || answer.equalsIgnoreCase("Compression")) {
                System.out.print("☼ Please Enter File Name You Want to Compress: ");
                fileName = read.nextLine();
                File file = new File(fileName);
                comp(file);
            } else if (answer.equalsIgnoreCase("2") || answer.equalsIgnoreCase("Decompression")) {
                System.out.print("☼ Please Enter File Name You Want to Decompress: ");
                fileName = read.nextLine();
                File file = new File(fileName);
                deComp(file);
            } else {
                System.out.println("☼ Exiting ☼ ");
                break;
            }
        }
        //  comp or deComp ?
        // take file name
        // validate file name
        // pass to comp or deComp
        // output result
        // Repeat


    }
}