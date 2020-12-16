import java.awt.*;
import java.awt.image.BufferedImage;

public class Images {

    /**
     * Saves all needed images as static fields in Runtime...
     */

    private static BufferedImage TANK_1;
    private static BufferedImage TANK_1_S;
    private static BufferedImage TANK_2;
    private static BufferedImage TANK_3;
    private static BufferedImage TANK_4;
    private static BufferedImage TANK_SHIELD_1;
    private static BufferedImage TANK_SHIELD_2;
    private static BufferedImage TANK_SHIELD_3;
    private static BufferedImage TANK_SHIELD_4;
    private static BufferedImage AIM;
    private static BufferedImage SOIL;
    private static BufferedImage CANNON_BULLET;
    private static BufferedImage CANNON_BULLET_LOGO;
    private static BufferedImage CANNON_CRATE;
    private static BufferedImage CANNON_CRATE_S;
    private static BufferedImage STATIC_ENEMY;
    private static BufferedImage STATIC_ENEMY_S;
    private static BufferedImage WALL_ENEMY_H;
    private static BufferedImage WALL_ENEMY_V;
    private static BufferedImage HARD_WALL;
    private static BufferedImage HARD_WALL_S;
    private static BufferedImage HEALTH;
    private static BufferedImage MAIN_MENU;
    private static BufferedImage MAIN_MENU_2;
    private static BufferedImage ESCAPE_MENU;
    private static BufferedImage END_MENU_1;
    private static BufferedImage END_MENU_2;
    private static BufferedImage MACHINE_GUN_BULLET;
    private static BufferedImage MACHINE_GUN_BULLET_LOGO;
    private static BufferedImage MACHINE_GUN_CRATE;
    private static BufferedImage MACHINE_GUN_CRATE_S;
    private static BufferedImage MINE;
    private static BufferedImage MINE_S;
    private static BufferedImage PLANT1;
    private static BufferedImage PLANT2;
    private static BufferedImage PLANT3;
    private static BufferedImage REPAIR_CRATE;
    private static BufferedImage REPAIR_CRATE_S;
    private static BufferedImage SOFT_WALL_1;
    private static BufferedImage SOFT_WALL_1_S;
    private static BufferedImage SOFT_WALL_2;
    private static BufferedImage SOFT_WALL_3;
    private static BufferedImage SOFT_WALL_4;
    private static BufferedImage GUN_1;
    private static BufferedImage GUN_2;
    private static BufferedImage GUN_3;
    private static BufferedImage GUN_4;
    private static BufferedImage GUN_5;
    private static BufferedImage GUN_6;
    private static BufferedImage BOMB;
    private static BufferedImage POTION;
    private static BufferedImage TEAZEL;
    private static BufferedImage TEAZEL_S;
    private static BufferedImage KEY;
    private static BufferedImage KEY_S;
    private static BufferedImage UPGRADE_STAR;
    private static BufferedImage UPGRADE_STAR_S;
    private static Image BUG_ENEMY;
    private static Image BUG_ENEMY_S;
    private static Image TANK_ENEMY;
    private static Image TANK_ENEMY_S;
    private static Image MACHINE_GUN_ENEMY;
    private static Image EXPLOSION;
    private static Image TANK_ANIM_1;
    private static Image TANK_ANIM_2;
    private static Image TANK_ANIM_3;
    private static Image TANK_ANIM_4;
    private static Image TANK_SHIELD_ANIM_1;
    private static Image TANK_SHIELD_ANIM_2;
    private static Image TANK_SHIELD_ANIM_3;
    private static Image TANK_SHIELD_ANIM_4;

