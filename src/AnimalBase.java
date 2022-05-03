import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class AnimalBase {

    private ArrayList<Animal> animals;
    //test1

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


    public void loadDatabase() throws FileNotFoundException {

        Scanner fileScanner = new Scanner(new File("animal.csv"));

        while (fileScanner.hasNextLine());

        String line = fileScanner.nextLine();

        Scanner lineScanner = new Scanner(line).useDelimiter(";");

        String name = lineScanner.next();
        String desc = lineScanner.next();
        String type = lineScanner.next();
        int age = lineScanner.nextInt();
        double weight = lineScanner.nextDouble();


        Animal animal = new Animal(name, desc, type, age, weight);

        animals.add(animal);

        //System.err.println("LOAD not yet implemented!");
    }

    public void saveDatabase() throws FileNotFoundException {
        PrintStream outputfile = new PrintStream("animals.csv");

        for (Animal animal : animals) {
            writeAnimal(outputfile, animal);
        }
        outputfile.close();

    }

    public void writeAnimal(PrintStream outputfile, Animal animal){
        outputfile.print(animal.getName());

        outputfile.print(";");

        outputfile.print(animal.getDesc());

        outputfile.print(";");

        outputfile.print(animal.getType());

        outputfile.print(";");

        outputfile.print(animal.getAge());

        outputfile.print(";");

        outputfile.print(animal.getWeight());

        outputfile.print("\n");

    }

}
