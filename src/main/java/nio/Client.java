package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Client {
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1", 8000);
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(inetSocketAddress);
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        while (true) {
            System.out.println("Введите текст или end");
            String message = scanner.nextLine();
            if (message.equalsIgnoreCase("end")) {
                break;
            }
            socketChannel.write(ByteBuffer.wrap(message.getBytes(StandardCharsets.UTF_8)));
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(byteBuffer);
            socketChannel.read(byteBuffer);
            byteBuffer.flip();
            System.out.println(new String(byteBuffer.array(),
                    byteBuffer.position(),
                    byteBuffer.remaining(),
                    StandardCharsets.UTF_8));
            byteBuffer.clear();
        }

    }
}
