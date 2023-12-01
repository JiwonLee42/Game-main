package client.main.object;

<<<<<<< HEAD
import java.awt.*;
=======
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
>>>>>>> 5a91dc5492dcad1ae48ae475fc99a26f8e3b1d58

public class PlanetNode extends Unit {
    private int id;
    private int coin; // 해당 행성에 할당되는 코인 수
    private boolean sun = false; // 해당 노드 태양 생성 여부
    private String name;
    private BufferedImage image; // 이미지 추가

<<<<<<< HEAD
    public PlanetNode(int id, int x, int y, Image img) {
        this.id = id;
        this.posX = x;
        this.posY = y;
        this.img = img;
    }

    public PlanetNode(int id, int coin) { // 노드마다 지정된 코인 수가 존재함 -> 생성할 때 설정
=======
    public PlanetNode(int id, int coin, String imagePath) {
>>>>>>> 5a91dc5492dcad1ae48ae475fc99a26f8e3b1d58
        this.id = id;
        this.coin = coin;
        try {
            this.image = ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getId() {
        return id;
    }

    public void setSun(boolean change) {
        sun = true;
    }

    public boolean isSun() {
        return sun;
    }

    public void draw(Graphics g, int x, int y, int width, int height) {
        g.drawImage(image, x, y, width, height, null);
    }
}
