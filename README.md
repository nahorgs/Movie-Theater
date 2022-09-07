# Movie-Theater
Emulated a movie theater ticket purchasing system using OOP in Java

All auditorium files are manually initialized (A1.txt, A2.txt, A3.txt).
Read all auditoriums into program, initialize them as custom Auditorium class (a doubly-linkedlist that represents the dimensions of an auditorium).
Each chair within the auditorium is a node within the linkedlist, which contains a seat.
Each user that is to make an order for tickets has an account (custom object) that consists of the username, password, and any orders associacated with the account (in arraylist).
Account class has functions that display the receipts for every order, or every order associated with the account to console.
Order class keeps track of information such as which auditorium the user places the order in, the row of the seats, which seat starting from the left, how many total seats that are purchased, and for each ticket purchased, a ticket object added to a ticket arraylist to keep track of every ticket individually in an order, and total order price.
Ticket class contains the row number, seat number, type of ticket (Adult, Child, Senior), and cost of ticket according to ticket type.

User functionality follows that the user has to first properly log-in to their account, with a hashmap storing all accounts that are active, confirming that the log-in details are correct before allowing the user access into the account actions.
Once user has succesfully logged-in, main menu actions are displayed to user, for which they can select from reserving seats, view any orders they have made, updating any previous orders, displaying the reciepts from their orders, or to log-out.
If a user chooses to reserve seats, then they are asked which auditorium they wish to reserve seats within, the row number, the starting seat letter, and how many tickets and what type of ticket being purchased.
With every succesful order of tickets, the auditorium is being updated to show proper availability and total ticket sales.
If user chooses to update any previous orders, they have options to either add tickets to an order, delete tickets from an order, or cancel the order entirely.
If a requested seat within the auditorium is already taken, the best available seats (closest to the center of the auditorium) is automatically calculated and given to the user to either accept or decline those seats.
