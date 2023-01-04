package viceCity.models.guns;

public class Pistol extends BaseGun {
    private static final int DEFAULT_BULLETS_PER_BARREL = 10;
    private static final int DEFAULT_TOTAL_BULLETS = 100;
    private static final int BULLETS_PER_SHOT = 1;

    private int currentBullets;

    public Pistol(String name) {
        super(name, DEFAULT_BULLETS_PER_BARREL, DEFAULT_TOTAL_BULLETS);
    }

    @Override
    public int fire() {
        if (this.currentBullets == 0) {
            this.currentBullets = super.reload();

            if (currentBullets == 0) {
                super.canFire = false;
                return 0;
            }
        }

        this.currentBullets -= BULLETS_PER_SHOT;

        return BULLETS_PER_SHOT;
    }
}
