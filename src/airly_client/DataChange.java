package airly_client;

public class DataChange {
 private double airQualityIndex;
 private double pm1;
 private double pm25;
 private double pm10;
 private double pressure;
 private double temperature;
 private double windDirection;
 private double windSpeed;
 private int pollutionLevel;
 private double humidity;

    public double getAirQualityIndex() {
        return airQualityIndex;
    }

    public double getPm1() {
        return pm1;
    }

    public double getPm25() {
        return pm25;
    }

    public double getPm10() {
        return pm10;
    }

    public double getPressure() {
        return pressure;
    }

    public double getTemperature() {
        return temperature;
    }

    public double getHumidity() {
        return humidity;
    }
    public double getWindDirection() {
        return windDirection;
    }
    public double windSpeed() {
        return windSpeed;
    }
    public int getPollutionLevel() {
        return pollutionLevel;
    }

}
