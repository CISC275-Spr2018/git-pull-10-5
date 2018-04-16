/**
 * Model: Contains all the state and logic
 * Does not contain anything about images or graphics, must ask view for that
 *
 * has methods to
 *  detect collision with boundaries
 * decide next direction
 * provide direction
 * provide location
 **/
package com.animation;



public class Model {
    int panelWidth;
    int panelHeight;
    int imageWidth;
    int imageHeight;
    final int xIncr = 1;
    final int yIncr = 1;
    int x = 0;
    int y = 0;
    Direction direction = Direction.SOUTHEAST;
    ActionStatus action = ActionStatus.WALK;

    public Model(int panelWidth,int panelHeight,int imageWidth,int imageHeight) {
        this.panelWidth = panelWidth;
        this.panelHeight = panelHeight;
        this.imageWidth = imageWidth;
        this.imageHeight = imageHeight;

    }
    public void updateLocation(int panelWidth,int panelHeight) {
        int xIncr = this.xIncr;
        int yIncr = this.yIncr;
        this.panelWidth = panelWidth;
        this.panelHeight = panelHeight;
        if(action == ActionStatus.FIRE)     //stop moving when he fires
            return;
        if(action == ActionStatus.JUMP) {   //add steps when he jumps
            xIncr *= 3;
            yIncr *= 3;
        }

        switch (direction) {
            case SOUTH:
                y+=yIncr;break;
            case WEST:
                x-=xIncr;break;
            case NORTH:
                y-=yIncr;break;
            case EAST:
                x+=xIncr;break;
            case SOUTHEAST:
                x+=xIncr;y+=yIncr;break;
            case NORTHEAST:
                x+=xIncr;y-=yIncr;break;
            case NORTHWEST:
                x-=xIncr;y-=yIncr;break;
            case SOUTHWEST:
                x-=xIncr;y+=yIncr;break;
        }
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public Direction getDirect() {
        return direction;
    }

    public ActionStatus getAction() {
        return action;
    }
}