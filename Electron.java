import java.util.*;

public class Electron extends Particles {
    public double angle;
    private double[] ans = new double[4];
    private double[] currentPos = new double[2];

    public Electron(Double length, Double height) {
        super(length, height);
        Random random = new Random();

        // Angle
        angle = Math.toRadians(random.nextDouble()*360);
        
        
        // Velocity
        double x_comp = random.nextDouble()*(length/10);
        velocity = new double[]{x_comp,x_comp*(Math.tan(x_comp))};
        if (velocity[0] < 0.5 && velocity[1] < 0.5){
            velocity[0]+= 0.5;
            velocity[1]+= 0.5;
        }
    }

    @Override
    public void move() {
        for (int i=0;i<2;i++) {
            currentPos[i]+=velocity[i];
        } // supposed position, colllision not accounted
        ans = new double[]{currentPos[0], currentPos[1], velocity[0], velocity[1]};
        double[] posVel = checkNewPosition(currentPos);
        pos[0] = posVel[0];
        pos[1] = posVel[1];
        velocity[0] = posVel[2];
        velocity[1] = posVel[3];
    }
    
    private double[] checkNewPosition(double[] outPos){
        if (outPos[0] >= 0 && outPos[0] <= length && outPos[1] >=0 && outPos[1] <= height){
            return ans;
        }
        // collision with top wall but not corner
        if (outPos[1]>=height) {
            // new pos, reflected back, no force+acceleration=reflect distance is distance supposed to travel outside of box
            outPos[0] = outPos[0];
            outPos[1] = height-(outPos[1]-height); // update position
            ans = new double[]{outPos[0],outPos[1],velocity[0],-velocity[1]};
            checkNewPosition(outPos);
        }

        // collision with bottom wall but not corner        
        else if (outPos[1]<=0) {
            // new pos, reflected back, no force+acceleration=reflect distance is distance supposed to travel outside of box
            outPos[0] = outPos[0];
            outPos[1] = -outPos[1]; // updated position
            ans = new double[]{outPos[0],outPos[1],velocity[0],-velocity[1]};
            checkNewPosition(outPos);
        }

        // collision with right wall but not corner
        else if (outPos[0]>= length) {
            // new pos, reflected back, no force+acceleration=reflect distance is distance supposed to travel outside of box
            outPos[0] = length-(outPos[0]-length);
            outPos[1] = outPos[1]; // updated position
            ans = new double[]{outPos[0],outPos[1],-velocity[0],velocity[1]};           
            checkNewPosition(outPos);
        }

        // collision with left wall but not corner
        else if (outPos[0] <= 0) {
            // new pos, reflected back, no force+acceleration=reflect distance is distance supposed to travel outside of box
            outPos[0] = -outPos[0];
            outPos[1] = outPos[1]; // updated position
            ans = new double[]{outPos[0],outPos[1],-velocity[0],velocity[1]};
            checkNewPosition(outPos);
        }
        return ans;
    }

    public static void main(String[] args) {
        Electron electron = new Electron(6.0, 6.0);
        electron.updateStatus();
        System.out.println(Arrays.toString(electron.updateStatus()));
    }
}
