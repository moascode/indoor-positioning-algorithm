package main.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static main.java.CalculateAll.calculateDistance;
import static main.java.CalculateAll.trilateration;

/**
 * Main class for calculating device position from the access points
 */

public class Main {
    public static void main(String[] args) throws IOException {
        double signalLevelInDb1, signalLevelInDb2, signalLevelInDb3, freqInMHz1, freqInMHz2, freqInMHz3;
        /*sample data for distance calculation
        * input: signal level and frequency of device from three access points
        * -69 2412 -62 2412 -60 2412
        * output: co-ordinates of device from the APs
        * AP1 = 15.671938
        * AP2 = 7.000397
        * AP3 = 5.560613
        * X = 15.181049 Y = 5.9827003
        * */
        BufferedReader reader = new BufferedReader( new InputStreamReader(System.in));
        StringTokenizer tokenizer;
        tokenizer = new StringTokenizer(reader.readLine());
        /*signal level from access point - 1*/
        signalLevelInDb1 = Double.parseDouble(tokenizer.nextToken());
        /*frequency of access point - 1*/
        freqInMHz1 = Double.parseDouble(tokenizer.nextToken());
        /*signal level from access point - 2*/
        signalLevelInDb2 = Double.parseDouble(tokenizer.nextToken());
        /*frequency of access point - 2*/
        freqInMHz2 = Double.parseDouble(tokenizer.nextToken());
        /*signal level from access point - 3*/
        signalLevelInDb3 = Double.parseDouble(tokenizer.nextToken());
        /*frequency of access point - 3*/
        freqInMHz3 = Double.parseDouble(tokenizer.nextToken());
        /*calculate distance of device from all the APs*/
        float r1 = (float)calculateDistance(signalLevelInDb1,freqInMHz1);
        float r2 = (float)calculateDistance(signalLevelInDb2,freqInMHz2);
        float r3 = (float)calculateDistance(signalLevelInDb3,freqInMHz3);
        /*identify device coordinates using trilateration algorithm*/
        float x2 = (float) 21, x3 = 8, y3 = (float) 6;
        float[] result = trilateration(r1, r2, r3, x2, x3, y3);
        System.out.println("AP1 = " + r1 + "\nAP2 = " + r2 + "\nAP3 = " + r3);
        System.out.println("\nX = " + result[0] + " Y = " + result[1]);
    }
}
