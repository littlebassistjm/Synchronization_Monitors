package Monitors;

/**
 * Created by jmkhilario on 26/07/2017.
 */
public class Train implements Runnable{

    private TrainMonitor trainMonitor;
    private Station station;
    private int train_id;


    public Train(TrainMonitor trainMonitor, Station station, int train_id) {
        this.trainMonitor = trainMonitor;
        this.station = station;
        this.train_id = train_id;
    }

    @Override
    public void run() {
        try {
            trainMonitor.board_next_station(station, train_id);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
