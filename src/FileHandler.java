import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class FileHandler {

  private String fileName = "animals.csv";

  public ArrayList<Animal> loadAnimalsFromFile()  {

    ArrayList<Animal> animals = new ArrayList<>();

    try {

      Scanner fileScanner = new Scanner(new File(fileName));

      while (fileScanner.hasNextLine()) {
        String line = fileScanner.nextLine();

        Animal animal = readAnimal(line);
        //Tilføj det til listen af objekter:
        animals.add(animal);

      }
    }
    catch (FileNotFoundException exception) {
      System.out.println("Could not load");

    }

    return animals;
  }

  public Animal readAnimal(String line) {
    //Åbn scanner der læser fra csv-fil:
    Scanner lineScanner = new Scanner (line).useDelimiter(";").useLocale(Locale.ENGLISH);

    //Træk variablerne ud i rækkefølgen, de var skrevet
    String name = lineScanner.next();
    String desc = lineScanner.next();
    String type = lineScanner.next();
    int age = lineScanner.nextInt();
    double weight = lineScanner.nextDouble();

    //Opret et nyt objekt med ovenstående variabler:
    Animal animal = new Animal(name,desc,type,age,weight);
    return animal;
  }

  public void saveAnimalsToFile(ArrayList<Animal> animals) throws FileNotFoundException {
    //Åbn en PrintStream der skriver til filen:
    PrintStream outputFile = new PrintStream(new File(fileName));

    //For hvert objekt i listen:
    for (Animal animal : animals) {
      // Kald en metode der skriver objektet til filen
      writeAnimal(outputFile, animal);
    }
    //Slut af med at lukke filen
    outputFile.close();
  }

  //Denne metode skriver ét objekt til en PrintStream:
  private void writeAnimal(PrintStream outputFile, Animal animal) {

    //Hvert attribut skrives til filen som en almindelig String
    outputFile.print(animal.getName());

    //Efterfulgt af et ;
    outputFile.print(";");
    outputFile.print(animal.getDesc());
    outputFile.print(";");
    outputFile.print(animal.getType());
    outputFile.print(";");
    outputFile.print(animal.getAge());
    outputFile.print(";");
    outputFile.print(animal.getWeight());

    // Undtaget den sidste, der efterfølges af et linjeskift
    outputFile.print("\n");
  }


}
