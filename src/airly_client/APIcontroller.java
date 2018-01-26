package airly_client;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIcontroller {

    static final String BASE_URL = "https://airapi.airly.eu/";

    public AirlyApi start() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

         AirlyApi airlyApi = retrofit.create(AirlyApi.class);
        return airlyApi;
    }
}
