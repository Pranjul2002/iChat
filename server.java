package chatApp;

import java.io.IOException;
import java.io.PrintStream;
import java.net.*;
import java.util.Scanner;

public class server{

    ServerSocket server;
    Socket socket;
    Scanner scan;     //reading recieving
    PrintStream ps;  //sending wrinting
    
    public server() throws IOException{
        server=new ServerSocket(9999);
        System.out.println("Waiting..............");
        socket=server.accept();

        scan=new Scanner(socket.getInputStream());
        ps=new PrintStream(socket.getOutputStream());


        startReading();
        startWriting();
    }

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
                    System.out.println("client: "+msg);
                }             
            }
        };
        new Thread(r1).start();
    }

    public void startWriting(){
        Runnable r2=new Runnable() {

            @Override
            public void run() {
                System.out.println("writer stared......");
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
        new server();
    }
}