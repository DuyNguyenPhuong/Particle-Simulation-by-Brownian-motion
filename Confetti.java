import java.util.*;

public class Confetti extends Particles {
    public Integer lifespan;  /** confetti has a lifespan */

    public Confetti(Double length, Double height, Integer lifespan) {
        super(length, height);
        this.lifespan = lifespan;
    }

    @Override
    public void move() {
        Random random = new Random();
        /** random posittion confined within the box
         * can be interpreted as random velocity and random acceleration
         */
        pos = new double[]{random.nextDouble()*length,random.nextDouble()*height};
    }

    @Override
    public double[] updateStatus() {
        move();
        lifespan-=1;
        return Arrays.copyOf(this.pos, 2);
    }
    
    public boolean isDead() {
        if (lifespan<=0) {
            return true;
        }
        return false;
    }
}