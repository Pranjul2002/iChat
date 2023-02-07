import java.io.IOException;
import java.net.*;


public class server {

    ServerSocket serverS;
    int clientNum=0;

    server() throws IOException{
        serverS=new ServerSocket(9999);
        System.out.println("Waiting...............");
        while(true){
            Socket client=serverS.accept();
            clientNum++;
            System.out.println("New client is Connected....");
            clientHandler clientHandle=new clientHandler(client,clientNum);
            Thread thread=new Thread(clientHandle);
            thread.start();
        }
    }


    public static void main(String[] args) throws IOException{
        new server();
    }
    
}
