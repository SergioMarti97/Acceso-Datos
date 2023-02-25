package menu;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Esta clase es la representación de un item o objeto
 * el cual pertenece a un menu
 */
public class MenuItem {

    /**
     * El identificador del item
     */
    private int id = -1;

    /**
     * El nombre del item
     */
    private final String name;

    /**
     * Hash map del nombre del objeto y su indice
     */
    private final HashMap<String, Integer> itemPointer = new HashMap<>();

    /**
     * Una lista con todos los items que pertence a este item
     */
    private final ArrayList<MenuItem> items = new ArrayList<>();

    /**
     * Constructor vacio
     */
    public MenuItem() {
        name = "root";
    }

    /**
     * Constructor parametrizado
     * @param name el nombre de este item del menu
     */
    public MenuItem(String name) {
        this.name = name;
    }

    public MenuItem setId(int id) {
        this.id = id;
        return this;
    }

    public MenuItem get(String option) {
        return items.get(itemPointer.get(option));
    }

    public MenuItem add(String option) {
        if ( !itemPointer.containsKey(option) ) {
            itemPointer.put(option, items.size());
            items.add(new MenuItem(option));
        }
        return items.get(itemPointer.get(option));
    }

    public MenuItem getSelectedItem(int index) {
        return items.get(index);
    }

    public MenuItem onConfirm(int index) {
        if (items.get(index).hasChildren()) {
            return items.get(index);
        } else {
            return this;
        }
    }

    public boolean hasChildren() {
        return !itemPointer.isEmpty();
    }

    public int numChildren() {
        return items.size();
    }

    public void show() {
        StringBuilder out = new StringBuilder();
        out.append("# --- ").append(name).append(" --- #\n");
        for (int i = 0; i < items.size(); i++) {
            out.append('(').append(i + 1).append(") ").append(items.get(i).getName()).append('\n'); // <- Indices
            //out.append('(').append(items.get(i).getId()).append(") ").append(items.get(i).getName()); // <- IDs
        }
        System.out.print(out);
    }

    // Getters

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public HashMap<String, Integer> getItemPointer() {
        return itemPointer;
    }

    public ArrayList<MenuItem> getItems() {
        return items;
    }

    @Override
    public String toString() {
        return name + " children: " + items.size();
    }

}
