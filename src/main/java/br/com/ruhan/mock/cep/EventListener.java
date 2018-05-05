package br.com.ruhan.mock.cep;

import br.com.ruhan.mock.Main;
import br.com.ruhan.mock.model.EventData;
import br.com.ruhan.mock.model.Value;
import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

/**
 * Created by ruhan on 15/12/16.
 */
public class EventListener implements UpdateListener {

    @Override
    public void update(EventBean[] arg0, EventBean[] arg1) {

        final Map<String, Double> map = (Map<String, Double>) arg0[0].getUnderlying();

        final Double heatIndex = map.get("avgTemperature");
        final Double humidityIndex = map.get("avgHumidity");

        final EventData eventData = new EventData();
        eventData.setTag("EventData");
        eventData.setLabel("HeatIndex");
        eventData.setData( new Value( heatIndex ) );

        final EventData eventData2 = new EventData();
        eventData2.setTag("EventData");
        eventData2.setLabel("HumidityIndex");
        eventData2.setData( new Value( humidityIndex ) );

        try {

            Main.sendMessage( eventData );
            Main.sendMessage( eventData2 );

        } catch (IOException e) {
            e.printStackTrace();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
