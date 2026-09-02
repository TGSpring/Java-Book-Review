# Chapter 11 — Complete Study Guide

> Focus: Lists (ArrayList vs LinkedList), iterators, Sets and set operations, Maps (key/value uniqueness), and two projects: Levenshtein (word neighbor graph + BFS) and Random Writer (k-gram Markov model).

---

## Table of Contents
- Short answer review (lists, sets, maps)
- Core concepts explained
- Programming problems & walkthroughs
  - Ch11_Q2 — alternate (alternating elements from two lists)
  - Ch11_Q11 — SymmetricSetDifference (set symmetric difference)
  - Ch11_Q13 — isUnique (map values uniqueness)
- Projects
  - Levenshtein (dictionary neighbors, BFS for shortest edit path)
  - Random Writer (k-gram Markov model for character generation)
  - Supporting files: dict.txt, input.txt
- Common pitfalls and how to fix them (as discussed in sources)
- Suggested refactors & improvements (kept to file notes)
- Practice exercises
- Quick reference (List/Set/Map operations and iteration)
- Code examples
- Mini-quiz
- Master checklist

---

## Short answer review

### Section 11.1: Lists

Question: When should you use a LinkedList instead of an ArrayList?

Answer (from shortResponse.txt):
- Use LinkedList when you frequently add or remove elements at the beginning or middle of the list, or when traversing and modifying the list often (no shifting required).
- Use ArrayList when you need fast random access by index.

Question: What is an Iterator? Why use iterators with LinkedList?

Answer:
- An Iterator is an object that allows sequential retrieval of elements (hasNext(), next()).
- Iterators are efficient for LinkedList because they move from node to node without index-based access; they also allow safe removal while iterating.


### Section 11.2: Sets

Question: Why use a Set instead of a List?

Answer:
- A Set enforces uniqueness (no duplicates) and provides fast membership tests. Use when you need to ensure elements are unique and don't need positional access.

Question: When to use TreeSet vs HashSet?

Answer (from shortResponse.txt):
- TreeSet keeps elements sorted (backed by a tree); HashSet gives faster lookups but unordered iteration. Choose TreeSet when sorted order is required; otherwise HashSet for speed.


### Section 11.3: Maps

Question: How do you examine all entries in a Map?

Answer:
- Iterate over entrySet(), keySet(), or values(), or use for-each over map.entrySet() to access keys and values. (shortResponse notes maps associate keys with values and require these approaches).


## Core concepts explained

### Lists: ArrayList vs LinkedList
- ArrayList: random-access O(1) get(index); adding to end amortized O(1); inserting/removing in middle O(n) due to shifting.
- LinkedList: node-based, fast insertion/removal at ends or when positioned, but random access get(index) is O(n).
- Iteration: use Iterator when you need to remove during traversal (Iterator.remove()).


### Iterators
- Use iterators to traverse when index-based access is inefficient or when you need safe removal during iteration.
- For-each loop is convenient but cannot safely remove elements (may cause ConcurrentModificationException).


### Sets and Symmetric Difference
- Set operations used in Chapter 11 examples: addAll, retainAll, removeAll.
- Symmetric difference of two sets A and B = (A ∪ B) - (A ∩ B).


### Maps and Uniqueness of Values
- Maps map keys -> values. The isUnique problem checks whether no two keys map to the same value by scanning map.values() into a Set and detecting duplicates.


## Programming Problems & Walkthroughs

### Ch11_Q2: alternate — alternating elements from two lists

Problem: Write alternate(list1, list2) returning a new List containing alternating elements: first from list1, first from list2, second from list1, etc. If lists differ in length, append remaining elements from the longer list.

Approach (from Alternating.java):
1. Create an empty result ArrayList.
2. Determine max = Math.max(list1.size(), list2.size()).
3. Loop i from 0 to max-1; at each iteration, if i < list1.size() add list1.get(i); if i < list2.size() add list2.get(i).
4. Return result.

