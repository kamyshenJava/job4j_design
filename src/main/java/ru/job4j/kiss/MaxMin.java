package ru.job4j.kiss;

import java.util.Comparator;
import java.util.List;

public class MaxMin {
    public <T> T max(List<T> value, Comparator<T> comparator) {
        return maxMin(value, comparator, 1);
    }

    public <T> T min(List<T> value, Comparator<T> comparator) {
        return maxMin(value, comparator, -1);
    }

    private <T> T maxMin(List<T> value, Comparator<T> comparator, int maxOrMin) {
        T rsl = value.get(0);
        for (int i = 1; i < value.size(); i++) {
            if (maxOrMin * comparator.compare(value.get(i), rsl) > 0) {
                rsl = value.get(i);
            }
        }
        return rsl;
    }
}
