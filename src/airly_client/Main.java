package airly_client;
import org.apache.commons.cli.*;
import retrofit2.Call;
import retrofit2.Response;

import javax.xml.crypto.Data;
import java.io.IOException;


public class Main {
    public static void main(String[] args) throws Exception {


        Options options = new Options();

        Option sensor_id = new Option(null, "sensor_id", true, "sensor id");
        options.addOption(sensor_id);

        Option latitude = new Option(null, "latitude", true, "latitude");
        options.addOption(latitude);

        Option longitude = new Option(null, "longitude", true, "longitude");
        options.addOption(longitude);

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd;

        if (args.length == 0) {
            System.err.println();
            System.out.println("Missing arguments");
            formatter.printHelp("java -jar airly_client.jar", options);
            return;
        }

        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp("utility-name", options);

            System.exit(1);
            return;
        }

        String sensorIdstring = cmd.getOptionValue("sensor_id");
        String latitudestring = cmd.getOptionValue("latitude");
        String longtitudestring = cmd.getOptionValue("longitude");
        String apikey = "93d6b9a2cc824885a202dd82871e39ae";

        int sensorIdf = 0;
        boolean sensflag = false;
        double latitudef = 0;
        boolean latflag = false;
        double longitudef = 0;
        boolean longitflag = false;

        try {
            if (sensorIdstring != null) {
                sensorIdf = Integer.parseUnsignedInt(sensorIdstring);
                sensflag = true;
            }
            if (latitudestring != null) {
                latitudef = Double.parseDouble(latitudestring);
                latflag = true;
            }
            if (longtitudestring != null) {
                longitudef = Double.parseDouble(longtitudestring);
                longitflag = true;
            }
        } catch (NumberFormatException e) {
            System.err.println("Invalid format of arguments");
            return;
        }
        Arguments argstocall;
        if (sensflag && (!latflag) && (!longitflag)) {
            argstocall = new Arguments(apikey, sensorIdf, -500000, -500000);
        } else if (!sensflag && latflag && longitflag) {
            argstocall = new Arguments(apikey, -50000, latitudef, longitudef);
        } else {
            System.err.println("Invalid format of arguments");
            return;
        }
        AirlyApi api = new APIcontroller().start();
        APICallBuilder apibuilder = new APICallBuilder(api, argstocall);

        Call<DataChange> tocall = apibuilder.buildcall(sensflag);

        Response<DataChange> response = null;

        try{
            response = tocall.execute();
        }
        catch(IOException e){
            System.err.println("Problem occured during sending the request to server.");
            return;
        }
        catch(RuntimeException e){
            System.err.println("Unexpected error occured creating the request or decoding the response. ");
        }
        System.out.println(response.code());
        if(response.code() != 200) {
            if (response.code() == 400 || response.code() == 401 || response.code() == 403) {
                System.err.println("Input validation/authorisation error. Wrong api key");
                return;
            }
            if (response.code() == 404) {
                System.err.println("Incorrect arguments, desired target not found.");
                return;
            }
            if (response.code() == 500) {
                System.err.println("Unexpected error, please try again.");
                return;
            }
        }
            double pm25 = response.body().getAirQualityIndex();
            System.out.println(pm25 + " airQualityIndex");
        }
    }



