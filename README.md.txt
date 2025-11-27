# SmartBank Java Project

Author: Lwano Abdoul Karim
Registration Number: 23/BIT/BU/R/0017

Language: Java
GUI Library: Swing

admin : 
u-N = "admin"
KEY = "1234567890"



 Table of Contents

1. Project Overview
2. Features
3. Project Structure
4. How It Works
5. User Roles
6. GUI Screens
7. Banking Mechanics
8. Future Improvements


 Project Overview

SmartBank is a Java Swing-based banking application that simulates a basic banking system. It supports two main roles: Customer and Admin. Users can deposit, request loans, and view their transactions. Admins manage the system by approving loans, processing withdrawals, broadcasting notifications, and viewing bank assets.

The UI is designed with **modern visual components**, including:

Card-based layouts
Circular avatars with hover and click preview effects
Responsive buttons and hints


 Features

Customer:

   Login and logout
   Upload and remove profile pictures
   Deposit money
   Request withdrawals
   Request loans
   View balance, debt, and interest
   See transaction history

Admin:

   Login with credentials
   View all users
   Approve loans
   Approve withdrawals
   Broadcast notifications
   View bank assets

UI Enhancements:

   Logo on landing page
   Vertical card layout with buttons
   Circular avatars for customers
   Transaction log in main area



Project Structure

| File/Class                     | Purpose                                                               |
| ------------------------------ | --------------------------------------------------------------------- |
| `UI_PRO.java`                  | Main GUI entry point and role selection                               |
| `Bank.java`                    | Core banking mechanics (register users, track assets)                 |
| `User.java`                    | User data model (username, balance, loans, transactions, avatar path) |
| `Loan.java`                    | Loan data model (amount, interest, approval status)                   |
| `SmartBank_Utils.java`         | Utility functions (username/password validation, notifications)       |
| `SmartBank_BankingEngine.java` | Handles deposits, loan submissions, and banking logic                 |
| `LoginScreen.java`             | Handles login for customers and admin                                 |
| `AdminModule.java`             | Admin functions like approvals, broadcast, view assets                |



How It Works

1. Landing Page:
   The app opens to a role selection screen with a logo and two buttons: Customer or Admin.

2. Role Selection:

   * Customer Button: Opens `LoginScreen` for customers.
   * Admin Button: Opens `LoginScreen` for admin.

3. **Login Validation:**

   * Customers: Validated against registered users in `Bank`.
   * Admin: Validated against a fixed credential (`admin / 1234567890`).

4. **Dashboard:**

   * **Customer Dashboard:** Shows sidebar with avatar, name, balance, debt, and interest. Main panel displays transactions.
   * **Admin Dashboard:** Shows options to manage users, loans, withdrawals, notifications, and view assets.

5. **Transactions & Banking Operations:**

   * Deposit updates balance and transaction log immediately.
   * Loan requests are stored and await admin approval.
   * Withdrawal requests are submitted to admin for approval.

---

## User Roles

### Customer

* Can **interact with their account**: deposit, request loans/withdrawals.
* **Avatar management**: upload, remove, and preview profile pictures.
* Transactions and financial stats are displayed in real-time.

### Admin

* Has **full control over the bank operations**:

  * Approve pending loans
  * Process withdrawal requests
  * Broadcast notifications to all users
  * View bank’s total assets and collected interest

---

## GUI Screens

1. **Landing / Role Selection:**

   * Logo at the top
   * Two big buttons for role selection
   * Hint with admin credentials

2. **Login Screen:**

   * Username/password fields
   * Login button
   * Validates user input

3. **Customer Dashboard:**

   * Sidebar with avatar, name, balance, debt, interest
   * Buttons: Deposit, Request Withdrawal, Request Loan, Upload/Remove Picture, Logout
   * Main panel: Transaction history

4. **Admin Dashboard:**

   * Sidebar with action buttons
   * Main panel: Logs, user lists, or asset reports


 Banking Mechanics

* **Bank Registration:** Users are registered via `Bank.register()`.
* **Deposits:** Directly update user balance and log transaction.
* **Loans:** Submitted for admin approval. Approved loans affect user debt and interest.
* **Withdrawals:** Requested and require admin approval.
* **Transactions Log:** Stored in `User.transactions` and displayed in dashboard.
* **Assets Tracking:** Bank tracks total collected interest and total bank assets.

