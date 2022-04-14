package my_rel;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8000);
        System.out.println("Сервер вкл");
        while (true) {
            try (
                    Socket clientSocket = serverSocket.accept();//жду подключения
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true)
            ) {
                System.out.printf("Сервер принял клиента по порту %s\n", clientSocket.getPort());
                writer.println("Введите число или end");
            String str;
                while((str = bufferedReader.readLine()) != null){
                    if(str.equalsIgnoreCase("end")){
                        break;
                    }
                    writer.println(fib(Integer.parseInt(str)));
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static int fib(int i) {
        if (i == 0 || i == 1) return i;
        return fib(i - 1) + fib(i - 2);
    }
}