    public static void importImages() {
        BufferedImageLoader bufferedImageLoader = new BufferedImageLoader();

        TANK_1 = bufferedImageLoader.loadImage("tank1.png");
        TANK_1_S = bufferedImageLoader.loadImage("tank1_s.png");
        TANK_2 = bufferedImageLoader.loadImage("tank2.png");
        TANK_3 = bufferedImageLoader.loadImage("tank3.png");
        TANK_4 = bufferedImageLoader.loadImage("tank4.png");
        TANK_SHIELD_1 = bufferedImageLoader.loadImage("tankShield1.png");
        TANK_SHIELD_2 = bufferedImageLoader.loadImage("tankShield2.png");
        TANK_SHIELD_3 = bufferedImageLoader.loadImage("tankShield3.png");
        TANK_SHIELD_4 = bufferedImageLoader.loadImage("tankShield4.png");
        AIM = bufferedImageLoader.loadImage("aim.png");
        SOFT_WALL_1 = bufferedImageLoader.loadImage("softWall1.png");
        SOFT_WALL_1_S = bufferedImageLoader.loadImage("softWall1_s.png");
        SOFT_WALL_2 = bufferedImageLoader.loadImage("softWall2.png");
        SOFT_WALL_3 = bufferedImageLoader.loadImage("softWall3.png");
        SOFT_WALL_4 = bufferedImageLoader.loadImage("softWall4.png");
        SOIL = bufferedImageLoader.loadImage("soil.png");
        GUN_1 = bufferedImageLoader.loadImage("tankGun1.png");
        GUN_2 = bufferedImageLoader.loadImage("tankGun2.png");
        GUN_3 = bufferedImageLoader.loadImage("tankGun3.png");
        GUN_4 = bufferedImageLoader.loadImage("tankGun4.png");
        GUN_5 = bufferedImageLoader.loadImage("tankGun5.png");
        GUN_6 = bufferedImageLoader.loadImage("tankGun6.png");
        POTION = bufferedImageLoader.loadImage("potion.png");
        MAIN_MENU = bufferedImageLoader.loadImage("mainMenu.png");
        ESCAPE_MENU = bufferedImageLoader.loadImage("escapeMenu.png");
        END_MENU_1 = bufferedImageLoader.loadImage("endMenu1.png");
        END_MENU_2 = bufferedImageLoader.loadImage("endMenu2.png");
        CANNON_BULLET = bufferedImageLoader.loadImage("cannonBullet.png");
        CANNON_BULLET_LOGO = bufferedImageLoader.loadImage("cannonBulletLogo.png");
        CANNON_CRATE = bufferedImageLoader.loadImage("cannonCrate.png");
        CANNON_CRATE_S = bufferedImageLoader.loadImage("cannonCrate_s.png");
        STATIC_ENEMY = bufferedImageLoader.loadImage("staticEnemy.png");
        STATIC_ENEMY_S = bufferedImageLoader.loadImage("staticEnemy_s.png");
        MACHINE_GUN_BULLET = bufferedImageLoader.loadImage("machineGunBullet.png");
        MACHINE_GUN_BULLET_LOGO = bufferedImageLoader.loadImage("machineGunBulletLogo.png");
        MACHINE_GUN_CRATE = bufferedImageLoader.loadImage("machineGunCrate.png");
        MACHINE_GUN_CRATE_S = bufferedImageLoader.loadImage("machineGunCrate_s.png");
        REPAIR_CRATE = bufferedImageLoader.loadImage("repairCrate.png");
        REPAIR_CRATE_S = bufferedImageLoader.loadImage("repairCrate_s.png");
        HEALTH = bufferedImageLoader.loadImage("health.png");
        HARD_WALL = bufferedImageLoader.loadImage("hardWall.png");
        HARD_WALL_S = bufferedImageLoader.loadImage("hardWall_s.png");
        MINE = bufferedImageLoader.loadImage("mine.png");
        MINE_S = bufferedImageLoader.loadImage("mine_s.png");
        PLANT1 = bufferedImageLoader.loadImage("plant1.png");
        PLANT2 = bufferedImageLoader.loadImage("plant2.png");
        PLANT3 = bufferedImageLoader.loadImage("plant3.png");
        BOMB = bufferedImageLoader.loadImage("bomb.png");
        TEAZEL = bufferedImageLoader.loadImage("teazel.png");
        TEAZEL_S = bufferedImageLoader.loadImage("teazel_s.png");
        KEY = bufferedImageLoader.loadImage("key.png");
        KEY_S = bufferedImageLoader.loadImage("key_s.png");
        UPGRADE_STAR = bufferedImageLoader.loadImage("upgrade.png");
        UPGRADE_STAR_S = bufferedImageLoader.loadImage("upgrade_s.png");
        WALL_ENEMY_H = bufferedImageLoader.loadImage("wallEnemy_H.png");
        WALL_ENEMY_V = bufferedImageLoader.loadImage("wallEnemy_V.png");
        BUG_ENEMY = bufferedImageLoader.loadGif("bugEnemy.gif");
        BUG_ENEMY_S = bufferedImageLoader.loadGif("bugEnemy_s.gif");
        TANK_ENEMY = bufferedImageLoader.loadGif("tankEnemy.gif");
        TANK_ENEMY_S = bufferedImageLoader.loadGif("tankEnemy_s.gif");
        MACHINE_GUN_ENEMY = bufferedImageLoader.loadGif("machineGunEnemy.gif");
        MAIN_MENU = bufferedImageLoader.loadImage("mainMenu.png");
        MAIN_MENU_2 = bufferedImageLoader.loadImage("mainMenu2.png");
        ESCAPE_MENU = bufferedImageLoader.loadImage("escapeMenu.png");
        EXPLOSION = bufferedImageLoader.loadGif("explosion.gif");
        TANK_ANIM_1 = bufferedImageLoader.loadGif("tankAnim1.gif");
        TANK_ANIM_2 = bufferedImageLoader.loadGif("tankAnim2.gif");
        TANK_ANIM_3 = bufferedImageLoader.loadGif("tankAnim3.gif");
        TANK_ANIM_4 = bufferedImageLoader.loadGif("tankAnim4.gif");
        TANK_SHIELD_ANIM_1 = bufferedImageLoader.loadGif("tankAnimShield1.gif");
        TANK_SHIELD_ANIM_2 = bufferedImageLoader.loadGif("tankAnimShield2.gif");
        TANK_SHIELD_ANIM_3 = bufferedImageLoader.loadGif("tankAnimShield3.gif");
        TANK_SHIELD_ANIM_4 = bufferedImageLoader.loadGif("tankAnimShield4.gif");
    }

