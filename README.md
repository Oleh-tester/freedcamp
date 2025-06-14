# 🔍 Freedcamp Automation Framework

**Author:** Oleh Khomik  
**Stack:** Java 17 · Gradle · JUnit 5 · Selenide · RestAssured · Lombok · Allure · HMAC Auth  
**Status:** 🛠 In Progress · 🎯 Targeted for smoke + core API/UI test coverage

---

## 📌 Purpose

This is a demonstration project designed to showcase automation expertise applied to a real-life SaaS product — [Freedcamp](https://freedcamp.com/). It includes a clean, scalable test framework focused on:

- 🔐 Business-critical **API testing** with HMAC authentication
- 💻 **UI tests** with Selenide for high-impact user flows
- 🧪 Clear separation between core, smoke, and regression suites
- 📊 Integrated **Allure reports** and structured logging (MDC)
- ⚙️ Designed to plug into any CI/CD pipeline with minimal setup

---

## 🧱 Project Structure

```
src/
├── main/
│   └── java/com.freedcamp/
│       ├── api/               → API clients (REST-assured)
│       ├── ui/                → UI clients (Selenide)
│       └── utils/             → Common helpers (timestamp, HMAC hashing, etc.)
├── test/
│   └── java/com.freedcamp/
│       ├── api/               → API tests (e.g. login, task management)
│       ├── ui/                → UI tests with Selenide
│       ├── pages/             → Page Objects and components
resources/
└── freedcamp.properties                 → 🔐 Your private creds (gitignored)
└── freedcamp.properties.example         → 📄 Config template (no secrets)
```

---

## 🚀 Getting Started

### 1. Clone the repo

```bash
git clone https://github.com/Oleh-tester/freedcamp.git
cd freedcamp
```

### 2. Provide your credentials

Create a `freedcamp.properties` file in `src/test/resources/` based on the `.example` file:

```properties
public_api_key=your_public_key
private_key=your_private_key
timestamp=auto_or_static
hash=auto_or_static
```

> ⚠️ This file is `.gitignore`d and must **never** be committed.

---

## ✅ Running Tests

### Run all API tests:
```bash
./gradlew clean test
```

### Run UI tests only:
```bash
./gradlew test -Dtags=ui
```

> Tag-based filtering is available via custom `@Tag` usage (e.g. smoke, api, regression, etc.)

---

## 📊 Allure Reporting

1. After running tests:
```bash
allure serve build/allure-results
```

2. Or generate static report:
```bash
./gradlew allureReport
open build/reports/allure-report/index.html
```

> Screenshots on UI test failures are auto-attached to Allure.

---

## ⚙️ CI/CD Friendly

The framework is designed for **integration into Jenkins/GitHub Actions**, with support for:

- Test separation by tags
- Logging with MDC for traceability
- Easy Allure report publication

---

## 📒 Additional Notes

- 🔐 `freedcamp.properties` must always be local only — never commit secrets.
- 🌐 If using corporate proxy/firewall, consider disabling browser security when running Selenide UI tests locally.
- 🧹 To avoid noise, `.DS_Store`, `/build`, and `.gradle` are excluded via `.gitignore`.

---

## 🧠 Lessons Learned

- HMAC auth adds a layer of complexity but can be abstracted into clean, reusable config utils.
- Selenide offers simplicity for UI testing, especially when paired with Allure.
- Clear separation of concerns in API vs UI layers leads to easier debugging and test isolation.

---