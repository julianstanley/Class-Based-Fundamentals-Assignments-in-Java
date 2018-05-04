import java.util.ArrayList;
import java.util.Random;

class Utils {
  // Scramble the given Arraylist
  <T> ArrayList<T> scramble(ArrayList<T> source) {
    Random rand = new Random();
    ArrayList<T> sourceCopy = new ArrayList<T>(source);
    ArrayList<T> finalList = new ArrayList<T>();

    for (int i = 0; i < source.size(); i += 1) {
      T inital = sourceCopy.remove(rand.nextInt(sourceCopy.size()));
      finalList.add(inital);
    }

    return finalList;
  }

  // Generate a random list of booleans, with the number of trues equal to the
  // *mines* argument.
  // And the total booleans equal to the *total* argument.
  ArrayList<Boolean> scrambledList(int mines, int total) {

    ArrayList<Boolean> baseList = new ArrayList<Boolean>();

    // Add true for number of mines
    for (int i = 0; i < mines; i++) {
      baseList.add(true);
    }

    // Add false for the rest
    for (int i = 0; i < total - mines; i++) {
      baseList.add(false);
    }

    return this.scramble(baseList);
  }
}