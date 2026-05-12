
abstract class Karyawan {
    protected String nama;

    public Karyawan(String nama) {
        this.nama = nama;
    }

    public abstract double hitungGaji();
}

class Programmer extends Karyawan {
    private double gajiPokok;

    public Programmer(String nama, double gajiPokok) {
        super(nama);
        this.gajiPokok = gajiPokok;
    }

    @Override
    public double hitungGaji() {
        return gajiPokok;
    }
}

public class Main {
    public static void main(String[] args) {

        Programmer p1 = new Programmer("ANDHIKA", 7500000);

        System.out.println("Nama Programmer : " + p1.nama);
        System.out.println("Total Gaji      : " + p1.hitungGaji());
    }
}