package com.steve.common;

import java.nio.ByteBuffer;
import java.util.logging.Logger;

/**
 * @Author: steve
 * @Date: Created in 9:47 2018/5/20
 * @Description: 定义消息Bean
 * @Modified By:
 */
public class Message {

    private Logger logger = Logger.getLogger(Message.class.getName());
    private final MessageBuffer messageBuffer;
    private byte[] messageArray = null;
    private int offset = 0;
    private int length = 0;
    private int capacity = 0;

    public Message(MessageBuffer messageBuffer){
        this.messageBuffer = messageBuffer;
    }

    public Message getMessage(){

    }

    /**
     * 将bytebuffer中的数据写入message对象中。
     * @param byteBuffer
     * @return
     */
    public int writeToMessage(ByteBuffer byteBuffer) {
        int remain = byteBuffer.remaining();
        while(remain + length > capacity){
            // 扩容处理
            if(!this.messageBuffer.expendMessage(this)){
                logger.severe("messageBuffer's shareArray has been the large capacity,it can't expend anymore.");
                return -1; // 表示扩容失败。
            }
        }
        int byteToCopy = Math.min(remain,offset+length);
        // 将buffer中的字节写入数组里面。
        byteBuffer.get(messageArray,offset+length, byteToCopy);
        length += byteToCopy;

        return byteToCopy;
    }

    public MessageBuffer getMessageBuffer() {
        return messageBuffer;
    }

    public byte[] getMessageArray() {
        return messageArray;
    }

    public void setMessageArray(byte[] messageArray) {
        this.messageArray = messageArray;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
