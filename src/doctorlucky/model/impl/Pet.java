package doctorlucky.model.impl;

import doctorlucky.model.PetReading;
import java.io.IOException;


/**
 * Pet class is represented by petName and petType.
 */
public class Pet implements PetReading {

  private final String petName;
  private final String petType;
  private int position;

  /**
   * Pet constructor consists of petName and petType.
   * 
   * @param petName represents name of the pet.
   * @param petType represents type of the pet.
   */

  public Pet(String petName, String petType) throws IllegalArgumentException, IOException {
    if (petName == null || petType == null) {
      throw new IllegalArgumentException("The value should not be negative");
    }
    this.petName = petName;
    this.petType = petType;
    this.position = 0;
  }

  @Override
  public int getPetPosition() {
    return this.position;
  }

  @Override
  public void setPetPosition(int petPosition) {
    if (petPosition < 0) {
      throw new IllegalArgumentException("The value cant be negative");
    }
    this.position = petPosition;
  }
}
