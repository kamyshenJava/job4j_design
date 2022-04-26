package ru.job4j.ood.isp.menu;

public class SimpleMenuPrinter implements MenuPrinter {

    @Override
    public void print(Menu menu) {
        for (Menu.MenuItemInfo item : menu) {
            String num = item.getNumber();
            String name = item.getName();
            long count = num.chars().filter(ch -> ch == '.').count();
            String intro = " ".repeat((int) count - 1);
            System.out.println(intro + num + name);
        }
    }
}
