package br.com.ruhan.mock.stream;

import br.com.ruhan.mock.model.SensorData;
import com.espertech.esper.client.EPRuntime;

import java.util.Arrays;

/**
 * Created by ruhan on 15/12/16.
 */
public class SensorDataStream implements Runnable {

    private EPRuntime epRuntime;

    public SensorDataStream(final EPRuntime epRuntime ) {
        this.epRuntime = epRuntime;
    }

    public void run() {

        final double humidity = 10;
        final double temperature = 80;
        final String source = "abcd";

        final String sensorNameHumidity = "Humidity";
        final String sensorNameTemperature = "Temperature";

        while( true ) {

            final SensorData sensorHumidity = new SensorData();
            sensorHumidity.setSensorName( sensorNameHumidity );
            sensorHumidity.setSensorValue( Arrays.asList( humidity ) );
            sensorHumidity.setSource( source );

            final SensorData sensorTemperature = new SensorData();
            sensorTemperature.setSensorName( sensorNameTemperature );
            sensorTemperature.setSensorValue( Arrays.asList( temperature ) );
            sensorTemperature.setSource( source );
            sensorTemperature.setUuid("");

            epRuntime.sendEvent( sensorHumidity );
            epRuntime.sendEvent( sensorTemperature );
        }
    }
}
