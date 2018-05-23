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
    private IMessageReader messageReader;
    private IMessageWrite messageWrite;

    public Socket(SocketChannel socketChannel) {
        this.socketChannel = socketChannel;
    }

    public int read(ByteBuffer byteBuffer) throws IOException {
        if(socketChannel == null){
            return -1;
        }
        int byteRead = socketChannel.read(byteBuffer);
        int totalRead = byteRead;
        while(byteRead > 0){
            byteRead = socketChannel.read(byteBuffer);
            totalRead += byteRead;
        }

        return totalRead;
    }

    public int write(ByteBuffer byteBuffer) throws IOException {
        if(socketChannel == null){
            return -1;
        }
        int byteWrite = socketChannel.write(byteBuffer);
        return byteWrite;
    }

    public long getSocketId() {
        return socketId;
    }

    public void setSocketId(long socketId) {
        this.socketId = socketId;
    }

    public SocketChannel getSocketChannel() {
        return socketChannel;
    }

    public IMessageReader getMessageReader() {
        return messageReader;
    }

    public void setMessageReader(IMessageReader messageReader) {
        this.messageReader = messageReader;
    }

    public IMessageWrite getMessageWrite() {
        return messageWrite;
    }

    public void setMessageWrite(IMessageWrite messageWrite) {
        this.messageWrite = messageWrite;
    }

}
