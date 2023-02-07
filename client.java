import java.io.IOException;
import java.io.PrintStream;
import java.net.*;
import java.util.Scanner;

public class client{

    Socket s;
    Scanner in;
    PrintStream out;

    client() throws IOException{
        s=new Socket("localhost",9999);

        in=new Scanner(s.getInputStream());
        out=new PrintStream(s.getOutputStream());

        startReading();
        startWriting();
    }
    
    public void startReading(){
        Runnable r1=new Runnable() {

            @Override
            public void run() {
                while(true){
                    String msgRecieve=in.nextLine();
                    System.out.println(msgRecieve);
                }
            }
        };
        new Thread(r1).start();
    }

    public void startWriting(){
        Runnable r2=new Runnable() {

            @Override
            public void run() {
                while(true){
                    Scanner scan=new Scanner(System.in);
                    String sendMsg=scan.nextLine();
                    out.println(sendMsg);
                }
            }
        };
        new Thread(r2).start();
    }


    public static void main(String[] args) throws IOException {
        new client();
    }
}
