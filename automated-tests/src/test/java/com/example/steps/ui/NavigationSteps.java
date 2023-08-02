package com.example.steps.ui;

import static com.codeborne.selenide.Selenide.open;

public class NavigationSteps {

    final static String baseURL = "http://localhost:8079";
    public static void openRemindersApp() {
        open(baseURL);
    }

}