    public static BufferedImage getTank1() {
        return TANK_1;
    }

    public static BufferedImage getTank2() {
        return TANK_2;
    }

    public static BufferedImage getTank3() {
        return TANK_3;
    }

    public static BufferedImage getTank4() {
        return TANK_4;
    }

    public static BufferedImage getAIM() {
        return AIM;
    }

    public static BufferedImage getWallEnemyH() {
        return WALL_ENEMY_H;
    }

    public static BufferedImage getWallEnemyV() {
        return WALL_ENEMY_V;
    }

    public static BufferedImage getStaticEnemy() {
        return STATIC_ENEMY;
    }

    public static BufferedImage getPOTION() {
        return POTION;
    }

    public static BufferedImage getBOMB() {
        return BOMB;
    }

    public static BufferedImage getSOIL() {
        return SOIL;
    }

    public static BufferedImage getCannonBullet() {
        return CANNON_BULLET;
    }

    public static BufferedImage getCannonBulletLogo() {
        return CANNON_BULLET_LOGO;
    }

    public static BufferedImage getCannonCrate() {
        return CANNON_CRATE;
    }

    public static BufferedImage getUpgradeStar() {
        return UPGRADE_STAR;
    }

    public static BufferedImage getHardWall() {
        return HARD_WALL;
    }

    public static BufferedImage getHEALTH() {
        return HEALTH;
    }

    public static BufferedImage getMachineGunBullet() {
        return MACHINE_GUN_BULLET;
    }

    public static BufferedImage getMachineGunBulletLogo() {
        return MACHINE_GUN_BULLET_LOGO;
    }

    public static BufferedImage getMachineGunCrate() {
        return MACHINE_GUN_CRATE;
    }

    public static BufferedImage getMINE() {
        return MINE;
    }

    public static BufferedImage getPLANT1() {
        return PLANT1;
    }

    public static BufferedImage getPLANT2() {
        return PLANT2;
    }

    public static BufferedImage getPLANT3() {
        return PLANT3;
    }

