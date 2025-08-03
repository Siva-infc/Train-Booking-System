import java.util.*;
public class TicketSystem{
    
    private final List<String> availableBerth = new ArrayList<>(Arrays.asList("L", "M", "U")); //3 berths
    
    private final Queue<Passanger> racQueue = new LinkedList<>(); //1 RAC Berth
    private final Queue<Passanger> waitingListQueue = new LinkedList<>(); //1 Wl 
    private final List<Passanger> conformedPassangers = new ArrayList<>(); //Cnfm conformedPassangers
    
    private int ticketCounter = 1;
    
    //Book Ticket
    public void bookTicket(String name, int age, String gender, String berthPreference) {
        String ticketId = "T" + ticketCounter++;
        Passanger passanger;
        if(!availableBerth.isEmpty()) { //isAvail
            String allocatedBerth = allocateBerth(age, gender, berthPreference);
            passanger = new Passanger(name, age, gender, berthPreference, allocatedBerth, ticketId);
            conformedPassangers.add(passanger);
            availableBerth.remove(allocatedBerth);
            System.out.println("Ticket Conformed: " + passanger);
        } else if(racQueue.size() < 1) { //if RAC
            passanger = new Passanger(name, age, gender, berthPreference, "RAC", ticketId);
            racQueue.offer(passanger);
            System.out.println("Ticket waiting in RAC: " + passanger);
        } else if(waitingListQueue.size() < 1){ //then Waiting List
            passanger = new Passanger(name, age, gender, berthPreference, "WL", ticketId);
            waitingListQueue.offer(passanger);
            System.out.println("Ticket in Waiting List: " + passanger);
        } else {  // NO seats
            System.out.println("No Tickets Available");
        }
    }
    
    //Allocate Berth
    private String allocateBerth(int age, String gender, String berthPreference) {
        if(age >= 60 || gender.equalsIgnoreCase("female") && availableBerth.contains("L")) {
            return "L";
        }
        if(availableBerth.contains(berthPreference)) return berthPreference;
        
        return availableBerth.get(0);
    }
    
    //Cancel Ticket
    public void cancelTicket(String ticketId) {
        Optional<Passanger> passangerOpt = conformedPassangers.stream()
                                .filter(p -> p.ticketId.equals(ticketId))
                                .findFirst();
        if(passangerOpt.isPresent()) {
            Passanger passanger = passangerOpt.get(); 
            conformedPassangers.remove(passanger);
            availableBerth.add(passanger.allotedBerth);
            if(!racQueue.isEmpty()) {
                Passanger racPassanger = racQueue.poll();
                String allocatedBerth = allocateBerth(racPassanger.age, racPassanger.gender, racPassanger.berthPreference);
                racPassanger.allotedBerth = allocatedBerth;
                conformedPassangers.add(racPassanger);
                availableBerth.remove(allocatedBerth);
                System.out.println("RAC ticket moved to conformed: " + racPassanger);
            }
            if(!waitingListQueue.isEmpty()) {
                Passanger waitingPassanger = waitingListQueue.poll();
                racQueue.offer(waitingPassanger);
                waitingPassanger.allotedBerth = "RAC";
                System.out.println("Waiting list moved to RAC: " + waitingPassanger);
            }
            System.out.println("Ticket cancelled Successfully for Id: " + ticketId);
        }
        
        else System.out.println("No ticket found with id:" + ticketId);
    }
    
    //Booked tickets println
    public void viewBookedTickets() {
        if(conformedPassangers.isEmpty()) System.out.println("No tickets are conformed");
        else {
            System.out.println("Conformed Tickets");
            for(Passanger passanger: conformedPassangers) {
                System.out.println(passanger);
            }
        }
    }
    
    //Printing available Tickets
    public void viewAvailableTickets() {
        System.out.println("Available Berths: " + availableBerth.size());
        System.out.println("Available RAC tickets: " + (1 - racQueue.size()));
        System.out.println("Availale waiting list Tickets: " + (1 - waitingListQueue.size()));
    }
    
    //view RAC Tickets
    public void viewRacTickets() {
        if(racQueue.isEmpty()) System.out.println("No RAC tickets is in list");
        else {
            System.out.println("RAC Tickets");
            for(Passanger passanger: racQueue) {
                System.out.println(passanger);
            }
        }
    }
    
    //Waiting list
    public void viewWaitingListTickets() {
        if(waitingListQueue.isEmpty()) System.out.println("No Waiting tickets in list");
        else {
            System.out.println("Waiting List Tickets:");
            for(Passanger passanger : waitingListQueue) {
                System.out.println(passanger);
            }
        }
    }
}
