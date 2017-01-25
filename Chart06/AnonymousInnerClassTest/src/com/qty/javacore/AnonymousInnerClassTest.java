package com.qty.javacore;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.Timer;

public class AnonymousInnerClassTest {

	public static void main(String[] args) {
		TalkingClock clock = new TalkingClock();
		clock.start(1000, true);
		
		// keep program running until user selects "OK"
		JOptionPane.showMessageDialog(null,  "Quit program?");
		System.exit(0);
	}
}

/**
 * A clock that prints the time in regular intervals.
 */
class TalkingClock {
	
	/**
	 * Starts the clock.
	 * @param interval the interval between messges (in milliseconds)
	 * @param beep true if the clock should beep
	 */
	public void start(int interval, final boolean beep) {
		ActionListener listener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				Date now = new Date();
				System.out.println("At the tone, the time is " + now);
				if (beep) Toolkit.getDefaultToolkit().beep();
			}
		};
		Timer t = new Timer(interval, listener);
		t.start();
	}
}