Example:
- list1 = [1,2,3,4,5], list2 = [6,7,8,9,10,11,12] => [1,6,2,7,3,8,4,9,5,10,11,12]

Code (see repository: Chapter11/Programming problems/Alternating.java)

Complexity (from file):
- Time: O(n) where n = length of longer list (each iteration does up to two get/add operations O(1)).
- Space: O(n + m) result list holds all elements from both lists.


### Ch11_Q11: SymmetricSetDifference

Problem: Return a new Set containing elements that are in either set but not both.

Approach (from SymDif.java):
1. result = new HashSet<>(set1); result.addAll(set2) // union
2. intersection = new HashSet<>(set1); intersection.retainAll(set2) // intersection
3. result.removeAll(intersection)
4. return result

Example: [1,4,7,9] and [2,4,5,6,7] => [1,2,5,6,9]

Complexity (from file):
- Time: O(n) total across set operations (addAll, retainAll, removeAll) proportional to number of elements.
- Space: O(n) for temporary sets.


### Ch11_Q13: isUnique — map values uniqueness

Problem: Given Map<String,String>, return true if no two keys map to same value.

Approach (from isUni.java):
1. Create empty HashSet<String> seenValues
2. For each value in map.values(): if seenValues.contains(value) return false; otherwise add value
3. If loop completes return true

Complexities (from file):
- Time: O(n) where n is number of map entries.
- Space: O(n) for seenValues set.


## Projects

### Levenshtein / Word Ladder (Chapter 11 Project 1)

Purpose (from Leven.java): Compute edit distance paths between words where each intermediate word must be a valid dictionary word. The implementation focuses on building a neighbor graph (words that differ by exactly one letter) and using BFS to find the shortest path between two words (e.g., "dog" → "cat").

Key steps (as implemented):
1. readWords(): read dictionary file into a Set<String> for O(1) membership checks. (Leven.java reads from "Dict.txt" and lowercases words.)
2. getNeighbors(word, dictionary): for each dictionary word of the same length, count character differences; if exactly one difference, it's a neighbor. Returns Set<String> neighbors.
   - This is done by iterating over dictionary and comparing lengths and characters with early exit when differences > 1.
3. Build neighborsMap: for each word in dictionary neighborsMap.put(word, getNeighbors(word, dictionary)). This precomputes adjacency for BFS.
4. findPath(start, end, neighborMap): BFS over the neighbor graph using a Queue<List<String>> of paths. Initialize with [start], mark visited words. While queue not empty, remove a path, if lastWord.equals(end) return path; otherwise enqueue new paths by appending unvisited neighbors.

Behavior and notes:
- BFS ensures shortest transformation path (fewest substitutions) because all edges have equal weight.
- The implementation stores whole paths in the queue (List<String>) which makes copying work but is straightforward and explicit.
- Input example: small dict.txt placed in Chapter11/Projects/dict.txt; Leven.java uses start="dog" and end="cat" in examples.

Complexity notes (from Leven.java comments):
- Building neighbors: O(n * m) where n = dictionary size and m = word length (character comparisons), with precomputation amortizing cost before BFS.
- BFS: visits each reachable word once; cost depends on branching factor and dictionary size.

Files referenced: Chapter11/Projects/dict.txt (small example dictionary). There is also a duplicate tempCodeRunnerFile.java containing the same Leven implementation.


### Random Writer (Chapter 11 Project 3)

Purpose (from randomWrite.java): Build a k-gram Markov model of input text to generate random text that reflects local character frequencies.

Key steps (as implemented):
1. Read entire input file ("input.txt") into a string `word` (includes newlines as read).
2. buildModel(text, k): for i from 0 to text.length() - k - 1 (actually text.length() - k in file), extract kgram = text.substring(i, i+k) and next = text.charAt(i+k); append `next` into the list stored at model.get(kgram) (using computeIfAbsent to create list).
   - The list of characters may contain duplicates to preserve frequency information.
