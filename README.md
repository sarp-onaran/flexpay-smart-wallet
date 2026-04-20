# 🚀 FlexPay Smart Wallet Engine

> A Proof-of-Concept (PoC) backend simulation for a next-generation loyalty and payment orchestration system.

## 📌 Overview
FlexPay is an intelligent payment routing engine designed to seamlessly orchestrate multiple user balances (Corporate Food Funds, Loyalty Points, and Credit Card limits) during a single transaction. Built with clean architecture principles, it eliminates checkout friction by automatically deciding the most optimal payment split based on the transaction context (e.g., Restaurant vs. Retail).

## ✨ Key Features
* **🧠 Smart Split-Payment Orchestration:** Automatically prioritizes B2B funds (Food Balance) for dining, utilizes accumulated loyalty points, and covers the remaining balance via credit card—all in milliseconds.
* **🎯 Dynamic Strategy Pattern:** Utilizes the Strategy Design Pattern to separate payment logic based on merchant types. The system is completely open to extension but closed to modification (Open/Closed Principle).
* **🐷 Micro-Saving Engine:** Incorporates a gamified "Round-Up" algorithm. It rounds up transaction amounts to the nearest ten and automatically converts the spare change into Loyalty Points.
* **🔒 Future Roadmap:** SQLite database integration to track transaction histories and maintain a secure audit trail.

## 🛠️ Architecture & Tech Stack
* **Language:** Java (Core)
* **Architecture:** Object-Oriented Programming (OOP)
* **Design Patterns:** Strategy Pattern

## 📂 Project Structure
src/main/java/
 ├── model/
 │    └── Wallet.java                 
 ├── strategy/
 │    ├── PaymentStrategy.java        
 │    ├── RestaurantPaymentStrategy.java 
 │    └── RetailPaymentStrategy.java  
 ├── service/
 │    └── MicroSavingProcessor.java   
 └── Main.java                        

## 🚀 How to Run the Simulation
1. Clone the repository: `git clone https://github.com/sarp-onaran/flexpay-smart-wallet.git`
2. Navigate to the project directory and compile the Java files.
3. Run the `Main.java` class to see the live console simulation of the Split-Payment and Micro-Saving engines in action.

## 👨‍💻 Author
**Sarp Onaran** - Software Engineering Student | [LinkedIn](https://linkedin.com/in/sarp-onaran)
