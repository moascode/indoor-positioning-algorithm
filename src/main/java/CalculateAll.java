package main.java;

/**
 * Calculate distance based on signal strength and frequency
 */
public class CalculateAll extends CalculateTrilateration{

    private static final int IOT_DEVICE_TX_POWER = 15;
    private static final int CABLE_LOSS = 0;
    private static final int ANTENNA_GAIN = 10;
    private static final int NORMAL_ROUTER_EIRP = 10;

    /*
    * calculate distance based on free space path loss formula
    * reference: https://en.wikipedia.org/wiki/Free-space_path_loss#Free-space_path_loss_in_decibels
    */
    public static double calculateDistance(double signalLevelInDb, double freqInMHz) {
        /*EIRP = Device TX Power - Cable Loss(0) + Antenna Gain (3-5)*/
        double iot_eirp = IOT_DEVICE_TX_POWER -  CABLE_LOSS + ANTENNA_GAIN;
        /*difference with actual EIRP from N-135 EIRP*/
        double difference = NORMAL_ROUTER_EIRP - iot_eirp;
        /*adjustment made for IOT device compared to normal router*/
        double signalInDb = difference + signalLevelInDb;
        /*distance = 10 ^ ((27.55 - (20 * log10(frequency)) + signalLevel)/20)*/
        double exp = (27.55 - (20 * Math.log10(freqInMHz)) + Math.abs(signalInDb)) / 20.0;
        return Math.pow(10.0, exp);
    }

    /*use trilateration technique*/
    public static float[] trilateration (float r1,float r2,float r3,float x2,float x3,float y3 ){
        float [] p1 = {0,0};
        float [] p2 = {x2,0};
        float [] p3 = {x3,y3};
        return trilaterate(p1, r1, p2, r2, p3, r3);
    }
}
