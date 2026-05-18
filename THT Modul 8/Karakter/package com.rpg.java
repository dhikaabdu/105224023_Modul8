package com.rpg.arena;

// Abstract class Karakter
public abstract class Karakter implements AksiBertarung {

    // Atribut protected
    protected String nama;
    protected int hp;
    protected int baseDamage;
    protected boolean isDefending;

    // Constructor
    public Karakter(String nama, int hp, int baseDamage) {
        this.nama = nama;
        this.hp = hp;
        this.baseDamage = baseDamage;
        this.isDefending = false;
    }

    // Getter dan Setter
    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getBaseDamage() {
        return baseDamage;
    }

    public void setBaseDamage(int baseDamage) {
        this.baseDamage = baseDamage;
    }

    public boolean isDefending() {
        return isDefending;
    }

    public void setDefending(boolean defending) {
        isDefending = defending;
    }

    // Method menerima damage
    public void terimaDamage(int damage) {

        // Jika sedang bertahan
        if (isDefending) {
            damage = damage / 2;
            isDefending = false;

            System.out.println(nama + " berhasil mengurangi damage!");
        }

        hp -= damage;

        // HP tidak boleh minus
        if (hp < 0) {
            hp = 0;
        }

        System.out.println(nama + " menerima damage: " + damage);
    }

    // Method abstract
    public abstract void tampilkanStatus();
}