package doctorlucky.model.impl;

import doctorlucky.model.ItemReading;

/**
 * Item class is represented by itemName, damageValue.
 */
public class Item implements ItemReading {
  private final String itemName;
  private final int damageValue;

  /**
   * Item constructor consists of itemName, damageValue of an item.
   * @param itemName represents name of the item.
   * @param damageValue represents the amount of damage an item can cause.
   */
  public Item(String itemName, int damageValue)  {
    if (itemName == null || damageValue < 0) {
      throw new IllegalArgumentException("This item cant be null damage value cant be negative");
    }
    this.itemName = itemName;
    this.damageValue = damageValue;
  }

  @Override
  public String getItemName() {

    return this.itemName;
  }

  @Override
  public int getDamageValue() {

    return this.damageValue;
  }

  @Override
  public String toString() {
    return String.format("ItemName: %s, Damage value: %s", this.itemName, this.damageValue);

  }
}
