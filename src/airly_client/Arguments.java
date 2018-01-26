package airly_client;

public class Arguments {
    public final String apikey;
    public final int sensorid;
    public final double latitude;
    public final double longitude;

    public Arguments (String apikey,int sensorid, double latitude, double longitude){
        this.apikey=apikey;
        this.sensorid=sensorid;
        this.latitude=latitude;
        this.longitude=longitude;
    }
}
