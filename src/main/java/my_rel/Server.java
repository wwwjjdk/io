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
                    Socket socket = serverSocket.accept();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true)
            ) {
                while (true) {
                    System.out.printf("подключен клиент, порт-> %s\n", socket.getPort());
                    printWriter.println("Введите число или end: ");
                    String str = bufferedReader.readLine();
                    if (str.equalsIgnoreCase("end")) {
                        break;
                    }
                    printWriter.println(fib(Integer.parseInt(str)));
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
