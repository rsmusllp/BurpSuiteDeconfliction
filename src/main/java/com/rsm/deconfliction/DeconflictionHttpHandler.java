package com.rsm.deconfliction;

import burp.api.montoya.http.handler.*;
import burp.api.montoya.http.message.HttpHeader;
import burp.api.montoya.http.message.requests.HttpRequest;

import static burp.api.montoya.http.handler.RequestToBeSentAction.continueWith;
import static burp.api.montoya.http.handler.ResponseReceivedAction.continueWith;

public class DeconflictionHttpHandler implements HttpHandler {
    private DeconflictionTab deconflictionTab;
    public DeconflictionHttpHandler(DeconflictionTab deconflictionTab) {
        this.deconflictionTab = deconflictionTab;
    }


    @Override
    public RequestToBeSentAction handleHttpRequestToBeSent(HttpRequestToBeSent requestToBeSent) {
        HttpHeader headerToModify = requestToBeSent.header(deconflictionTab.getHeaderName());
        HttpHeader deconflictionHeader;
        if (headerToModify != null) {
            String deconflictionString = headerToModify.value() + " " + deconflictionTab.deconflictionString;
            deconflictionHeader = HttpHeader.httpHeader(headerToModify.name(),deconflictionString);
        } else {
            deconflictionHeader = HttpHeader.httpHeader(deconflictionTab.getHeaderName(),deconflictionTab.deconflictionString);
        }
        HttpRequest modifiedRequest = requestToBeSent.withHeader(deconflictionHeader);

        return continueWith(modifiedRequest);
    }

    @Override
    public ResponseReceivedAction handleHttpResponseReceived(HttpResponseReceived responseReceived) {
        return continueWith(responseReceived);
    }
}