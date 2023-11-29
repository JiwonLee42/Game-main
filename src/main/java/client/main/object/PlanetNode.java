package client.main.object;

import java.util.Random;

public class PlanetNode extends Unit{
    private int id;
    private int coin; //해당 행성에 할당되는 코인 수
    private int sun; //해당 행성에 할당되는 태양 수
    private String name;

    public PlanetNode(int id) {
        this.id = id;
        Random random = new Random();

        if((Math.random()) <= 0.7) { // 70%의 확률로
            this.sun = 0;
            this.coin = (random.nextInt(5) + 1) * 10; // 10 ~ 50사이의 10의 배수인 정수값 할당
        }

        else { // 30%의 확률로
            this.coin = 0;
            this.sun = 1;
        }
    }
}