    public static BufferedImage getRepairCrate() {
        return REPAIR_CRATE;
    }

    public static BufferedImage getSoftWall1() {
        return SOFT_WALL_1;
    }

    public static BufferedImage getSoftWall2() {
        return SOFT_WALL_2;
    }

    public static BufferedImage getSoftWall3() {
        return SOFT_WALL_3;
    }

    public static BufferedImage getSoftWall4() {
        return SOFT_WALL_4;
    }

    public static BufferedImage getGun1() {
        return GUN_1;
    }

    public static BufferedImage getGun2() {
        return GUN_2;
    }

    public static BufferedImage getGun3() {
        return GUN_3;
    }

    public static BufferedImage getGun4() {
        return GUN_4;
    }

    public static BufferedImage getGun5() {
        return GUN_5;
    }

    public static BufferedImage getGun6() {
        return GUN_6;
    }

    public static BufferedImage getTEAZEL() {
        return TEAZEL;
    }

    public static BufferedImage getKEY() {
        return KEY;
    }

    public static Image getBugEnemy() {
        return BUG_ENEMY;
    }

    public static Image getTankEnemy() {
        return TANK_ENEMY;
    }

    public static BufferedImage getTank1S() {
        return TANK_1_S;
    }

    public static BufferedImage getCannonCrateS() {
        return CANNON_CRATE_S;
    }

    public static BufferedImage getStaticEnemyS() {
        return STATIC_ENEMY_S;
    }

    public static BufferedImage getHardWallS() {
        return HARD_WALL_S;
    }

    public static BufferedImage getMachineGunCrateS() {
        return MACHINE_GUN_CRATE_S;
    }

    public static BufferedImage getMineS() {
        return MINE_S;
    }

    public static BufferedImage getRepairCrateS() {
        return REPAIR_CRATE_S;
    }

    public static BufferedImage getSoftWall1S() {
        return SOFT_WALL_1_S;
    }

    public static BufferedImage getTeazelS() {
        return TEAZEL_S;
    }

    public static BufferedImage getKeyS() {
        return KEY_S;
    }

    public static BufferedImage getUpgradeStarS() {
        return UPGRADE_STAR_S;
    }

    public static Image getBugEnemyS() {
        return BUG_ENEMY_S;
    }

    public static Image getTankEnemyS() {
        return TANK_ENEMY_S;
    }

    public static Image getMachineGunEnemy() {
        return MACHINE_GUN_ENEMY;
    }

    public static BufferedImage getMainMenu() {
        return MAIN_MENU;
    }

    public static BufferedImage getMainMenu2() {
        return MAIN_MENU_2;
    }

    public static BufferedImage getEscapeMenu() {
        return ESCAPE_MENU;
    }

    public static Image getEXPLOSION() {
        return EXPLOSION;
    }

    public static BufferedImage getTankShield1() {
        return TANK_SHIELD_1;
    }

    public static BufferedImage getTankShield2() {
        return TANK_SHIELD_2;
    }

    public static BufferedImage getTankShield3() {
        return TANK_SHIELD_3;
    }

    public static BufferedImage getTankShield4() {
        return TANK_SHIELD_4;
    }

    public static Image getTankAnim1() {
        return TANK_ANIM_1;
    }

    public static Image getTankAnim2() {
        return TANK_ANIM_2;
    }

    public static Image getTankAnim3() {
        return TANK_ANIM_3;
    }

    public static Image getTankAnim4() {
        return TANK_ANIM_4;
    }

    public static Image getTankShieldAnim1() {
        return TANK_SHIELD_ANIM_1;
    }

    public static Image getTankShieldAnim2() {
        return TANK_SHIELD_ANIM_2;
    }

    public static Image getTankShieldAnim3() {
        return TANK_SHIELD_ANIM_3;
    }

    public static Image getTankShieldAnim4() {
        return TANK_SHIELD_ANIM_4;
    }

    public static BufferedImage getEndMenu1() {
        return END_MENU_1;
    }

    public static BufferedImage getEndMenu2() {
        return END_MENU_2;
    }
}
