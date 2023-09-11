package doctorlucky.model;

/**
 * An ItemReading represents the various properties of an item within a room
 * in the mansion. Classes that implement this interface will vary depending
 * upon the type of item they are trying to implement.
 */
public interface ItemReading {


  /**
 * Retrieves the name of the item.
 * @return the name of the item
 */
  public String getItemName();

  /**
   * Get the amount of damage the item can do to the target.
   * @return damageValue
   */
  public int getDamageValue();


}
