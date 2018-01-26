package airly_client;

import retrofit2.Call;

import javax.xml.crypto.Data;

public class APICallBuilder {
    AirlyApi api;
    Arguments arguments;

    public APICallBuilder (AirlyApi api, Arguments arguments){
        this.api=api;
        this.arguments=arguments;
    }

    public Call<DataChange> buildcall(boolean sensflag){

        if(sensflag)
            return this.api.sensorMeasurements(arguments.apikey,arguments.sensorid);
        else
            return this.api.mapPointMeasurements(arguments.apikey,arguments.latitude,arguments.longitude);
    }
}
