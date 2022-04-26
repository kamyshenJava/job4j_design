package ru.job4j.ood.isp.menu;

import java.util.*;

public class SimpleMenu implements Menu {

    private final List<MenuItem> rootElements = new ArrayList<>();

    @Override
    public boolean add(String parentName, String childName, ActionDelegate actionDelegate) {
        Optional<ItemInfo> parent = findItem(parentName);
        boolean hasParent = parent.isPresent();
        boolean isNull = Objects.equals(parentName, Menu.ROOT);
        if (isNull) {
            MenuItem newItem = new SimpleMenuItem(childName, actionDelegate);
            rootElements.add(newItem);
        }
        if (hasParent) {
            MenuItem newItem = new SimpleMenuItem(childName, actionDelegate);
            parent.get().menuItem.getChildren().add(newItem);
        }
        return hasParent || isNull;
    }

    @Override
    public Optional<MenuItemInfo> select(String itemName) {
        Optional<MenuItemInfo> rsl = Optional.empty();
        Optional<ItemInfo> item = findItem(itemName);
        if (item.isPresent()) {
            MenuItem mi = item.get().menuItem;
            String num = item.get().number;
            rsl = Optional.of(new MenuItemInfo(mi, num));
        }
        return rsl;
    }

    @Override
    public Iterator<MenuItemInfo> iterator() {
        DFSIterator iterator = new DFSIterator();
        return new Iterator<>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public MenuItemInfo next() {
                ItemInfo temp = iterator.next();
                return new MenuItemInfo(temp.menuItem, temp.number);
            }
        };
    }

    private Optional<ItemInfo> findItem(String name) {
        DFSIterator iterator = new DFSIterator();
        Optional<ItemInfo> rsl = Optional.empty();
        if (!Objects.equals(name, Menu.ROOT)) {
            while (iterator.hasNext()) {
                ItemInfo temp = iterator.next();
                if (name.equals(temp.menuItem.getName())) {
                    rsl = Optional.of(temp);
                    break;
                }
            }
        }
        return rsl;
    }

    private static class SimpleMenuItem implements MenuItem {

        private String name;
        private List<MenuItem> children = new ArrayList<>();
        private ActionDelegate actionDelegate;

        public SimpleMenuItem(String name, ActionDelegate actionDelegate) {
            this.name = name;
            this.actionDelegate = actionDelegate;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public List<MenuItem> getChildren() {
            return children;
        }

        @Override
        public ActionDelegate getActionDelegate() {
            return actionDelegate;
        }
    }

    private class DFSIterator implements Iterator<ItemInfo> {

        Deque<MenuItem> stack = new LinkedList<>();

        Deque<String> numbers = new LinkedList<>();

        DFSIterator() {
            int number = 1;
            for (MenuItem item : rootElements) {
                stack.addLast(item);
                numbers.addLast(String.valueOf(number++).concat("."));
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public ItemInfo next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            MenuItem current = stack.removeFirst();
            String lastNumber = numbers.removeFirst();
            List<MenuItem> children = current.getChildren();
            int currentNumber = children.size();
            for (var i = children.listIterator(children.size()); i.hasPrevious();) {
                stack.addFirst(i.previous());
                numbers.addFirst(lastNumber.concat(String.valueOf(currentNumber--)).concat("."));
            }
            return new ItemInfo(current, lastNumber);
        }

    }

    private class ItemInfo {

        MenuItem menuItem;
        String number;

        public ItemInfo(MenuItem menuItem, String number) {
            this.menuItem = menuItem;
            this.number = number;
        }
    }

}
