package com.benyamephrem.utils;

import java.util.Comparator;
import java.util.Map;

public class EntryComparator implements Comparator<Map.Entry<String,Integer>> {

    /*
    * Credits to 'sebadagostino' on Stack Overflow for this code
    * to sort HashMap by value (whereas sorting by key is built into
    * the Hashmpa class, therefore we have to do this to make our own
    * value sorting implementation
     */
    public EntryComparator(){

    }
    //Descending order
    @Override
    public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
        if (o1.getValue() < o2.getValue()) {
            return 1;
        } else if (o1.getValue() > o2.getValue()) {
            return -1;
        }
        return 0;
    }

}