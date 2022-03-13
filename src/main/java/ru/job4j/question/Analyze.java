package ru.job4j.question;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Analyze {

    public static Info diff(Set<User> previous, Set<User> current) {
        Info rsl = new Info(0, 0, 0);
        Set<User> copyCurrent = new HashSet<>(current);
        copyCurrent.removeAll(previous);
        Set<Integer> prevIds = getSetOfIds(previous);
        Set<Integer> curIds = getSetOfIds(current);
        curIds.removeAll(prevIds);
        int added = curIds.size();
        int deleted = previous.size() - current.size() + added;
        int changed = copyCurrent.size() - added;
        rsl.setChanged(changed);
        rsl.setAdded(added);
        rsl.setDeleted(deleted);
        return rsl;
    }

    private static Set<Integer> getSetOfIds(Set<User> set) {
        return set.stream()
                .map(User::getId)
                .collect(Collectors.toSet());
    }
}
