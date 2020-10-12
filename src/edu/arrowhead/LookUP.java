package edu.arrowhead;

import edu.arrowhead.ASystem;
import edu.arrowhead.Asset;

public class LookUP extends Asset {

    private static String assetResponse;
    private static String service_Name_Random_Number_Generator="randomNumberGenerator";
    private static String service_Provicer_Random_Number_Generator= "localhost:8460";

    private static String service_Name_Orchestration="Orchestrator";
    private static String service_Orchestrator_Address= "localhost:8440";


    public LookUP(String assetName, ASystem incumbent) {
        super(assetName, incumbent);
    }

    @Override
    public void go(String assetRequest) {


        try {

            if (assetRequest.equals(service_Name_Orchestration)){

                assetResponse = service_Orchestrator_Address;
                assetResponse = assetResponse + " \n";

            }
            else if (assetRequest.equals(service_Name_Random_Number_Generator)){

                assetResponse = service_Provicer_Random_Number_Generator;
                assetResponse = assetResponse + " \n";

            }else
            {

                throw new Exception();

            }


        } catch (Exception e) {
            assetResponse = "INVALID SERVICE REQUEST \n";
        }

    }
    public  String getAssetResponse() {
        return assetResponse;
    }


}
