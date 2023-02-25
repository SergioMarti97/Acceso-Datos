package properties;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Properties;

/**
 * Esta clase contiene los métodos que se piden en el ejercicio:
 * - Lectura de fichero properties
 * - Devolver todos los nombres de las keys del fichero properties
 * - Devolver el nombre del usuario
 * - Devolver el email del usuario
 * - Cambiar la propiedad idioma del fichero
 * - Añadir la propiedad nueva "appStarted", con valor de la fecha actual
 */
public class PropertiesUtils {

    /**
     * Este método instancia un objeto Properties con la información del archivo indicado por parámetro
     * @param fileName el nombre del archivo .properties
     * @return objeto Properties
     */
    public static Properties getProperties(String fileName) {
        Properties properties = null;
        try {
            properties = new Properties();
            properties.load(new FileReader(fileName));
        } catch (FileNotFoundException e) {
            System.out.println("Archivo: " + fileName + " no encontrado");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + fileName);
            e.printStackTrace();
        }
        return properties;
    }

    /**
     * Este método devuelve una lista con los nombres de todas las "keys" presentes
     * en el objeto properties pasado por parámetro.
     * @param properties objeto properties
     * @return una lista con los nombres de todas las "keys" presentes en el objeto properties
     */
    public static ArrayList<String> getAllKeyNames(Properties properties) {
        ArrayList<String> keyNames = new ArrayList<>();
        Enumeration<Object> keys = properties.keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            keyNames.add(key.toString());
        }
        return keyNames;
    }

    /**
     * Este método devuelve el nombre del usuario presente en el archivo app.properties
     * @param properties objeto properties
     * @return el nombre del usuario presente en el archivo app.properties
     */
    public static String getUserName(Properties properties) {
        return (String) properties.get("userName");
    }

    /**
     * Este método devuelve el email del usuario presente en el archivo app.properties
     * @param properties objeto properties
     * @return el email del usuario presente en el archivo app.properties
     */
    public static String getUserEmail(Properties properties) {
        return (String) properties.get("userEmail");
    }

    /**
     * Este método cambia/actualiza la propiedad idioma (language) del archivo properties
     * @param properties objeto properties
     */
    public static void updateLang(Properties properties, String newLanguage) {
        properties.setProperty("language", newLanguage);
    }

    /**
     * Este método añade al archivo app.properties el campo "appStarted", con el valor
     * de la fecha actual
     * @param properties objeto properties
     */
    public static void addPropertyAppStarted(Properties properties) {
        properties.setProperty("appStarted", LocalDate.now().toString());
    }

    /**
     * Este método guarda el objeto properties. Utiliza la función store, ya que save está deprecated
     * @param properties objeto properties
     * @return verdadero o falso según se ha podido o no guardar el archivo
     */
    public static boolean saveProperties(Properties properties, String fileName) {
        try {
            properties.store(new FileOutputStream(fileName), "Archivo: " + fileName);
            return true;
        } catch (FileNotFoundException e) {
            System.out.println("Archivo: " + fileName + " no encontrado");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error al guardar el archivo " + fileName);
            e.printStackTrace();
        }
        return false;
    }

}
