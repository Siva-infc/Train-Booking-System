import java.util.*;
public class TicketBooking{
    public static void main(String[] args) {
        TicketSystem ticketSystem = new TicketSystem();
        Scanner scanner = new Scanner(System.in);
        
        while(true) {
            System.out.println("       Railway Booking System");
            System.out.println("1. Book Tickets");
            System.out.println("2. Cancel Ticket");
            System.out.println("3. View Confirmed Tickets");
            System.out.println("4. View Available Tickets");
            System.out.println("5. View RAC Tickets");
            System.out.println("6. View Waiting List Tickets");
            System.out.println("7. Exit");
            
            System.out.println("Enter Your Choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            switch(choice) {
                case 1:
                    System.out.println("Enter your name ");
                    String name = scanner.nextLine();
                    System.out.println("Enter your Age: ");
                    int age = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Enter Gender(Male / Female): ");
                    String gender = scanner.nextLine();
                    System.out.println("Enter Berth Preference(L, M, U): ");
                    String berthPreference = scanner.nextLine();
                    
                    ticketSystem.bookTicket(name, age, gender, berthPreference);
                    break;
                
                case 2:
                    System.out.println("Enter your Ticket Id: ");
                    String ticketId = scanner.nextLine();
                    
                    ticketSystem.cancelTicket(ticketId);
                    break;
                
                case 3:
                    ticketSystem.viewBookedTickets();
                    break;
                
                case 4:
                    ticketSystem.viewAvailableTickets();
                    break;
                    
                case 5:
                    ticketSystem.viewRacTickets();
                    break;
                
                case 6:
                    ticketSystem.viewWaitingListTickets();
                    break;
                    
                case 7:
                    System.out.println("Exiting...");
                    System.exit(0);
                    
                default:
                    System.out.println("Invalid choice!. Try again..");
            }
        }
    }
}
