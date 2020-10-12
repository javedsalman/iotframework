package edu.arrowhead;

import java.io.IOException;

public abstract class Service {
    private edu.arrowhead.ASystem incumbent;
    private String serviceName;
    private String serviceURI;
    private String payload;



    public Service(edu.arrowhead.ASystem incumbent, String serviceName  , String serviceURI/*, String role*/) {
        this.incumbent = incumbent;
        this.serviceName = serviceName;
        this.serviceURI = serviceURI;
    }

    public abstract void transceive (String payload) throws IOException;

    public abstract String transceive () throws IOException;

    public abstract void runService ();
}
