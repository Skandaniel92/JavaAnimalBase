import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;

public class AnimalBase {

  private ArrayList<Animal> animals;

  public AnimalBase() {
    animals = new ArrayList<>();
  }

  public void start() throws FileNotFoundException {
    UserInterface ui = new UserInterface(this);
    ui.start();
  }

  public static void main(String[] args) throws FileNotFoundException {
    AnimalBase app = new AnimalBase();
    app.start();
  }

  public Iterable<Animal> getAllAnimals() {
    return animals;
  }

  public int getAnimalCount() {
    return animals.size();
  }

  public void sortBy(String sortBy, SortDirection sortDirection) {
    // Comparator comparator = null;
    //if
    //efter else: Collections.sort(animals, comparator);
    //evt. flyt if ind i comparatorklassen
    AgeComparator comparator = new AgeComparator();
    Collections.sort(animals, comparator);

    // TODO: Implement sorting!
    System.out.println("TODO: Sort the list of animals by: " + sortBy);
  }

  public void createNewAnimal(String name, String description, String type, int age, double weight) {
    Animal animal = new Animal(name,description,type,age,weight);
    animals.add(animal);
  }

  public boolean deleteAnimal(String name) {
    // find animal with this name
    Animal animal = findAnimalByName(name);
    if(animal == null) {
      return false;
    } else {
      animals.remove(animal);
      return true;
    }
  }

  private Animal findAnimalByName(String name) {
    for(Animal animal : animals) {
      if(animal.getName().equalsIgnoreCase(name)) {
        return animal;
      }
    }
    return null;
  }

  public void loadDatabase()  {

    FileHandler fileHandler = new FileHandler();
    animals.addAll(fileHandler.loadAnimalsFromFile());
    //Eller:
    //animals = fileHandler.loadDatabase();
  }

  public void saveDatabase()  {

    try {
      FileHandler fileHandler = new FileHandler();
      fileHandler.saveAnimalsToFile(animals);
    }
    catch (FileNotFoundException exception){
      System.err.println("Could not save");
    }
  }



}
