package Monitors;

/**
 * Created by jmkhilario on 24/07/2017.
 */
public class PassengerMonitor {


    public void enter_station (Station station, int passenger_id) throws InterruptedException {
        synchronized (this){
            station.num_waiting_passenger++;
        }
        wait_for_train(station, passenger_id);
    }

    public void wait_for_train(Station station, int passenger_id) throws InterruptedException {

        System.out.println("Passenger " + passenger_id + " arrived in station.");
        synchronized (this){
            if(!station.isOccupied()){
                System.out.println("Passenger " + passenger_id + " is waiting for train.");
                wait();
            }
            else if(!station.isTrain_boarding()) wait();

            if (station.getCurrent_train()!=null)board(station);
        }
    }

    public void board(Station station){
        //notify
        //one at a time; call once per passenger to board
        synchronized (this){
            if(station.num_waiting_passenger>0 && station.getCurrent_train().seats_available>0){
                System.out.println("A Passenger is now boarding.");
                station.getCurrent_train().seats_available--;
                System.out.println("TEST: seats available: " + station.getCurrent_train().seats_available);
                station.num_waiting_passenger--;
                System.out.println("TEST: waiting passengers: " + station.num_waiting_passenger);
            }

            if(station.num_waiting_passenger==0 || station.getCurrent_train().seats_available==0){
                System.out.println("Releasing train.");
                station.release_train();
                System.out.println("Train released.");
            }
            else notify();
            notify();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
