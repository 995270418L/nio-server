package com.steve.common;

public interface IMessageWrite {
    IMessageWrite newMessageWrite();
    void writeToMessage(Message message);
}
