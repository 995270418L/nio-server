package com.steve.common;

import java.util.logging.Logger;

/**
 * @Author: steve
 * @Date: Created in 9:56 2018/5/20
 * @Description:
 * @Modified By:
 */
public class MessageBuffer {

    private Logger logger = Logger.getLogger(MessageBuffer.class.getName());

    public static final int KB = 1024;
    public static final int MB = 1024 * KB;

    /**
     * 对不同大小的请求进行扩容处理。
     */
    public static final int SMALL_CAPACITY = 4 * KB;
    public static final int MEDIUM_CAPACITY = 128 * KB;
    public static final int LARGE_CAPACITY = 1024 * KB;

    /**
     * 具体消息字节数的储存。
     */
    private byte[] smallMessageBuffer = new byte[SMALL_CAPACITY];
    private byte[] mediumMessageBuffer = new byte[MEDIUM_CAPACITY];
    private byte[] largeMessageBuffer = new byte[LARGE_CAPACITY];

    public Message getMessage(){
        Message message = new Message(this);
        message.setMessageArray(smallMessageBuffer);
        message.setOffset(0);
        message.setCapacity(SMALL_CAPACITY);
        message.setLength(0);
        return message;
    }

    public boolean expendMessage(Message message) {
        if(message.getCapacity() == SMALL_CAPACITY){
            return moveMessage(message,mediumMessageBuffer,MEDIUM_CAPACITY);
        }else if(message.getCapacity() == MEDIUM_CAPACITY){
            return moveMessage(message,largeMessageBuffer,LARGE_CAPACITY);
        }else{
            logger.warning("The MessageBuffer is under largeMessageBuffer, can't expend ");
            return false;
        }
    }

    private boolean moveMessage(Message message, byte[] mediumMessageBuffer, int mediumCapacity) {
        // 这是通过复制来实现array扩容的。
        System.arraycopy(message.getMessageArray(),message.getOffset(),mediumMessageBuffer,message.getLength(),message.getLength());
        message.setCapacity(mediumCapacity);
        message.setMessageArray(mediumMessageBuffer);
        return true;
    }
}
