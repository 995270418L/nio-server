package com.steve.common;

import java.io.IOException;
import java.nio.ByteBuffer;

public interface IMessageReader {

    IMessageReader newMessageReader();

    int read(Socket socket, ByteBuffer byteBuffer) throws IOException;

}
