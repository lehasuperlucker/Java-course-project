package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;

public class Server{
    public Server(){};

    public static void main(String[] args)
    {
        try
        {
            int counter=0;
            ServerSocket serverSocket=new ServerSocket(2525);
            System.out.println("Server started...");
             while (true)
             {
                 Socket socket=serverSocket.accept();
                 System.out.println(socket.getInetAddress().getHostName() + " connected");
                 ++counter;
                 System.out.println("Client №" + counter + " connected");

                 ServThread thread = null;
                 try {
                     thread = new ServThread(socket, counter);
                 } catch (SQLException e) {
                     e.printStackTrace();
                 } catch (ClassNotFoundException e) {
                     e.printStackTrace();
                 }
                 thread.start();
             }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}