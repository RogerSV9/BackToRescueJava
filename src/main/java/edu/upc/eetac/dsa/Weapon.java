package edu.upc.eetac.dsa;

public class Weapon {

    int damage;
    int defense;
    int mana;

    public Weapon(int damage, int defense, int mana) {
        this.damage = damage;
        this.defense = defense;
        this.mana = mana;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }
}
