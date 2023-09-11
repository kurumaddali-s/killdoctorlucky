package doctorlucky.model.impl;

import java.util.Random;

/**
 * This class randomizes actions.
 *
 */
public class RandomPlayer {

  private Random random;
  private int[] arg;

  /**
   * This constructor initializes the Random class object that is used.
   * by the computer player to choose actions randomly anyone at one turn
   */
  public RandomPlayer() {
    this.random = new Random();
  }

  /**
     * This constructor allows the computer player to choose actions
     * from a set of actions which are passed as arguments.
     *
     * @param arg the arguments for the game commands
     */
  public RandomPlayer(int... arg) throws IllegalArgumentException {

    if (arg == null) {
      throw new IllegalArgumentException("Args passed are null");
    }

    this.arg = arg;
  }

  /**
     * Gets one random number from the size provided in param.
     * @param size the largest random number that can be returned.
     * @return the number of players in the room class
     */
  public int nextInt(int size) throws IllegalArgumentException {

    if (size < 0) {
      throw new IllegalArgumentException("Size cannot be negative.");
    }

    if (this.random == null) {
      return arg[0];
    }

    return random.nextInt(size);
  }

}







