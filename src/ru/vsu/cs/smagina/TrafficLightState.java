package ru.vsu.cs.smagina;

public class TrafficLightState {

    public final boolean red;
    public final boolean yellow;
    public final boolean green;
    public final int time;

    public TrafficLightState (boolean red, boolean yellow, boolean green, int time) {
        this.red = red;
        this.yellow = yellow;
        this.green = green;
        this.time = time;
    }
}
