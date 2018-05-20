package com.steve.common;

/**
 * @Author: steve
 * @Date: Created in 9:56 2018/5/20
 * @Description:
 * @Modified By:
 */
public class MessageBuffer {

    public static final int KB = 1024;
    public static final int MB = 1024 * KB;

    /**
     * 对不同大小的请求进行扩容处理。
     */
    public static final int SMALL_CAPACITY = 4 * KB;
    public static final int MEDIUM_CAPACITY = 128 * KB;
    public static final int LARGE_CAPACITY = 1024 * KB;

    private byte[] smallMessageBuffer = new byte[4*KB];
    private byte[] mediumMessageBuffer = new byte[128*KB];
    private byte[] largeMessageBuffer = new byte[MB];


}
