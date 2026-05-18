package com.rpg.entitas;

import com.rpg.arena.Karakter;

// Class Pahlawan
public class Pahlawan extends Karakter {

    // Atribut private
    private int mana;
    private int level;

    // Constructor
    public Pahlawan(String nama, int hp, int baseDamage,
                     int mana, int level) {

        super(nama, hp, baseDamage);

        this.mana = mana;
        this.level = level;
    }

    // Getter Setter
    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    // Override serang
    @Override
    public int serang() {

        int damage = baseDamage * level;

        System.out.println(nama + " menyerang!");

        return damage;
    }

    // Method Overloading
    public int serang(String namaSkill, int manaCost) {

        if (mana >= manaCost) {

            mana -= manaCost;

            int damage = baseDamage * level * 2;

            System.out.println(nama + " menggunakan skill "
                    + namaSkill);

            return damage;

        } else {

            System.out.println("Mana tidak cukup!");

            return 0;
        }
    }

    // Override bertahan
    @Override
    public void bertahan() {

        isDefending = true;

        System.out.println(nama + " bersiaga bertahan!");
    }

    // Override gunakan item
    @Override
    public void gunakanItem() {

        hp += 30;

        System.out.println(nama + " menggunakan potion!");
        System.out.println("HP bertambah 30!");
    }

    // Override tampilkan status
    @Override
    public void tampilkanStatus() {

        System.out.println("===== STATUS PAHLAWAN =====");
        System.out.println("Nama  : " + nama);
        System.out.println("HP    : " + hp);
        System.out.println("Mana  : " + mana);
        System.out.println("Level : " + level);
    }
}