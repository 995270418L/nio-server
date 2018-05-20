package com.steve.accept;

import com.steve.common.Socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.logging.Logger;

/**
 * @Author: steve
 * @Date: Created in 10:18 2018/5/20
 * @Description: 单独的线程去运行监听NioServer的程序
 * @Modified By:
 */
public class NioServerAccept implements Runnable {

    private Logger logger = Logger.getLogger(NioServerAccept.class.getName());

    private ServerSocketChannel serverSocketChannel;
    private final int tcpPort;
    private final ArrayBlockingQueue inboundQueue;

    public NioServerAccept(int tcpPort, ArrayBlockingQueue inboundQueue){
        this.tcpPort = tcpPort;
        this.inboundQueue = inboundQueue;
    }

    @Override
    public void run() {
        ServerSocketChannel serverSocketChannel = null;
        try {
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.bind(new InetSocketAddress(tcpPort));
        } catch (IOException e) {
            e.printStackTrace();
        }

        while(true){
            try {
                SocketChannel socketChannel = serverSocketChannel.accept();
                Socket socket = new Socket(socketChannel);

                this.inboundQueue.put(socket);
            } catch (IOException e) {
                logger.severe("ServerSocketChannel 接受连接异常");
                e.printStackTrace();
            } catch (InterruptedException e) {
                logger.severe("线程中断异常");
                e.printStackTrace();
            }
        }
    }
}
