package viceCity.models.guns;

import static viceCity.common.ExceptionMessages.*;

public abstract class BaseGun implements Gun {
    private String name;
    private int bulletsPerBarrel;
    private int totalBullets;
    protected boolean canFire;

    protected BaseGun(String name, int bulletsPerBarrel, int totalBullets) {
        this.setName(name);
        this.setBulletsPerBarrel(bulletsPerBarrel);
        this.setTotalBullets(totalBullets);
        this.canFire = true;
    }

    private void setName(String name) {
        validateName(name);
        this.name = name;
    }

    private void setBulletsPerBarrel(int bulletsPerBarrel) {
        validateBullets(bulletsPerBarrel);
        this.bulletsPerBarrel = bulletsPerBarrel;
    }

    protected void setTotalBullets(int totalBullets) {
        validateTotalBullets(totalBullets);
        this.totalBullets = totalBullets;
    }

    private void validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new NullPointerException(NAME_NULL);
        }
    }

    private void validateBullets(int bullets) {
        if (bullets < 0) {
            throw new IllegalArgumentException(BULLETS_LESS_THAN_ZERO);
        }
    }

    private void validateTotalBullets(int totalBullets) {
        if (totalBullets < 0) {
            throw new IllegalArgumentException(TOTAL_BULLETS_LESS_THAN_ZERO);
        }
    }

    protected int reload() {
        int relatedBullets = this.totalBullets - this.bulletsPerBarrel;
        if (relatedBullets >= 0) {
            this.setTotalBullets(relatedBullets);
            return this.bulletsPerBarrel;
        }

        return 0;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getBulletsPerBarrel() {
        return this.bulletsPerBarrel;
    }

    @Override
    public int getTotalBullets() {
        return this.totalBullets;
    }

    @Override
    public boolean canFire() {
        return this.canFire;
    }
}
