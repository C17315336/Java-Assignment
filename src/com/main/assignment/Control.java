package com.main.assignment;

import java.awt.EventQueue;
import javax.swing.JFrame;

public class Control extends JFrame {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				GUI form = new GUI();
				form.setVisible(true);
			}
		});
	}
}