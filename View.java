/**
 * View: Contains everything about graphics and images
 * Know size of world, which images to load etc
 *
 * has methods to
 * provide boundaries
 * use proper images for direction
 * load images for all direction (an image should only be loaded once!!! why?)
 **/

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class View extends JFrame{
    class GamePanel extends JPanel {
        Direction direct = Direction.SOUTHEAST;
        int posX;
        int posY;
        int picSeq = 0;
       
        //@Override
        public GamePanel() {
            super();
        }
		public void paint(Graphics g) {
			super.paint(g);
			//System.out.println(posX+","+posY);
            g.drawImage(walkPics[direct.ordinal()][picSeq % frameCount],posX,posY,Color.gray,this);

            //g.drawImage(walkPics[0][9],0,0,Color.gray,this);
        }
        public Dimension getPreferredSize() {
            return new Dimension(frameStartSize, frameStartSize);
        }

    }

    GamePanel panel = new GamePanel();
    JButton start_btn = new JButton("Stop");
    JButton up = new JButton("Up");
    JButton down = new JButton("Down");
    JButton left = new JButton("Left");
    JButton right = new JButton("Right");

    BufferedImage[][] walkPics;

    final int frameCount = 10;
    final int picSize = 165;
    final int frameStartSize = 700;
    boolean wether_draw = true;
    int direction_control = -1;
    public View() {
        loadAnimationPngs();
        start_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                //Pop-up dialouge box
                if(wether_draw) {
                    wether_draw = false;
                    start_btn.setText("Start");
                } else {
                    wether_draw = true;
                    start_btn.setText("Stop");
                }
            }
        });
        up.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                direction_control = 0;
            }
        });
        down.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                direction_control = 4;
            }
        });
        left.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                direction_control = 6;
            }
        });
        right.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                direction_control = 2;
            }
        });

        panel.setBackground(Color.gray);
        panel.add(start_btn);
        panel.add(up);
        panel.add(down);
        panel.add(left);
        panel.add(right);
        this.getContentPane().add(panel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(frameStartSize, frameStartSize);
        this.setVisible(true);
        pack();
    }





    private void loadAnimationPngs() {
        BufferedImage []img = new BufferedImage[8];
        img[0] = createImage("orc/orc_forward_north.png");
        img[1] = createImage("orc/orc_forward_northeast.png");
        img[2] = createImage("orc/orc_forward_east.png");
        img[3] = createImage("orc/orc_forward_southeast.png");
        img[4] = createImage("orc/orc_forward_south.png");
        img[5] = createImage("orc/orc_forward_southwest.png");
        img[6] = createImage("orc/orc_forward_west.png");
        img[7] = createImage("orc/orc_forward_northwest.png");

        walkPics = new BufferedImage[8][frameCount];
        for(int i = 0;i < 8;++i) {
            for(int j = 0;j < frameCount;++j) {
                walkPics[i][j] = img[i].getSubimage(picSize * j, 0, picSize, picSize);
            }
        }
    }
    public void update(int X,int Y,Direction direction, boolean toTurn) {
        if(toTurn)
			direction_control = -1;
		panel.posX = X;
        panel.posY = Y;
        if(panel.direct != direction)
            panel.picSeq = 0;
        else
            panel.picSeq++;
        panel.direct = direction;

        this.repaint();
    }

    public int getImageWidth() {
        return picSize;
    }
    public int getImageHeight() {
        return picSize;
    }
    private BufferedImage createImage(String url){
        BufferedImage bufferedImage;
        try {
            bufferedImage = ImageIO.read(new File(url));
            return bufferedImage;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}