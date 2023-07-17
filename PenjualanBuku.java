abstract class Buku {
    protected String judul;
    protected double hargaBeli;
    protected double hargaJual;
    protected int stok;
 
    public Buku(String judul, double hargaBeli, double hargaJual, int stok) {
        this.judul = judul;
        this.hargaBeli = hargaBeli;
        this.hargaJual = hargaJual;
        this.stok = stok;
    }
 
    public String getJudul() {
        return judul;
    }
 
    public double getHargaBeli() {
        return hargaBeli;
    }
 
    public double getHargaJual() {
        return hargaJual;
    }
 
    public int getStok() {
        return stok;
    }
 
    public void tambahStok(int jumlah) {
        stok += jumlah;
    }
 
    public void kurangiStok(int jumlah) {
        stok -= jumlah;
        if (stok == 0) {
            System.out.println("Stok " + judul + " sudah mencapai nol.");
        }
    }
 
    public abstract void tampilkanInfo();
}
 
class BukuFiksi extends Buku {
    public BukuFiksi(String judul, double hargaBeli, double hargaJual, int stok) {
        super(judul, hargaBeli, hargaJual, stok);
    }
 
    @Override
    public void tampilkanInfo() {
        System.out.println("Jenis: Buku Fiksi");
        System.out.println("Judul: " + judul);
        System.out.println("Stok: " + stok);
    }
}
 
class BukuNonFiksi extends Buku {
    public BukuNonFiksi(String judul, double hargaBeli, double hargaJual, int stok) {
        super(judul, hargaBeli, hargaJual, stok);
    }
 
    @Override
    public void tampilkanInfo() {
        System.out.println("Jenis: Buku Non-Fiksi");
        System.out.println("Judul: " + judul);
        System.out.println("Stok: " + stok);
    }
}
 
class Majalah extends Buku {
    private int nomorEdisi;
 
    public Majalah(String judul, double hargaBeli, double hargaJual, int stok, int nomorEdisi) {
        super(judul, hargaBeli, hargaJual, stok);
        this.nomorEdisi = nomorEdisi;
    }
 
    @Override
    public void tampilkanInfo() {
        System.out.println("Jenis: Majalah");
        System.out.println("Judul: " + judul);
        System.out.println("Nomor Edisi: " + nomorEdisi);
        System.out.println("Stok: " + stok);
    }
}
 
class PenjualanBuku {
    private static double modalAwal = 5000000.0;
    private static double modalBerjalan = 100000000.0;
    private static ArrayList<Buku> daftarBuku = new ArrayList<>();
 
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
 
                // Menambahkan beberapa buku sebagai contoh
        daftarBuku.add(new BukuFiksi("Harry Potter", 8, 15, 20));
        daftarBuku.add(new BukuFiksi("The Lord of the Rings", 10, 18, 15));
        daftarBuku.add(new BukuNonFiksi("Sapiens: A Brief History of Humankind", 12, 20, 30));
        daftarBuku.add(new BukuNonFiksi("Educated", 9, 16, 25));
        daftarBuku.add(new Majalah("National Geographic", 5, 10, 50, 123));
        daftarBuku.add(new Majalah("Time", 6, 12, 40, 456));
 
        while (true) {
            System.out.println("*******************************************************************************************");
            System.out.println("Sistem Penjualan Buku");
            System.out.println("By: <AL ALIF DITO>, <NIM: 2220194>");
            System.out.println("*******************************************************************************************5");
            System.out.println("Silahkan pilih menu: ");
            System.out.println("1. Tampilkan Laporan Modal");
            System.out.println("2. Tampilkan Stok Buku");
            System.out.println("3. Penjualan Buku");
            System.out.println("4. Pembelian Buku");
            System.out.println("5. Exit");
            System.out.print("Pilih menu: ");
            int pilihan = scanner.nextInt();
 
            switch (pilihan) {
                case 1:
                    tampilkanLaporanModal();
                    break;
                case 2:
                    tampilkanStokBuku();
                    break;
                case 3:
                    penjualanBuku();
                    break;
                case 4:
                    pembelianBuku();
                    break;
                case 5:
                    System.out.println("Program Selesai");
                    System.exit(0);
                default:
                    System.out.println("Pilihan tidak valid");
            }
 
            System.out.println();
        }
    }
 
    private static void tampilkanLaporanModal() {
        System.out.println("===== Laporan Modal =====");
        System.out.println("Modal Awal: " + modalAwal);
        System.out.println("Modal Berjalan: " + modalBerjalan);
    }
 
    private static void tampilkanStokBuku() {
        System.out.println("===== Stok Buku =====");
        for (Buku buku : daftarBuku) {
            buku.tampilkanInfo();
        }
    }
 
    private static void penjualanBuku() {
        Scanner scanner = new Scanner(System.in);
 
        System.out.println("===== Penjualan Buku =====");
        System.out.print("Masukkan judul buku: ");
        String judul = scanner.nextLine();
        System.out.print("Masukkan jumlah yang terjual: ");
        int jumlahTerjual = scanner.nextInt();
 
        Buku bukuTerjual = cariBuku(judul);
        if (bukuTerjual != null) {
            double pendapatan = bukuTerjual.getHargaJual() * jumlahTerjual;
            modalBerjalan += pendapatan;
            bukuTerjual.kurangiStok(jumlahTerjual);
            System.out.println("Penjualan berhasil. Pendapatan: " + pendapatan);
        } else {
            System.out.println("Buku tidak ditemukan.");
        }
    }
 
    private static void pembelianBuku() {
        Scanner scanner = new Scanner(System.in);
 
        System.out.println("===== Pembelian Buku =====");
        System.out.print("Masukkan judul buku: ");
        String judul = scanner.nextLine();
        System.out.print("Masukkan jumlah yang dibeli: ");
        int jumlahDibeli = scanner.nextInt();
 
        Buku bukuDibeli = cariBuku(judul);
        if (bukuDibeli != null) {
            double pengeluaran = bukuDibeli.getHargaBeli() * jumlahDibeli;
            if (pengeluaran <= modalBerjalan) {
                modalBerjalan -= pengeluaran;
                bukuDibeli.tambahStok(jumlahDibeli);
                System.out.println("Pembelian berhasil. Pengeluaran: " + pengeluaran);
            } else {
                System.out.println("Modal berjalan tidak mencukupi.");
            }
        } else {
            System.out.println("Buku tidak ditemukan.");
        }
    }
 
    private static Buku cariBuku(String judul) {
        for (Buku buku : daftarBuku) {
            if (buku.getJudul().equalsIgnoreCase(judul)) {
                return buku;
            }
        }
        return null;
    }
}
