abstract class Pembayaran {
    protected String namaPembayar;
    protected double nominal;
    public Pembayaran(String namaPembayar, double nominal) {
        this.namaPembayar = namaPembayar;
        this.nominal = nominal;
    }

    public void tampilkanDetail() {
        System.out.println("Nama Pembayar : " + namaPembayar);
        System.out.println("Nominal       : Rp " + nominal);
    }
    public abstract void prosesPembayaran();
}
interface Keamanan {
    boolean autentikasi();
}

// Class KartuKredit
class KartuKredit extends Pembayaran implements Keamanan {
    private String nomorKartu;

    public KartuKredit(String namaPembayar, double nominal, String nomorKartu) {
        super(namaPembayar, nominal);
        this.nomorKartu = nomorKartu;
    }

    @Override
    public boolean autentikasi() {
        System.out.println("Autentikasi PIN berhasil.");
        return true;
    }
    @Override
    public void prosesPembayaran() {
        double admin = nominal * 0.02;
        double total = nominal + admin;

        System.out.println("Metode         : Kartu Kredit");
        System.out.println("Biaya Admin    : Rp " + admin);
        System.out.println("Total Tagihan  : Rp " + total);
    }
}

// Class EWallet
class EWallet extends Pembayaran implements Keamanan {
    private String nomorHP;

    public EWallet(String namaPembayar, double nominal, String nomorHP) {
        super(namaPembayar, nominal);
        this.nomorHP = nomorHP;
    }

    @Override
    public boolean autentikasi() {
        System.out.println("Autentikasi berhasil.");
        return true;
    }

    @Override
    public void prosesPembayaran() {
        System.out.println("Metode         : E-Wallet");
        System.out.println("Biaya Admin    : Rp 0");
        System.out.println("Total Tagihan  : Rp " + nominal);
    }
}

// Main class
public class Main {
    public static void main(String[] args) {

        Pembayaran p1 = new KartuKredit(
                "Andi",
                500000,
                "1234-5678-9999"
        );

        Pembayaran p2 = new EWallet(
                "Budi",
                250000,
                "08123456789"
        );

        Pembayaran[] daftarPembayaran = {p1, p2};


        for (Pembayaran p : daftarPembayaran) {

            System.out.println("========================");
            p.tampilkanDetail();

            if (p instanceof Keamanan) {

                Keamanan k = (Keamanan) p;

                if (k.autentikasi()) {
                    p.prosesPembayaran();
                }
            }

            System.out.println();
        }
    }
}