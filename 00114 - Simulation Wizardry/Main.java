import java.util.Scanner;

enum ItemType {BALL, BUMPER, EMPTY, WALL}
enum Direction {UP, LEFT, DOWN, RIGHT}

class Item {
  public int x;
  public int y;
  public Item(int x, int y) {
    this.x = x;
    this.y = y;
  }
  ItemType getItemType() {
    return ItemType.EMPTY;
  }
  public boolean isAtTheSamePosition(Item item) {
    return x == item.x && y == item.y;
  }

  @Override
  public String toString() {
    return "+";
  }
}

class Ball extends Item {
  public Direction direction;
  public int life;
  public int pointsWon;
  public Ball(int x, int y, int direction, int life) {
    super(x, y);
    switch(direction) {
      case 0:
        this.direction = Direction.RIGHT;
        break;
      case 1:
        this.direction = Direction.UP;
        break;
      case 2:
        this.direction = Direction.LEFT;
        break;
      case 3:
        this.direction = Direction.DOWN;
        break;
    }
    this.life = life;
  }
  public boolean isAlive() {
    return --life > 0;
  }

  public void move(Board map) {
    int newLife = life;
    int newPoints = pointsWon;
    int newx = x, newy = y;
    switch(direction) {
      case UP:
        newy++;
        break;
      case RIGHT:
        newx++;
        break;
      case DOWN:
        newy--;
        break;
      case LEFT:
        newx--;
        break;
    }
    boolean moveTheBall = false;
    Item item = map.getItem(newx, newy);
    //System.out.println(item.getItemType());
    switch(item.getItemType()) {
      case WALL:
        newLife -= ((Wall)item).cost;
        break;
      case EMPTY:
        moveTheBall = true;
        break;
      case BUMPER:
        Bumper bumper = (Bumper) item;
        newLife -= bumper.cost;
        newPoints += bumper.value;
        break;
    }
    if (moveTheBall) {
      map.addItem(x, y, new Item(x, y));
      map.addItem(newx, newy, this);
      x = newx;
      y = newy;
    } else {
      calculateNewDirectin();
    }
    life = newLife;
    pointsWon = newPoints;
    //System.out.println(pointsWon);
  }

  private void calculateNewDirectin() {
    switch(direction) {
      case UP:
        direction = Direction.RIGHT;
        break;
      case RIGHT:
        direction = Direction.DOWN;
        break;
      case DOWN:
        direction = Direction.LEFT;
        break;
      case LEFT:
        direction = Direction.UP;
        break;
    }
  }

  @Override
  ItemType getItemType() {
    return ItemType.BALL;
  }

  @Override
  public String toString() {
    return "O";
  }
}

class Bumper extends Item {
  public int value;
  public int cost;
  public Bumper(int x, int y, int value, int cost) {
    super(x, y);
    this.value = value;
    this.cost = cost;
  }

  @Override
  public String toString() {
    return "B";
  }

  ItemType getItemType() {
    return ItemType.BUMPER;
  }
}

class Wall extends Item {
  public int cost;
  public Wall(int x, int y, int cost) {
    super(x, y);
    this.cost = cost;
  }
  @Override
  public String toString() {
    return "W";
  }

  ItemType getItemType() {
    return ItemType.WALL;
  }
}

class Board {
  int m, n;
  Item map[][];
  int wallCost;

  public Board(int m, int n, int wallCost) {
    this.m = m;
    this.n = n;
    this.wallCost = wallCost;
    map = new Item[m][n];
    for (int i = 0; i < m; i++)
      for (int j = 0; j < n; j++)
        if (i == 0 || j == 0 || i == (m - 1) || j == (n - 1))
          map[i][j] = new Wall(i, j, wallCost);
        else
          map[i][j] = new Item(i, j);
  }

  public Item getItem(int x, int y) {
    return map[x - 1][y - 1];
  }

  public void addItem(int x, int y, Item item) {
    //this can be really strange, need to test it more
    map[x - 1][y - 1] = item;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        builder.append(map[i][j]);
      }
      builder.append("\n");
    }
    return builder.toString();
  }
}

public class Main {

	public static void main(String[] args) throws Exception {
		//Scanner in = new Scanner (new FileReader(new File("proba.txt")));
		Scanner in = new Scanner(System.in);
    int m = in.nextInt();
    int n = in.nextInt();
    int wallCost = in.nextInt();
    Board board = new Board(m, n, wallCost);
    int numOfBumpers = in.nextInt();
    for (int i = 0; i < numOfBumpers; i++) {
      int x = in.nextInt();
      int y = in.nextInt();
      int value = in.nextInt();
      int cost = in.nextInt();
      board.addItem(x, y, new Bumper(x, y, value, cost));
    }
    int totalPoints = 0;
    while(in.hasNextInt()) {
      int x = in.nextInt();
      int y = in.nextInt();
      int direction = in.nextInt();
      int life = in.nextInt();
      Ball ball = new Ball(x, y, direction, life);
      board.addItem(x, y, ball);
      //System.out.println(board);
      while(ball.isAlive()) {
        //System.out.println(ball.x + ", " + ball.y + ", " + ball.life);
        ball.move(board);
        //System.out.println(board);
      }
      board.addItem(ball.x, ball.y, new Item(ball.x, ball.y));
      totalPoints += ball.pointsWon;
      System.out.println(ball.pointsWon);
    }
    System.out.println(totalPoints);
	}
}
