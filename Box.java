import java.util.*;

public class Box {
    public double length;
    public double height;
    public Integer timeSec;
    public Integer numElectron;
    public Integer numConfetti;
    public ArrayList<Electron> electrons = new ArrayList<>();
    public ArrayList<Confetti> confettis = new ArrayList<>();
    public ArrayList<Confetti> deadConfettis = new ArrayList<>();
    public SortBox[] particleDistribution;
    public String[][] box = new String[23][23];


    public ArrayList<double[]> hello = new ArrayList<>();

    public Box(double boxLength, double boxHeight, Integer boxTime, Integer electron, Integer confetti) {
        length = boxLength;
        height = boxHeight;
        timeSec = boxTime;
        numElectron = electron;
        numConfetti = confetti;

        for (int i=0;i<numElectron;i++) {
            electrons.add(new Electron(length, height));
        }
        for (int k=0;k<numConfetti;k++) {
            Random random = new Random();
            int lifespan = random.nextInt(timeSec*2);
            confettis.add(new Confetti(length, height, lifespan));
        }
    }

    class SortBox implements Comparable<SortBox> {
        private int boxPos;
        private int numParticles;

        SortBox(int boxIndex, int numPar) {
            boxPos = boxIndex;
            numParticles = numPar;
        }

        @Override
        public int compareTo(SortBox otherBox) {
            // descending order
            return -1*Integer.valueOf(numParticles).compareTo(otherBox.numParticles);
        }
    }

    public void simulate() {
        while (timeSec!=0) {
            System.out.println(timeSec);
            for (int i=0;i<electrons.size();i++) {
                double[] position = electrons.get(i).updateStatus();
                electrons.get(i).addPos(position);



                hello.add(position);

                for (int z =0; z < hello.size(); z++)
                {
                    System.out.println(hello.get(z)[0]);
                }
                System.out.println(Arrays.toString(position));
            }

            for (int k=0;k<confettis.size();k++) {
                double[] pos = confettis.get(k).updateStatus();
                confettis.get(k).addPos(pos);
                // if (confettis.get(k).isDead()) {
                //     deadConfettis.add(confettis.remove(k));
                // }
                // System.out.println(Arrays.toString(pos));
            }


            timeSec-=1;
        }
        //electrons.get(1).printTraj();
    }

    public void distribution(int numPartition) {
        simulate();
        double firstWall = length/numPartition;
        particleDistribution = new SortBox[numPartition];

        for (int i=0;i<numPartition;i++) {
            particleDistribution[i] = new SortBox(i, 0);
        }
        // electrons
        for (int i=0;i<electrons.size();i++) {
            double xPos = electrons.get(i).pos[0];
            double divide = xPos/firstWall;
            int boxPartition = (int)divide;
            particleDistribution[boxPartition].numParticles+=1;
        }

        for (int k=0;k<confettis.size();k++) {
            double xPos = confettis.get(k).pos[0];
            double divide = xPos/firstWall;
            int boxPartition = (int)divide;
            particleDistribution[boxPartition].numParticles+=1;
        }

        Arrays.sort(particleDistribution);
        double leftWall = particleDistribution[0].boxPos*firstWall; double rightWall = (particleDistribution[0].boxPos+1)*firstWall;

        System.out.println("Box " + (particleDistribution[0].boxPos+1) + " (from " + leftWall + " to " + rightWall + ") has the highest particle density of " + particleDistribution[0].numParticles + " particles.");
        System.out.println("Unfortunately " + deadConfettis.size() + " confettis have left us.");
    }

    public void drawBox() {
        for (int i=0;i<23;i++) {
            for (int j =0;j<23;j++){
                box[i][j] = " ";
            }
        }

        box[0][0] = "*";
        box[0][22] = "*";
        box[22][0] ="*";
        box[22][22] ="*";

        for (int i=1;i<22;i++) {
            if (i%2==1) {
                box[i][0] = "+";
                box[i][22] = "+";
            }

            else {
                box[i][0] = "|";
                box[i][22] = "|";
            }
        }

        for (int j=1;j<22;j++) {
            if (j % 2 == 1){
                box[0][j] = "+"; 
                box[22][j] = "+";
            }
            else {
                box[0][j] = "-";
                box[22][j] = "-";
            }
        }

        while (electrons.get(0).trajectoryMap.size()!=0) {
            double[] currentPos = electrons.get(0).trajectoryMap.poll();
            double xPosd = currentPos[0]*2+1;
            int xPos = (int) xPosd;
            System.out.println(xPos);
            double yPosd = currentPos[1]*2+1;
            int yPos = (int) yPosd;
            box[xPos][yPos] = ".";
        }
        

        while (confettis.get(0).trajectoryMap.size()!=0) {
            double[] currentPos = confettis.get(0).trajectoryMap.poll();
            double xPosd = currentPos[0]*2+1;
            int xPos = (int) xPosd;
            System.out.println(xPos);
            double yPosd = currentPos[1]*2+1;
            int yPos = (int) yPosd;
            box[xPos][yPos] = "&";
        }

        for (int i=0;i<23;i++) {
            for (int j=0;j<23;j++) {
                System.out.print(box[i][j]);
            }
            System.out.print("\n");
        }
    }

    public static void main(String[] args) {
        Box box = new Box(10.0, 10.0, 50, 1, 1);
        box.distribution(4);
        box.drawBox();
    }
}
