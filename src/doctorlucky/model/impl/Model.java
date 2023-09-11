package doctorlucky.model.impl;

import doctorlucky.model.ModelReading;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Model class represents the functionality to parse the input file and pass the
 * data into objects. This class is responsible for creation of the world .It
 * implements the interface ModelReading.
 */
public class Model implements ModelReading {

  @Override
  public Mansion processMansionFile(File mansionFile, int maxTurns) {

    try {
      if (mansionFile == null) {
        throw new IllegalArgumentException("Mansion file is null");
      }

      if (maxTurns < 0) {
        throw new IllegalArgumentException("Max turns are invalid");
      }

      List<String> al = new ArrayList<String>();

      FileReader filereader;
      filereader = new FileReader(mansionFile);
      BufferedReader bufferedreader = new BufferedReader(filereader);
      String line;

      while ((line = bufferedreader.readLine()) != null) {
        al.add(line);
      }

      String[] first = al.get(0).split(" ");
      String w = " ";
      String worldName = "";
      int totalRows = Integer.parseInt(first[0]);
      int totalColumns = Integer.parseInt(first[1]);
      for (int i = 2; i < first.length; i++) {
        worldName = worldName + w + first[i];
      }

      String targetName = "";
      String t = " ";
      String[] second = al.get(1).split(" ");
      int totalHealth = Integer.parseInt(second[0]);
      for (int i = 1; i < second.length; i++) {
        targetName = targetName + t + second[i];
      }

      // creating Target object
      Target target = new Target(targetName, totalHealth);

      String[] petstuff = al.get(2).split(" ");
      String petName = petstuff[0];
      String peType = petstuff[2];

      // creating Pet object
      Pet pet = new Pet(petName, peType);

      // creating Mansion object
      Mansion mansion = new Mansion(totalRows, totalColumns, worldName, target, pet, maxTurns);

      Room room = null;
      int totalRooms = Integer.parseInt(al.get(3));
      for (int i = 4; i < totalRooms + 4; i++) {
        String temp = al.get(i);
        temp = temp.trim();
        temp = temp.replaceAll("  ", " ");
        String[] help = temp.split(" ");
        String roomname = "";
        int x = Integer.parseInt(help[0]);
        int y = Integer.parseInt(help[1]);
        int x1 = Integer.parseInt(help[2]);
        int y1 = Integer.parseInt(help[3]);

        // creating Point objects
        Point p = new Point(x, y);
        Point p1 = new Point(x1, y1);

        if (help.length != 5) {
          String rn = help[4] + " ";
          for (int j = 5; j < help.length; j++) {
            rn = rn + help[j];
          }

          roomname = rn;
        } else {
          roomname = help[4];
        }

        // creating room object
        room = new Room(p, p1, roomname);

        // adding the room object to List<Room> in Mansion
        mansion.addRoom(room);

      }

      for (int i = totalRooms + 5; i < al.size(); i++) {
        String temp = al.get(i);
        String[] help = temp.split(" ");
        String itemname;
        int roomNumber = Integer.parseInt(help[0]);
        int damage = Integer.parseInt(help[1]);
        if (help.length != 3) {
          String rn = help[2];
          String r = " ";
          for (int j = 3; j < help.length; j++) {
            rn = rn + r + help[j];
          }
          itemname = rn;
        } else {
          itemname = help[2];
        }

        // creating item object
        Item item = new Item(itemname, damage);

        // adding item to respective room
        mansion.getRoomById(roomNumber).additem(item);

      }

      return mansion;

    } catch (IOException e) {
      throw new IllegalArgumentException("Error reading the file");
    }

  }

}
