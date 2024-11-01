import Model.Person;
import Service.PersonService;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        PersonService personService = new PersonService(2); // 2 reactivos iniciales

        // Cargar datos de ejemplo
        personService.registerPerson(new Person("Marcos Lopez", 37, "Bernardino Rivadavia", "12345678", "Programador"));
        personService.registerPerson(new Person("Marisa Juarez", 25, "Los Pinares", "12345670", "Docente"));
        personService.registerPerson(new Person("Carlos Perez", 22, "Chauvin", "65432100", "Doctor"));

        // Mostrar personas registradas
        personService.showRegisteredPeople();

        // Realizar test
        personService.test();

        // Aislar personas con alta temperatura y guardar en JSON
        try {
            personService.isolate();
        } catch (IOException e) {
            System.out.println("Error al aislar o guardar los resultados: " + e.getMessage());
        }
    }

}
