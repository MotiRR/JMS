package com.geekbrains.book.store.console.util;

import com.geekbrains.book.store.console.consumer.SimpleReceiverApp;
import com.geekbrains.book.store.console.producer.SimpleSenderApp;

import java.util.Scanner;

public class CommandHandler {

    private String[] argsConsole;
    private SimpleReceiverApp simpleReceiverApp;
    private SimpleSenderApp simpleSenderApp;
    private boolean contain;

    public CommandHandler(SimpleReceiverApp simpleReceiverApp) {
        this.simpleReceiverApp = simpleReceiverApp;
        simpleSenderApp = new SimpleSenderApp();
    }

    public void consoleRead() {
        try (Scanner in = new Scanner(System.in)) {
            while (in.hasNextLine()) {
                try {
                    argsConsole = in.nextLine().split(" ");
                    if (argsConsole[0].equals("/Готово")) {
                        contain = simpleReceiverApp.getMap().containsKey(argsConsole[1]);
                        if (contain)
                            simpleSenderApp.send(argsConsole[1]);
                    }
                } catch (ArrayIndexOutOfBoundsException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
