//Rohan Gopinathan
//rsg190003

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main{
    // class that holds all user details to be stored in the hashmap 
    private static class Account{
        private String username;
        private String password;
        private ArrayList<Order> orders = new ArrayList<Order>();
        private double totCost;

        public Account(String user, String pass){
            this.username = user;
            this.password = pass;
           
        }

        // class methods

         // add a new order for this existing user
         public void addOrder(Order someOrder){
            // store the order in the account arraylist
            orders.add(someOrder);
            // update the total cost for every order with that order's cost
            //totCost += someOrder.getOrderCost();
        }

        public void displayReceipts(){
            Object[] array = orders.toArray();

            System.out.println();

            if(array.length == 0){
                System.out.println("No orders");
                System.out.println("Customer Total: $0.00");
                System.out.println();
                return;
            }

            for(int i = 0; i < array.length; i++){
                System.out.println(array[i].toString());
                System.out.println("Order Total: $" + String.format("%.2f",((Main.Order) array[i]).orderCost));
                System.out.println();
                
            }

            System.out.println("Customer Total: $" + String.format("%.2f", totCost));
        }

        public void displayOrders(){

            Object[] array = orders.toArray();

            if(array.length == 0){
                System.out.println("No orders");
                System.out.println("Customer Total: $0.00");
                System.out.println();
                return;
            }

            for(int i = 0; i < array.length; i++){
                System.out.println();

                if(array[i] != null){
                    System.out.println(array[i].toString());
                }
             }
        }

        public void displayUpdate(){

            Object[] array = orders.toArray();

            if(array.length == 0){
                System.out.println("No orders");
                System.out.println("Customer Total: $0.00");
                System.out.println();
                return;
            }

            for(int i = 0; i < array.length; i++){
                System.out.println();

                if(array[i] != null){
                    System.out.println((i+1) + ". " + array[i].toString());
                }
             }
        }

        // Mutators
        public void setUsername(String username) {
            this.username = username;
        }
        public void setPassword(String password) {
            this.password = password;
        }
        public void setTotCost(double totCost) {
            this.totCost = totCost;
        }
        
        // Accessors 
        public String getUsername() {
            return username;
        }
        public String getPassword() {
            return password;
        }

        public double getTotCost() {
            return totCost;
        }

        public Order getOrder(int index){
            index -= 1;

            return orders.get(index);
        }
    }

    private static class Order{
        // class that keeps track of all the orders for a user

        private int audiNumber;
        private int row;
        private char startSeat;
        private int numAdult;
        private int numChild;
        private int numSenior;
        private int totTix;
        private double orderCost;

        private ArrayList<Ticket> tickets = new ArrayList<Ticket>();

        private Ticket[][] tix = new Ticket[10][26];

        public Order(){}

        public Order(int audiNum, int orderRow, char seat, int adults, int childs, int seniors){
            this.audiNumber = audiNum;
            this.row = orderRow;
            this.startSeat = seat;
            
        }

        // add a new ticket for this existing order
        public void addTicket(Ticket someTicket){
            // store the order in the account arraylist
            tickets.add(someTicket);
            totTix++;
        }


        // search for a specific ticket in a order and return the ticket
        public Ticket getTicket(int row, char startSeat) {

            Object[] allTickets = tickets.toArray();

            for(int i = 0; i < allTickets.length; i++){
                if(((Main.Ticket) allTickets[i]).getSeat() == startSeat &&  ((Main.Ticket) allTickets[i]).getRow() == row){
                    return (Main.Ticket) allTickets[i];
                }
            }

            return null;
        }

        // search for a specific ticket in a order and see if it exists
        public boolean searchTicket(int row, char startSeat) {

            Object[] allTickets = tickets.toArray();

            for(int i = 0; i < allTickets.length; i++){
                if(((Main.Ticket) allTickets[i]).getSeat() == startSeat &&  ((Main.Ticket) allTickets[i]).getRow() == row){
                    return true;
                }
            }

            return false;
        }

        // check if every ticket in an order has been removed
        // if so, then set the order to null
        // return a boolean
        public boolean allRemoved(){
            int countNull = 0;

            for(int i = 0; i < tickets.size()-1; i++){
                if(tickets.get(i) == null){
                    countNull +=1;
                }
            }

            if(countNull == tickets.size()){
                return true;
            }

            return false;
        }

        public void sortTickets(){
            // put each ticket in corresponding row in the 2D array
            // sort the rows by alphabetical
            // put ordered tickets back into the arraylist

            for(int i = 0; i < tickets.size(); i++){
                int thisRow = tickets.get(i).getRow() - 1;
                int thisSeat = tickets.get(i).getSeat() - 65;

                tix[thisRow][thisSeat] = tickets.get(i);
            }

            // reset arraylist
            tickets.clear();

            // put all ordered tickets back into arraylist 

            for(int i = 0; i < 10; i++){
                for(int j = 0; j < 26; j++){
                    if(tix[i][j] != null){
                        tickets.add(tix[i][j]);
                    }
                }
            }
           
        }

        public double getOrderCost() {
            return this.orderCost;
        }

        public int getAudiNumber() {
            return audiNumber;
        }

        public int getRow() {
            return row;
        }

        public char getStartSeat() {
            return startSeat;
        }

        public int getTotTix() {
            return totTix;
        }

        public int getNumAdult() {
            return numAdult;
        }
        public int getNumChild() {
            return numChild;
        }   
        public int getNumSenior() {
            return numSenior;
        }

        public void setOrderCost(double orderCost) {
            this.orderCost = orderCost;
        }

        public void setTotTix(int totTix) {
            this.totTix = totTix;
        }

        public void setNumAdult(int numAdult) {
            this.numAdult = numAdult;
        }
        public void setNumChild(int numChild) {
            this.numChild = numChild;
        }
        public void setNumSenior(int numSenior) {
            this.numSenior = numSenior;
        }

        @Override
        public String toString() {
            String outString = "Auditorium " + this.audiNumber + ", ";
            String str = "";
           
            for(int i = 0; i < tickets.size(); i++){
                if(this.tickets.get(i) != null){
                    if(i == tickets.size()-1){
                        str = str +  this.tickets.get(i).toString();   
                    }
                    else{
                        str = str +  this.tickets.get(i).toString() + ",";   
                    }
                }
            }
            
            outString = outString + str + "\n" +
            numAdult + " adult, " + numChild + " child, " + numSenior + " senior";

            return outString;
        }
    }

    private static class Ticket{
    
        private int row;
        private char seat;
        private char tType;
        private double tCost;
        private String tHolder;

        //default
        public Ticket() {}

        //overloaded
        public Ticket(int row, char seat, char tType){
            this.row = row;
            this.seat = seat;
            this.tType = tType;

            if(tType == 'A'){
                tCost = 10.00;
            }
            if(tType == 'C'){
                tCost = 5.00;
            }
            if(tType == 'S'){
                tCost = 7.50;
            }
        }

        //accessors
        public char getSeat() {
            return seat;
        }
        public int getRow() {
            return row;
        }
        public char gettType() {
            return tType;
        }

        public String gettHolder() {
            return tHolder;
        }

        public double gettCost() {
            return tCost;
        }

        public void settCost(double tCost) {
            this.tCost = tCost;
        }

        public void settHolder(String tHolder) {
            this.tHolder = tHolder;
        }

        //mutators
        public void setRow(int row) {
            this.row = row;
        }
        public void setSeat(char seat) {
            this.seat = seat;
        }
        public void settType(char tType) {
            this.tType = tType;
        }

        @Override
        public String toString() {
            
            return (String) "" + this.row + this.seat;
        }
    }
    
    
    // start of main
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(System.in);

        // create the initial user database hashmap that stores all username and passwords
        HashMap<String, Account> dataB = createDB();
        
        ArrayList<Character> letters = letters();

        // create all three auditorium objects to user for reserving seats
        Auditorium audi1 = Auditorium1(letters);
        Auditorium audi2 = Auditorium2(letters);
        Auditorium audi3 = Auditorium3(letters);
        
        boolean loggedout;

        do{
            // starting point (login menu)
           String accountName = startingPoint(in, dataB, audi1, audi2, audi3);
        
           // main menu access point
           // while user doesn't choose to logout, keep looping the main menu
           // if user chooses to log out, go back to starting point
           loggedout = false;
           do{
              accountName = mainMenu(in,dataB, audi1, audi2, audi3, letters, accountName);

              if(accountName.equals("logout")){
                  loggedout = true;
              }

           } while(!loggedout);

        } while(true);
    }

    private static String mainMenu(Scanner in, HashMap<String, Account> dataB, Auditorium audi1, Auditorium audi2, Auditorium audi3, ArrayList<Character> letters, String accountName) {
        String tempString = " ";
        int userChoice = 0;

        do{
            try {
                System.out.println();
                System.out.println("1. Reserve Seats");
                System.out.println("2. View Orders");
                System.out.println("3. Update order");
                System.out.println("4. Display Receipt");
                System.out.println("5. Log out");
            
                if(in.hasNextLine()){
                    tempString = in.nextLine();
                }
                
                try {
                    userChoice = Integer.parseInt(tempString);
                } catch (NumberFormatException nfe) {
                    System.out.println("Invalid number option entered.");
                    userChoice = 0;
                }
                
                if(userChoice != 1 && userChoice != 2  && userChoice != 3 && userChoice != 4 && userChoice != 5){
                    userChoice = 0;
                }

            } catch (NoSuchElementException nse) {

                if(in.hasNextLine()){
                    in.nextLine();
                }
            }

        } while(userChoice == 0);

        if(userChoice == 1){
            boolean checkBest = true;
 
            Order someOrder = new Order();

            int audiNum = 0;  
            chooseAudi(in, audi1, audi2, audi3, letters, dataB, accountName,checkBest, someOrder, audiNum);
        }
        if(userChoice == 2){
            viewOrders(dataB, accountName);
        }
        if(userChoice == 3){
            updateOrder(in, dataB, accountName, audi1, audi2, audi3, letters);
        }
        if(userChoice == 4){
            receipts(dataB, accountName);
        }
        if(userChoice == 5){
          System.out.println("Logging out...");
          System.out.println();

          return "logout";
        }

        return accountName;
    }

    private static void receipts(HashMap<String, Main.Account> dataB, String acccountName) {
       // access the account of the user that is logged in
       Account thisAccount = dataB.get(acccountName);

       thisAccount.displayReceipts();
    }

    private static void updateOrder(Scanner in, HashMap<String, Main.Account> dataB, String acccountName, Auditorium audi1, Auditorium audi2, Auditorium audi3, ArrayList<Character> letters) {
        String tempString = " ";
        int userChoice = 0, orderIndex = 0;;

        Account thisAccount = dataB.get(acccountName);

        thisAccount.displayUpdate();

        if(thisAccount.orders.isEmpty()){
            System.out.println("No order to update");
            return;
        }

        System.out.println();
        System.out.println("Select which order to update");

        do{
            try {
               
                if(in.hasNextLine()){
                    tempString = in.nextLine();
                }
                
                try {
                    orderIndex = Integer.parseInt(tempString);
                } catch (NumberFormatException nfe) {
                    System.out.println("Invalid number option entered.");
                    orderIndex = 0;
                }
                
                if(thisAccount.orders.size() < orderIndex || orderIndex <= 0){
                    System.out.println("Invalid Order number entered");
                    orderIndex = 0;
                }

            } catch (NoSuchElementException nse) {

                if(in.hasNextLine()){
                    in.nextLine();
                }
            }

        } while(orderIndex == 0);

        // access the order that the user has selected to update
        Order thisOrder = thisAccount.getOrder(orderIndex);

        // get the auditorium that this order has seats in
        int audiNum = thisOrder.getAudiNumber();

        do{
            try {
                System.out.println();
                System.out.println("1. Add Tickets to Order");
                System.out.println("2. Delete Tickets from Order");
                System.out.println("3. Cancel Order");
                System.out.println();
            
                if(in.hasNextLine()){
                    tempString = in.nextLine();
                }
                
                try {
                    userChoice = Integer.parseInt(tempString);
                } catch (NumberFormatException nfe) {
                    System.out.println("Invalid number option entered.");
                    userChoice = 0;
                }
                
                if(userChoice != 1 && userChoice != 2  && userChoice != 3){
                    userChoice = 0;
                }

            } catch (NoSuchElementException nse) {

                if(in.hasNextLine()){
                    in.nextLine();
                }
            }

        } while(userChoice == 0);

        if(userChoice == 1){
            // add tickets to order
            boolean checkBest = false;
            chooseAudi(in, audi1, audi2, audi3, letters, dataB, acccountName,checkBest,thisOrder, audiNum);          
        }

        if(userChoice == 2){
            // remove tickets
            // if all tickets from order removed, cancel order
            // unreserve seats and decrement order/auditorium costs

            tempString = "";
            int rowNumber = 0;
            char startLetter = ' ';
        
            System.out.println();
            
            //use a flag system to loop for every user input to ensure loops until correct input is put in
            //read in all input as a string and then parse as an int to detect if \n characters are inputted 

            boolean flag1 = true;
            while(flag1){
                try {
                    System.out.println("Enter row number: ");

                    if(in.hasNextLine()){
                        tempString = in.nextLine();
                    }
      
                    try {
                        rowNumber = Integer.parseInt(tempString);
                    } catch (NumberFormatException nfe) {
                        rowNumber = 0;
                    }

                    if(audiNum == 1){
                        if(rowNumber > 0 && rowNumber <= audi1.getNumRows()){
                            flag1 = false;
                        }
                    }
                    if(audiNum == 2){
                        if(rowNumber > 0 && rowNumber <= audi2.getNumRows()){
                            flag1 = false;
                        }
                    }
                    if(audiNum == 3){
                        if(rowNumber > 0 && rowNumber <= audi3.getNumRows()){
                            flag1 = false;
                        }
                    }
        
                    else if(flag1) {
                        System.out.println("Try again");
                    }
                } catch (InputMismatchException ime) {

                    System.out.println("Invalid type of input");
                    System.out.println();

                    if(in.hasNextLine()){
                        in.nextLine();
                    }
                }                         
            }
           
            //user input for starting seat letter, catches anything not appropriate
            boolean flag2 = true;
            while(flag2){
                try {
                    System.out.println("Enter starting seat letter: ");

                    if(in.hasNextLine()){
                        tempString = in.nextLine();
                    }
                    
                    startLetter = Character.toUpperCase(tempString.charAt(0));

                    if(startLetter >= 'A' && startLetter <= 'Z'){
                        flag2 = false;
                    }

                    else{
                        System.out.println("Input not within range");
                    }

                    } catch (InputMismatchException ime) {

                        System.out.println("Invalid type of input");
                        System.out.println();

                        if(in.hasNextLine()){
                            in.nextLine();
                        }
                    }
            }

            // if user chose a ticket that is in auditorium 1
            if(audiNum == 1){
               
                int row = thisOrder.getRow();
                
                // search for the specific node that the user is trying to remove ticket from
                Node thisNode = audi1.searchNode(row - 1, startLetter);
                
                // if user input matches a ticket in account's orders
                if(thisOrder.searchTicket(row,startLetter)){
                    System.out.println("Removing ticket...");

                    Ticket thisTicket = thisOrder.getTicket(row, startLetter);

                    // decrement the number of types of tickets in the order according to the ticket type that was removed
                    if(thisTicket.tType == 'A'){
                        thisOrder.setNumAdult(thisOrder.getNumAdult()-1);
                    }
                    if(thisTicket.tType == 'C'){
                        thisOrder.setNumChild(thisOrder.getNumChild()-1);
                    }
                    if(thisTicket.tType == 'S'){
                        thisOrder.setNumSenior(thisOrder.getNumSenior()-1);
                    }

                    // mark the seat as available once more
                    // update the account and order totals according to the ticket that was removed
                    // decrement the number of tickets within the account
                    // remove the ticket from the order arraylist
                    thisNode.getPayload().settType('.');
                    thisAccount.setTotCost(thisAccount.getTotCost() - thisTicket.gettCost());
                    thisOrder.setOrderCost(thisOrder.getOrderCost() - thisTicket.gettCost());
                    thisOrder.setTotTix(thisOrder.getTotTix()-1);

                    thisOrder.tickets.remove(thisTicket);
                }
                else{
                    // the user input doesn't match any tickets within the order
                    // go back to the update menu
                    System.out.println("Entered seat does not match any ticket in this order");
                    updateOrder(in, dataB, acccountName, audi1, audi2, audi3, letters);
                }             
            }

            // if user chose a ticket that is in auditorium 2
            if(audiNum == 2){
        
                int row = thisOrder.getRow();
                
                // search for the specific node that the user is trying to remove ticket from
                Node thisNode = audi2.searchNode(row - 1, startLetter);
                
                // if user input matches a ticket in account's orders
                if(thisOrder.searchTicket(row,startLetter)){

                    Ticket thisTicket = thisOrder.getTicket(row, startLetter);

                    // decrement the number of types of tickets in the order according to the ticket type that was removed
                    if(thisTicket.tType == 'A'){
                        thisOrder.setNumAdult(thisOrder.getNumAdult()-1);
                    }
                    if(thisTicket.tType == 'C'){
                        thisOrder.setNumChild(thisOrder.getNumChild()-1);
                    }
                    if(thisTicket.tType == 'S'){
                        thisOrder.setNumSenior(thisOrder.getNumSenior()-1);
                    }

                    // mark the seat as available once more
                    // update the account and order totals according to the ticket that was removed
                    // decrement the number of tickets within the account
                    // remove the ticket from the order arraylist
                    thisNode.getPayload().settType('.');
                    thisAccount.setTotCost(thisAccount.getTotCost() - thisTicket.gettCost());
                    thisOrder.setOrderCost(thisOrder.getOrderCost() - thisTicket.gettCost());
                    thisOrder.setTotTix(thisOrder.getTotTix()-1);

                    thisOrder.tickets.remove(thisTicket);
                }
                else{
                    // the user input doesn't match any tickets within the order
                    // go back to the update menu
                    System.out.println("Entered seat does not match any ticket in this order");
                    updateOrder(in, dataB, acccountName, audi1, audi2, audi3, letters);
                }
            }

            // if user chose a ticket that is in auditorium 3
            if(audiNum == 3){
                int row = thisOrder.getRow();
                
                // search for the specific node that the user is trying to remove ticket from
                Node thisNode = audi3.searchNode(row - 1, startLetter);

                 // if user input matches a ticket in account's orders
                if(thisOrder.searchTicket(row,startLetter)){

                    Ticket thisTicket = thisOrder.getTicket(row, startLetter);

                    // decrement the number of types of tickets in the order according to the ticket type that was removed
                    if(thisTicket.tType == 'A'){
                        thisOrder.setNumAdult(thisOrder.getNumAdult()-1);
                    }
                    if(thisTicket.tType == 'C'){
                        thisOrder.setNumChild(thisOrder.getNumChild()-1);
                    }
                    if(thisTicket.tType == 'S'){
                        thisOrder.setNumSenior(thisOrder.getNumSenior()-1);
                    }

                    // mark the seat as available once more
                    // update the account and order totals according to the ticket that was removed
                    // decrement the number of tickets within the account
                    // remove the ticket from the order arraylist
                    thisNode.getPayload().settType('.');
                    thisAccount.setTotCost(thisAccount.getTotCost() - thisTicket.gettCost());
                    thisOrder.setOrderCost(thisOrder.getOrderCost() - thisTicket.gettCost());
                    thisOrder.setTotTix(thisOrder.getTotTix()-1);

                    thisOrder.tickets.remove(thisTicket);
                }
                else{
                    // the user input doesn't match any tickets within the order
                    // go back to the update menu
                    System.out.println("Entered seat does not match any ticket in this order");
                    updateOrder(in, dataB, acccountName, audi1, audi2, audi3, letters);
                }
            }

            // if every ticket in the order is removed, set the order to null
            if(thisOrder.allRemoved()){
                System.out.println("All tickets removed, cancelling order");
                // set the new account total after removing the order
                thisAccount.setTotCost(thisAccount.getTotCost() - thisOrder.getOrderCost());
                // set the order to null
                thisAccount.orders.remove(thisOrder);
            }

        }

        if(userChoice == 3){
            // cancel order
            System.out.println("Cancelling order...");

            // unreserve all seats of the order
            
            if(audiNum == 1){
                int totTix = thisOrder.getTotTix();
                // for each ticket in the order, set all of the seats back to available
                for(int i = 0; i < totTix; i++){
                    int row = thisOrder.tickets.get(i).getRow() - 1;
                    char startSeat = (char) (thisOrder.tickets.get(i).getSeat());

                    Node thisNode = audi1.searchNode(row, startSeat);
                    thisNode.getPayload().settType('.');
                }
            }

            if(audiNum == 2){
                int totTix = thisOrder.getTotTix();
                // for each ticket in the order, set all of the seats back to available
                for(int i = 0; i < totTix; i++){
                    int row = thisOrder.tickets.get(i).getRow() - 1;
                    char startSeat = (char) (thisOrder.tickets.get(i).getSeat()+i);

                    Node thisNode = audi2.searchNode(row, startSeat);
                    thisNode.getPayload().settType('.');
                }
            }

            if(audiNum == 3){
                int totTix = thisOrder.getTotTix();
                // for each ticket in the order, set all of the seats back to available
                for(int i = 0; i < totTix; i++){
                    int row = thisOrder.tickets.get(i).getRow() - 1;
                    char startSeat = (char) (thisOrder.tickets.get(i).getSeat()+i);

                    Node thisNode = audi3.searchNode(row, startSeat);
                    thisNode.getPayload().settType('.');
                }
            }

            // set the new account total after removing the order
            thisAccount.setTotCost(thisAccount.getTotCost() - thisOrder.getOrderCost());
            // set order to null
            thisAccount.orders.remove(thisOrder);
        }
    }

    private static void viewOrders(HashMap<String, Main.Account> dataB, String acccountName) {
        // access the account of the user that is logged in
        Account thisAccount = dataB.get(acccountName);

        thisAccount.displayOrders();
    }

    private static String startingPoint(Scanner in, HashMap<String, Account> dataB, Auditorium audi1, Auditorium audi2, Auditorium audi3) {
        // have the user log in
        // verify password before letting user perform actions
        // while user has yet to log in, keep running back to starting point
        boolean loggedIn = false;
        String accountName = "";

        while(!loggedIn){

            accountName = login(in, dataB, audi1, audi2, audi3);

            if(!accountName.equals("false")){
                loggedIn = true;
            }
        }

        return accountName;
    }

    private static String login(Scanner in, HashMap<String,Account> dataB, Auditorium audi1, Auditorium audi2, Auditorium audi3) {
        // take in user input for username
        // confirm is a username that is within the database
        // if not, call function again
        // if so, have user enter password, and verify password

        System.out.print("Enter username: ");

        if(in.hasNextLine()){
            String user = in.nextLine();

            // if a valid username is entered, prompt for the password
            if(dataB.containsKey(user)){

                Account thisAccount = dataB.get(user);
                String actualPass = thisAccount.getPassword();

                for(int i = 2; i > -1; i--){
                    System.out.print("Enter password (case sensitive): ");
                    
                    String inputPass = in.nextLine();

                    // if input password matches password of the account, log the user in and proceed to main menu
                    if(inputPass.equals(actualPass)){

                        // if admin, go to admin commands
                        if(user.equals("admin")){

                            try {
                                admin(in,dataB, audi1, audi2, audi3);

                            } catch (IOException e) {
                                
                                e.printStackTrace();
                            }

                            return user;
                        }
                
                        // if regular user, perform as normal
                        else{
                            System.out.println("Welcome " + user + ", you are logged in. ");
                            System.out.println();
                            return user;
                        }
                       
                    }
                    else{
                        System.out.println("Invalid password, " + i + " attempt(s) left.");
                        System.out.println();
                    }
                }
            }

            // if an username is entered that does not exist in the hashmap, start login process again until valid username entered
            else{
                System.out.println("No existing account, enter again.");
                System.out.println();
                return "false";
            }
        }

        System.out.println("No valid password entered, returning to login.");
        System.out.println();

        return "false";
    }

    private static void admin(Scanner in, HashMap<String, Main.Account> dataB, Auditorium audi1, Auditorium audi2, Auditorium audi3) throws IOException {
        // admin main menu
        // display all auditorium detailos
        // log out and go back to starting point
        // exit the program and write final auditorium states to respective files
        String tempString = " ";
        int userChoice = 0;

        do{
            try {
                System.out.println();
                System.out.println("1. Print Report");
                System.out.println("2. Logout");
                System.out.println("3. Exit");
                System.out.println();
            
                if(in.hasNextLine()){
                    tempString = in.nextLine();
                }
                
                try {
                    userChoice = Integer.parseInt(tempString);
                } catch (NumberFormatException nfe) {
                    System.out.println("Invalid number option entered.");
                    userChoice = 0;
                }
                
                if(userChoice != 1 && userChoice != 2  && userChoice != 3 && userChoice != 4 && userChoice != 5){
                    userChoice = 0;
                }

            } catch (NoSuchElementException nse) {

                if(in.hasNextLine()){
                    in.nextLine();
                }
            }

        } while(userChoice == 0);

        if(userChoice == 1){
            audi1.printReport("Auditorium 1");
            audi2.printReport("Auditorium 2");
            audi3.printReport("Auditorium 3");

            System.out.println();
            printOverall(audi1, audi2, audi3);

            admin(in, dataB, audi1, audi2, audi3);
        }

        if(userChoice == 2){
            System.out.println("Logging out...");
            System.out.println();

            startingPoint(in, dataB, audi1, audi2, audi3);
        }
        
        if(userChoice == 3){
            System.out.println("Exiting program...");
            
            FileWriter fw1 = new FileWriter("A1Final.txt");
            try {

                audi1.writeToFile(fw1);

            } catch (IOException e) {
                
                e.printStackTrace();
            }

            FileWriter fw2 = new FileWriter("A2Final.txt");
            try {

                audi1.writeToFile(fw2);

            } catch (IOException e) {
                
                e.printStackTrace();
            }

            FileWriter fw3 = new FileWriter("A3Final.txt");
            try {

                audi1.writeToFile(fw3);

            } catch (IOException e) {
                
                e.printStackTrace();
            }
            System.exit(0);

        } 
    }

    private static void printOverall(Auditorium audi1, Auditorium audi2, Auditorium audi3) {
        Double audiTotals = audi1.getTotAmount() + audi2.getTotAmount() + audi3.getTotAmount();

        System.out.println();
        System.out.print("Total" + '\t');
        System.out.print((audi1.getTotOpen() +  audi2.getTotOpen() + audi3.getTotOpen() ) + "\t");
        System.out.print( (audi1.getTotRes() + audi2.getTotRes() + audi3.getTotRes() ) + "\t");
        System.out.print((audi1.getTotAdult() + audi2.getTotAdult() + audi3.getTotAdult() ) + "\t");
        System.out.print((audi1.getTotChild() + audi2.getTotChild() + audi3.getTotChild() ) + "\t");
        System.out.print( (audi1.getTotSenior() + audi2.getTotSenior() + audi3.getTotSenior() ) + "\t");
        System.out.print("$" + String.format("%.2f", audiTotals ) + "\t" );
        System.out.println();

    }

    private static Auditorium Auditorium3(ArrayList<Character> letters) throws FileNotFoundException {
        File inputFile = new File("A3.txt");

        if(!inputFile.exists()){
            System.out.println("File does not exist");
            System.exit(0);
        }

        int numRows = numRows(inputFile);
        int numCols = numCols(inputFile);
        
        //creates linked list object based on user input
        Auditorium audi = new Auditorium(inputFile, numRows, numCols, letters);

        return audi;
    }

    private static Auditorium Auditorium2(ArrayList<Character> letters) throws FileNotFoundException{
        File inputFile = new File("A2.txt");

        if(!inputFile.exists()){
            System.out.println("File does not exist");
            System.exit(0);
        }

        int numRows = numRows(inputFile);
        int numCols = numCols(inputFile);
        
        //creates linked list object based on user input
        Auditorium audi = new Auditorium(inputFile, numRows, numCols, letters);

        return audi;
    }

    private static Auditorium Auditorium1(ArrayList<Character> letters) throws FileNotFoundException {
        File inputFile = new File("A1.txt");

        if(!inputFile.exists()){
            System.out.println("File does not exist");
            System.exit(0);
        }

        int numRows = numRows(inputFile);
        int numCols = numCols(inputFile);
        
        //creates linked list object based on user input
        Auditorium audi = new Auditorium(inputFile, numRows, numCols, letters);

        return audi;
    }

    private static int[] bestAvailable(Auditorium audi, int rowNumber, int totTix, int numRows, int numCols, ArrayList<Character> letters) {
        //parses through entire linked lists, finds all possible empty selections of number of seats requests, and calls distance function

        Node tempCols =  audi.getHead();
        Node tempRows = audi.getHead();
        int countAvail = 0;
        int numPoss = 0;
        String coords[][] = new String[numRows][numCols];

        Node copyCols = tempCols;
        Node copyRows = tempRows;

        while(tempRows != null){
            while(tempCols != null ){
                for(int i = 0; i < totTix; i++)
                {
                    if(copyCols == null){
                        countAvail = 0;
                        copyRows = tempRows;
                        copyCols = copyRows;
                        //break;
                    }
                    if((copyCols.getPayload().gettType() == '.')) {
                        countAvail++;
                    }
                    if(countAvail == totTix){
                        numPoss++;
                        coords[copyCols.getPayload().getRow()][(int) copyCols.getPayload().getSeat()-'A'] = "V";
                        countAvail = 0;
                        break;
                    }
                    copyCols = copyCols.getNext();
                }
                countAvail = 0;
                
                tempCols = tempCols.getNext();
                copyCols = tempCols;
                
            }
            tempRows = tempRows.getDown();
            tempCols = tempRows;
        }

        int[] bestCoords = new int[2];
        bestCoords = getDistance(coords, numPoss, numRows, numCols, totTix, letters);

        return bestCoords;
    }
    
    private static int[] getDistance(String[][] coords, int numPoss, int numRows, int numCols, int totTix, ArrayList<Character> letters) {
        //calculates distance of every possible open seat selection within the auditorium and calulates their distance fromt the center of the auditorium
        //conditionals to determine best row and col based on the closest distance to the center of auditorium

        int countMatch = 0;

        double midX = 0, midY = 0;
        double audiX = ((double)numRows+1)/2;
        double audiY = ((double)numCols+1)/2;

        double distance = 0, best = 30;
        int bestRow = 0, bestCol = 0;
        
        for(int i = 0; i < numRows; i++){
            for(int j = 0; j < numCols; j++){
                if(coords[i][j] == "V"){
                    countMatch++;
                    
                    if(numCols == 17){
                        midY = (((j)+(j+(totTix-1.0)))/2.0) - 1; 
                    }
                    else{
                        midY = (((j)+(j+(totTix-1.0)))/2.0);
                    }
                    if(totTix == 1){
                        midY = j+1;
                    }
                    midX = i+1;

                    distance = Math.hypot(audiY - midY,audiX - midX);

                    if(best > distance){
                        best = distance;
                        bestRow = i+1;
                        bestCol = j+1 - (totTix-1);
                    }
                    //deal with tie breaker situations for closest distance and closest row
                    else if(best == distance){
                        //keep closest row to audi center
                        if(bestRow < i + 1){

                        }
                        else if(Math.abs(audiX-(i+1)) < Math.abs(audiX-bestRow)){
                            bestRow = i+1;
                            bestCol = j;
                        }
                    }
                }
            }     
        }

        //System.out.println(bestRow  + "" + (char)(letters.get(bestCol-1)) + " - " + (bestRow) + letters.get(bestCol-1 + totTix-1));
        int[] bestCoords = new int[2];
        bestCoords = passBest(bestRow,bestCol);
        return bestCoords;
    }

    private static int[] passBest(int bestRow, int bestCol) {
        //stores the best possible row and column of the starting seat and passes it all the way back to main

        int[] bestCoords = new int[2];

            bestCoords[0] = bestRow;
            bestCoords[1] = bestCol;

        return bestCoords;
    }

    private static void reserveSeats(Auditorium audi, int row, char seat, int adults, int kids, int seniors, int numRows) {
        // modify the auditorium nodes (setting them to the ticket type user requests at specific row/seat)
        //establishes a seat as taken, not reservable again unless order cancelled/seats removed 

      
        //Nodes to traverse all columns and rows
        //Both initially set to first (head) pointer
        Node tempCol;
        Node tempRow = audi.getHead();

        for (int i = 0; i < row - 1; i++) {
            tempRow = tempRow.getDown();
        } // Checks to see if all the seats are taken already in that row

        tempCol = tempRow;

        char seatEdit = Character.toUpperCase(seat);
        int num = seatEdit - 'A';

        for (int i = 0; i < num; i++) {
            tempCol = tempCol.getNext();
        }

        while (adults != 0 && tempCol != null) {
            tempCol.getPayload().settType('A');
            tempCol.getPayload().settCost(10.00);
            tempCol = tempCol.getNext();
            adults--;
        }
        while (kids != 0 && tempCol != null) {
            tempCol.getPayload().settType('C');
            tempCol.getPayload().settCost(5.00);
            tempCol = tempCol.getNext();
            kids--;
        }
        while (seniors != 0 && tempCol != null) {
            tempCol.getPayload().settType('S');
            tempCol.getPayload().settCost(7.50);

            tempCol = tempCol.getNext();
            seniors--;
        }
    }

    private static boolean checkSeat(Auditorium audi, int row, char seat, int totSeats) {
        //row translation from user input to array format
        row-=1;
        
        Node tempCols =  audi.getHead();
        Node tempRows = audi.getHead();

        //if only one ticket is bought
        if(totSeats==1)
        {
            while(tempRows != null) {
                while(tempCols != null){
                    if((tempCols.getPayload().getRow() == row) && (tempCols.getPayload().getSeat() == seat) && (tempCols.getPayload().gettType() != '.')){
                        return false;
                    }

                    tempCols = tempCols.getNext();
                }
                tempRows = tempRows.getDown();
                tempCols = tempRows;
            }
            return true;
        }

        //multiple tickets are bought
        else
        {   
            int numAvail = 0;
            
            while(tempRows != null) {
                char copySeat = seat;
                while(tempCols != null){
                    if((tempCols.getPayload().getRow() == row) && (tempCols.getPayload().getSeat() == copySeat) && (tempCols.getPayload().gettType() != '.')){
                        return false;
                    }
                    if((tempCols.getPayload().getRow() == row) && (tempCols.getPayload().getSeat() == copySeat && (tempCols.getPayload().gettType() == '.'))){
                        copySeat++;
                        numAvail++;

                        if(numAvail == totSeats){
                            return true;
                        }
                    }
                    tempCols = tempCols.getNext();
                }
                tempRows = tempRows.getDown();
                tempCols = tempRows;
            }
            return true;
        }
    }

    private static void displayAudi(Auditorium audi, int numRows, int numCols, ArrayList<Character> letters) {
        //print out whole auditorium in user-friendly display format
        System.out.print("  ");
        Node tempCols =  audi.getHead();
        Node tempRows = audi.getHead();
        int countRow = 0;
        
        for(int i = 0; i < numCols; i++){
            
            System.out.print(letters.get(i));
        }

        System.out.println();

            while(tempRows != null) {
                System.out.print((countRow+1) + " ");
                while(tempCols != null){
                    if(tempCols.getPayload().gettType() == '.'){
                        System.out.print(tempCols.getPayload().gettType());
                    }
                    else{
                        System.out.print('#');
                    }
    
                    tempCols = tempCols.getNext();
                }
                tempRows = tempRows.getDown();
                tempCols = tempRows;

                System.out.println();
                countRow++;
            }
    }

    private static ArrayList<Character> letters() {
        ArrayList<Character> letterList = new ArrayList<Character>();
        letterList.add('A');
        letterList.add('B');
        letterList.add('C');
        letterList.add('D');
        letterList.add('E');
        letterList.add('F');
        letterList.add('G');
        letterList.add('H');
        letterList.add('I');
        letterList.add('J');
        letterList.add('K');
        letterList.add('L');
        letterList.add('M');
        letterList.add('N');
        letterList.add('O');
        letterList.add('P');
        letterList.add('Q');
        letterList.add('R');
        letterList.add('S');
        letterList.add('T');
        letterList.add('U');
        letterList.add('V');
        letterList.add('W');
        letterList.add('X');
        letterList.add('Y');
        letterList.add('Z');
        
        return letterList;
    }

    private static int numCols(File inputFile) throws FileNotFoundException {
        Scanner fileRead = new Scanner(inputFile);
        int numChars = 0;
        String str = "";

        while(fileRead.hasNextLine()){
            str = fileRead.nextLine();
            numChars = str.length();
        }

        fileRead.close();

        return numChars;
    }

    private static int numRows(File inputFile) throws FileNotFoundException {

        Scanner fileRead = new Scanner(inputFile);
        int counter = 0;
        
        while(fileRead.hasNextLine()){
            fileRead.nextLine();
            counter++;
        }

        fileRead.close();

        return counter;
    }
    
    private static void chooseAudi(Scanner in, Auditorium audi1, Auditorium audi2, Auditorium audi3, ArrayList<Character> letters, HashMap<String, Main.Account> dataB, String acccountName, boolean checkBest, Main.Order thisOrder, int audiNum) {
        String tempString = "";
        int audiChoice = 0;

        // keep looping until valid auditorium is chosen
        // then display that auditorium in user friendly display
        // then go to reserve method
        do{
            try {
                if(audiNum != 0){
                    audiChoice = audiNum;
                    break;
                }
                System.out.println();
                System.out.println("1. Auditorium 1");
                System.out.println("2. Auditorium 2");
                System.out.println("3. Auditorium 3");
                
                if(in.hasNextLine()){
                    tempString = in.nextLine();
                }
                
                try {
                    audiChoice = Integer.parseInt(tempString);
                } catch (NumberFormatException nfe) {
                    System.out.println("Invalid number option entered.");
                    audiChoice = 0;
                }
                
                if(audiChoice != 1 && audiChoice != 2 && audiChoice != 3){
                    audiChoice = 0;
                }

            } catch (NoSuchElementException nse) {

                if(in.hasNextLine()){
                    in.nextLine();
                }
            }

        } while(audiChoice == 0);



        if(audiChoice == 1){
            displayAudi(audi1, audi1.getNumRows(), audi1.getNumCols(), letters);

            reserve(in, audi1, letters, dataB, acccountName, audiChoice, checkBest, thisOrder); 
        }
        if(audiChoice == 2){
            displayAudi(audi2, audi2.getNumRows(), audi2.getNumCols(), letters);
            
            reserve(in, audi2, letters, dataB, acccountName, audiChoice, checkBest, thisOrder); 
        }
        if(audiChoice == 3){
            displayAudi(audi3, audi3.getNumRows(), audi3.getNumCols(), letters);
            
            reserve(in, audi3, letters, dataB, acccountName, audiChoice, checkBest, thisOrder); 
        }
    }

    private static void reserve(Scanner in, Auditorium audi, ArrayList<Character> letters, HashMap<String, Main.Account> dataB, String acccountName, int audiChoice, boolean checkBest, Order thisOrder) {
            // take all of the user input, handling all potential input errors
            // set up for the reservation system (reserveSeats method)
            // create a new order for every confirmed reservation
            // store the order within the account (within account arraylist)

            String tempString = "";
            int rowNumber = 0, numAdult = 0, numChild = 0, numSenior = 0;
            char startLetter = ' ';
            
            System.out.println();
            
            //use a flag system to loop for every user input to ensure loops until correct input is put in
            //read in all input as a string and then parse as an int to detect if \n characters are inputted 

            boolean flag1 = true;
            while(flag1){
                try {
                    System.out.println("Enter row number: ");

                    if(in.hasNextLine()){
                        tempString = in.nextLine();
                    }
      
                    try {
                        rowNumber = Integer.parseInt(tempString);
                    } catch (NumberFormatException nfe) {
                        rowNumber = 0;
                    }

                    if(rowNumber > 0 && rowNumber <= audi.getNumRows()){
                        flag1 = false;
                    }

                    else{
                        System.out.println("Try again");
                    }
                } catch (InputMismatchException ime) {

                    System.out.println("Invalid type of input");
                    System.out.println();

                    if(in.hasNextLine()){
                        in.nextLine();
                    }
                }                         
      
            }
           
            //user input for starting seat letter, catches anything not appropriate
            boolean flag2 = true;
            while(flag2){
                try {
                    System.out.println("Enter starting seat letter: ");

                    if(in.hasNextLine()){
                        tempString = in.nextLine();
                    }
                    
                    startLetter = Character.toUpperCase(tempString.charAt(0));

                    if(startLetter >= 'A' && startLetter <= 'Z'){
                        flag2 = false;
                    }

                    else{
                        System.out.println("Input not within range");
                    }

                    } catch (InputMismatchException ime) {

                        System.out.println("Invalid type of input");
                        System.out.println();

                        if(in.hasNextLine()){
                            in.nextLine();
                        }
                    }
            }

            boolean flag3 = true;
            while(flag3)
            {   
                try {
                    System.out.println("Enter number of adult tickets: ");

                    if(in.hasNextLine()){
                        tempString = in.nextLine();
                    }

                    try {
                        numAdult = Integer.parseInt(tempString);
                    } catch (NumberFormatException nfe) {
                        numAdult = -1;
                    }
                    
                    if(numAdult >= 0){
                        flag3 = false;
                    }
                    else{
                        System.out.println("Try again");
                    }
                } catch (InputMismatchException ime) {
                    numAdult = -1;

                    System.out.println("Invalid type of input");
                    System.out.println();

                    if(in.hasNextLine()){
                        in.nextLine();
                    }
                }                         
            }

            boolean flag4 = true;
            while(flag4)
            {   
                try {
                    System.out.println("Enter number of child tickets: ");

                    if(in.hasNextLine()){
                        tempString = in.nextLine();
                    }

                    try {
                        numChild = Integer.parseInt(tempString);
                    } catch (NumberFormatException nfe) {
                        numChild = -1;
                    }
                  
                    if(numChild >= 0){
                        flag4 = false;
                    }
                    else{
                        System.out.println("Try again");
                    }

                } catch (InputMismatchException ime) {
                    numAdult = -1;
                    System.out.println("Invalid type of input");
                    System.out.println();

                    if(in.hasNextLine()){
                        in.nextLine();
                    }
                }                         
            }
            
            boolean flag5 = true;
            while(flag5)
            {   
                try {
                    System.out.println("Enter number of senior tickets: ");

                    if(in.hasNextLine()){
                        tempString = in.nextLine();
                    }

                    try {
                        numSenior = Integer.parseInt(tempString);
                    } catch (NumberFormatException nfe) {
                        numSenior = -1;
                    }

                    if(numSenior >= 0){
                        flag5 = false;
                    }
                    else{
                        System.out.println("Try again");
                    }

                } catch (InputMismatchException ime) {
                    numSenior = -1;
                    System.out.println("Invalid type of input");
                    System.out.println();

                    if(in.hasNextLine()){
                        in.nextLine();
                    }
                }                         
            }

            int totSeats = numAdult + numChild + numSenior;
            
            if(totSeats == 0)
            {
                return;
            }

            // access the account of the user that is logged in
            Account thisAccount = dataB.get(acccountName);

            //if checkseat returns true, that means the seat is available to reserve
            //and then it goes ahead and reserves the seat in the auditorium
            if(checkSeat(audi,rowNumber,startLetter,totSeats))
            {
                reserveSeats(audi,rowNumber,startLetter,numAdult,numChild,numSenior,audi.getNumRows());

                // create a new order for the account once the seat reservations has been confirmed
                // only if not reserving seats in a pre-existing order being updated
                if(checkBest){
                    thisOrder = new Order(audiChoice, rowNumber, startLetter, numAdult, numChild, numSenior);
                }
                
                //create a new ticket for each seat reserved in the order
                for(int i = 0; i < numAdult; i++){
                    Ticket thisTicket = new Ticket(rowNumber, (char) (startLetter), 'A');
                    
                    thisAccount.setTotCost(thisAccount.getTotCost() + thisTicket.gettCost());
                    thisOrder.setOrderCost(thisOrder.getOrderCost() + thisTicket.gettCost());
                    thisOrder.addTicket(thisTicket);
                    thisOrder.setNumAdult(thisOrder.getNumAdult()+1);
                    startLetter = (char) (startLetter+1);

                }
                for(int i = 0; i < numChild; i++){
                    Ticket thisTicket = new Ticket(rowNumber, (char) (startLetter), 'C');

                    thisAccount.setTotCost(thisAccount.getTotCost() + thisTicket.gettCost());
                    thisOrder.setOrderCost(thisOrder.getOrderCost() + thisTicket.gettCost());
                    thisOrder.addTicket(thisTicket);
                    thisOrder.setNumChild(thisOrder.getNumChild()+1);
                    startLetter = (char) (startLetter+1);
                }
                for(int i = 0; i < numSenior; i++){
                    Ticket thisTicket = new Ticket(rowNumber, (char) (startLetter), 'S');

                    thisAccount.setTotCost(thisAccount.getTotCost() + thisTicket.gettCost());
                    thisOrder.setOrderCost(thisOrder.getOrderCost() + thisTicket.gettCost());
                    thisOrder.addTicket(thisTicket);
                    thisOrder.setNumSenior(thisOrder.getNumSenior()+1);
                    startLetter = (char) (startLetter+1);
                }

                // only add a new order if not a pre-existing order being updated
                if(checkBest){
                    thisAccount.addOrder(thisOrder);
                }
                               
            }
            //if checkseat returns false, then bestAvailable seats are checked
            else if(checkBest)
            {  
                //runs through bestAvailable algorithm to see if seats are found
                if(bestAvailable(audi,rowNumber,totSeats,audi.getNumRows(),audi.getNumCols(),letters)[0] > -1 && bestAvailable(audi,rowNumber,totSeats,audi.getNumRows(),audi.getNumCols(),letters)[0] != 0)
                {
                    //the best available seat is found and returned as a integer
                    int bestRow = bestAvailable(audi,rowNumber,totSeats,audi.getNumRows(),audi.getNumCols(),letters)[0];
                    int bestCol = bestAvailable(audi,rowNumber,totSeats,audi.getNumRows(),audi.getNumCols(),letters)[1];

                    //converts int values to letters for output
                    char startSeat = (char) (bestCol + 64);
                    char finalSeat = (char) (bestCol + 64 + (totSeats-1));

                    System.out.println(bestRow + "" + startSeat + " - " + bestRow + finalSeat);

                    String y_n = "";
                    
                    
                    while(!y_n.equals("Y")  &&  !y_n.equals("N")) {

                        System.out.println("Reserve seats (Y/N)");

                        if(in.hasNextLine()){
                            tempString = in.nextLine();
                        }
                        
                        y_n = tempString.toUpperCase();
                        
                        //if user requests best available seats to be rserved, reserve those seats
                        if(y_n.equals("Y"))
                        {
                            reserveSeats(audi,bestRow,startSeat,numAdult,numChild,numSenior,audi.getNumRows());

                            // create a new order for the account once the seat reservations has been confirmed
                            thisOrder = new Order(audiChoice, bestRow,startSeat, numAdult, numChild, numSenior);
                            
                            //create a new ticket for each seat reserved in the order
                            for(int i = 0; i < numAdult; i++){
                                Ticket thisTicket = new Ticket(bestRow, (char) (startSeat), 'A');

                                thisAccount.setTotCost(thisAccount.getTotCost() + thisTicket.gettCost());
                                thisOrder.setOrderCost(thisOrder.getOrderCost() + thisTicket.gettCost());                       
                                thisOrder.setNumAdult(thisOrder.getNumAdult() +1);
                                thisOrder.addTicket(thisTicket);

                                startSeat = (char) (startSeat+1);
                            }
                            for(int i = 0; i < numChild; i++){
                                Ticket thisTicket = new Ticket(bestRow, (char) (startSeat), 'C');

                                thisAccount.setTotCost(thisAccount.getTotCost() + thisTicket.gettCost());
                                thisOrder.setOrderCost(thisOrder.getOrderCost() + thisTicket.gettCost());                       
                                thisOrder.setNumChild(thisOrder.getNumChild() +1);
                                thisOrder.addTicket(thisTicket);

                                startSeat = (char) (startSeat+1);
                            }
                            for(int i = 0; i < numSenior; i++){
                                Ticket thisTicket = new Ticket(bestRow, (char) (startSeat), 'S');

                                thisAccount.setTotCost(thisAccount.getTotCost() + thisTicket.gettCost());
                                thisOrder.setOrderCost(thisOrder.getOrderCost() + thisTicket.gettCost());                       
                                thisOrder.setNumSenior(thisOrder.getNumSenior() +1);
                                thisOrder.addTicket(thisTicket);

                                startSeat = (char) (startSeat+1);
                            }

                            thisAccount.addOrder(thisOrder);
                        }
                        //if user rejects the seats, ('N'), then no seats are available
                        else if(y_n.equals("N"))
                        {
                            System.out.println("no seats available");
                        }

                        else{
                            System.out.println("Invalid user input");
                        }
                    }                   
                }

                //prints out if checkseat returns false and bestAvailable finds no seats 
                else
                {
                    System.out.println("no seats available");
                }
            }

            //prints out if checkseat returns false and bestAvailable finds no seats 
            else
            {
                System.out.println("no seats available");
            }

            if(!thisOrder.tickets.isEmpty() && thisOrder.tickets.size() > 1){
                thisOrder.sortTickets();
            }
    }

    private static HashMap<String, Account> createDB() throws FileNotFoundException {
        // hashmap stores all usernames as key, and  account for each user as value
        // account holds the username, password, and orders for that user/account

        File userDB = new File("userdb.dat");
        Scanner read = new Scanner(userDB);

        String str = "";

        HashMap<String, Account> hm = new HashMap<String, Account>();

        while(read.hasNextLine()){

            str = read.nextLine();
            String[] arrStrings = str.split(" ");
            String username = arrStrings[0];
            String password = arrStrings[1];

            Account newAccount = new Account(username, password);

            hm.put(username, newAccount);
        }

        return hm;
    }
   
}
