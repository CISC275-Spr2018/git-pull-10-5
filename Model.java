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
    final int xIncr = 5;
    final int yIncr = 5;
    int x = 0;
    int y = 0;
	static int minX = 0;
	static int maxX;// = frameWidth - imgWidth;
	static int minY = 0;
	static int maxY;// = frameHeight - imgHeight;
	boolean autoTurn; //Flag to show that ork turned w/out input
    Direction direction = Direction.SOUTHEAST;

    public Model(int panelWidth,int panelHeight,int imageWidth,int imageHeight) {
        this.panelWidth = panelWidth;
        this.panelHeight = panelHeight;
        this.imageWidth = imageWidth;
        this.imageHeight = imageHeight;
		this.maxX = panelWidth - imageWidth;
		this.maxY = panelHeight - imageHeight;
        //System.out.print(panelWidth+","+panelHeight);

    }
    public void updateLocationAndDirection(int panelWidth,int panelHeight,int direction_control) {
        if(autoTurn)
			autoTurn = false;
		this.panelWidth = panelWidth;
        this.panelHeight = panelHeight;
        changeDirect(direction_control);
        int nextX = x;
		int nextY = y;
        switch (direction) {
            case SOUTH:
                nextY+=yIncr;break;
            case WEST:
                nextX-=xIncr;break;
            case NORTH:
                nextY-=yIncr;break;
            case EAST:
                nextX+=xIncr;break;
            case SOUTHEAST:
                nextX+=xIncr;nextY+=yIncr;break;
            case NORTHEAST:
                nextX+=xIncr;nextY-=yIncr;break;
            case NORTHWEST:
                nextX-=xIncr;nextY-=yIncr;break;
            case SOUTHWEST:
                nextX-=xIncr;nextY+=yIncr;break;
        }
		//Boundary checking
		if(nextX > maxX || nextX < minX){
			direction = flipHorizontalDir();
			autoTurn = true;
			nextX += (x-nextX)*2; //Go the opposite direction
		}
		if(nextY > maxY|| nextY < minY){
			direction = flipVerticalDir();
			autoTurn = true;
			nextY += (y-nextY)*2; //Go the opposite direction
		}
		x = nextX;
		y = nextY;
    }

	public Direction flipHorizontalDir(){
		Direction d;// = direction;
		switch (direction) {
            case WEST:
                d = Direction.EAST;break;
            case EAST:
                d = Direction.WEST;break;
            case SOUTHEAST:
                d = Direction.SOUTHWEST;break;
            case NORTHEAST:
                d = Direction.NORTHWEST;break;
            case NORTHWEST:
                d = Direction.NORTHEAST;break;
            case SOUTHWEST:
                d = Direction.SOUTHEAST;break;
			default:
				d = direction;
		}
		System.out.println("X:Before " + direction + " After " + d);
		return d;
	}
	
	public Direction flipVerticalDir(){
		Direction d;// = direction;
		switch (direction) {
            case SOUTH:
                d = Direction.NORTH;break;
            case NORTH:
                d = Direction.SOUTH;break;
            case SOUTHEAST:
                d = Direction.NORTHEAST;break;
            case NORTHEAST:
                d = Direction.SOUTHEAST;break;
            case NORTHWEST:
                d = Direction.SOUTHWEST;break;
            case SOUTHWEST:
                d = Direction.NORTHWEST;break;
			default:
				d = direction;
		}
		System.out.println("Y:Before " + direction + " After " + d);
		return d;
	}
	
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
	public boolean toTurn(){
		return autoTurn;
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