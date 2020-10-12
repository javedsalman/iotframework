package edu.arrowhead;

import java.io.IOException;

public class DisplayService extends Service {


    ASystem incumbent;
    String serviceRequest ;
    String serviceResponse;

    public DisplayService(ASystem incumbent, String serviceName, String serviceURI) {
        super(incumbent, serviceName, serviceURI);
    }

    Asset display = new Display("Display", incumbent);

    @Override
    public void transceive(String payload) throws IOException {
        this.serviceRequest = payload;
        runService();
    }

    @Override
    public String transceive() throws IOException {
        return this.serviceResponse = display.getAssetResponse();
    }

    @Override
    public void runService() {

        display.go(serviceRequest);

    }
}
