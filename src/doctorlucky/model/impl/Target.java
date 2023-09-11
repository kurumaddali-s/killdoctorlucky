package doctorlucky.model.impl;

import doctorlucky.model.TargetReading;

/**
 * A Target class represented by targetName, health, position.
 */
public class Target implements TargetReading {

  private final String targetName;
  private int health;
  private int position;

  /**
   * Target constructor consists of targetname, health.
   * 
   * @param targetname represents name of the target.
   * @param health     represents health of the target.
   */
  public Target(String targetname, int health) throws IllegalArgumentException {
    if (health < 0 || targetname == null) {
      throw new IllegalArgumentException("The value should not be negative");
    }
    this.health = health;
    this.targetName = targetname;
    this.position = 0;
  }

  @Override
  public String getTargetname() {
    return this.targetName;
  }

  @Override
  public int getHealth() {
    return this.health;
  }

  @Override
  public int getTargetPosition() {
    return this.position;
  }

  @Override
  public void resetTargetPosition(int targetPosition) {
    if (targetPosition < 0) {
      throw new IllegalArgumentException("The value cant be negative");
    }

    this.position = targetPosition;
  }

  @Override
  public void incrementTargetPosition() {

    this.position += 1;
  }

  @Override
  public void resetTargetHealth(int targetNewHealth) {
    if (targetNewHealth < 0) {
      throw new IllegalArgumentException("The value cant be negative");
    }

    this.health = targetNewHealth;
  }

  @Override
  public String toString() {
    return String.format("TargetName: %s, health: %s", this.targetName, this.health);

  }
}
