package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import view.Frejm;

public class TimeListen implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
		String s = sdf.format(new Date(System.currentTimeMillis()));
		Frejm.getInstance().getPanBottom().getCurrentTime().change(s);
	}

}
