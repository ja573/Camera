package com.develogical.camera;

public class Camera {

    boolean isOn;
    Sensor sensor;
    MemoryCard memoryCard;

    public Camera(Sensor sensor, MemoryCard memoryCard) {
        this.sensor = sensor;
        this.memoryCard = memoryCard;
        this.isOn = false;
    }

    public void pressShutter() {
        if (this.isOn) {
            this.memoryCard.write(this.sensor.readData(), null);
        }
    }

    public void powerOn() {
        sensor.powerUp();
        this.isOn = true;
    }

    public void powerOff() {
        sensor.powerDown();
        this.isOn = false;
    }
}

