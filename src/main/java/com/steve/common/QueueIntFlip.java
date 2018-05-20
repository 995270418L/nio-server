package com.steve.common;

/**
 * @Author: steve
 * @Date: Created in 10:05 2018/5/20
 * @Description: 消息的offset
 * @Modified By:
 */
public class QueueIntFlip {

    private int capacity;
    public int[] queue;

    private int writeIndex = 0;
    private int readIndex = 0;
    private boolean flip = false;

    public QueueIntFlip(int capacity){
        queue = new int[capacity];
        for(int i=0;i<capacity;i++){
            queue[i] = i;
        }
    }

    public int take(){
        if(!flip){

        }else{

        }
    }

    public void offer(int element){

    }
}
