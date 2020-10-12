package edu.arrowhead;

import java.io.IOException;

public class LookUpService extends Service{

    ASystem incumbent;
    String serviceRequest;
    String serviceResponse;

    public LookUpService(ASystem incumbent, String serviceName, String serviceURI) {
        super(incumbent, serviceName, serviceURI);
        this.incumbent = incumbent;
    }

    Asset lookup = new LookUP("LookUP", incumbent);

    public  void runService (){

        lookup.go(serviceRequest);

    }

    public  void transceive (String payload) throws IOException {

        this.serviceRequest = payload;
        runService();

    }

    public  String transceive () throws IOException {


        return this.serviceResponse = lookup.getAssetResponse();

    }

}


