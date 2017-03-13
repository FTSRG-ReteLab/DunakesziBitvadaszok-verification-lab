package hu.bme.mit.train.sensor;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainSensor;
import hu.bme.mit.train.interfaces.TrainUser;

import static org.mockito.Mockito.*;

public class TrainSensorTest {

	
	TrainController controller;
	TrainSensor sensor;
	TrainUser user;
	
	@Before
	public void before() {
		controller = mock(TrainController.class);
		user = mock(TrainUser.class);
		sensor = new TrainSensorImpl(controller,user);
	}

    
    @Test
    public void alert_SpeedLimitLessThanZero(){
    	sensor.overrideSpeedLimit(-1);
    	
    	when(user.getAlarmState()).thenReturn(true);
    	
    	sensor.alarmSpeedLimit();
    
    	Assert.assertEquals(true, sensor.getAlarm());
    }
    
    @Test
    public void alert_SpeedLimitMoreThan500(){
    	sensor.overrideSpeedLimit(501);
    	
    	when(user.getAlarmState()).thenReturn(true);
    	
    	sensor.alarmSpeedLimit();
    	
    	Assert.assertEquals(true, sensor.getAlarm());
    	
    }
    
    @Test
    public void noAlert_EverythingIsFine(){
    	sensor.overrideSpeedLimit(250);
    	
    	when(user.getAlarmState()).thenReturn(false);
    	
    	sensor.alarmSpeedLimit();
    	
    	Assert.assertEquals(false, sensor.getAlarm());
    }
    
    @Test
    public void alert_SpeedLimitLessThanHalfReference(){
    	sensor.overrideSpeedLimit(150);
    	
    	when(user.getJoystickPosition()).thenReturn(150);
    	
    	sensor.setReferenceSpeed();
    	
    	sensor.overrideSpeedLimit(50);
    	
    	when(user.getAlarmState()).thenReturn(true);
    	
    	sensor.alarmSpeedLimit();
    	
    	Assert.assertEquals(true, user.getAlarmState());
    }
}
