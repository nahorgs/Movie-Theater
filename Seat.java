//Rohan Gopinathan
//rsg190003

public class Seat {

    private int row;
    private char seat;
    private char tType;
    private double tCost;
    private String tHolder;

    //default
    public Seat() {}

    

    //overloaded
    public Seat(int row, char seat, char tType){
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

}
