package edu.arrowhead;

public class Display extends Asset {
    private static String assetResponse;

    public Display(String assetName,  ASystem incumbent) {
        super(assetName, incumbent);
    }
    @Override
    public  void go (String assetRequest) {
        System.out.println(assetRequest);


    }

    public  String getAssetResponse() {
        return assetResponse;
    }

    public static void setAssetResponse(String assetResponse) {
        Display.assetResponse = assetResponse;
    }



}
