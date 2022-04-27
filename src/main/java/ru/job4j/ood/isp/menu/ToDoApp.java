package ru.job4j.ood.isp.menu;

import java.util.Scanner;

public class ToDoApp {

    private final Menu menu;
    private final MenuPrinter menuPrinter;
    private static final ActionDelegate STUB_ACTION = null;
    private static final String CREATE_NEW_TASK = "1";
    private static final String PRINT = "2";
    private static final String EXIT = "3";

    public ToDoApp(Menu menu, MenuPrinter menuPrinter) {
        this.menu = menu;
        this.menuPrinter = menuPrinter;
    }

    private boolean add(String parentName, String childName) {
        if ("".equals(parentName)) {
            parentName = null;
        }
        return menu.add(parentName, childName, STUB_ACTION);
    }

    private void print() {
        menuPrinter.print(menu);
    }

    public void outputMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean run = true;
        while (run) {
            System.out.println("This app helps you build a todo list");
            System.out.println("What would you like to do?");
            System.out.println("Create a new task? — enter 1");
            System.out.println("Print out existing tasks? - enter 2");
            System.out.println("Exit? — enter 3");
            String choice = scanner.nextLine();
            switch (choice) {
                case CREATE_NEW_TASK:
                    System.out.println("Enter the name of the task");
                    String name = scanner.nextLine();
                    System.out.println("Enter the name of the parent task, or leave it blank");
                    String parentName = scanner.nextLine();
                    if (add(parentName, name)) {
                        System.out.println("The task has been added.");
                    } else {
                        System.out.println("Something went wrong");
                    }
                    break;
                case PRINT:
                    print();
                    break;
                case EXIT:
                    run = false;
                    break;
                default:
                    System.out.println("Please, enter 1, 2 or 3");
            }
        }
    }

    public static void main(String[] args) {
        Menu menu = new SimpleMenu();
        MenuPrinter printer = new SimpleMenuPrinter();
        ToDoApp toDoApp = new ToDoApp(menu, printer);
        toDoApp.outputMenu();
    }
}
