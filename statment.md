# Project Statement: HostelEase

## Problem Statement
Campus residential management at educational institutions frequently depends on paper logbooks, manual ledgers, or unlinked spreadsheets. This outdated approach introduces severe administrative bottlenecks:
* **Capacity Overbooking:** Manual tracking lacks atomic validation, leading to double-allocation of rooms and bed capacity overflows.
* **Billing Discrepancies:** Hostel room rents and dietary subscriptions (Veg vs. Non-Veg meal plans) are often computed manually, resulting in miscalculated balances and uncollected student dues.
* **Lack of Instant Audit:** Retrieving room occupancy rates, finding vacant spaces, and tracking student checkouts requires scanning physical files, creating delays during semester admissions.
* **Data Loss & Tampering:** Paper-based logs or accidental spreadsheet overwrites lead to unrecoverable student records and broken audit trails.

**HostelEase** solves these operational flaws through a dedicated, console-driven Java application that enforces strict business rules, handles error boundaries via custom checked exceptions, and ensures zero-dependency local data persistence.

---

## Scope of the Project
The scope of **HostelEase** covers core front-desk residential operations for university dormitories:

### In Scope
* **Room Allocation Workflow:** Enforcing validation logic based on room type (AC vs. Non-AC), individual room capacity, and active room occupancy.
* **Dynamic Fee Calculation:** Automatically computing combined base fees during registration by pairing room amenities with dietary meal plans.
* **Occupancy & De-allocation Tracking:** Real-time updates to occupancy counters when students are allotted or vacated.
* **Persistent Local Storage:** Automatic serialization and deserialization of student records into a local structured CSV ledger (`hostel_data.csv`).
* **Console Reporting:** Formatted ASCII tabular outputs displaying live occupant rosters, billing states, and real-time room capacity availability.

### Out of Scope (Future Roadmap)
* Direct integration with payment gateways or bank APIs (transactions are tracked as fee dues).
* Multi-block or multi-hostel distributed networking across different campus branches.
* Graphical User Interface (GUI) or web portals (the system is deliberately maintained as a high-performance terminal utility).

---

## Target Users
The primary stakeholders who interact with **HostelEase** include:
* **Hostel Wardens & Caretakers:** Need immediate visibility into room occupancies, vacant beds, and quick allotment workflows during new admissions without double-booking rooms.
* **Campus Administrative Staff:** Manage student checkouts, room vacating routines, and roster audits at the end of academic semesters.
* **Hostel Accounts Department:** Rely on standardized fee ledger outputs to verify student room dues and meal plan charges accurately.

---

## High-Level Features

* **Strict Room Capacity Guard (`RoomFullException`):**
  Prevents over-allocation by verifying available capacity before confirming any allotment. If an administrator attempts to book a fully occupied room, the custom exception intercepts the request gracefully without crashing the application.

* **Unique Identification & Duplicate Rejection:**
  Validates student registration numbers before record insertion, ensuring that duplicate student profiles cannot exist simultaneously in the ledger.

* **Tier-Based Automated Billing Engine:**
  Eliminates human billing error by computing semester fees automatically:
  * **Room Types:** Non-AC (Rs. 30,000) | AC (Rs. 45,000)
  * **Mess Plans:** Vegetarian (Rs. 18,000) | Non-Vegetarian (Rs. 22,000)

* **Real-Time Room Vacating & Slot De-allocation:**
  Enables administrative checkout of students by registration number, instantly decrementing the room's occupancy count and freeing the slot for future students.

* **Lightweight CSV Persistence Engine:**
  Maintains an uncorrupted, comma-separated local database file (`hostel_data.csv`). Loads existing records into dynamic collections on program launch and synchronizes modifications immediately.

* **Defensive CLI Navigation:**
  Includes a scanner-driven text menu with input sanitation, tabular record displays, and structured error notifications.
