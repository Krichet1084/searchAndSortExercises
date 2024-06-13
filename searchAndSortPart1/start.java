package searchAndSortPart1;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

class start{

    private static int choice;
    private static ArrayList<String> todoList = new ArrayList<>();
    private static ArrayList<String> sortedTodoList = new ArrayList<>();
    private static String tempInput=null;
    private static final Scanner input = new Scanner(System.in);

    public static void main(String[] args){
        System.out.println("Welcome to your personal to do list! ");
        todoList.add("clean room");
        todoList.add("shop");
        todoList.add("taxes");
        run();
        
    }
    private static void run(){
        System.out.println("What would you like to do with your todo list? \n1. Search todo list \n2. Add elements \n3. Print todo list \n4. Print sorted todo list");
        switch(choseAction()){
            case(1):
                System.out.print("Please input the task you would like to search for: ");
                tempInput=input.next();
                if(stringSearch(todoList, tempInput)){
                    System.out.println(tempInput+" is on the to do list.");
                }
                else{
                    System.out.print("Element is not on the list would you like to add it? ");
                    if(input.next().equalsIgnoreCase("yes")){
                        todoList.add(tempInput);
                    }
                }
                break;
            case(2):
                addElement(todoList);
                break;
            case(3):
                System.out.println("To do list:");
                printToDo(false);
                break;
            case(4):
                System.out.println("Sorted to do list: ");
                printToDo(true);
                break;
            case(5):
                System.exit(0);
        }
        run();
    }

    public static void printToDo(boolean s){
        if(s){
            sortedTodoList=todoList;
            mergeSort(sortedTodoList);
            System.out.print("Todo list has been sorted! \nIt is now as follows:");
            for(String x: sortedTodoList){
                System.out.print(x+" ");
            }
        }
        else{
            System.out.print("Your todo list is as follows: ");
            for(String x: todoList){
                System.out.print(x+" ");
            }
        }
        System.out.print("\n");
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

    public static int choseAction(){
        int temp;
        while(true){
            try{
                temp=input.nextInt();
                if(temp>5 || temp<0){
                    System.out.println("Input not in range please try again ");
                    return choseAction();
                }
                else{
                    return temp;
                }
            }
            catch(InputMismatchException e){
                System.out.println("Invalid input");
                if (input.next().isEmpty()) {
                    break;
                }
            }
        }
        return 0;
    }

    public static boolean stringSearch(List<String> a, String s){
        for(String x: a){
            if(x.equals(s)){
                return true;
            }
        }
        return false;
    }

    public static List<String> addElement(List<String> a){
        System.out.print("What would you like to add to your list? ");
        tempInput=input.next().toLowerCase();
        if(stringSearch(todoList, tempInput)){
            System.out.print("Task is already on your todo list");
        }
        else{
            a.add(tempInput);
        }
        return a;
    }
}
