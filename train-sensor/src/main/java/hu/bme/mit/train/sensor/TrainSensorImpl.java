package hu.bme.mit.train.sensor;

import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainSensor;
import hu.bme.mit.train.interfaces.TrainUser;

public class TrainSensorImpl implements TrainSensor {

	private TrainController controller;
	private TrainUser user;
	private int speedLimit = 5;
	private boolean finished = false;
	private boolean alarm = false;

	public boolean getAlarm() {
		return alarm;
	}

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
	public void setReferenceSpeed() {
		while (!finished) {

			if (user.getJoystickPosition() != 0) {
				controller.setJoystickPosition(user.getJoystickPosition());
				controller.followSpeed();
			}

			switchFinishFlag();
		}
	}

	@Override
	public void switchFinishFlag() {
		finished = !finished;
	}

	@Override
	public void alarmSpeedLimit() {
		if (speedLimit < 0 || speedLimit > 500) {
			user.setAlarmState(true);
		} else {
			if (speedLimit < controller.getReferenceSpeed() * 0.5) {
				user.setAlarmState(true);
			} else {
				user.setAlarmState(false);
			}
		}
		alarm = user.getAlarmState();
	}

}
