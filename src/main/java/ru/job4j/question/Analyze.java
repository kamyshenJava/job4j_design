package ru.job4j.question;

import java.util.HashSet;
import java.util.Set;

public class Analyze {

    public static Info diff(Set<User> previous, Set<User> current) {
        Info rsl = new Info(0, 0, 0);
        int changed = 0;
        int added = current.size();
        for (User cur : current) {
            for (User prev : previous) {
                if (cur.getId() == prev.getId() && !cur.getName().equals(prev.getName())) {
                    changed++;
                }
                if (cur.equals(prev)) {
                    added--;
                }
            }
        }
        rsl.setChanged(changed);
        rsl.setAdded(added - changed);
        rsl.setDeleted(previous.size() - current.size() + added - changed);
        return rsl;
    }
}
