package doctorlucky.model.impl;

import doctorlucky.model.PointReading;

/**
 * Point class is represented by row, column.
 */
public class Point implements PointReading {
  private final int row;
  private final int column;

  /**
   * Point constructor consists of row and column.
   * 
   * @param row    represents row of a coordinate.
   * @param column represents column of a coordinate.
   */
  public Point(int row, int column) throws IllegalArgumentException {
    if (row < 0 || column < 0) {
      throw new IllegalArgumentException("The value should not be negative");
    }
    this.row = row;
    this.column = column;
  }

  @Override
  public int getPointRow() {
    return this.row;
  }

  @Override
  public int getPointColumn() {
    return this.column;
  }

  @Override
  public String toString() {

    return String.format("Point(x, y): (%s, %s)", this.row, this.column);

  }
}
