package it.betacom.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import it.betacom.model.Car;
import it.betacom.util.ScheduledOutput;

public class Controller {
	public static void main(String[] args) {
		Car toyota = new Car();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		Scanner scanner = new Scanner(System.in);
		ScheduledOutput output = new ScheduledOutput();
		final Integer DEFAULT_TIMEOUT = 700;
		Character mainFlag = '9';
		LocalDate today = LocalDate.now();
		
		toyota.setConsumption(20);
		toyota.setRegistrationDate(LocalDate.parse("16-10-2020", formatter));
		
		System.out.println("INFO ABOUT YOUR VEHICLE");
		System.out.println("Car consumption: " + toyota.getConsumption() + " KM/L");
		System.out.println("Registration Date: " + toyota.getRegistrationDate() + "\n");

		Integer welcomeTimeout = DEFAULT_TIMEOUT;
		for (int i = 1; i < 4; i++) {
			output.setScheduledPrint(".", i * (welcomeTimeout / 4));
		}
		output.setScheduledPrint(" Welcome to the gas station!", welcomeTimeout);

		while (mainFlag == '9') {
			try {
				loop: while (true) {
					Integer optionTimeout = DEFAULT_TIMEOUT;
					output.setScheduledPrintln("\nSelect the option you need to perform:", optionTimeout);
					output.setScheduledPrintln("1 - Refill your car tank | 2 - Start a new road trip | 0 - Exit\n",
							optionTimeout);

					Character option = scanner.next().charAt(0);
					Character optionFlag = Character.toUpperCase(option);

					switch (Character.toUpperCase(optionFlag)) {
					case '1':
						toyota.RefillTank();
						break loop;
					case '2':
						if (toyota.getRegistrationDate().isAfter(today.minusYears(10))) {
							toyota.RoadTrip();
							break loop;
						} else {
							optionTimeout = optionTimeout / 2;
							output.setScheduledPrintln("\nYour vehicle is too old to drive!", optionTimeout);
							output.setScheduledPrintln("Consider to buy a new car.", optionTimeout);
							break;
						}
					case '0':
						scanner.close();
						System.exit(0);
					default:
						optionTimeout = optionTimeout / 2;
						output.setScheduledPrintln("\nInvalid option!", optionTimeout);
						break;
					}
				}
			} catch (Exception exception) {
				System.out.println("\nSomething gone wrong!");
			} finally {
				loop: while (true) {
					Integer exitTimeout = DEFAULT_TIMEOUT;
					output.setScheduledPrintln(
							"\nPress \"9\" to perform another operation. Otherwise, press \"0\" to exit.\n",
							exitTimeout * 2);

					Character exit = scanner.next().charAt(0);
					mainFlag = Character.toUpperCase(exit);

					switch (mainFlag) {
					case '9':
						break loop;
					case '0':
						scanner.close();
						System.exit(0);
					default:
						exitTimeout = exitTimeout / 2;
						output.setScheduledPrintln("\nInvalid option!", exitTimeout);
						break;
					}
				}
			}
		}
	}
}
