abstract class LayananPengiriman {
    protected String noResi;
    protected double beratBarang;
    protected double jarakTempuh;

    public LayananPengiriman(String noResi, double beratBarang, double jarakTempuh) {
        this.noResi = noResi;
        this.beratBarang = beratBarang;
        this.jarakTempuh = jarakTempuh;
    }

    public void cetakResi() {
        System.out.println("Nomor Resi   : " + noResi);
        System.out.println("Berat Barang : " + beratBarang + " kg");
        System.out.println("Jarak Tempuh : " + jarakTempuh + " km");
    }
    public abstract double hitungOngkosKirim();
}

// Interface LacakKargo
interface LacakKargo {
    void updateStatus(String status);

    String cekLokasiTerakhir();
}

// Interface Asuransi
interface Asuransi {
    double hitungPremi(double nilaiBarang);

    default void cetakPolis() {
        System.out.println(
            "Polis Asuransi aktif: Menanggung kehilangan dan kerusakan fisik sebesar 100% dari nilai barang."
        );
    }
}

// Class PengirimanDarat
class PengirimanDarat extends LayananPengiriman implements LacakKargo {

    private String jenisTruk;
    private String statusSaatIni;

    public PengirimanDarat(String noResi,
                           double beratBarang,
                           double jarakTempuh,
                           String jenisTruk) {

        super(noResi, beratBarang, jarakTempuh);
        this.jenisTruk = jenisTruk;
        this.statusSaatIni = "Menunggu Kurir";
    }

    @Override
    public double hitungOngkosKirim() {

        double ongkir = (beratBarang * 5000) + (jarakTempuh * 2000);

        if (jenisTruk.equalsIgnoreCase("Tronton")) {
            ongkir += 150000;
        }

        return ongkir;
    }

    // Override interface
    @Override
    public void updateStatus(String status) {
        this.statusSaatIni = status;
    }

    @Override
    public String cekLokasiTerakhir() {
        return statusSaatIni;
    }
}

// Class PengirimanUdara
class PengirimanUdara extends LayananPengiriman
        implements LacakKargo, Asuransi {

    private String nomorPenerbangan;
    private String statusSaatIni;
    private double nilaiBarang;

    public PengirimanUdara(String noResi,
                           double beratBarang,
                           double jarakTempuh,
                           String nomorPenerbangan,
                           double nilaiBarang) {

        super(noResi, beratBarang, jarakTempuh);

        this.nomorPenerbangan = nomorPenerbangan;
        this.nilaiBarang = nilaiBarang;
        this.statusSaatIni = "Menunggu Jadwal Penerbangan";
    }

    @Override
    public double hitungOngkosKirim() {

        return (beratBarang * 25000) + (jarakTempuh * 5000);
    }

    @Override
    public void updateStatus(String status) {
        this.statusSaatIni = status;
    }

    @Override
    public String cekLokasiTerakhir() {
        return statusSaatIni;
    }
    @Override
    public double hitungPremi(double nilaiBarang) {
        return nilaiBarang * 0.03;
    }

    public double getNilaiBarang() {
        return nilaiBarang;
    }
}

// Main Class
public class Main {

    public static void main(String[] args) {

        PengirimanDarat darat = new PengirimanDarat(
                "DRT-001",
                50,
                100,
                "Tronton"
        );

        PengirimanUdara udara = new PengirimanUdara(
                "UDR-999",
                10,
                800,
                "GA-123",
                5000000
        );

        darat.updateStatus("Sedang di jalan tol Cipali");
        udara.updateStatus("Transit di Bandara Soekarno-Hatta");

        LayananPengiriman[] daftarPengiriman = {darat, udara};
        for (LayananPengiriman lp : daftarPengiriman) {

            System.out.println("======================================");
            System.out.println("DETAIL PENGIRIMAN");
            System.out.println("======================================");

            lp.cetakResi();

            if (lp instanceof LacakKargo) {

                LacakKargo lk = (LacakKargo) lp;

                System.out.println("Status Lokasi : "
                        + lk.cekLokasiTerakhir());
            }

            double totalTagihan = lp.hitungOngkosKirim();

            System.out.println("Ongkos Kirim  : Rp " + totalTagihan);

            if (lp instanceof Asuransi) {

                Asuransi as = (Asuransi) lp;

                as.cetakPolis();

                PengirimanUdara pu = (PengirimanUdara) lp;

                double premi = as.hitungPremi(
                        pu.getNilaiBarang()
                );

                System.out.println("Premi Asuransi: Rp " + premi);

                totalTagihan += premi;
            }

            System.out.println("--------------------------------------");
            System.out.println("TOTAL TAGIHAN : Rp " + totalTagihan);
            System.out.println("======================================");
            System.out.println();
        }
    }
}