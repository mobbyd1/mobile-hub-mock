package br.com.ruhan.mock.model;

import java.util.List;

/**
 * Created by ruhan on 15/12/16.
 */
public class EventData {

    private String tag;
    private String uuid;
    private String label;
    private Value data;
    private double latitude;
    private double longitude;

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public Value getData() {
        return data;
    }

    public void setData(Value data) {
        this.data = data;
    }
}
