import java.util.*;

public class Particles {
    public double[] pos;
    public double[] velocity;
    public double length;
    public double height;
    public Deque<double[]> trajectoryMap = new LinkedList<>();

    public Particles(Double boxLength, Double boxHeight) {
        length = boxLength;
        height = boxHeight;
        Random random = new Random();
        pos = new double[]{random.nextDouble()*length,random.nextDouble()*height};
    }

    public double[] updateStatus() {
        move();
        return Arrays.copyOf(this.pos, 2); // return new position after moving
    } // per second

    /**
     * 
     * @param pos old position
     */
    public void move() {
        for (int i=0;i<2;i++) {
            pos[i]+=velocity[i];
        }
    }

    public void addPos(double[] position) {
        trajectoryMap.add(position);
    }

    public void printTraj() {
        for (int i=0;i<trajectoryMap.size();i++) {
            System.out.println(trajectoryMap.poll()[0]);
        }
    }
}