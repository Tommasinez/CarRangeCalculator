package it.betacom.model;

import java.time.LocalDate;
import java.util.Scanner;

import it.betacom.util.ScheduledOutput;

public class Car {	
	Scanner scanner = new Scanner(System.in);

	private Integer consumption = 0;
	private Double gasLevel = 0.0;
	private Double range = 0.0;
	private LocalDate registrationDate;

	public void RefillTank() {
		ScheduledOutput output = new ScheduledOutput();
		final Integer DEFAULT_TIMEOUT = 350;
		Integer timeout = DEFAULT_TIMEOUT;
		Integer gas;
		
		output.setScheduledPrint("\nHow much gas do you want to refill you tank with? L ", timeout);
		gas = scanner.nextInt();
		
		setGasLevel(getGasLevel() + gas);
		setRange(getGasLevel() * getConsumption());
		
		output.setScheduledPrintln("\nGas Tank Level: L " + getGasLevel(), timeout);
		output.setScheduledPrintln("Range: KM " + getRange(), timeout);		
	}

	public void RoadTrip() {
		ScheduledOutput output = new ScheduledOutput();
		final Integer DEFAULT_TIMEOUT = 350;
		Integer timeout = DEFAULT_TIMEOUT;
		Integer roadLength;
		
		output.setScheduledPrint("\nGet an estimation of your car tank level after a trip!", timeout);
		output.setScheduledPrint("\nEnter the mileage of your road trip: KM ", timeout);
		roadLength = scanner.nextInt();
		
		if (roadLength <= getRange()) {
			setRange(getRange() - roadLength);
			setGasLevel(getRange() / getConsumption());
			output.setScheduledPrintln("\nRange left: KM " + getRange(), timeout);
			output.setScheduledPrintln("Gas left: L " + getGasLevel(), timeout);
		} else {
			output.setScheduledPrintln("Warning! The mileage of your trip (KM " + roadLength + ") exceed you range (KM "
					+ getRange() + ")", 500);
		}		
	}

	public Integer getConsumption() {
		return consumption;
	}

	public void setConsumption(Integer consumption) {
		this.consumption = consumption;
	}

	public Double getGasLevel() {
		return gasLevel;
	}

	public void setGasLevel(Double gasLevel) {
		this.gasLevel = gasLevel;
	}

	public Double getRange() {
		return range;
	}

	public void setRange(Double range) {
		this.range = range;
	}

	public LocalDate getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(LocalDate formattedCarDate) {
		this.registrationDate = formattedCarDate;
	}
}
