

import javax.swing.Action;
import javax.swing.AbstractAction;
import javax.swing.Timer;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;

public class Controller {

	private Model model;
	private View view;
    final int drawDelay = 80; //msec
    Action drawAction;

	public Controller(){
		view = new View();
		model = new Model(view.getWidth(), view.getHeight(), view.getImageWidth(), view.getImageHeight());
        drawAction = new AbstractAction(){
            public void actionPerformed(ActionEvent e){
                //Only update position when not stopped
				if(view.wether_draw) {
					model.updateLocationAndDirection(view.getWidth(), view.getHeight(),view.direction_control);
                }
				//Allows the orc to animate and turn even when he's not moving
				view.update(model.getX(), model.getY(), model.getDirect(), model.toTurn());
            }
        };
	}
	
        //run the simulation
	public void start() {

		EventQueue.invokeLater(new Runnable(){
			public void run() {
                Timer t = new Timer(drawDelay, drawAction);
                t.start();
            }
		});
	}
}
