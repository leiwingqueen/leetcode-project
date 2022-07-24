package com.liyongquan.weeklycontest.wc303;

import java.util.*;

public class FoodRatings {
    Map<String, Map<String, Integer>> m1;
    Map<String, TreeMap<Integer, TreeSet<String>>> m2;
    Map<String, String> m3;

    public FoodRatings(String[] foods, String[] cuisines, int[] ratings) {
        m1 = new HashMap<>();
        m2 = new HashMap<>();
        m3 = new HashMap<>();
        int n = foods.length;
        for (int i = 0; i < n; i++) {
            String food = foods[i];
            String cuisine = cuisines[i];
            int rating = ratings[i];
            if (!m1.containsKey(cuisine)) {
                m1.put(cuisine, new HashMap<>());
                m2.put(cuisine, new TreeMap<>());
            }
            m1.get(cuisine).put(food, rating);
            if (!m2.get(cuisine).containsKey(rating)) {
                m2.get(cuisine).put(rating, new TreeSet<>());
            }
            m2.get(cuisine).get(rating).add(food);

            m3.put(food, cuisine);
        }
    }

    public void changeRating(String food, int newRating) {
        String cuisine = m3.get(food);
        Integer old = m1.get(cuisine).get(food);
        if (old.intValue() == newRating) {
            return;
        }
        m1.get(cuisine).put(food, newRating);
        TreeMap<Integer, TreeSet<String>> treeMap = m2.get(cuisine);
        treeMap.get(old).remove(food);
        if (treeMap.get(old).size() == 0) {
            treeMap.remove(old);
        }
        if (!treeMap.containsKey(newRating)) {
            treeMap.put(newRating, new TreeSet<>());
        }
        treeMap.get(newRating).add(food);
    }

    public String highestRated(String cuisine) {
        TreeMap<Integer, TreeSet<String>> treeMap = m2.get(cuisine);
        return treeMap.lastEntry().getValue().first();
    }
}
