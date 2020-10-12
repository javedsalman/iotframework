package edu.arrowhead;

public class RNG extends Asset {
    private static int min;
    private static int max;
    private static String assetResponse;

    public  String getAssetResponse() {
        return assetResponse;
    }


    public RNG(String assetName, ASystem incumbent) {
        super(assetName,incumbent );
    }

    @Override
    public  void  go (String assetRequest) {

        StringTrimmer(assetRequest);
        assetResponse = Integer.toString(rngLoad (RNG.min, RNG.max));

    }

    public static int rngLoad (int min, int max){

        return (int) ((Math.random() * ((max - min) + 1)) + min);

    }

    public void StringTrimmer(String assetRequest){
        String delimiter = ":";
        String[] tokensVal = assetRequest.split(delimiter);

        String minimum = (tokensVal[0]).trim();
        this.min = Integer.parseInt(minimum);

        String maximum = (tokensVal[1]).trim();
        this.max = Integer.parseInt(maximum);



    }


}
