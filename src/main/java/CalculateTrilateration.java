package main.java;

/**
 * Calculate position using trilateration technique
 * r1^2 = x^2+y^2+z^2
 */

public class CalculateTrilateration {
    /** p1=[0,0,0], p2=[x,0,0]. p1 is not actually used in the formula.
     */
    public static float[] trilaterate(float[] p1, float r1, float[] p2, float r2, float[] p3, float r3) {
        float[] pos = new float[2];
        pos[0] = calcX(r1, r2, p2[0]);
        pos[1] = calcY(r1, r3, p3[0], p3[1], pos[0]);
        return pos;
    }
    /**
     * Calculate the X coordinate
     * x = (r1^2 - r2^2 + d^2) / 2d
     */
    public static float calcX(float r1, float r2, float d) {
        return (r1*r1 - r2*r2 + d*d) / (2*d);
    }
    /**
     * Calculate the Y coordinate
     * y = ((r1^2 - r3^2 + i^2 + j^2) / 2j) - x*(i/j)
     */
    public static float calcY(float r1, float r3, float i, float j, float x) {
        return ((r1*r1 - r3*r3 + i*i + j*j) / (2*j)) - x*(i/j);
    }
}
