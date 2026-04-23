# 💳 FlexPay Smart Wallet Engine

> A split-payment and micro-saving engine simulation built with **Java 17** and **SQLite**, showcasing the **Strategy Design Pattern** in a real-world fintech scenario.

![Java](https://img.shields.io/badge/Java-17-orange?style=flat-square&logo=openjdk)
![SQLite](https://img.shields.io/badge/SQLite-3-blue?style=flat-square&logo=sqlite)
![Maven](https://img.shields.io/badge/Maven-3.9-red?style=flat-square&logo=apachemaven)
![License](https://img.shields.io/badge/License-MIT-green?style=flat-square)

---

## 🧠 What Does It Do?

FlexPay simulates a **smart digital wallet** that automatically splits payments across multiple funding sources — just like modern fintech apps (Chippin, Sodexo, Param, etc.).

### Key Features

| Feature | Description |
|---------|-------------|
| **🍽️ Split Payments** | Automatically distributes a restaurant bill across meal balance → loyalty points → credit card |
| **🛍️ Context-Aware Rules** | Retail stores cannot use meal balance — the engine enforces this via strategy |
| **🐷 Micro-Saving (Round-Up)** | Rounds up purchases to the nearest ₺10 and converts the difference into loyalty points |
| **💾 SQLite Persistence** | Wallet balances and transaction history are stored in a local SQLite database |
| **📋 Transaction Logging** | Every payment is logged with timestamp and category for full audit trail |

---

## 🏗️ Architecture

The project follows **clean architecture principles** with clear separation of concerns:

```
src/main/java/
├── model/
│   └── Wallet.java              # Domain model with wallet balances
├── strategy/
│   ├── PaymentStrategy.java     # Strategy interface (contract)
│   ├── RestaurantPaymentStrategy.java  # Restaurant: meal → points → card
│   └── RetailPaymentStrategy.java      # Retail: points → card (no meal)
├── service/
│   ├── Main.java                # Simulation entry point
│   ├── PaymentService.java      # Strategy executor (context)
│   └── MicroSavingProcessor.java # Round-up savings logic
└── repository/
    ├── DatabaseManager.java     # SQLite connection (Singleton)
    ├── WalletRepository.java    # CRUD operations for wallets
    └── TransactionRepository.java # Transaction logging & history
```

### Design Pattern: Strategy

```
┌─────────────────────┐
│   PaymentService    │  (Context)
│   ┌───────────────┐ │
│   │  «interface»  │ │
│   │ PaymentStrategy│ │
│   └───────┬───────┘ │
└───────────┼─────────┘
            │
    ┌───────┴────────┐
    │                │
┌───▼────┐    ┌──────▼───────┐
│Restaurant│   │   Retail     │
│Strategy  │   │  Strategy    │
│          │   │              │
│meal→pts  │   │pts→card     │
│→card     │   │(no meal)     │
└──────────┘   └──────────────┘
```

---

## 🚀 Getting Started

### Prerequisites

- **Java 17** or higher
- **Maven 3.9+** (or use the included Maven Wrapper)

### Run the Simulation

```bash
# Clone the repository
git clone https://github.com/sarp-onaran/flexpay-smart-wallet.git
cd flexpay-smart-wallet

# Compile and run
./mvnw compile exec:java -Dexec.mainClass="service.Main"
```

### Sample Output

```
==================================================
🚀 FLEXPAY SMART WALLET ENGINE - V1.0
==================================================
📦 Default wallet seeded into database for: Sarp Onaran
👤 Wallet loaded for: Sarp Onaran
💳 [WALLET STATUS] Owner: Sarp Onaran
   Credit Card Limit  : 5000.0 TL
   Corporate Meal Fund : 400.0 TL
   Loyalty Points      : 50.0 Points

📍 LOCATION: Midpoint Restaurant
🍽️ Restaurant Payment Initiated: 584.0 TL
   -> Deducted from Meal Balance: 400.0 TL
   -> Deducted from ChipPoints: 50.0 TL
   -> Charged to Credit Card: 134.0 TL
✅ Restaurant payment completed successfully!
🐷 [Piggy Bank] Rounded up to 590.0 TL. 6.0 TL added as ChipPoints!

📍 LOCATION: LC Waikiki Store
🛍️ Retail Payment Initiated: 200.0 TL
   -> Deducted from ChipPoints: 6.0 TL
   -> Charged to Credit Card: 194.0 TL
✅ Retail payment completed successfully!

💾 Wallet balances saved to database.
📋 TRANSACTION HISTORY:
   [2026-04-23 18:46:10] RESTAURANT | 584.00 TL
   [2026-04-23 18:46:10] RETAIL     | 200.00 TL

✅ FLEXPAY SIMULATION COMPLETED SUCCESSFULLY.
```

---

## 🛠️ Tech Stack

| Technology | Purpose |
|------------|---------|
| Java 17 | Core language |
| Maven | Build & dependency management |
| SQLite (via JDBC) | Lightweight embedded database |
| JUnit 5 | Unit testing framework |
| Strategy Pattern | Payment processing flexibility |
| Singleton Pattern | Database connection management |

---

## 📈 Roadmap

- [x] Core payment engine with Strategy Pattern
- [x] Restaurant & Retail payment strategies
- [x] Micro-saving (round-up) processor
- [x] SQLite database integration
- [x] Transaction history logging
- [ ] Unit tests with JUnit 5
- [ ] New strategies (Online, Fuel, Transportation)
- [ ] REST API layer (Spring Boot)
- [ ] Budget alerts & spending analytics

---

## 📄 License

This project is open source and available under the [MIT License](LICENSE).

---

<p align="center">
  Built with ☕ by <a href="https://github.com/sarp-onaran">Sarp Onaran</a>
</p>
