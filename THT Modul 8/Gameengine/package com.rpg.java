package com.rpg.main;

import java.util.Scanner;

import com.rpg.entitas.Monster;
import com.rpg.entitas.Pahlawan;

// Main Class
public class GameEngine {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        // Input nama pahlawan
        System.out.print("Masukkan nama Pahlawan: ");
        String nama = input.nextLine();

        // Membuat object pahlawan
        Pahlawan hero = new Pahlawan(
                nama,
                150,
                20,
                100,
                2
        );

        // Array monster
        Monster[] monsters = {

                new Monster("Goblin", 60,
                        10, "Goblin"),

                new Monster("Orc", 90,
                        15, "Orc"),

                new Monster("Dragon", 150,
                        25, "Dragon")
        };

        // Loop monster satu per satu
        for (Monster musuh : monsters) {

            System.out.println("\n=================================");
            System.out.println("Monster muncul: "
                    + musuh.getNama());
            System.out.println("=================================");

            // Battle loop
            while (hero.getHp() > 0
                    && musuh.getHp() > 0) {

                // Status karakter
                hero.tampilkanStatus();
                musuh.tampilkanStatus();

                // Menu
                System.out.println("\nPilih aksi:");
                System.out.println("1. Serang");
                System.out.println("2. Skill");
                System.out.println("3. Bertahan/Heal");

                System.out.print("Pilihan: ");
                int pilihan = input.nextInt();

                int damage;

                switch (pilihan) {

                    case 1:

                        // Serangan biasa
                        damage = hero.serang();

                        musuh.terimaDamage(damage);

                        break;

                    case 2:

                        // Skill
                        damage = hero.serang(
                                "Fire Slash",
                                20
                        );

                        musuh.terimaDamage(damage);

                        break;

                    case 3:

                        // Bertahan dan heal
                        hero.bertahan();
                        hero.gunakanItem();

                        break;

                    default:

                        System.out.println("Pilihan tidak valid!");
                }

                // Jika monster masih hidup
                if (musuh.getHp() > 0) {

                    int seranganMonster =
                            musuh.serang();

                    hero.terimaDamage(
                            seranganMonster
                    );
                }
            }

            // Jika hero kalah
            if (hero.getHp() <= 0) {

                System.out.println("\nGAME OVER!");
                break;
            }

            // Jika monster kalah
            System.out.println("\nMonster "
                    + musuh.getNama()
                    + " berhasil dikalahkan!");
        }

        // Kondisi akhir game
        if (hero.getHp() > 0) {

            System.out.println("\n=================================");
            System.out.println("SELAMAT!");
            System.out.println(hero.getNama()
                    + " berhasil menamatkan Dungeon!");
            System.out.println("=================================");
        }

        input.close();
    }
}