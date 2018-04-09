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




public class Model {
    int panelWidth;
    int panelHeight;
    int imageWidth;
    int imageHeight;
    final int xIncr = 2;
    final int yIncr = 2;
    int x = 0;
    int y = 0;
    Direction direction = Direction.SOUTHEAST;

    public Model(int panelWidth,int panelHeight,int imageWidth,int imageHeight) {
        this.panelWidth = panelWidth;
        this.panelHeight = panelHeight;
        this.imageWidth = imageWidth;
        this.imageHeight = imageHeight;
        //System.out.print(panelWidth+","+panelHeight);

    }
    public void updateLocationAndDirection(int panelWidth,int panelHeight,int direction_control) {
        this.panelWidth = panelWidth;
        this.panelHeight = panelHeight;
        changeDirect(direction_control);
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
        //changeDirect();
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
    private void changeDirect(int d) {
        for(Direction tmp : Direction.values())
            if(tmp.ordinal() == d)
                this.direction = tmp;
    }
}