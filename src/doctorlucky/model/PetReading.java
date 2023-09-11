package doctorlucky.model;

/**
 * A PetReading represents the various properties of a pet of the target within in
 * the mansion. Classes that implement this interface will vary depending
 * upon the type of pet they are trying to implement.
 */
public interface PetReading {

  /**
     * Returns the name of the pet.
     * @return Name of the pet
     */
  public int getPetPosition();

  /**
  * Sets the position of the pet to the parameter room index
   * As pet keeps moving from one room to another whenever a player commands it
   * a method to set the position of the pet is required.
  * @param petPosition is the position to which it is reset
  */
  public void setPetPosition(int petPosition);



}
