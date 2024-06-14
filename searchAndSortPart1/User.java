package searchAndSortPart1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class User{

    private static String user;
    private static String pass;

    private static int choice;
    private ArrayList<String> todoList = new ArrayList<>();
    private ArrayList<String> sortedTodoList = new ArrayList<>();
    private String tempInput;
    private static Scanner input = new Scanner(System.in);

    public User(String u, String p){
        user=u;
        pass=p;
    }

    

    public static List<String> sortTodoList(List<String> l){
        mergeSort(l);
        return l;
    }

    public static void mergeSort(List<String> list) {
        if (list.size() <= 1) {
            return;
        }

        int middle = list.size() / 2;
        ArrayList<String> left = new ArrayList<>(list.subList(0, middle));
        ArrayList<String> right = new ArrayList<>(list.subList(middle, list.size()));

        mergeSort(left);
        mergeSort(right);

        merge(left, right, list);
    }

    private static void merge(List<String> left, List<String> right, List<String> list) {
        int leftIndex = 0;
        int rightIndex = 0;
        int listIndex = 0;

        while (leftIndex < left.size() && rightIndex < right.size()) {
            if (left.get(leftIndex).compareTo(right.get(rightIndex)) < 0) {
                list.set(listIndex++, left.get(leftIndex++));
            } else {
                list.set(listIndex++, right.get(rightIndex++));
            }
        }

        while (leftIndex < left.size()) {
            list.set(listIndex++, left.get(leftIndex++));
        }

        while (rightIndex < right.size()) {
            list.set(listIndex++, right.get(rightIndex++));
        }
    }

    public List<String> addElement(List<String> a){
        System.out.print("What would you like to add to your list? ");
        tempInput=input.next().toLowerCase();
        if(start.stringSearch(todoList, tempInput)){
            System.out.print("Task is already on your todo list");
        }
        else{
            a.add(tempInput);
        }
        return a;
    }

    public ArrayList<String> getToDo(){
        return todoList;
    }

    public List<String> getSorted(){
        return sortedTodoList;
    }

    public void setSorted(ArrayList<String> l){
        sortedTodoList=l;
    }

    public String getUser(){
        return user;
    }

    public String getPass(){
        return pass;
    }
}