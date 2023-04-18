package ru.tinkoff.edu.java.scrapper.service;

public interface UserService {
    void register(long tgChatId);
    void unregister(long tgChatId);
}
