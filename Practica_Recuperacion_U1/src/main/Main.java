package main;

import menu.MenuItem;
import menu.MenuManager;
import menu.MenuUtils;
import properties.PropertiesUtils;

import java.util.Properties;
import java.util.Scanner;

/**
 * Este es la clase Main.
 * Contiene la función "main", el punto de ejecución del programa.
 */
public class Main {

    /**
     * Nombre del archivo ".properties"
     */
    private static final String propertiesFileName = "app.properties";

    /**
     * Objeto properties
     */
    private static Properties properties;

    /**
     * Manejador del menu
     */
    public static MenuManager mm;

    /**
     * El scanner para leer el input del usuario por consola
     */
    public static Scanner scanner;

    /**
     * Esta función crea el menu del programa
     */
    public static void createMenu() {
        mm = new MenuManager();
        MenuItem mi = new MenuItem("Menu");
        mi.add("Mostrar todos los nombres de las keys").setId(1);
        mi.add("Mostrar el nombre del usuario").setId(2);
        mi.add("Mostrar el email del usuario").setId(3);
        mi.add("Cambiar idioma de " + propertiesFileName).setId(4);
        mi.add("Salir").setId(-1);
        mm.open(mi);
    }

    /**
     * Esta función realiza una de las funcionalidades del programa
     * @param id el id de la opción para seleccionar una funcionalidad
     */
    public static void manageAction(int id) {
        switch (id) {
            case 1: // Mostrar todos los nombres de las keys
                for (var key : PropertiesUtils.getAllKeyNames(properties)) {
                    System.out.println(key);
                }
                break;
            case 2: // Mostrar el nombre del usuario
                System.out.println(PropertiesUtils.getUserName(properties));
                break;
            case 3: // Mostrar el email del usuario
                System.out.println(PropertiesUtils.getUserEmail(properties));
                break;
            case 4: // Cambiar el idioma en el archivo properties
                System.out.print("Introduce el nuevo idioma (idioma actual: " + properties.getProperty("language") + "): ");
                String newLanguage = scanner.nextLine();
                PropertiesUtils.updateLang(properties, newLanguage);
                break;
        }
    }

    /**
     * Función "main". Punto de ejecución del programa
     * @param args argumentos del programa (en este caso no se utilizan)
     */
    public static void main(String[] args) {
        // Instanciar el objeto scanner para poder leer la entrada por consola
        scanner = new Scanner(System.in);
        // Leer el archivo una sola vez, ya queda cacheado
        properties = PropertiesUtils.getProperties(propertiesFileName);
        // Añadir una nueva propiedad cuando se inicia el programa y se lee el archivo properties
        PropertiesUtils.addPropertyAppStarted(properties);
        // Crear el menu
        createMenu();

        // Bucle de la aplicación
        MenuItem command;
        do {
            mm.show();
            System.out.print("Introduza una opción: ");
            command = mm.onConfirm(MenuUtils.getUserInputIntBetweenBounds(0, mm.getStack().peek().numChildren()) - 1);
            System.out.println("Seleccionado: " + command.getName());
            if (!command.hasChildren() && mm.getStack().size() > 1) {
                mm.getStack().pop();
            }
            manageAction(command.getId());
        } while (command.getId() != -1);

        // Guardar las modificaciones del archivo properties
        boolean isSaved = PropertiesUtils.saveProperties(properties, propertiesFileName);
        if (isSaved) {
            System.out.println("Se ha guardado el archivo: " + propertiesFileName);
        } else {
            System.out.println("No se ha podido guardar el arhivo: " + propertiesFileName);
        }
    }

}
