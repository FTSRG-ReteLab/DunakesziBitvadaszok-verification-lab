package hu.bme.mit.train.interfaces;

public interface TrainSensor {
	
	boolean getAlarm();

	int getSpeedLimit();

	void overrideSpeedLimit(int speedLimit);
	
	void setReferenceSpeed();
	
	void switchFinishFlag();
	
	public void alarmSpeedLimit();

}
