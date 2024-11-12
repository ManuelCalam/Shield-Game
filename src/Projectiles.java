import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Projectiles extends JPanel {
    ImageIcon imagenFondo = new ImageIcon("src/img/Projectile.png");
    Image imagen = imagenFondo.getImage();

    public int grades = 270;
    int posX = 451, posY = 303;

    public Projectiles() {
        setOpaque(false);  
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.translate(posX, posY);
        g2d.rotate(Math.toRadians(grades));
        g2d.drawImage(imagen, 0, 0, 30, 50, this);
    }

    public void getProjectilePos(int posX, int posY, int grades) {
        this.posX = posX;
        this.posY = posY;
        this.grades = grades;
        repaint();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(800, 600);
    }
}
