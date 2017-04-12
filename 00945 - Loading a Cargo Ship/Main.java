import java.io.*;
import java.util.*;

class Container {
  private ArrayList<Integer> cargoList;
  private int capacity;
  private int currentCapacity;
  private int containerNumber;
  public Container(int capacity, int containerNumber) {
    this.containerNumber = containerNumber;
    this.capacity = capacity;
    this.cargoList = new ArrayList<>();
  }
  public int getCapacity() {
    return capacity;
  }

  public int getNumberOfPackages() {
    return cargoList.size();
  }

  public int getPackage(int i) {
    return cargoList.get(i);
  }

  public int getContainerNumber() {
    return containerNumber;
  }

  public int getCurrentCapacity() {
    return currentCapacity;
  }

  public int getAvalableSpaceLeft() {
    return capacity - currentCapacity;
  }

  public boolean addPackage(int packageWeight) {
    if (currentCapacity + packageWeight <= capacity) {
      //System.out.println("Added to container: " + containerNumber);
      cargoList.add(packageWeight);
      currentCapacity += packageWeight;
      return true;
    }
    return false;
  }
}


class Ship {
  private ArrayList<Container> containers;
  public Ship(int numOfContainers) {
    containers = new ArrayList<>();
  }

  public void addContainer(Container container) {
    containers.add(container);
  }

  public int getTotalWeightCapacity() {
    int sum = 0;
    for (Container container : containers) {
      sum += container.getCapacity();
    }
    return sum;
  }

  public boolean addPackage(int packageWeight) {
    ArrayList<Container> minPackageContainers = getContainersWithMinPackages();
    if (minPackageContainers.size() == 1) {
      return minPackageContainers.get(0).addPackage(packageWeight);
    }
    ArrayList<Container> gratestAvailableCapacityContainers =
      getContainersWithGratesCapacityLeft(minPackageContainers);
    if (gratestAvailableCapacityContainers.size() == 1) {
      return gratestAvailableCapacityContainers.get(0).addPackage(packageWeight);
    }

    Container minContainer = null;
    int minNumber = 10;
    for (Container container : gratestAvailableCapacityContainers) {
      if (container.getContainerNumber() < minNumber) {
        minNumber = container.getContainerNumber();
        minContainer = container;
      }
    }
    return minContainer.addPackage(packageWeight);
  }

  private ArrayList<Container> getContainersWithMinPackages() {
    int min = 2000;
    for (Container container : containers)
      if (container.getNumberOfPackages() < min)
        min = container.getNumberOfPackages();

    ArrayList<Container> minPackageContainers = new ArrayList<>();
    for (Container container : containers)
      if (container.getNumberOfPackages() == min)
        minPackageContainers.add(container);
    return minPackageContainers;
  }

  private ArrayList<Container> getContainersWithGratesCapacityLeft(ArrayList<Container> minPackageContainers) {
    int max = 0;
    for (Container container : minPackageContainers)
      if (container.getAvalableSpaceLeft() > max)
        max = container.getAvalableSpaceLeft();

    ArrayList<Container> maxWeightLeftContainers = new ArrayList<>();
    for (Container container : minPackageContainers)
      if (container.getAvalableSpaceLeft() == max)
        maxWeightLeftContainers.add(container);
    return maxWeightLeftContainers;
  }

  @Override
  public String toString() {
    int max = 0;
    for (Container container : containers)
      if (container.getNumberOfPackages() > max)
        max = container.getNumberOfPackages();
    char mat[][] = new char[containers.size()][max + 2];
    //System.out.println(max);
    for (int i = 0; i < containers.size(); i++) {
      mat[i][0] = (char)('0' + i + 1);
      mat[i][1] = '=';
      Container container = containers.get(i);
      for (int j = 0; j < max; j++) {
        mat[i][2+j] = (j < container.getNumberOfPackages())
          ? (char)(container.getPackage(j) + '0')
          : ':';
      }
    }
    StringBuilder stringBuilder = new StringBuilder();
    for (int j = max + 1; j >= 0; j--) {
      for (int i = 0; i < containers.size(); i++) {
        stringBuilder.append(mat[i][j]);
      }
      stringBuilder.append('\n');
    }
    return stringBuilder.toString();
  }
}

class Main
{
  public static void main (String args[]) {
    Scanner in = new Scanner(System.in);
    int numOfContainers = in.nextInt();
    Ship ship = new Ship(numOfContainers);
    for (int i = 0; i < numOfContainers; i++) {
      ship.addContainer(new Container(in.nextInt(), i+1));
    }
    int numOfPackages = in.nextInt();
    for (int i = 0; i < numOfPackages; i++) {
      ship.addPackage(in.nextInt());
    }
    System.out.println(ship);
  }
}
