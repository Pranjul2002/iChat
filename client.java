package chatApp;

import java.io.IOException;
import java.io.PrintStream;
import java.net.*;
import java.util.Scanner;

public class client {
    
    Socket socket;
    Scanner scan;       //reading OR recieving
    PrintStream ps;     //writing OR seding


    client() throws IOException{
        System.out.println("sending request to server");
        socket=new Socket("localhost",9999);
        System.out.println("Connected.........");

        scan=new Scanner(socket.getInputStream());
        ps=new PrintStream(socket.getOutputStream());


        startReading();
        startWriting();
    }

    //RECIEVING function
    public void startReading(){
        Runnable r1=new Runnable() {

            @Override
            public void run() {
                while(true){
                    String msg=scan.nextLine();
                    if(msg.equals("exit")){
                        System.out.println("client terminated the chat");
                        break;
                    }
                    System.out.println("server: "+msg);
                }
            }
            
        };
        new Thread(r1).start();
    }


    //SENDING Function
    public void startWriting(){
        Runnable r2=new Runnable() {

            @Override
            public void run() {
                while(true){
                    Scanner writemsg=new Scanner(System.in);
                    String content=writemsg.nextLine();
                    ps.println(content);
                }
            }
            
        };
        new Thread(r2).start();
    }

    public static void main(String[] args) throws Exception{
        new client();
    }
}
