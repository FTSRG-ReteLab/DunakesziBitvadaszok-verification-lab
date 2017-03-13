package hu.bme.mit.train.sensor;

import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainSensor;
import hu.bme.mit.train.interfaces.TrainUser;

public class TrainSensorImpl implements TrainSensor {

	private TrainController controller;
	private TrainUser user;
	private int speedLimit = 5;
	private boolean finished=false;

	public TrainSensorImpl(TrainController controller, TrainUser user) {
		this.controller = controller;
		this.user = user;
	}

	@Override
	public int getSpeedLimit() {
		return speedLimit;
	}

	@Override
	public void overrideSpeedLimit(int speedLimit) {
		this.speedLimit = speedLimit;
		controller.setSpeedLimit(speedLimit);
	}
	
	@Override
	public void setReferenceSpeed(){
		while(!finished){
			try {
				if(user.getJoystickPosition()!=0){
					controller.setJoystickPosition(user.getJoystickPosition());
					controller.followSpeed();
				}
			
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.err.println("Thread sleep error!");
			}
		}
	}
	
	@Override
	public void switchFinishFlag(){
		finished = !finished;
	}

}
