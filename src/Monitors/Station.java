package Monitors;

/**
 * Created by jmkhilario on 16/07/2017.
 */
public class Station {

    //full of locks
//has connection with monitors, middle man
//no direct connection with Trains and Passengers themselves
//each station has their own TrainMonitor and PassengerMonitor Monitor?
    private boolean occupied = false;
    private boolean train_boarding = false;
    public int num_waiting_passenger = 0;
    private TrainMonitor current_train;
    public PassengerMonitor passengerMonitorMonitor;


    public Station(PassengerMonitor passengerMonitorMonitor) {
        this.passengerMonitorMonitor = passengerMonitorMonitor;
    }

    public synchronized void start_boarding(){
        this.passengerMonitorMonitor.board(this);
    }

    public void release_train(){
            System.out.println("Releasing train. (2)");
            this.current_train.leave_station(this);
    }

    public boolean isTrain_boarding() {
        return train_boarding;
    }

    public void setTrain_boarding(boolean train_boarding) {
        this.train_boarding = train_boarding;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    public TrainMonitor getCurrent_train() {
        return current_train;
    }

    public void setCurrent_train(TrainMonitor train) {
        this.current_train = train;
    }

}

