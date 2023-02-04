package chatApp.src;

import java.io.IOException;
import java.io.PrintStream;
import java.net.*;
import java.util.Scanner;

public class clientHandler implements Runnable{
    
    Socket client;
    int clientNum;
    Scanner in;
    PrintStream out;

    clientHandler(Socket client,int clientNum) throws IOException{
        this.client=client;
        this.clientNum=clientNum;
    }

    @Override
    public void run() {
        try{
            in=new Scanner(client.getInputStream());
            out=new PrintStream(client.getOutputStream());
            while(true){
                String recieveMsg=in.nextLine();
                System.out.println(clientNum+": "+recieveMsg);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
}
