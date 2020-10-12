package edu.arrowhead;
/**
 * @author javsal@ltu
 */

import edu.arrowhead.ASystem;

/**
 * Abstract Class Asset that extends to the available assets and devices e.g sensors, actuators etc
 */
public abstract class Asset {

    String assetName;

    ASystem incumbent;

    /**
     *
     * @param assetName
     * @param incumbent
     */
    public Asset(String assetName, ASystem incumbent) {
        this.assetName = assetName;
        this.incumbent = incumbent;


    }

    /**
     *
     * @param assetRequest
     */
    public abstract void go(String assetRequest);


    /**
     * @return
     */
    public abstract String getAssetResponse();

}