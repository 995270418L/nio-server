package com.steve.common;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @Author: steve
 * @Date: Created in 9:32 2018/5/20
 * @Description: 抽象出每一个连接的对象socket，内部持有ServerSocketChannel的read和write方法,这个需要是个多例的对象。
 * @Modified By:
 */
public class Socket {

    private long socketId;

    private final SocketChannel socketChannel;

    public Socket(SocketChannel socketChannel) {
        this.socketChannel = socketChannel;
    }

    public int read(ByteBuffer byteBuffer) throws IOException {
        if(socketChannel == null){
            return -1;
        }
        int byteRead = socketChannel.read(byteBuffer);
        return byteRead;
    }

    public int write(ByteBuffer byteBuffer) throws IOException {
        if(socketChannel == null){
            return -1;
        }
        int byteWrite = socketChannel.write(byteBuffer);
        return byteWrite;
    }

}
