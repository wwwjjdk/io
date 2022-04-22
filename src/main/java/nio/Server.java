package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

public class Server {
    public static void main(String[] args) throws IOException {

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open()
                .bind(new InetSocketAddress(8000));
        while (true) {
            try (SocketChannel socketChannel = serverSocketChannel.accept()) {


                ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                while (socketChannel.isConnected()) {
                    int byteCount = socketChannel.read(byteBuffer);// записываем в буфер + кол-во присваиваем в объект
                    if (byteCount == -1) {// если считали 0 и выдало -1, прекращаем работу
                        break;
                    }
                    byteBuffer.flip();//переворот
                    String str0 = new String(byteBuffer.array(),//читаем с буфера
                            byteBuffer.position(),//до куда записали инфу, которую прочли
                            byteBuffer.remaining(),//разница между позицией и лимитом, для определения длины сколько не читать
                            StandardCharsets.UTF_8);//кодировка для записи в объект
                    byteBuffer.clear();//отчистка буфера
                    String str = str0.replaceAll("\\s", "");
                    socketChannel.write(ByteBuffer.wrap(("Ваше сообщение без пробелов: " + str).getBytes(StandardCharsets.UTF_8)));

                }

            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }


    }
}