3. generateText(model, start, length): start with `start` (first k characters) and repeatedly look up model.get(currentKgram) and randomly choose a next character from that list (using Random). Append chosen char to result and slide currentKgram forward by one.

Edge cases and notes (from file):
- If `word.length() < k` the program exits as there is insufficient data.
- When model.get(currentKgram) is null/empty, generation stops early.

Complexity notes included in file:
- Building model: Time O(n * k) (substring cost O(k) each iteration), overall O(n) when k is constant. Space O(n) for model.
- Generating text: Because result is built with `result += next` (String concatenation), generation time is O(m^2) due to repeated string copying; file comments remark that this could be fixed with StringBuilder but the implementation intentionally shows the naive approach.

Supporting file: Chapter11/Projects/input.txt


## Common pitfalls and how to fix them (as discussed in the files)

1. Inefficient string concatenation when building generated text (randomWrite.java): using `result += next` causes O(m^2) time; use a StringBuilder to achieve O(m).
2. Precomputing neighbors for Levenshtein may be expensive if dictionary is large; the implementation chooses precomputation to make BFS efficient at runtime.
3. When alternating lists, inserting into a list in-place at middle indices repeatedly would be O(n^2); the provided solution builds a new list to keep operations linear.


## Suggested refactors & improvements (restricted to notes in source files)

- randomWrite.java notes that String concatenation could be replaced by StringBuilder to avoid O(m^2) generation cost.
- Leven.java organizes precomputation of neighbor sets to make BFS simpler; alternative approaches (not implemented in these files) are not included here per your instruction to stay within discussed content.


## Practice exercises (relative to the chapter files)

1. Modify alternate to be generic (<T>) rather than List<Integer> and ensure type-safety.
2. Add unit tests for isUnique, SymmetricSetDifference, and alternate covering edge cases (empty collections, unequal sizes).
3. Extend the Levenshtein project to accept start/end words from user input or command-line arguments and test with larger dictionaries.
4. Replace string concatenation in randomWrite.generateText with StringBuilder and compare performance on sample input.txt.


## Quick reference — List / Set / Map patterns

List (alternating example):
- Creation: List<T> res = new ArrayList<>();
- Iteration by index when combining two lists: for (int i = 0; i < max; i++) { if (i < a.size()) res.add(a.get(i)); }

Set operations:
- Union: a.addAll(b)
- Intersection: tmp.retainAll(other)
- Symmetric difference: union - intersection (as implemented in SymDif.java)

Map value-uniqueness check:
- Use a HashSet to collect seen values while iterating map.values(); detect duplicates with contains().


## Code examples (extracted / adapted from repository files)

Alternating (core method):

```java name=Alternating.java url=https://github.com/TGSpring/Java-Book-Review/blob/main/Chapter11/Programming%20problems/Alternating.java
public static List alternate(List<Integer> list1, List<Integer> list2) {
    List<Integer> res = new ArrayList<>();
    int max = Math.max(list1.size(), list2.size());
    for (int i = 0; i < max; i++) {
        if (i < list1.size()) res.add(list1.get(i));
        if (i < list2.size()) res.add(list2.get(i));
    }
    return res;
}
```

isUnique (core method):

```java name=isUni.java url=https://github.com/TGSpring/Java-Book-Review/blob/main/Chapter11/Programming%20problems/isUni.java
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
```

SymmetricSetDifference (core method):

```java name=SymDif.java url=https://github.com/TGSpring/Java-Book-Review/blob/main/Chapter11/Programming%20problems/SymDif.java
public static Set<Integer> SymmetricSetDifference(Set<Integer> set1, Set<Integer> set2) {
    Set<Integer> result = new HashSet<>(set1);
    result.addAll(set2);
    Set<Integer> intersection = new HashSet<>(set1);
    intersection.retainAll(set2);
    result.removeAll(intersection);
    return result;
}
```

Levenshtein neighbor detection & BFS (core methods):

