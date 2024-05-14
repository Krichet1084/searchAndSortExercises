import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;

class start{

    private static int choice;
    private static ArrayList<String> todoList = new ArrayList<>();
    private static ArrayList<String> sortedTodoList = new ArrayList<>();
    private static String tempInput=null;
    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args){
        System.out.println("Welcome to your personal to do list! ");
        run();
        
    }
    private static void run(){
        choice = choseAction();
            switch(choice){
                case(1):
                    System.out.print("Please input the task you would like to search for: ");
                    tempInput=input.nextLine();
                    if(stringSearch(todoList, tempInput)){
                        System.out.println(tempInput+" is on the to do list.");
                    }
                    else{
                        addElement(todoList, false);
                        }
                    break;
                case(2):
                    addElement(todoList, true);
                    break;
                case(3):
                    System.out.println("To do list:");
                    printToDo(false);
                    break;
                case(4):
                    System.out.println("Sorted to do list: ");
                    printToDo(true);
                    break;                        
            }
            run();
    }

    public static void printToDo(String s){
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

    public static void mergeSort(List<String> arr, int start, int end) {
        if (start != end) {
            int mid = (start + end) / 2;
            mergeSort(arr, start, mid);
            mergeSort(arr, mid + 1, end);
            merge(arr, start, mid, end);
        }
    }

    private static void merge(List<String> arr, int start, int mid, int end) {
        int i = start;
        int j = mid + 1;
        int k = 0;

        int[] temp = new int[end - start + 1];

        while ((i <= mid) && (j <= end)) {
            if (arr.get(i) < arr.get(j))
                temp[k++] = arr.get(i++);
            else
                temp[k++] = arr.get(j++);
        }

        while (i <= mid) {
            temp[k++] = arr.get(i++);
        }

        while (j <= end) {
            temp[k++] = arr.get(j++);
        }

        for (i = start; i <= end; i++) {
            arr.remove(i);
            int e = temp[i - start];
            arr.add(i, e);
        }
    }

    public static int choseAction(){
        int temp;
        try{
            temp=input.nextLine()
            if(temp>4 || temp<0){
                System.out.println("Input not in range please try again ");
                return choseAction;
            }
            else{
                return temp;
            }
        }
        catch{
            return choseAction;
        }
    }

    public static boolean stringSearch(ArrayList<String> a, String s){
        for(String x: a){
            if(x.equals(s)){
                return true;
            }
        }
        return false;
    }

    public static ArrayList<String> addElement(ArrayList<String> a,boolean r){
        if(!r)
            System.out.print("Task is not on the list, would you like to add it yes or no? ");
        tempInput=input.nextLine().toLowerCase();
        if(tempInput.equals("yes")){
            System.out.print("What is the name of the task you would like to add ");
            tempInput=input.nextLine();
            a.add(tempInput);
        }
        else if(tempInput.equals("no")){
            System.out.println("No changes made to the to do list. ");
        }
        return a;
    }
}
