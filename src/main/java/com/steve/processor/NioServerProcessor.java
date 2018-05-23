package com.steve.processor;

import com.steve.common.IMessageReader;
import com.steve.common.IMessageWrite;
import com.steve.common.Socket;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * @Author: steve
 * @Date: Created in 10:48 2018/5/20
 * @Description:
 * @Modified By:
 */
public class NioServerProcessor implements Runnable{

    private final ArrayBlockingQueue<Socket> inboundQueue;

    private long NEXTSOCKETID= 16*1024 ;

    private Selector socketSelector;
    private final IMessageReader messageReader;
    private final IMessageWrite messageWrite;
    private final ByteBuffer READBUFFER = ByteBuffer.allocate(1024*1024); // 1MB
    private final ByteBuffer WRITEBUFFER = ByteBuffer.allocate(1024*1024); //1MB

    public NioServerProcessor(ArrayBlockingQueue inboundQueue, IMessageReader messageReader, IMessageWrite messageWrite) {
        this.inboundQueue = inboundQueue;
        this.messageReader = messageReader;
        this.messageWrite = messageWrite;
    }

    public void init() throws IOException {
        socketSelector = Selector.open();
    }

    @Override
    public void run() {
        // 从socket队列中取出socket
        try {
            takeNewSockets();
            readFromSocket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readFromSocket() throws IOException {
        int selectRead = socketSelector.selectNow();
        if(selectRead > 0) {
            Iterator<SelectionKey> keys = socketSelector.keys().iterator();
            while (keys.hasNext()) {
                SelectionKey key = keys.next();
                readFromKey(key);
                keys.remove();
            }
        }
    }

    private void readFromKey(SelectionKey key) throws IOException {
        if(key.isReadable()){
            Socket socket = (Socket) key.attachment();
            IMessageReader reader = socket.getMessageReader();
            reader.read(socket,READBUFFER);

        }
    }

    private void takeNewSockets() throws IOException {
        Socket socket = inboundQueue.poll();
        while (socket != null){
            socket.setSocketId(NEXTSOCKETID++);
            SocketChannel sc = socket.getSocketChannel();
            sc.configureBlocking(false);
            socket.setMessageReader(messageReader.newMessageReader());
            socket.setMessageWrite(messageWrite.newMessageWrite());

            //给每个socket注册读事件，用于读取客户端请求的数据。
            SelectionKey key = sc.register(socketSelector, SelectionKey.OP_READ);
            key.attach(socket);
            socket = inboundQueue.poll();
        }
    }

}
