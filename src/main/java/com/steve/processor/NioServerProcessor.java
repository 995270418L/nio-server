package com.steve.processor;

import java.util.Queue;

/**
 * @Author: steve
 * @Date: Created in 10:48 2018/5/20
 * @Description:
 * @Modified By:
 */
public class NioServerProcessor {
    private final Queue inboundQueue;

    public NioServerProcessor(Queue inboundQueue) {
        this.inboundQueue = inboundQueue;
    }

}
