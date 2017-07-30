package Monitors;

import javax.management.monitor.Monitor;

/**
 * Created by jmkhilario on 24/07/2017.
 */
public class TrainMonitor {

    public int seats_available;
    private int MAX_SEATS;

    public TrainMonitor(int MAX_SEATS) {
        this.seats_available = MAX_SEATS;
        this.MAX_SEATS = MAX_SEATS;
    }


    //load_train
    public void board_next_station(Station station, int train_id) throws InterruptedException {
        synchronized (this){
            // if next station is occupied, wait
            System.out.println("Train " + train_id + " attempting to enter platform.");
            if (station.isOccupied()){
                System.out.println("Station occupied. Train " + train_id + " waiting to enter.");
                wait();
            }
            // occupy station
            station.setOccupied(true);
            station.setCurrent_train(this);
            System.out.println("Train " + train_id + " entering platform.");


            // release all passengers
            this.seats_available = this.MAX_SEATS;
            System.out.println("Train " + train_id + " all current passengers dropped-off.");

            // board passengers
            if(station.num_waiting_passenger>0){
                //System.out.println("TEST: Train " + train_id + " seats available: " + station.getCurrent_train().seats_available);
                //System.out.println("TEST: waiting passengers: " + station.num_waiting_passenger);
                System.out.println("Train " + train_id + " will now start boarding.");
                station.setTrain_boarding(true);
                station.start_boarding();
                System.out.println("TEST: Train " + train_id + " ended boarding.");
                //wait();
                //Thread.sleep(1000);
            }
            else {
                leave_station(station);
                System.out.println("TEST: Train " + train_id + " had none to board.");
            }

        }
    }

    public void leave_station(Station station) {

        System.out.println("Releasing train. (3): " + this.seats_available + " seats.");

        synchronized(this){
        if(this.seats_available==0)System.out.println("Train is full and is leaving the station.");
        else System.out.println("The station has no more waiting passengers. Train is leaving the station.");
            station.setTrain_boarding(false);
            station.setCurrent_train(null);
            station.setOccupied(false);
            notify(); // ...the current train to leave the platform
            //notify(); // ...the next train to enter the platform/*
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        System.out.println("Train has left the station. Next train may now enter. ");
        System.out.println("======================================================");
    }
}
