package com.steve.http;

import com.steve.common.IMessageReader;
import com.steve.common.Message;
import com.steve.common.Socket;

import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * @Author: steve
 * @Date: Created in 19:45 2018/5/21
 * @Description:
 * @Modified By:
 */
public class HttpMessageReader implements IMessageReader {

    private Message nextMessage;

    @Override
    public IMessageReader newMessageReader() {
        return new HttpMessageReader();
    }

    @Override
    public int read(Socket socket, ByteBuffer byteBuffer) throws IOException {
        // 调用这一步，会将消息写入byteBuffer。
        int totalByte = socket.read(byteBuffer);
        byteBuffer.flip();
        if(!byteBuffer.hasRemaining()){
            return -1;
        }
        nextMessage.writeToMessage(byteBuffer);
        return totalByte;
    }

}
