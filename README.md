# HostelEase: Campus Hostel Room Allotment & Mess Billing System

An automated, console-based desktop utility built with Core Java to streamline student hostel onboarding, room occupancy tracking, and automatic mess fee computation.

---

## Overview

Managing campus residential facilities using manual registers often leads to double-allocation of beds, untracked fee balances, and inaccurate occupancy counts. **HostelEase** replaces manual logbooks with an automated, object-oriented system. It ensures strict room capacity control, computes tailored semester dues based on room tier (AC vs. Non-AC) and meal plans (Veg vs. Non-Veg), and guarantees local data persistence across sessions via CSV storage.

---

## Key Features

- **Automated Room Allotment:** Allots students to designated rooms after verifying real-time capacity and duplicate registration constraints.
- **Capacity Guard (Custom Exceptions):** Throws `RoomFullException` when an allotment attempt exceeds room limits, preventing accidental overbooking.
- **Dynamic Fee Calculation:** Automatically computes combined semester dues factoring in room amenities (AC @ Rs. 45,000 / Non-AC @ Rs. 30,000) and dietary subscriptions (Veg @ Rs. 18,000 / Non-Veg @ Rs. 22,000).
- **Checkout & De-allocation:** Vacates students upon departure, automatically freeing room slots and recalculating current room occupancy.
- **Data Persistence:** Automatically serializes and deserializes student records to a structured local storage file (`hostel_data.csv`) during runtime.
- **Tabular Terminal Reporting:** Generates formatted ASCII ledger tables displaying real-time occupant rosters and room status.

---

## Technologies & Tools Used

- **Language:** Java SE (JDK 17 or higher)
- **Paradigm:** Object-Oriented Programming (Inheritance, Polymorphism, Encapsulation, Custom Checked Exceptions)
- **Data Structures:** Java Collections Framework (`ArrayList`)
- **Persistence:** Java File I/O (`BufferedReader`, `BufferedWriter`, `FileWriter`)
- **Environment:** Visual Studio Code / Command Prompt / PowerShell
- **Version Control:** Git & GitHub

---

## Steps to Install & Run the Project

### Prerequisites
* Java Development Kit (JDK 17 or higher) installed on your system.
* Verify Java installation by running these commands in your terminal:
  ```bash
  javac -version
  java -version

  --

  ## Instructions for Testing

Follow these quick test cases in the terminal to verify allotment, fee computation, and capacity restrictions:

### Test Case 1: Room Allotment & Automatic Billing
* **Action:** Choose menu option `1` and enter:
  * **Reg No:** `25BAI11243`
  * **Student Name:** `Tanmay Tejra`
  * **Phone:** `7003966928`
  * **Room No:** `201`
  * **Mess Type:** `Veg`
* **Expected Result:** 
  * Displays `>> Success: Room allotted and record saved.`
  * Option `2` confirms Room `201` (AC: Rs. 45,000) + Veg mess (Rs. 18,000) totals `Rs. 67000.00`.

---

### Test Case 2: Room Capacity Overflow (`RoomFullException`)
* **Action:** Choose menu option `1` to fill single-seater Room `202` (Capacity: 1):
  * **Reg No:** `23BCE1002` | **Room:** `202` | **Mess:** `Veg`
  * Try allotting another student to `202`: **Reg No:** `23BCE1003` | **Room:** `202` | **Mess:** `Veg`
* **Expected Result:** 
  * Exception is handled gracefully and displays: `>> Error: Room 202 is already full to capacity (1).`

 <img width="1917" height="1037" alt="Screenshot 2026-09-18 001932" src="https://github.com/user-attachments/assets/bf20660f-7b8d-428b-9a9a-a8453204955b" />

