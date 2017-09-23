package com.infoshare;

import com.infoshare.controller.MainController;
import com.infoshare.core.models.exceptions.NoDataForCriteria;

public class App {
    public static void main(String[] args) throws NoDataForCriteria {

        MainController mainController = new MainController();
        mainController.initialize(args);
    }
}
