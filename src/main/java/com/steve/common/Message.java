package com.steve.common;

/**
 * @Author: steve
 * @Date: Created in 9:47 2018/5/20
 * @Description: 定义消息Bean
 * @Modified By:
 */
public class Message {

    private final MessageBuffer messageBuffer;
    private byte[] messageArray = null;
    private int offset = 0;
    private int length = 0;
    private int capacity = 0;

    public Message( MessageBuffer messageBuffer){
        this.messageBuffer = messageBuffer;
    }

    public Message getMessage(){

    }

}
