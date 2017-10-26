package com.benyamephrem.utils;

import java.util.Comparator;
import java.util.Map;

public class DoubleComparator implements Comparator<Map.Entry<Double, Double>> {

    public DoubleComparator() {
    }

    @Override
    public int compare(Map.Entry<Double, Double> o1, Map.Entry<Double, Double> o2) {
        if (o1.getValue() < o2.getValue()) {
            return 1;
        } else if (o1.getValue() > o2.getValue()) {
            return -1;
        }
        return 0;
    }
}
