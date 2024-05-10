import java.util.Scanner;

class start{

    private static int choice;
    private static ArrayList<String> todoList;
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
                    printToDo(unsorted);
                    break;
                case(4):
                    System.out.println("Sorted to do list: ");
                    printToDo(sorted);
                    break;                        
            }
            run();
    }

    private static int choseAction(){
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

    private static boolean stringSearch(ArrayList<String> a, String s){
        for(String x: a){
            if(x.equals(s)){
                return true;
            }
        }
        return false;
    }

    private static ArrayList<String> addElement(ArrayList<String> a,boolean r){
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
