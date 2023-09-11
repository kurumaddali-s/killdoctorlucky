package doctorlucky.model;

import java.util.List;

/**
 * A PointReading represents the dimensions of a room, each dimension has row
 * and column values all integer type.
 */
public interface PointReading {

  /**
   * Gets the row dimension of a room it is an integer. This row is a value of the
   * top left coordinate or bottom right coordinate of each room.
   * 
   * @return row
   */
  public int getPointRow();

  /**
   * Gets the column dimension of a room it is an integer. This column is a value
   * of the top left coordinate or bottom right coordinate of each room.
   * 
   * @return column
   */
  public int getPointColumn();

}
