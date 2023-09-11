package doctorlucky.model;

import doctorlucky.model.impl.Mansion;
import java.io.File;
import java.io.IOException;


/**
 * ModelReading represented by Mansion class objects
 * This interface has methods used to parse the input file and pass the data into objects.
 * This interface implementation is responsible for creation of the world .
 */
public interface ModelReading {

  /**
     * processMansionFile method is to parse the input file.
     * @param mansionFile represents file containing the text file
     * @return Mansion class object if the file is successfully parsed else throws exception
   */
  public Mansion processMansionFile(File mansionFile, int maxTurns) throws IOException;

}
