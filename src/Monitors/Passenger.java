package Monitors;

/**
 * Created by jmkhilario on 26/07/2017.
 */
public class Passenger implements Runnable{

    private PassengerMonitor passengerMonitor;
    private Station station;
    private int passenger_id;


    public Passenger(PassengerMonitor passengerMonitor, Station station, int passenger_id) {
        this.passengerMonitor = passengerMonitor;
        this.station = station;
        this.passenger_id = passenger_id;
    }

    @Override
    public void run() {
        try {
            // station.waiting_passengers.add(passenger);
            passengerMonitor.enter_station(station, passenger_id);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
