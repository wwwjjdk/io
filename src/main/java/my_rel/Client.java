package my_rel;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 8000);
        try (
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
                Scanner scanner = new Scanner(System.in)
        ) {
            while (true) {
                System.out.println(bufferedReader.readLine());
                String str = scanner.nextLine();
                printWriter.println(str);
                if (str.equalsIgnoreCase("end")) {
                    break;
                }
                System.out.println("Server: " + bufferedReader.readLine());
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
