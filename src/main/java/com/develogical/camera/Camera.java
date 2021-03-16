package com.develogical.camera;

public class Camera {

    boolean isOn;
    boolean isWriting;
    Sensor sensor;
    MemoryCard memoryCard;
    WriteCompleteListener writeCompleteListener;

    public Camera(Sensor sensor, MemoryCard memoryCard) {
        this.sensor = sensor;
        this.memoryCard = memoryCard;
        this.isOn = false;
        this.isWriting = false;
    }

    public void pressShutter() {
        if (this.isOn) {
            this.isWriting = true;
            this.memoryCard.write(this.sensor.readData(), this.writeCompleteListener);
        }
    }

    public void powerOn() {
        sensor.powerUp();
        this.isOn = true;
    }

    public void powerOff() {
        if (!this.isWriting) {
            sensor.powerDown();
            this.isOn = false;
        }
    }
}

