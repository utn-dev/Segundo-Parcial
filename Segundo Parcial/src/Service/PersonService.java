package Service;

import Model.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class PersonService {

    private Integer reagentsAvailable;
    private Set<Person> registeredPeoples = new LinkedHashSet<>();
    private Map<Integer, RegisterTester> resultsTest = new HashMap<>();
    private TestResult testResult = new TestResult();

    public PersonService(int reagentsInitials) {
        this.reagentsAvailable = reagentsInitials;
    }

//    // Método para registrar personas y asignar kit si hay reactivos
//    public void registerPerson(Person p) {
//        if (!registeredPeoples.contains(p)) { // Verificar si la persona ya está registrada
//            if (assignKitIfAvailable(p)) { // Verificar si hay reactivos y asignar kit
//                registeredPeoples.add(p);
//            }
//        } else {
//            System.out.println("Esta persona ya se encuentra registrada: " + p);
//        }
//    }

    public void registerPerson(Person p) {
        boolean exists = false;
        for (Person registeredPerson : registeredPeoples) {
            if (p.equals(registeredPerson)) {
                exists = true;
                break;
            }
        }
        // Si no existe, asignar kit y agregar al conjunto
        if (!exists) {
            if (assignKitIfAvailable(p)) {
                registeredPeoples.add(p);
                System.out.println("Persona registrada exitosamente: " + p);
            }
        } else {
            System.out.println("Esta persona ya se encuentra registrada: " + p);
        }
    }


    // Asignar kit a persona
    private void asignKit(Person p) {
        p.setKit(new Random().nextInt(1, 1000));
        reagentsAvailable--;
    }

    // Verificar si quedan reactivos
    private void verifyReagents() throws NoReactiveException {
        if (reagentsAvailable <= 0) {
            throw new NoReactiveException("No quedan reactivos disponibles.");
        }
    }

    // Verificar si hay kits y asignar
    private boolean assignKitIfAvailable(Person p) {
        if (p.getKit() == null) {
            try {
                verifyReagents();
                asignKit(p);

            } catch (NoReactiveException e) {
                System.out.println(e.getMessage());
                replenishReagentsInteractive();
                return assignKitIfAvailable(p);
            }
        }
        return true;
    }

    // Método interactivo para reponer reactivos
    private void replenishReagentsInteractive() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("¿Desea reponer reactivos? (1. Sí, 2. No)");
        int opcion = scanner.nextInt();
        if (opcion == 1) {
            System.out.println("Ingrese la cantidad de reactivos a reponer:");
            int adicionales = scanner.nextInt();
            reagentsAvailable += adicionales;
            System.out.println("Reactivos repuestos. Total: " + reagentsAvailable);
        } else {
            System.out.println("No se asignará kit a esta persona.");
        }
    }

    // Método para mostrar lista de personas registradas
    public void showRegisteredPeople() {
        System.out.println("--Lista de personas registradas--");
        for (Person p : registeredPeoples) {
            System.out.println(p);
        }
    }

    // Método para realizar test
    public void test() {
        Random random = new Random();
        for (Person p : registeredPeoples) {
            double temperatura = 36 + random.nextDouble() * 3;
            resultsTest.put(p.getKit(), new RegisterTester(p.getDni(), temperatura));
        }
    }

    //Sacar dos arreglos
    // Método para aislar personas con resultado positivo
    public void isolate() throws IOException {
        for (Map.Entry<Integer, RegisterTester> entry : resultsTest.entrySet()) {
            Person p = search(entry.getValue().getId()); 
            try {
                verifyTemperature(entry.getValue().getTemperature());
                testResult.getHealthy().add(p);
            } catch (MaxTemperatureException e) {
                testResult.getIsolated().add(entry.getValue());
                String neighbour = p.getNeighbourhood();
                System.out.println("Aislar kit " + entry.getKey() + " | Barrio: " + neighbour);
                saveIsolatedInFile(entry.getKey(), entry.getValue().getTemperature(), neighbour);
            }
        }
        saveResultsInJson();
    }

    // Guardar en archivo binario
    public void saveIsolatedInFile(Integer kit, Double temperature, String neighbourhood) throws IOException {
        File file = new File("urgente.dat");

        if (!file.exists()) { //Corroboro que el archivo exista
            file.createNewFile(); //Si no existe, lo creo
        }
        BufferedWriter b = new BufferedWriter(new FileWriter(file, true));
        //Paso la información a un string
        String s = "Kit: " + kit + " | Temperatura: " + temperature + " grados | Barrio: " + neighbourhood;
        //Escribo y cierro el archivo
        b.write(s);
        b.close();
    }

    // Guardar en archivo JSON usando Jackson
    private void saveResultsInJson() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File("resultados.json"), testResult);
    }

    public Person search(String id) {
        for (Person p : registeredPeoples) {
            if (p.getDni().equals(id)) {
                return p;
            }
        }
        return null;
    }

    private void verifyTemperature(double temperature) throws MaxTemperatureException {
        if (temperature >= 38) {
            throw new MaxTemperatureException("La temperatura excede el límite permitido.");
        }
    }


}
