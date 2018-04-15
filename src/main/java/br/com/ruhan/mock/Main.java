package br.com.ruhan.mock;

import br.com.ruhan.mock.cep.EventListener;
import br.com.ruhan.mock.model.SensorData;
import br.com.ruhan.mock.stream.SensorDataStream;
import com.espertech.esper.client.*;
import com.google.gson.Gson;
import lac.cnclib.net.mrudp.MrUdpNodeConnection;
import lac.cnclib.sddl.message.ApplicationMessage;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Date;

/**
 * Created by ruhan on 15/12/16.
 */
public class Main {

    private static MrUdpNodeConnection connection;

    public static void main(String[] args) throws IOException {
        connection = new MrUdpNodeConnection();

        final InetSocketAddress inetSocketAddress = new InetSocketAddress("10.211.55.4", 5500);
        connection.connect( inetSocketAddress );

        final Configuration cepConfig = new Configuration();
        cepConfig.addEventType("SensorData", SensorData.class.getName());

        final EPServiceProvider cep = EPServiceProviderManager
                .getProvider("myCEPEngine", cepConfig);

        final EPRuntime epRuntime = cep.getEPRuntime();

        final EPAdministrator cepAdm = cep.getEPAdministrator();

//        final EPStatement insertStatement = cepAdm.createEPL(
//            "INSERT INTO AVGHumidityTemperature " +
//                    "SELECT avg(humidity.sensorValue[0]) as avgHumidity, ( avg(temperature.sensorValue[0]) * 9/5 ) + 32 as avgTemperature " +
//            "FROM SensorData(sensorName='Humidity').win:length(1000000) as humidity,  " +
//                    "SensorData(sensorName='Temperature').win:length(1000000) as temperature  " +
//            "WHERE humidity.sensorName <> temperature.sensorName    " +
//            "AND humidity.source = temperature.source "
//        );
//
//        final EPStatement epl = cepAdm.createEPL(
//                "SELECT ( " +
//                        "-43.379 " + //c1
//                        "+ ( 2.049015233 * avgTemperature ) " + //c2
//                        "+ ( 10.14333127 * avgHumidity ) " + //c3
//                        "+ ( -0.22475541 * avgTemperature * avgHumidity ) " + //c4
//                        "+ ( -6.837883 * 0.001 * avgTemperature * avgTemperature ) " + //c5
//                        "+ ( -5.481717 * 0.01 * avgHumidity * avgHumidity ) " + //c6
//                        "+ ( 1.22874 * 0.001 * avgTemperature * avgTemperature * avgHumidity ) " + //c7
//                        "+ ( 8.5282 * 0.0001 * avgTemperature * avgHumidity * avgHumidity ) " + //c8
//                        "+ ( -1.99 * 0.000001 * avgTemperature * avgTemperature * avgHumidity * avgHumidity )" +//c9
//                        ") as heatIndex " +
//                        "FROM AVGHumidityTemperature");

        final EPStatement insertStatement = cepAdm.createEPL(
                "SELECT avg(temperature.sensorValue[0]) as avgTemperature " +
                    "FROM SensorData(sensorName='Temperature').win:time (10 sec) as temperature  "
        );

        insertStatement.addListener(new EventListener());

        final SensorDataStream sensorDataStream = new SensorDataStream(epRuntime);
        sensorDataStream.run();
    }

    public static void sendMessage( Object msg ) throws IOException, InterruptedException {
        final ApplicationMessage applicationMessage = new ApplicationMessage();
        final Gson gson = new Gson();

        final String json = gson.toJson(msg);

        applicationMessage.setContent( json.getBytes() );

        connection.sendMessage( applicationMessage );
        System.out.println(new Date() + " Sent message: " + json );

        Thread.sleep( 100 * 1000 );
    }
}
