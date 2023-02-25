package menu;

import java.util.Stack;

/**
 * Esta clase maneja el menu
 * Un menu esta formado por items los cuales a su vez puede
 * contener items hijos
 * Maneja todos los items en un stack
 */
public class MenuManager {

    /**
     * La mejor opción para trabajar con los menus es un Stack:
     * First In, First Out.
     */
    private final Stack<MenuItem> menus;

    /**
     * Constructor
     */
    public MenuManager() {
        menus = new Stack<>();
    }

    /**
     * Limpia el stack
     */
    public void clear() {
        menus.clear();
    }

    /**
     * Abre un menu item
     */
    public void open(MenuItem mo) {
        clear();
        menus.add(mo);
    }

    /**
     * Función para la acción de confirmar una opción del menu
     * @param index el índice de la opción
     * @return
     */
    public MenuItem onConfirm(int index) {
        if (menus.isEmpty()) {
            return null;
        }

        MenuItem next = menus.peek().onConfirm(index);

        if (next.equals(menus.peek())) {
            return menus.peek().getSelectedItem(index);
        } else {
            return menus.push(next);
        }
    }

    public void show() {
        if (menus.isEmpty()) {
            return;
        }
        menus.peek().show();
    }

    // Getters

    public Stack<MenuItem> getStack() {
        return menus;
    }

}
