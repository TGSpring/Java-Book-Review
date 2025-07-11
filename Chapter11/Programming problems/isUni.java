/*
 * Tyler Spring
 * 7/11/2025
 * Chapter 11 Question 13
 * Write a method isUnique that accepts a map whose keys and values are strings as a parameter and returns true if no two keys 
 * map to the same value (and false if any two or more keys do map to the same value). For example, if the map contains the following
 * key/value pairs, your method should return true: {Marty=Stepp, Stuart=Reges, Jessica=Miller, Amanda=Camp, Hal=Perkins}. But calling
 * it on the following map would return false, because of two mappings for Perkins and Reges: {Kendrick=Perkins, Stuart=Reges, Jessica=Miller,
 * Bruce=Reges, Hal=Perkins}.
 */

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class isUni {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();

        map.put("Marty", "Stepp");
        map.put("Stuart", "Reges");
        map.put("Jessica", "Miller");
        map.put("Amanda", "Camp");
        map.put("Hal", "Perkins");

        // Used to edge case for false.
        // map.put("Bruce", "Reges");

        System.out.println(isUnique(map));
    }

    /*
     * Checks if the value has already been seen using contains.
     * If yes, returns false.
     * Otherwise, is added to the seenValues map.
     */
    public static boolean isUnique(Map<String, String> map) {
        Set<String> seenValues = new HashSet<>();
        for (String value : map.values()) {
            if (seenValues.contains(value)) {
                return false;
            }
            seenValues.add(value);
        }
        return true;
    }
}
/*
 * Time Complexity: O(n), where n is the number of entries in the map, each
 * value is checked and added at most once.
 * 
 * Space Complexity: O(n), for the seenValues set storing up to n unique values.
 */