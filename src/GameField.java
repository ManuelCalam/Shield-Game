import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GameField extends JFrame{
    public ImageIcon imagenFondo = new ImageIcon("src/img/shield.png");
    public Image imagen = imagenFondo.getImage();
    private Random random = new Random();
    
    AudioPlayer mainSong = new AudioPlayer();
    String filepath = "src/songs/Run.wav";

    private boolean heartDamaged = false;
    public static volatile int vida = 10;
    public int x = 262;
    public int y = 157;
    public static int shieldGrade = 0;

    public GameField(){
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        

        JPanel fondo = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                //Gradiente arriba
                g.setColor(new Color(0x148c74));
                Graphics2D g2d = (Graphics2D) g;

                GradientPaint degradado = new GradientPaint(
                        0, 0, new Color(0x77148c74, true),
                        0, 100, Color.black, false
                );

                g2d.setPaint(degradado);
                g2d.fillRect(0, 0, getWidth(), 100);

                //Gradiente abajo
                GradientPaint degradado2 = new GradientPaint(
                        0, 475, Color.BLACK,
                        0, 600, new Color(0x77148c74, true), false
                );

                g2d.setPaint(degradado2);
                g2d.fillRect(0, 475, getWidth(), 100);

                //Lineas de enmedio
                g.setColor(new Color(0x148c74));
                g.drawRect(0, 285, getWidth(), 5);               

                //fondo
                //Abajo
                g.fillRect(0, 455, getWidth(), 4);
                g.fillRect(0, 410, getWidth(), 3);
                g.fillRect(0, 383, getWidth(), 3);
                g.fillRect(0, 368, getWidth(),1);
                g.fillRect(0, 355, getWidth(),1);
                g.setColor(new Color(0x042d2b));
                g.fillRect(0, 340, getWidth(),1);

                g.setColor(new Color(0x148c74));
                g.fillRect(395, 340, 5, 300);

                //Izquierda
                g.drawLine(180, 600, 340, 340);
                g.drawLine(-35, 600, 285, 340);
                g.drawLine(-250, 600, 230, 340);
                g.drawLine(-465, 600, 175, 340);
                g.drawLine(-680, 600, 120, 340);
                g.drawLine(-895, 600, 65, 340);

                //Derecha
                g.drawLine(610, 600, 450, 340);
                g.drawLine(825, 600, 505, 340);
                g.drawLine(1040, 600, 560, 340);
                g.drawLine(1255, 600, 615, 340);
                g.drawLine(1470, 600, 670, 340);
                g.drawLine(1685, 600, 725, 340);

                
                //Arriba
                g.setColor(new Color(0x042d2b));
                g.fillRect(0, 240, getWidth(),1);
                g.setColor(new Color(0x148c74));
                g.fillRect(0, 225, getWidth(),1);
                g.fillRect(0, 212, getWidth(),1);
                g.fillRect(0, 197, getWidth(), 3);
                g.fillRect(0, 170, getWidth(), 3);
                g.fillRect(0, 125, getWidth(), 4);

                g.fillRect(395, 0, 5, 240);

                //Izquierda
                g.drawLine(180, 0, 340, 240);
                g.drawLine(-35, 0, 285, 240);
                g.drawLine(-250, 0, 230, 240);
                g.drawLine(-465, 0, 175, 240);
                g.drawLine(-680, 0, 120, 240);
                g.drawLine(-895, 0, 65, 240);

                 //Derecha
                 g.drawLine(610, 0, 450, 240);
                 g.drawLine(825, 0, 505, 240);
                 g.drawLine(1040, 0, 560, 240);
                 g.drawLine(1255, 0, 615, 240);
                 g.drawLine(1470, 0, 670, 240);
                 g.drawLine(1685, 0, 725, 240);



                 //Recuadro
                g.setColor(Color.white);
                g.fillRect(353, 243, 90, 90);
                g.setColor(Color.black);
                g.fillRect(358, 248, 80, 80);

                drawHeart(g, 397, 280);

                AffineTransform originalTransform = g2d.getTransform();

                // Aplicar transformaciones solo para la imagen que responde al teclado
                AffineTransform transform = new AffineTransform();
                transform.rotate(Math.toRadians(shieldGrade), 500, 500);  
                transform.translate(x, y);  
                g2d.setTransform(transform);
                g2d.drawImage(imagen, x, y, 150, 105, this);

                // Restaurar el estado gráfico original para no afectar otros elementos
                g2d.setTransform(originalTransform);

                int anchoVida = (int) ((vida / (float) 10) * 100); // Ancho proporcional a la vida

                g.setColor(Color.black);
                g.fillRect(10, 10, 110, 30);
                g.setColor(new Color(0xFF7D00));
                g.fillRect(15, 15, anchoVida, 20);
            } 
        };

        fondo.setBackground(new Color(0x040414));
        fondo.setFocusable(true); 
        fondo.addKeyListener(new KeyAdapter(){
            @Override
            public void keyPressed(KeyEvent e){
                int keyCode = e.getKeyCode();
                switch (keyCode) {
                    case KeyEvent.VK_W -> { 
                        x = 262;
                        y = 157;
                        shieldGrade = 0;
                    }
                    case KeyEvent.VK_S -> {
                        x = 163;
                        y = 224;
                        shieldGrade = 180;
                    }
                    case KeyEvent.VK_A -> {
                        x = 246;
                        y = 240;
                        shieldGrade = 270;
                    }
                    case KeyEvent.VK_D -> {
                        x = 178;
                        y = 141;
                        shieldGrade = 90;
                    }
                    default -> {
                    }

                }
                
                repaint();
            }
        });


        setLayout(null);
        setContentPane(fondo);  
        fondo.setBounds(0, 0, 800, 600);

        fondo.requestFocusInWindow();  
        setVisible(true);
        //mainSong.playSound(filepath);        

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        for(int i = 0; i<30; i++){
            if(vida < 1){
                JOptionPane.showMessageDialog(null, "Has perdido");
                break;
            }


            int direction = random.nextInt(4)+1; 
            Projectiles shoot = new Projectiles();
            Animation animation = new Animation(shoot, direction, fondo);
            
            shoot.setBounds(0, 0, 800, 600); 
            shoot.setOpaque(false);  
            fondo.add(shoot); 

            animation.start();
            
            

            try{
                Thread.sleep(380); //450
            }catch(InterruptedException e){

            }

            if (i>=29) {
                JOptionPane.showMessageDialog(null, "Has GANADO");
            }
        }
    
    }

    private void drawHeart(Graphics g, int x, int y) {
        g.setColor(heartDamaged ? Color.RED : new Color(0xFF7D00));
        g.fillArc(x, y, 10, 10, 0, 180);   
        g.fillArc(x - 10, y, 10, 10, 0, 180); 
    
        int[] xPoints = {x - 10, x + 10, x}; 
        int[] yPoints = {y + 5, y + 5, y + 15};
        g.fillPolygon(xPoints, yPoints, 3);
    }

    public void damageHeart() {
        heartDamaged = true;
        repaint();
    
        Timer timer = new Timer(100, e -> { 
            heartDamaged = false;
            repaint();
        });
        timer.setRepeats(false);
        timer.start();
    }


}
