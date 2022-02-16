package com.night.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

/**
 * （1）可读 : SelectionKey.OP_READ
 * （2）可写 : SelectionKey.OP_WRITE
 * （3）连接 : SelectionKey.OP_CONNECT
 * （4）接收 : SelectionKey.OP_ACCEPT
 * @author night
 */
public class Service {
    public static void testService() throws IOException {

        // 选择Selector
        Selector selector = Selector.open();

        // 获取通道
        ServerSocketChannel channel = ServerSocketChannel.open();

        // 设置非阻塞
        channel.configureBlocking(false);

        // 绑定连接
        channel.bind(new InetSocketAddress("127.0.0.1", 9876));

        // 将通道注册到选择器，并注册操作 "接收"
        channel.register(selector, SelectionKey.OP_ACCEPT);

        // 采用轮训到方式， 查询获取"准备就绪"的注册过到操作
        while (selector.select() > 0) {
            // 获取当前选择器中 所有注册的选择键
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                // 获取"准备就绪"的键
                SelectionKey selectionKey = iterator.next();

                // 判断key具体事件
                if (selectionKey.isAcceptable()) {
                    // 如果是接收就绪 操作， 就获取客户端连接
                    SocketChannel accept = channel.accept();
                    // 切换非阻塞
                    accept.configureBlocking(false);
                    accept.register(selector, SelectionKey.OP_READ);
                } else if (selectionKey.isReadable()) {
                    // 获取选择器 傻姑娘 "读就绪"状态的通道
                    SocketChannel accept = (SocketChannel) selectionKey.channel();

                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                    int length = 0;
                    while ((length = accept.read(byteBuffer)) != -1) {
                        byteBuffer.flip();
                        System.out.println(new String(byteBuffer.array(), 0, length));
                        byteBuffer.clear();
                    }
                    accept.close();
                }

                iterator.remove();
            }
        }
    }


    public static void main(String[] args) throws IOException {
        testService();
    }
}
