package com.geekbrains.book.store.console;

import com.geekbrains.book.store.console.consumer.SimpleReceiverApp;
import com.geekbrains.book.store.console.util.CommandHandler;

public class MainApp {

    private static void run() throws Exception {
        SimpleReceiverApp simpleReceiverApp = new SimpleReceiverApp();
        Thread threadSimpleReceiverApp = new Thread(simpleReceiverApp);
        threadSimpleReceiverApp.start();
        new CommandHandler(simpleReceiverApp).consoleRead();
    }

    public static void main(String[] args) {
        try {
            run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
