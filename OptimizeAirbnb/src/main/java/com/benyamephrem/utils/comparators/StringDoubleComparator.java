package com.benyamephrem.utils.comparators;

import java.util.Comparator;
import java.util.Map;

public class StringDoubleComparator implements Comparator<Map.Entry<String, Map.Entry<String, Double>>> {

    public StringDoubleComparator() {
    }

    @Override
    public int compare(Map.Entry<String, Map.Entry<String, Double>> o1, Map.Entry<String, Map.Entry<String, Double>> o2) {
        if (o1.getValue().getValue() < o2.getValue().getValue()) {
            return 1;
        } else if (o1.getValue().getValue() > o2.getValue().getValue()) {
            return -1;
        }
        return 0;
    }
}