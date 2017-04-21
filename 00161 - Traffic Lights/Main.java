import java.util.Scanner;
import java.util.*;

class Light implements Comparable<Light> {
  public boolean green;
  public int greenInterval;
  public int redInterval;
  public int currTime;
  public Light(int time) {
    greenInterval = time - 5;
    redInterval = 2 * time - greenInterval;
    currTime = greenInterval;
    green = true;
  }

  public void change() {
    if (green)
      currTime += redInterval;
    else
      currTime += greenInterval;
    green = !green;
  }

  @Override
  public String toString() {
    return "[g:" + greenInterval + ", r:" + redInterval + ", ct:" + currTime + "v: " + green + "]";
  }

  @Override
  public int compareTo(Light light) {
    if (this.currTime == light.currTime)
      return 0;
    else
      return this.currTime > light.currTime ? 1 : -1;
  }
}

public class Main {

	public static void main(String[] args) throws Exception {
		//Scanner in = new Scanner (new FileReader(new File("proba.txt")));
		Scanner in = new Scanner(System.in);
    int a, b, c, tc = 0;
    while (true) {
      a = in.nextInt();
      b = in.nextInt();
      c = in.nextInt();
      if (a == 0 && b == 0 && c == 0)
        break;
      PriorityQueue<Light> que = new PriorityQueue<>();
      que.add(new Light(a));
      que.add(new Light(b));
      if (c != 0) {
        que.add(new Light(c));
        while (true) {
          c = in.nextInt();
          if (c == 0)
            break;
          que.add(new Light(c));
        }
      }
      Iterator i = que.iterator();
      boolean done = false;
      int prevTime = 0;
      int numOfLights = que.size();
      int numOfGreens = numOfLights;
      while (!done) {
        Light light = que.poll();
        if (light.currTime > 18000) {
          prevTime = light.currTime;
          break;
        }
        prevTime = light.currTime;
        light.change();
        numOfGreens += light.green?1:-1;
        if (prevTime != que.peek().currTime) {
          if (numOfGreens == numOfLights) {
            done = true;
            break;
          }
        }
        que.add(light);
      }
      if (prevTime <= 18000) {
        int h = prevTime / 3600;
        int min = (prevTime % 3600) / 60;
        int sec = prevTime % 60;
        System.out.printf("%02d:%02d:%02d\n", h, min, sec);
      } else {
        System.out.println("Signals fail to synchronise in 5 hours");
      }
    }
	}
}
