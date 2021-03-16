package com.develogical.camera;

import org.junit.Test;
import org.mockito.ArgumentCaptor;

import static org.mockito.Mockito.*;

public class CameraTest {

    Sensor sensor = mock(Sensor.class);

    @Test
    public void switchingTheCameraOnPowersUpTheSensor() {


        Camera underTest = new Camera(sensor);
        underTest.powerOn();

        verify(sensor).powerUp();
    }

    @Test
    public void switchingTheCameraOffPowersDownTheSensor(){
        Camera underTest = new Camera(sensor);
        underTest.powerOff();

        verify(sensor).powerDown();
    }
}
