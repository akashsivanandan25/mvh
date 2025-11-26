package utils;

public class MathUtil {
    private MathUtil(){}

    /**
     * Clamp a given int down between a given min and max value.
     * If the given number exceeds the max value, then the max value is returned.
     * If given number is smaller than the minimum limit, then the minimum value is returned.
     * @param val Value to be clamped
     * @param min Minimum value
     * @param max Maximum value
     * @return Clamped value between 2 extremes.
     */
    public static int clamp(int val, int min, int max){
        if (val < min){ return min; }
        else if (val > max){ return max;}
        return val;
    }

    /**
     * Computes a percentage of a given integer value.
     *
     * @param value      the base number to apply the percentage to
     * @param percentage the percentage to extract (0–100 or more if allowed)
     * @return the result of (value * percentage / 100), rounded to the nearest integer
     */
    public static int percentage(int value, int percentage){
        double toReturn = (double) (value * percentage) / 100;
        return Math.toIntExact(Math.round(toReturn));
    }
}