```java name=Leven.java url=https://github.com/TGSpring/Java-Book-Review/blob/main/Chapter11/Projects/Leven.java
public static Set<String> getNeighbors(String word, Set<String> dictionary) {
    Set<String> neighbors = new HashSet<>();
    for (String cur : dictionary) {
        if (cur.length() != word.length()) continue;
        int diff = 0;
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) != cur.charAt(i)) {
                diff++;
                if (diff > 1) break;
            }
        }
        if (diff == 1) neighbors.add(cur);
    }
    return neighbors;
}

public static List<String> findPath(String start, String end, Map<String, Set<String>> neighborMap) {
    Queue<List<String>> queue = new LinkedList<>();
    Set<String> visited = new HashSet<>();
    List<String> initialPath = new ArrayList<>();
    initialPath.add(start);
    queue.add(initialPath);
    visited.add(start);

    while (!queue.isEmpty()) {
        List<String> path = queue.remove();
        String lastWord = path.get(path.size() - 1);
        if (lastWord.equals(end)) return path;
        for (String neighbor : neighborMap.getOrDefault(lastWord, Collections.emptySet())) {
            if (!visited.contains(neighbor)) {
                visited.add(neighbor);
                List<String> newPath = new ArrayList<>(path);
                newPath.add(neighbor);
                queue.add(newPath);
            }
        }
    }
    return null;
}
```

Random Writer (build model & generate):

```java name=randomWrite.java url=https://github.com/TGSpring/Java-Book-Review/blob/main/Chapter11/Projects/randomWrite.java
public static Map<String, List<Character>> buildModel(String text, int k) {
    Map<String, List<Character>> model = new HashMap<>();
    for (int i = 0; i < text.length() - k; i++) {
        String kgram = text.substring(i, i + k);
        char next = text.charAt(i + k);
        model.computeIfAbsent(kgram, key -> new ArrayList<>()).add(next);
    }
    return model;
}

public static String generateText(Map<String, List<Character>> model, String start, int length) {
    String result = start;
    String currentKgram = start;
    Random rand = new Random();
    for (int i = 0; i < length - start.length(); i++) {
        List<Character> nextChars = model.get(currentKgram);
        if (nextChars == null || nextChars.isEmpty()) break;
        char next = nextChars.get(rand.nextInt(nextChars.size()));
        result += next; // note: causes O(m^2) behavior as discussed in the file
        currentKgram = currentKgram.substring(1) + next;
    }
    return result;
}
```


## Mini-quiz (self-check)

1. Why prefer LinkedList over ArrayList for frequent removals near the front?
   - Answer: LinkedList avoids shifting elements; insert/remove at front is O(1) once positioned.

2. How do you compute symmetric difference of two sets with basic Set operations?
   - Answer: union = A ∪ B; intersection = A ∩ B; symmetric difference = union - intersection.

3. How does the Levenshtein/word-ladder solution find the shortest path between words?
   - Answer: Build neighbor graph where edges connect words that differ by one letter, then run BFS which returns the shortest path.

4. Why does randomWrite.generateText as implemented have O(m^2) runtime for generation?
   - Answer: Because it uses repeated String concatenation (result += next) which copies the string each time; using StringBuilder would be O(m).


## Master checklist — before submitting

- [ ] alternate: returns a new list interleaving elements; handles unequal lengths and empty lists.
- [ ] SymmetricSetDifference: uses addAll, retainAll, removeAll to compute symmetric difference.
- [ ] isUnique: checks map.values() against a HashSet to ensure no duplicate values.
- [ ] Leven: reads dictionary into Set, builds neighborMap with getNeighbors, uses BFS to find shortest path.
- [ ] randomWrite: builds a k-gram model mapping k-gram -> list of following characters (frequency preserved), generates text by choosing random next characters; aware of string concatenation inefficiency.
- [ ] Examples and complexities are present in the repository files and reflected in this guide.


---

**Last Updated:** 2026-09-02
**Author:** Generated from Chapter 11 source files in repository TGSpring/Java-Book-Review
**Status:** Complete study guide based only on the Chapter11 files provided
