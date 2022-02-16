package com.night.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

public class Client {
    public static void testClient() throws IOException {
        InetSocketAddress address = new InetSocketAddress("127.0.0.1", 9876);

        // 获取通道
        SocketChannel socketChannel = SocketChannel.open(address);
        // 切换非阻塞
        socketChannel.configureBlocking(false);

        // 分配缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byteBuffer.put("hello world".getBytes(StandardCharsets.UTF_8));
        byteBuffer.flip();
        socketChannel.write(byteBuffer);

        socketChannel.close();
    }

    public static void main(String[] args) {
        try {
            testClient();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
