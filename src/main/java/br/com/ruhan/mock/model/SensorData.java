package br.com.ruhan.mock.model;

import java.util.List;

/**
 * Created by ruhan on 15/12/16.
 */
public class SensorData {

    private String tag;
    private String uuid;
    private String source;
    private String action;
    private int signal;
    private String sensorName;
    private List<Double> SensorValue;
    private double latitude;
    private double longitude;
    private long timestamp;

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

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public int getSignal() {
        return signal;
    }

    public void setSignal(int signal) {
        this.signal = signal;
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

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getSensorName() {
        return sensorName;
    }

    public void setSensorName(String sensorName) {
        this.sensorName = sensorName;
    }

    public List<Double> getSensorValue() {
        return SensorValue;
    }

    public void setSensorValue(List<Double> sensorValue) {
        SensorValue = sensorValue;
    }
}
