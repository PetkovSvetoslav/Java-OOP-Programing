package p06_TirePressureMonitoringSystem;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AlarmTest {
    private Alarm alarm;
    private Sensor mockedSensor;

    @Before
    public void setUp() {
        this.mockedSensor = mock(Sensor.class);
        this.alarm = new Alarm(mockedSensor);
    }

    @Test
    public void testGetAlarmOnWithLowerPressure() {
        when(this.mockedSensor.popNextPressurePsiValue()).thenReturn(16.9);
        this.alarm.check();
        assertTrue(this.alarm.getAlarmOn());
    }

    @Test
    public void testGetAlarOnWithHigherPressure() {
        when(this.mockedSensor.popNextPressurePsiValue()).thenReturn(21.1);
        this.alarm.check();
        assertTrue(this.alarm.getAlarmOn());
    }

    @Test
    public void testGetAlarmOnWithCorrectPressure() {
        when(this.mockedSensor.popNextPressurePsiValue()).thenReturn(18d);
        this.alarm.check();
        assertFalse(this.alarm.getAlarmOn());
    }
}