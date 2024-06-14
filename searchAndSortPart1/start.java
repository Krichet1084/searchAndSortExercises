package searchAndSortPart1;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

class start{

    private static String tempInput=null;
    private static final Scanner input = new Scanner(System.in);
    private static ArrayList<User> users = new ArrayList<>();
    public static void main(String[] args){
        login();
    }

    private static void run(User u){
        System.out.println("What would you like to do with your todo list? \n1. Search todo list \n2. Add elements \n3. Print todo list \n4. Print sorted todo list \n5. Log Out");
        switch(choseAction()){
            case(1):
                System.out.print("Please input the task you would like to search for: ");
                tempInput=input.next();
                if(stringSearch(u.getToDo(), tempInput)){
                    System.out.println(tempInput+" is on the to do list.");
                }
                else{
                    System.out.print("Element is not on the list would you like to add it? ");
                    if(input.next().equalsIgnoreCase("yes")){
                        u.getToDo().add(tempInput);
                    }
                }
                break;
            case(2):
                u.addElement(u.getToDo());
                break;
            case(3):
                System.out.println("To do list:");
                printToDo(false, u);
                break;
            case(4):
                System.out.println("Sorted to do list: ");
                printToDo(true, u);
                break;
            case(5):
                login();
        }
        run(u);
    }

    public static void login(){
        System.out.println("Enter a username and password to login, if it does not exist a new account will be created or you can use username quit to exist");
        System.out.print("Username: ");
        tempInput=input.next();
        if(tempInput.equals("quit")) System.exit(0);
        else{
            for(User x: users){
                if(x.getUser().equals(tempInput)){
                    System.out.println("User found");
                    System.out.print("Password: ");
                    if(x.getPass().equals(input.next())) run(x);
                    else{
                        login();
                    }
                    return;
                }
            }
            System.out.println("Creating new account");
            System.out.print("Password: ");
            users.add(new User(tempInput, input.next()));
            run(users.get(users.size()-1));
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

    public static void printToDo(boolean s, User u){
        if(s){
            u.setSorted(u.getToDo());
            User.mergeSort(u.getSorted());
            System.out.print("Todo list has been sorted! \nIt is now as follows:");
            for(String x: u.getSorted()){
                System.out.print(x+" ");
            }
        }
        else{
            System.out.print("Your todo list is as follows: ");
            for(String x: u.getToDo()){
                System.out.print(x+" ");
            }
        }
        System.out.print("\n");
    }

    public static boolean stringSearch(List<String> a, String s){
        for(String x: a){
            if(x.equals(s)){
                return true;
            }
        }
        return false;
    }

    

}
