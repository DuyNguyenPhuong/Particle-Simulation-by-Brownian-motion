import java.util.LinkedList;
import java.util.*;

public class Test {
    Deque<double[]> trajectoryMap = new LinkedList<>();

    public Test() {

    }

    public void addPos(double[] pos) {
        trajectoryMap.add(pos);
    }

    public void printTraj() {
        while (trajectoryMap.size()!=0) {
            System.out.println(trajectoryMap.poll()[0]);
        }
    }
    public static void main(String[] args) {
        // Test newTest = new Test();
        // double[] double1 = new double[]{1.0,2.0};
        // double[] double2 = new double[]{4.2,5.5};
        // double[] double3 = new double[]{2.4,9.8};
        // newTest.addPos(double1);
        // newTest.addPos(double2);
        // newTest.addPos(double3);
        // System.out.println(newTest.trajectoryMap.size());
        // newTest.printTraj();
        double a = 0.9609690517567008;

        System.out.println((int) a*2+1);
    }
}
