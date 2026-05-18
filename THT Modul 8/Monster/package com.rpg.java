package com.rpg.entitas;

import com.rpg.arena.Karakter;

// Class Monster
public class Monster extends Karakter {

    // Atribut tambahan
    private String jenisMonster;

    // Constructor
    public Monster(String nama, int hp,
                   int baseDamage, String jenisMonster) {

        super(nama, hp, baseDamage);

        this.jenisMonster = jenisMonster;
    }

    // Override serang
    @Override
    public int serang() {

        System.out.println(nama + " menyerang!");

        return baseDamage;
    }

    // Override bertahan
    @Override
    public void bertahan() {

        hp += 10;

        System.out.println(nama + " memulihkan HP sebesar 10!");
    }

    // Override gunakan item
    @Override
    public void gunakanItem() {

        System.out.println(nama + " tidak memiliki item!");
    }

    // Override tampilkan status
    @Override
    public void tampilkanStatus() {

        System.out.println("===== STATUS MONSTER =====");
        System.out.println("Nama   : " + nama);
        System.out.println("HP     : " + hp);
        System.out.println("Jenis  : " + jenisMonster);
    }
}