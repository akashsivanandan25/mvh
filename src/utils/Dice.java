package utils;
import java.util.Random;

/**
 * Utiilty class used to generate pseudo-random numbers used in-game.
 * Has dice rolls, binary decisions and random number generator
 */
public class Dice {
    final static Random rand = new Random();
    private Dice(){
    }
    /**
     This function simulates rolling a die with a specified number of sides.
     @param sides Number of sides our simulated die has
     @return An integer number randomly picked from all the sides of the specified die
     **/
    public static int roll(int sides){
        return rand.nextInt(sides) + 1;
    }

    /**
     * Roll a 6 sided die
     * @return Integer number between 1 and 6
     */
    public static int roll(){
        return roll(6);
    }

    /**
     * Use to simulate a binary decision with a custom chance of success
     * @param probability Probability of success
     * @return Boolean value representing success
     */
    public boolean takeChance(float probability){
        if (probability >= 1f){
            return true;
        } else if (probability < 0f){
            return false;
        }
        return rand.nextFloat() <= probability;
    }

    /**
     * Generate a pseudo-random number
     * @return Pseudo-random integer
     */
    public int randomNumber(){
        return rand.nextInt();
    }
}
