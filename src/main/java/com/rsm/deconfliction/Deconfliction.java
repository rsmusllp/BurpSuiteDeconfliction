package com.rsm.deconfliction;

import burp.api.montoya.BurpExtension;
import burp.api.montoya.MontoyaApi;

public class Deconfliction implements BurpExtension {
    private static final String VERSION = "1.0";
    private static final String EXTENSION_NAME = "Deconfliction";
    public void initialize(MontoyaApi api) {
        api.extension().setName("Deconfliction");
        api.logging().logToOutput(" == Deconfliction version 1.0 == ");
        api.logging().logToOutput("Appends deconfliction information to all outgoing request from Burp Suite.");
        api.logging().logToOutput("Created by Micah Van Deusen, RSM US LLP.");

        DeconflictionTab tab = new DeconflictionTab(api);
        api.userInterface().registerSuiteTab(EXTENSION_NAME, tab.getMainPanel());

        api.http().registerHttpHandler(new DeconflictionHttpHandler(tab));

    }
}