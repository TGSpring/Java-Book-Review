/*
 * Tyler Spring
 * 11/14/2025
 * Chapter 15 Project 3
 * The actual ArrayList class in the java.util package has a method called subList that returns a view 
 * of a subportion of a list through a given range of indexes. It can be useful to think of part of a list as if 
 * it were its own list, complete with its own set of indexes and values. The sublist is "backed" by the 
 * original list, meaning that it is not a copy; if any change is made to the sublist, the original 
 * list is also affected.
 * 
 * In order to implement this method, you will need to write an inner class inside ArrayList<E> that extends 
 * ArrayList and implements the behavior of the sublist. Override the methods for getting and setting values 
 * at particular indexes, as well as the size method, so that they reflect the sublist's index range and size. 
 * Also modify the outer ArrayList<E> class so that it always refers to its own elements through the use of these
 * methods. The outer class should be given the following new method that returns an object of your new inner subList
 * class:
 * public ArrayList<E> subList(int fromIndex, int toIndex)
 */
public class Chpt15_Project3 {

}
