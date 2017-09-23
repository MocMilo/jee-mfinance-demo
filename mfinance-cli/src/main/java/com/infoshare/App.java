package com.infoshare;

import com.infoshare.controller.MainController;

public class App {
    public static void main(String[] args) {

        MainController mainController = new MainController();
        mainController.initialize(args);
    }
}
