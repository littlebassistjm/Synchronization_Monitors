package Monitors;

import java.util.ArrayList;

/**
 * Created by jmkhilario on 17/07/2017.
 */
public class CalTrain2 {

    public static void main(String[] args) throws InterruptedException {

        final TrainMonitor trainMonitor = new TrainMonitor(5);
        final PassengerMonitor passengerMonitor = new PassengerMonitor();
        int num_total_passengers=6;
        int num_total_trains = 2;
        ArrayList<Station> station_list= new ArrayList<Station>(8);

        // TODO(7): create multiple Stations
        // DONE(1): create Station
            Station station = new Station(passengerMonitor);

        // TODO(9): create multiple Trains
        // DONE(2): create TrainMonitor thread


            ArrayList<Thread> train_thread_list= new ArrayList<Thread>();
            ArrayList<Train> train_list = new ArrayList<Train>();
            for(int i=0; i<num_total_trains; i++){
                train_list.add(new Train(trainMonitor, station,i+1));
                train_thread_list.add(new Thread(train_list.get(i)));
                System.out.println("Train " + (i+1) + " created.");
            }

        // DONE(3.1): create multiple Passenger threads
        // DONE(3): create Passenger thread

            ArrayList<Thread> passenger_thread_list= new ArrayList<Thread>();
            ArrayList<Passenger> passenger_list = new ArrayList<Passenger>();
            for(int i=0; i<num_total_passengers; i++){
                passenger_list.add(new Passenger(passengerMonitor, station, i+1));
                passenger_thread_list.add(new Thread(passenger_list.get(i)));
                System.out.println("Passenger " + (i+1) + " created.");
            }

        // TODO(6): other processes
        // TODO(8): integrate multiple stations into the processes
        // TODO(10): integrate multiple trains into the processes

        System.out.println("*******************************************************");
        // DONE(4): start threads
        for (int i=0; i<num_total_passengers; i++){
                passenger_thread_list.get(i).start();
        }
        for (int i=0; i<num_total_trains; i++){
            train_thread_list.get(i).start();
        }

        // DONE(5): join threads
        for (int i=0; i<num_total_passengers; i++){
            passenger_thread_list.get(i).join();
        }
        for (int i=0; i<num_total_trains; i++){
            train_thread_list.get(i).join();
        }

    }

}
