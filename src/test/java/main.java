import org.apache.commons.lang3.RandomUtils;

public class main {

    public static void main(String[] args) {
        do {
            double randomInt =  RandomUtils.nextDouble(0.000000000000000,100.000000000000000);

            System.out.println(randomInt <= 60 + 10.243365596046448);
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }while (true);
    }
}
