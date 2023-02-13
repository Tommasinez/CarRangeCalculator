package it.betacom.util;

import java.util.Timer;
import java.util.TimerTask;

public class ScheduledOutput {
	static Timer timer = new Timer();

	public void setScheduledPrint(String output, Integer timeout) {
		timer.schedule(new TimerTask() {
			public void run() {
				System.out.print(output);
			}
		}, timeout);
	}

	public void setScheduledPrintln(String output, Integer timeout) {
		timer.schedule(new TimerTask() {
			public void run() {
				System.out.println(output);
			}
		}, timeout);
	}
}
