package my_rel;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 8000);

        try(
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter printWriter = new PrintWriter(socket.getOutputStream())
                ){
            System.out.println(bufferedReader.readLine());
            printWriter.println(scanner.nextLine());

            System.out.println(bufferedReader.readLine());
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

}
