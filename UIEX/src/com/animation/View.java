/**
 * View: Contains everything about graphics and images
 * Know size of world, which images to load etc
 *
 * has methods to
 * provide boundaries
 * use proper images for direction
 * load images for all direction (an image should only be loaded once!!! why?)
 **/
package com.animation;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class View extends JFrame {
    class GamePanel extends JPanel {
        Direction direct = Direction.SOUTHEAST;
        ActionStatus action = ActionStatus.WALK;
        int posX;
        int posY;
        int picSeq = 0;
        //@Override
        public GamePanel() {
            super();
        }
        public void paint(Graphics g) {
            super.paint(g);
            switch (action) {
                case WALK:
                    g.drawImage(walkPics[direct.ordinal()][picSeq % walkFrameCount],posX,posY,Color.gray,this);
                    break;
                case FIRE:
                    g.drawImage(firePics[direct.ordinal()][picSeq % fireFrameCount],posX,posY,Color.gray,this);
                    break;
                case JUMP:
                    g.drawImage(jumpPics[direct.ordinal()][picSeq % jumpFrameCount],posX,posY,Color.gray,this);
                    break;
                default:
                    break;
            }
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
    JButton fire = new JButton("Fire");
    JButton jump = new JButton("Jump");

    BufferedImage[][] walkPics;
    BufferedImage[][] firePics;
    BufferedImage[][] jumpPics;

    final int walkFrameCount = 10;
    final int fireFrameCount = 4;
    final int jumpFrameCount = 8;
    final int picSize = 165;
    final int frameStartSize = 800;

    public View() {
        loadAnimationPngs();

        panel.setBackground(Color.gray);
        panel.add(start_btn);
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

        walkPics = new BufferedImage[8][walkFrameCount];
        for(int i = 0;i < 8;++i) {
            for(int j = 0;j < walkFrameCount;++j) {
                walkPics[i][j] = img[i].getSubimage(picSize * j, 0, picSize, picSize);
            }
        }

        img[0] = createImage("orc/orc_fire_north.png");
        img[1] = createImage("orc/orc_fire_northeast.png");
        img[2] = createImage("orc/orc_fire_east.png");
        img[3] = createImage("orc/orc_fire_southeast.png");
        img[4] = createImage("orc/orc_fire_south.png");
        img[5] = createImage("orc/orc_fire_southwest.png");
        img[6] = createImage("orc/orc_fire_west.png");
        img[7] = createImage("orc/orc_fire_northwest.png");
        firePics = new BufferedImage[8][fireFrameCount];
        for(int i = 0;i < 8;++i) {
            for(int j = 0;j < fireFrameCount;++j) {
                firePics[i][j] = img[i].getSubimage(picSize * j, 0, picSize, picSize);
            }
        }

        img[0] = createImage("orc/orc_jump_north.png");
        img[1] = createImage("orc/orc_jump_northeast.png");
        img[2] = createImage("orc/orc_jump_east.png");
        img[3] = createImage("orc/orc_jump_southeast.png");
        img[4] = createImage("orc/orc_jump_south.png");
        img[5] = createImage("orc/orc_jump_southwest.png");
        img[6] = createImage("orc/orc_jump_west.png");
        img[7] = createImage("orc/orc_jump_northwest.png");
        jumpPics = new BufferedImage[8][jumpFrameCount];
        for(int i = 0;i < 8;++i) {
            for(int j = 0;j < jumpFrameCount;++j) {
                jumpPics[i][j] = img[i].getSubimage(picSize * j, 0, picSize, picSize);
            }
        }

    }
    public void update(int X,int Y,Direction direction,ActionStatus action) {
        panel.posX = X;
        panel.posY = Y;

        if(action != panel.action)
            panel.action = action;
        if(direction != panel.direct)
            panel.direct = direction;

        this.repaint();
        panel.picSeq++;
        panel.requestFocus();
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


    /*
    public static void main(String[] args) {
        Direction d = Direction.SOUTH;
        Direction s = Direction.SOUTH;
        if(d == s) {
            System.out.print("Yes");
        }
    }
    */

}