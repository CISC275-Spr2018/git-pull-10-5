/**
 * Do not modify this file without permission from your TA.
 **/
package com.animation;

import javax.swing.Action;
import javax.swing.AbstractAction;
import javax.swing.Timer;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Controller {

	private Model model;
	private View view;
	boolean wether_draw = true;        //to start or stop
    int direction_control = -1;     //to record the control to direction
    final int drawDelay = 30; //msec
    Action drawAction;

	public Controller(){
		view = new View();
		model = new Model(view.getWidth(), view.getHeight(), view.getImageWidth(), view.getImageHeight());

        drawAction = new AbstractAction(){
            public void actionPerformed(ActionEvent e){
                if(wether_draw) {
                    model.updateLocation(view.getWidth(), view.getHeight());
                    view.update(model.getX(), model.getY(),model.getDirect(),model.getAction());
                }
            }
        };
        view.start_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                if(wether_draw) {
                    wether_draw = false;
                    view.start_btn.setText("Start");
                } else {
                    wether_draw = true;
                    view.start_btn.setText("Stop");
                }
            }
        });
        view.panel.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                //nothing to do
            }

            @Override
            public void keyPressed(KeyEvent e) {
                /*
                If any direction keys were pressed,he should back to walk status
                If you want him to fire or jump,you need to kepp pressing F and J respectively
                 */
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        model.direction = Direction.NORTH;
                        model.action = ActionStatus.WALK;
                        break;
                    case KeyEvent.VK_DOWN:
                        model.direction = Direction.SOUTH;
                        model.action = ActionStatus.WALK;
                        break;
                    case KeyEvent.VK_LEFT:
                        model.direction = Direction.WEST;
                        model.action = ActionStatus.WALK;
                        break;
                    case KeyEvent.VK_RIGHT:
                        model.direction = Direction.EAST;
                        model.action = ActionStatus.WALK;
                        break;
                    case KeyEvent.VK_F:
                        model.action = ActionStatus.FIRE;
                        break;
                    case KeyEvent.VK_J:
                        model.action = ActionStatus.JUMP;
                        break;
                    default:break;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                /*
                If F key or J key was released,he will back to status immediately
                 */
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_J:
                        model.action = ActionStatus.WALK;
                        break;
                    case KeyEvent.VK_F:
                        model.action = ActionStatus.WALK;
                        break;
                    default:
                        break;
                }
            }
        });
    }
	
        //run the simulation
	public void start() {
		/*
		for (int i = 0; i < 5000; i++) {
			//increment the x and y coordinates, alter direction if necessary

			//I add two arguments for this function,so that I can change the size of window when it's running;

			model.updateLocationAndDirection(view.getWidth(),view.getHeight());
			//update the view
			view.update(model.getX(), model.getY(), model.getDirect());
		}
		*/

		EventQueue.invokeLater(new Runnable(){
			public void run() {
                Timer t = new Timer(drawDelay, drawAction);
                t.start();
                //t.setDelay(2*drawDelay);
            }
		});
	}
}
