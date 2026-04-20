import model.Wallet;
import service.PaymentService;
import repository.DatabaseManager;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Chippin Smart Wallet Engine ===");
        System.out.println("Proje başarıyla oluşturuldu!\n");

        // Örnek cüzdan oluştur
        Wallet wallet = new Wallet(5000.0, 1500.0, 200.0);
        System.out.println("Cüzdan bilgileri: " + wallet);

        // Veritabanı bağlantısını başlat
        DatabaseManager db = DatabaseManager.getInstance();

        // TODO: Strateji implementasyonları eklenecek
        // PaymentService service = new PaymentService(new MarketStrategy());
        // service.executePayment(150.0, wallet);

        // Bağlantıyı kapat
        db.closeConnection();
    }
}
