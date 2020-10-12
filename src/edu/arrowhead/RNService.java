package edu.arrowhead;

import java.io.IOException;

public class RNService extends Service{

    ASystem incumbent;
    String serviceRequest = "1:100";
    String serviceResponse;

    public RNService(ASystem incumbent, String serviceName, String serviceURI) {
        super(incumbent, serviceName, serviceURI);
        this.incumbent = incumbent;
    }

    Asset rng = new RNG("RNG", incumbent);

    public  void runService (){

        rng.go(serviceRequest);

    }

    public  void transceive (String payload) throws IOException {

        this.serviceRequest = payload;
        runService();

    }

    public  String transceive () throws IOException {


        return this.serviceResponse = rng.getAssetResponse();

    }

}
