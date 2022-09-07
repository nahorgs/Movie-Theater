//Rohan Gopinathan
//rsg190003

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Auditorium {

    //head pointer
    private Node first;

    // auditorium dimensions
    private int numRows = 0;
    private int numCols = 0;

    // auditorium trackers
    private int totSeats;
    private int totOpen;
    private int totRes;
    private int totAdult;
    private int totChild;
    private int totSenior;

    private double totAmount;

    File inputFile;
    ArrayList<Character> letters;

    private static char getLetter(int i, ArrayList<Character> letters) {
        char ch = 'a';

        for(int j = 0; j <= i; j++){
            if(j == i){
                ch = (char) letters.get(i);
            }
        }
        return ch;
    }
   
    //Auditorium constructor that takes in passed file, reads the contents line by line, and establishes the linked list with appropriate seat objects
    public Auditorium(File inputFile, int numRows, int numCols, ArrayList<Character> letters) throws FileNotFoundException {

        Scanner fileRead = new Scanner(inputFile);
        String[] line = new String [numRows];
        char[] copyArray = new char[numCols];
        int countRow = 0;

        first = new Node();
        

        Node curRow = first;
        while(fileRead.hasNextLine()){

            Node curCol = curRow;
            line[countRow] = fileRead.nextLine();

            for(int i = 0; i < numCols; i++){
                copyArray[i] = line[countRow].charAt(i);
                
                Seat currSeat = new Seat(countRow, getLetter(i, letters), copyArray[i]); 
                
                curCol.setPayload(currSeat);

                if(i != numCols -1){
                    curCol.setNext(new Node());
                    curCol = curCol.getNext();
                }
            }
            
            if(countRow != numRows -1){
                curRow.setDown(new Node());
                curRow = curRow.getDown();
            }

            countRow++;
        }    
        
        this.numCols = numCols;
        this.numRows = numRows;

        fileRead.close();
    }

    // Accessors
    public Node getHead() {
        return first;
    }
    public int getNumCols() {
        return numCols;
    }
    public int getNumRows() {
        return numRows;
    }
    public int getTotOpen() {
        return totOpen;
    }
    public int getTotRes() {
        return totRes;
    }
    public int getTotAdult() {
        return totAdult;
    }
    public int getTotChild() {
        return totChild;
    }
    public int getTotSenior() {
        return totSenior;
    }
    public double getTotAmount() {
        return totAmount;
    }
    
    // Mutators
    public  void setHead(Node first) {
        this.first = first;
    }

    public Node searchNode(int row, char seat){
        // search the entire auditorium for a specific node you need to access
        // if found, return that node

        Node tempCols =  this.getHead();
        Node tempRows = this.getHead();

        while(tempRows != null) {
            while(tempCols != null){
                if((tempCols.getPayload().getRow() == row) && (tempCols.getPayload().getSeat() == seat)){
                    return tempCols;
                }

                tempCols = tempCols.getNext();
            }
            tempRows = tempRows.getDown();
            tempCols = tempRows;
        }
        return null;
    }

    public void printReport(String audiNumber){
        
        Node tempCols = this.getHead();
        Node tempRows = this.getHead();
    

            while(tempRows != null) {
                while(tempCols != null){
                    if(tempCols.getPayload().gettType() =='.'){
                        totOpen++;
                    }
                    if(tempCols.getPayload().gettType() =='A'){
                        totAdult++;
                        totRes++;
                    }
                    if(tempCols.getPayload().gettType() == 'C'){
                        totChild++;
                        totRes++;
                    }
                    if(tempCols.getPayload().gettType() =='S'){
                        totSenior++;
                        totRes++;
                    }
    
                    tempCols = tempCols.getNext();
                }
                tempRows = tempRows.getDown();
                tempCols = tempRows;
           
            }
    
        totAmount = (totAdult * 10.00) + (totChild * 5.00) + (totSenior * 7.50);

        System.out.println();
        System.out.print(audiNumber + "\t");
        System.out.print(totOpen + "\t");
        System.out.print(totRes + "\t");
        System.out.print( totAdult + "\t");
        System.out.print(totChild + "\t");
        System.out.print( totSenior + "\t");
        System.out.println("$" + String.format("%.2f",totAmount));
    }

    public void writeToFile(FileWriter fw) throws IOException {
        PrintWriter printWriter = new PrintWriter(fw);

        Node tempCols = this.getHead();
        Node tempRows = this.getHead();

        while(tempRows != null) {
            while(tempCols != null){
                if(tempCols.getPayload().gettType() == '.'){
                    printWriter.append(tempCols.getPayload().gettType());
                }
                else{
                    printWriter.append(tempCols.getPayload().gettType());
                }

                tempCols = tempCols.getNext();
            }
            tempRows = tempRows.getDown();
            tempCols = tempRows;

            printWriter.println();
        }

        printWriter.close();
    }

}
