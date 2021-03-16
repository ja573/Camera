package com.develogical.camera;

import org.junit.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.core.Is.*;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

public class CameraTest {

    Sensor sensor = mock(Sensor.class);
    MemoryCard memoryCard = mock(MemoryCard.class);

    @Test
    public void switchingTheCameraOnPowersUpTheSensor() {


        Camera underTest = new Camera(sensor, memoryCard);
        underTest.powerOn();

        verify(sensor).powerUp();
    }

    @Test
    public void switchingTheCameraOffPowersDownTheSensor(){
        Camera underTest = new Camera(sensor, memoryCard);
        underTest.powerOff();

        verify(sensor).powerDown();
    }

    @Test
    public void pressingShutterWithPowerOnCopiesDataFromSensorToMemCard(){
        Camera underTest = new Camera(sensor, memoryCard);
        given(sensor.readData()).willReturn(new byte[]{123});

        underTest.powerOn();
        underTest.pressShutter();

        verify(memoryCard).write(eq(new byte[]{123}), any());
    }

    @Test
    public void pressingShutterWithPowerOffDoesNothing(){
        Camera underTest = new Camera(sensor, memoryCard);
        given(sensor.readData()).willReturn(new byte[]{123});

        underTest.powerOff();

        underTest.pressShutter();

        verify(memoryCard, never()).write(eq(null), any());
    }

    @Test
    public void switchingCameraOffWhileWritingDoesNotPowerDownSensor(){
        Camera underTest = new Camera(sensor, memoryCard);
        underTest.powerOn();
        underTest.pressShutter();
        underTest.powerOff();
        verify(sensor, never()).powerDown();
    }

    @Test
    public void powerDownCameraAfterWritingComplete(){
        Camera underTest = new Camera(sensor, memoryCard);
        underTest.powerOn();
        underTest.pressShutter();
        underTest.powerOff();
        ArgumentCaptor<WriteCompleteListener> writeCompleteListenerArgumentCaptor = ArgumentCaptor.forClass(WriteCompleteListener.class);
        verify(memoryCard).write(any(), writeCompleteListenerArgumentCaptor.capture());
        writeCompleteListenerArgumentCaptor.getValue().writeComplete();
        verify(sensor).powerDown();
    }
}
