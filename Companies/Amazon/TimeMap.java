package Companies.Amazon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
Design a time-based key-value data structure that can store multiple values 
for the same key at different time stamps and retrieve the key's value at a certain timestamp.

Implement the TimeMap class:

TimeMap() Initializes the object of the data structure.
void set(String key, String value, int timestamp) Stores the key key with the value value 
at the given time timestamp.
String get(String key, int timestamp) Returns a value such that set was called previously, 
with timestamp_prev <= timestamp. If there are multiple such values, 
it returns the value associated with the largest timestamp_prev. If there are no values, 
it returns "".
*/
class TimeMap {

    private Map<String, List<Stamp>> m;
  
    /** Initialize your data structure here. */
    public TimeMap() {
        m = new HashMap<>();
    }

    public void set(String key, String value, int timestamp) {
        m.computeIfAbsent(key, k -> new ArrayList<>()).add(new Stamp(value, timestamp));
    }

    public String get(String key, int timestamp) {
        if (!m.containsKey(key)) {
            return "";
        }
        return search(m.get(key), timestamp);

    }

    private String search(List<Stamp> list, int time) {
        int low = 0, high = list.size() - 1;
        while (low < high) {
            int mid = (low + high + 1) >> 1;
            if (list.get(mid).time <= time) {
                low = mid;
            } else {
                high = mid - 1;
            }
        }
        return list.get(low).time <= time ? list.get(low).val : "";
    }

    private class Stamp {
        String val;
        int time;

        Stamp(String val, int time) {
            this.val = val;
            this.time = time;
        }
    }
}
