
import javax.swing.JPanel;

public class Animation extends Thread{
    public Projectiles projectile;
    public JPanel fondo;
    public int direction = 0;

    public int posX = 0, posY = 0;
    public int posFinal = 0;

    AudioPlayer soundEffects = new AudioPlayer();
    String BellPath = "src/songs/bell.wav";
    String HitPath= "src/songs/hit.wav";

    public Animation(Projectiles projectile, int direction, JPanel fondo){
        this.projectile = projectile;
        this.direction = direction;
        this.fondo = fondo;

        switch (direction) {
            case 1 -> {
                posX = 412;
                posY = 20;
                posFinal = 288;
            }
            case 2 -> {
                posX = 382;
                posY = 540;
                posFinal = 290;
            }
            case 3 -> {
                posX = 30;
                posY = 272;
                posFinal = 397;
            }
            case 4 -> {
                posX = 770;
                posY = 303;
                posFinal = 398;
            }
            default -> throw new AssertionError();
        }
    }

    public void setProjectilePos(){
        switch (direction) {
            case 1 -> posY += 1;
            case 2 -> posY -=1;
            case 3 -> posX += 1;
            case 4 -> posX -= 1;
            default -> throw new AssertionError();
        }
    }

    private void removeProjectile() {
        fondo.remove(projectile); 
        fondo.repaint(); 
    }

    @Override
    public void run(){
        if(direction == 1){
            while(posY <= posFinal+1){
                if(GameField.shieldGrade == 0 && (posY >= 235 && posY <= 245)){
                    removeProjectile();
                    //soundEffects.playSound(BellPath);
                    break;
                }
                if((GameField.shieldGrade != 0 && posY >= 288) || (GameField.shieldGrade == 0 && posY >= 288)){
                    damageHeartOnHit();
                    break;
                }
                setProjectilePos();
                projectile.getProjectilePos(posX, posY, 180);
                try {
                    sleep(1);
                } catch (InterruptedException e) {
                    // TODO: handle exception
                }
            }
        }
        if(direction == 2){
            while(posY >= posFinal){
                if(GameField.shieldGrade == 180 && (posY >= 333 && posY <= 343)){
                    removeProjectile();
                    //soundEffects.playSound(BellPath);
                    break;
                }
                if((GameField.shieldGrade != 180 && posY <= 290) || (GameField.shieldGrade == 180 && posY <= 290)){
                    damageHeartOnHit();
                    break;
                }
                setProjectilePos();
                projectile.getProjectilePos(posX, posY, 0);
                try {
                    sleep(1);
                } catch (InterruptedException e) {
                    // TODO: handle exception
                }
            }
        }
        if(direction == 3){
            while(posX <= posFinal){
                if(GameField.shieldGrade == 270 && (posX >= 354 && posX <= 364)){
                    removeProjectile();
                    //soundEffects.playSound(BellPath);
                    break;
                }
                if((GameField.shieldGrade != 270 && posX >= 397) || (GameField.shieldGrade == 270 && posX >= 397)){
                    damageHeartOnHit();
                    break;
                }
                setProjectilePos();
                projectile.getProjectilePos(posX, posY, 90);
    
                try {
                    sleep(1);
                } catch (InterruptedException e) {
                    // TODO: handle exception
                }
            }
        }
        if(direction == 4){
            while(posX >= posFinal){
                if(GameField.shieldGrade == 90 && (posX >= 441 && posX <= 451)){
                    removeProjectile();
                    //soundEffects.playSound(BellPath);
                    break;
                }
                if((GameField.shieldGrade != 90 && posX <= 398) || (GameField.shieldGrade == 90 && posX <= 398)){
                    damageHeartOnHit();
                    break;
                }
                setProjectilePos();
                projectile.getProjectilePos(posX, posY, 270);
    
                try {
                    sleep(1);
                } catch (InterruptedException e) {
                    // TODO: handle exception
                }
            }
        }
    }

    private void damageHeartOnHit() {
        GameField.vida--;
        ((GameField) fondo.getTopLevelAncestor()).damageHeart();
        //soundEffects.playSound(HitPath);
        removeProjectile();
    }
}
