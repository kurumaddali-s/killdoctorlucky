package doctorlucky.model;

/**
 * A TargetReading represents the various properties of a target within in
 * the mansion. Classes that implement this interface will vary depending
 * upon the type of target they are trying to implement.
 */
public interface TargetReading {

  /**
   * Gets the Name of the target which is of the type String. This target is the one that needs
   * to killed and all players attempt to do it. In the world built Doctor Lucky is the name of
   * the target.
   * @return name of the target
   */
  public String getTargetname();

  /**
   * Get the health of the target.
   * @return health
   */
  public int getHealth();

  /**
   * Gets the current position of the target.
   * @return position of the target
   */
  public int getTargetPosition();

  /**
   * Resets the position of the target once it reaches the
   * last indexed room.
   * @param targetPosition is the position to which it is reset
   */
  public void resetTargetPosition(int targetPosition);

  /**
   * Increments the position of the target by one.
   */
  public void incrementTargetPosition();

  /**
     * Resets the health of the target to that mentioned in parameter.
     * As players keep trying to kill the target this method is used to reduce
     * the health of the target.
     * @param targetNewHealth is the position to which it is reset
     */
  public void resetTargetHealth(int targetNewHealth);
}
