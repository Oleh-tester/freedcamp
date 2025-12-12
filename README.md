# 🔍 Freedcamp Test Automation Framework

> **Enterprise-grade test automation framework demonstrating professional QA engineering practices**

**Author:** Oleh Khomik  
**Tech Stack:** Java 17 · Gradle · JUnit 5 · Selenide · Rest-Assured · Allure · Owner · Lombok · AssertJ · DataFaker

[![Java](https://img.shields.io/badge/Java-17-orange)](https://openjdk.java.net/)
[![Gradle](https://img.shields.io/badge/Gradle-8.x-blue)](https://gradle.org/)
[![Selenide](https://img.shields.io/badge/Selenide-7.11-green)](https://selenide.org/)

---

## 🚀 Quick Start

### Prerequisites
- **Java 17+** ([Download](https://adoptium.net/))
- **Allure CLI** (for report viewing): `brew install allure` (macOS) or [Installation Guide](https://docs.qameta.io/allure/)
- **Freedcamp Account** with valid credentials

### Setup in 3 Steps

**1. Clone & Navigate**
```bash
git clone <repository-url>
cd freecampPromo
```

**2. Configure Credentials**
```bash
cp src/test/resources/freedcamp.properties.example src/test/resources/freedcamp.properties
```
Edit `freedcamp.properties` with your credentials:
```properties
baseUrl=https://freedcamp.com
owner.email=your_email@example.com
owner.password=your_password
testUserId=12345
projectTemplateId=67890
ui.browser=chrome
ui.headless=false
```

**3. Run Tests**
```bash
# Run all tests
./gradlew clean test

# Run smoke tests only (fastest, ~2 min)
./gradlew test -DincludeTags=Smoke

# Generate and view Allure report
allure serve build/allure-results
```

🎉 **That's it!** Your first test run should complete successfully.

---

## 📊 Project Highlights

This framework showcases **production-ready test automation** for [Freedcamp](https://freedcamp.com/), a real-world SaaS project management platform.

### Key Achievements
- ✅ **90+ automated tests** covering critical user journeys
- ✅ **Multi-layer testing**: API (fast feedback) + UI (user-centric) + E2E (integration)
- ✅ **60% faster execution** via smart session management (cookie injection vs. UI login)
- ✅ **Parallel execution**: Configurable forks × threads (up to 10x speed boost)
- ✅ **Zero flakiness tolerance**: Robust waits, retry mechanisms, isolated test data
- ✅ **Rich diagnostics**: Allure reports with screenshots, API logs, request/response dumps
- ✅ **CI/CD ready**: Jenkins pipeline with secure credential injection
- ✅ **Secure by design**: No hardcoded credentials, .gitignore protection

### Why This Framework Stands Out
- **Real-world application**: Tests actual production SaaS, not toy examples
- **Enterprise patterns**: Page Object Model, Factory pattern, Builder pattern, Strategy pattern
- **Maintainability**: Clean separation (config, auth, pages, tests, assertions, steps)
- **Scalability**: Thread-safe design, parallel-ready, resource pooling
- **Observability**: Structured logging with MDC context (test name, user, correlation ID)

> **⚠️ Security Notice:** This project requires valid Freedcamp credentials. Never commit `freedcamp.properties`. Use environment variables or CI secret management in production.

---

## 🧭 Architecture Overview

This framework follows a **layered testing strategy** for optimal coverage and execution speed:

```
┌─────────────────────────────────────────────────────────┐
│  E2E Tests (1 test)                                     │  Full user journey
│  Cross-layer validation with UI + API verification      │  ~2 min
├─────────────────────────────────────────────────────────┤
│  UI Tests (6 test classes, ~40 tests)                  │  User interactions
│  Auth, Projects, Tasks, Milestones, Calendar, TimeLog  │  ~8 min
├─────────────────────────────────────────────────────────┤
│  API Tests (5 test classes, ~50 tests)                 │  Fast feedback
│  Auth, Users, Projects, Tasks, Calendar Events         │  ~3 min
├─────────────────────────────────────────────────────────┤
│  Smoke Tests (~10 critical tests)                      │  Health check
│  Tagged subset across all layers                        │  ~2 min
└─────────────────────────────────────────────────────────┘
```

### Design Principles
1. **Fast Feedback Loop**: API tests run first; UI tests use pre-authenticated sessions
2. **Test Independence**: Each test creates its own data; no shared state
3. **Resilience**: Custom waits for SPA hydration, network idle detection, retry on transient failures
4. **Traceability**: Every action logged with context; every failure captured with screenshot + HTML source

---

## 🗂 Framework Architecture

### Project Structure
```
src/main/java/com/freedcamp/
├── api/
│   ├── auth/
│   │   ├── AuthHelper.java              # Session cookie caching (8h TTL)
│   │   └── AuthSignatureUtil.java       # HMAC signature generation
│   └── controllers/
│       ├── ProjectsController.java      # Project CRUD operations
│       ├── TasksController.java         # Task management API
│       ├── UsersController.java         # User operations
│       └── CalendarEventsController.java # Calendar API
├── testdata/
│   ├── CreatedProject.java              # Domain model for projects
│   ├── CreatedTask.java                 # Domain model for tasks
│   └── TimeRecord.java                  # Domain model for time logs
└── utils/
    ├── FreedcampConfig.java             # Owner-based configuration
    ├── RequestSpecFactory.java          # Rest-Assured spec builder
    ├── WebDriverPool.java               # Session management
    └── logging/
        ├── LoggingExtension.java        # JUnit5 MDC enrichment
        └── LogRequestFilter.java        # HTTP request/response logging

src/test/java/com/freedcamp/
├── tests/
│   ├── api/
│   │   ├── AuthTests.java               # Login, logout, negative scenarios
│   │   ├── ProjectTests.java            # Project API validation
│   │   ├── TaskTests.java               # Task CRUD tests
│   │   ├── UsersTests.java              # User management tests
│   │   └── CalendarEventTests.java      # Calendar event tests
│   ├── ui/
│   │   ├── AuthTests.java               # UI login flows
│   │   ├── ProjectTests.java            # Project creation via UI
│   │   ├── TasksTests.java              # Task management UI
│   │   ├── MilestonesTests.java         # Milestone workflows
│   │   ├── CalendarTests.java           # Calendar UI interactions
│   │   └── TimeRecordsTests.java        # Time logging UI
│   └── EndToEndTests.java               # Full user journey
├── pages/
│   ├── BasePage.java                    # Common page utilities
│   ├── LoginPage.java                   # Login page object
│   ├── DashboardPage.java               # Dashboard interactions
│   ├── ProjectsPage.java                # Projects page
│   └── components/
│       ├── TaskDrawer.java              # Task creation drawer
│       └── ProjectSidebar.java          # Project navigation
├── steps/
│   └── CommonSteps.java                 # Reusable business actions
└── assertions/
    └── ProjectAssertions.java           # Custom assertion helpers

src/test/resources/
├── freedcamp.properties.example         # Configuration template
├── freedcamp.properties                 # Local config (gitignored)
├── junit-platform.properties            # JUnit5 parallel config
└── logback-test.xml                     # Logging configuration
```

### Core Components

#### 1. Configuration Management (`FreedcampConfig`)
- **Technology**: [Owner library](https://matteobaccan.github.io/owner/) for type-safe config
- **Resolution order**: System properties → External file (`-PcredsFile`) → Classpath resource
- **Features**: Hot-reload, default values, validation, conversion

#### 2. Session Management (`AuthHelper`)
- **Cookie caching**: 8-hour TTL to avoid repeated logins
- **Thread-safe**: ConcurrentHashMap for parallel execution
- **UI injection**: Skip login page, inject session directly into browser

#### 3. API Layer (`RequestSpecFactory`)
- **Specifications**: Authenticated, unauthenticated, custom cookies
- **Filters**: Allure attachment, request/response logging, timing
- **Base path management**: Automatic endpoint resolution

#### 4. UI Layer (`BaseUiTest` + Page Objects)
- **Pattern**: Page Object Model with component extraction
- **Waits**: DOM ready, hydration complete, network idle detection
- **Screenshots**: On failure, on steps (configurable)
- **Browser management**: WebDriver pooling, automatic cleanup

#### 5. Test Data Management
- **Strategy**: Generate fresh data per test (DataFaker)
- **Cleanup**: Delete created entities in `@AfterEach`
- **Builders**: Fluent API for complex objects
- **Fixtures**: Template-based project creation via API

#### 6. Logging & Observability
- **MDC Context**: Test name, user, correlation ID injected per test
- **Structured logs**: JSON-friendly format for log aggregation
- **Request tracing**: Full HTTP request/response with timing

---

## ⚙️ Configuration & Setup

### Configuration Management
The framework uses **Owner library** for type-safe, hierarchical configuration. Configuration sources are resolved in this order (first wins):

1. **System properties** (highest priority)
   ```bash
   ./gradlew test -Dui.browser=firefox -Dui.headless=true
   ```

2. **External credentials file** (for CI/CD)
   ```bash
   ./gradlew test -PcredsFile=/path/to/secure-creds.properties
   ```

3. **Local `freedcamp.properties`** (classpath resource)
   ```properties
   baseUrl=https://freedcamp.com
   owner.email=your_email@example.com
   owner.password=your_password
   testUserId=12345
   projectTemplateId=67890
   ui.browser=chrome
   ui.headless=false
   ```

### Authentication Strategy

The framework implements **smart session management** to dramatically reduce test execution time:

**Traditional Approach** (slow):
- Each UI test logs in via UI → 5-10 seconds per test
- 40 UI tests × 8s = ~5 minutes wasted on logins

**Optimized Approach** (fast):
- `AuthHelper` performs API login **once** and caches session cookies (8-hour TTL)
- UI tests inject cached cookies directly into browser
- Navigate straight to authenticated pages (`/dashboard/home`)
- **Result**: 60% faster execution

**How it works**:
```java
// AuthHelper.java
public Map<String, String> getCookies(String email, String password) {
    String cacheKey = email + ":" + password;
    if (cache.containsKey(cacheKey) && !isExpired(cacheKey)) {
        return cache.get(cacheKey); // Return cached cookies
    }
    // Perform API login and cache cookies
    Map<String, String> cookies = performLogin(email, password);
    cache.put(cacheKey, cookies);
    return cookies;
}
```

For tests that **must** exercise the login UI (e.g., `AuthTests`), session injection is skipped.

### Technology Stack

| Library | Version | Purpose |
|---------|---------|---------|
| **Selenide** | 7.11.1 | Fluent WebDriver wrapper with smart waits |
| **Rest-Assured** | 5.5.5 | HTTP client for API testing |
| **JUnit 5** | 5.10.0 | Modern test engine with extensions |
| **Allure** | 2.29.0 | Rich HTML reports with attachments |
| **Owner** | 1.0.12 | Type-safe configuration management |
| **DataFaker** | 2.4.3 | Realistic test data generation |
| **AssertJ** | 3.24.2 | Fluent assertions library |
| **Lombok** | 1.18.28 | Boilerplate reduction |
| **Awaitility** | 4.2.0 | Asynchronous system testing |
| **Logback** | - | Structured logging with MDC |

---

## 🧪 Running Tests

### Test Tags Strategy

Tests are organized with JUnit 5 `@Tag` annotations for flexible execution:

| Tag | Description | Test Count | Execution Time |
|-----|-------------|------------|----------------|
| `Smoke` | Critical path tests | ~10 | ~2 min |
| `API` | Backend API tests | ~50 | ~3 min |
| `UI` | User interface tests | ~40 | ~8 min |
| `E2E` | End-to-end journeys | 1 | ~2 min |

### Basic Execution Commands

```bash
# Run all tests (full suite)
./gradlew clean test

# Run by tag
./gradlew test -DincludeTags=Smoke         # Fast smoke tests
./gradlew test -DincludeTags=API           # API tests only
./gradlew test -DincludeTags=UI            # UI tests only
./gradlew test -DincludeTags=API,UI        # Multiple tags

# Run specific test class
./gradlew test --tests "com.freedcamp.tests.api.AuthTests"
./gradlew test --tests "com.freedcamp.tests.ui.TasksTests"

# Run single test method
./gradlew test --tests "com.freedcamp.tests.ui.TasksTests.verifyLoggingTimeOnTask"

# Browser configuration
./gradlew test -Dui.headless=true          # Headless mode
./gradlew test -Dui.browser=firefox        # Different browser
```

### Parallel Execution

The framework supports **two-level parallelism** for optimal performance:

**Configuration Parameters:**
- `-Pforks` → Number of JVM processes (default: 1)
- `-PjunitThreads` → Threads per JVM (default: 2)
- **Effective concurrency** = `forks × junitThreads`

**Examples:**

```bash
# Conservative: 2 forks × 3 threads = 6 concurrent tests
./gradlew test -Pforks=2 -PjunitThreads=3

# API-optimized: High parallelism (no browser overhead)
./gradlew test -DincludeTags=API -Pforks=3 -PjunitThreads=6

# UI-optimized: Moderate parallelism (browser resource limits)
./gradlew test -DincludeTags=UI -Pforks=2 -PjunitThreads=4

# Smoke with parallelism
./gradlew test -DincludeTags=Smoke -Pforks=2 -PjunitThreads=3
```

**Tuning Guidelines:**
- **Increase `forks`** for CPU-bound tests or memory isolation
- **Increase `junitThreads`** for I/O-bound tests (API calls, network waits)
- Start with modest values (`-Pforks=2 -PjunitThreads=3`) and scale up
- Watch for server rate limiting and resource contention
- UI tests: Limit based on available CPU/memory (browsers are heavy)

---

## 📊 Reporting & Observability

### Allure Reports

Generate and view rich HTML reports with:

```bash
# Serve report dynamically (auto-opens browser)
allure serve build/allure-results

# Or generate static report
./gradlew allureReport
open build/reports/allure-report/index.html
```

**Captured Artifacts:**
- 📸 **Screenshots** on every failure + configurable steps
- 📄 **Page source** HTML dumps for debugging
- 🌐 **HTTP logs** with full request/response bodies
- ⏱️ **Timing metrics** for performance analysis
- 🏷️ **Tags & metadata** for filtering and trending

### Structured Logging

The framework uses **Logback with MDC enrichment**:

```
[2024-12-12 10:30:45] INFO [testName=verifyTaskCreation, user=test@example.com, corrId=abc123]
  POST https://freedcamp.com/api/tasks → 201 Created (345ms)
```

**MDC Context Keys:**
- `testName` - Current test method name
- `user` - Authenticated user email
- `corrId` - Correlation ID for request tracing

Logs are JSON-friendly for aggregation in ELK/Splunk/CloudWatch.

---

## 🚀 CI/CD Integration

### Jenkins Pipeline

The included `Jenkinsfile` demonstrates production-ready CI/CD:

**Pipeline Stages:**
1. **Checkout** - Clone repository and clean workspace
2. **Credentials Injection** - Securely inject `freedcamp.properties` via `withCredentials`
3. **Test Execution** - Run tests with tags and parallel configuration
4. **Reporting** - Publish JUnit XML and Allure reports

**Example Jenkins Configuration:**

```groovy
pipeline {
    agent any
    stages {
        stage('Test') {
            steps {
                withCredentials([file(credentialsId: 'freedcamp-creds', variable: 'CREDS_FILE')]) {
                    sh './gradlew clean test -DincludeTags=Smoke -PcredsFile="${CREDS_FILE}"'
                }
            }
        }
        stage('Report') {
            steps {
                junit 'build/test-results/test/*.xml'
                allure includeProperties: false, results: [[path: 'build/allure-results']]
            }
        }
    }
}
```

**Tag-based execution in CI:**
```bash
./gradlew test -DincludeTags=Smoke -PcredsFile=/var/jenkins/creds.properties
./gradlew test -DincludeTags=API,UI -Pforks=2 -PjunitThreads=4
```

---

## 🛠 Troubleshooting

### Common Issues & Solutions

| Issue | Root Cause | Solution |
|-------|-----------|----------|
| **401 Unauthorized API** | Missing/invalid credentials | Verify `freedcamp.properties` exists and contains correct credentials |
| **UI opens login page** | Session injection failed | Check `AuthHelper` cookie names match application; verify auth endpoint |
| **"No tests found"** | Incorrect test filter pattern | Use fully-qualified class name or wildcard: `*TasksTests*` |
| **Empty Allure report** | Results not generated | Ensure `build/allure-results` directory exists; check test execution logs |
| **Parallel test failures** | Shared mutable state | Use `@RepeatedTest` to detect; isolate test data with unique identifiers |
| **StaleElementException** | SPA hydration delays | Use `awaitDomAndHydration()` helper; increase Selenide timeout |
| **Rate limiting errors** | Too aggressive parallelism | Reduce `-Pforks` and `-PjunitThreads`; add delays between requests |

### Debug Mode

```bash
# Run with verbose logging
./gradlew test --info

# Debug specific test with breakpoints
./gradlew test --tests "TasksTests.verifyTaskCreation" --debug-jvm

# Disable headless to watch browser
./gradlew test -Dui.headless=false -DincludeTags=UI
```

---

## 📚 Best Practices

### Adding New Tests

**Checklist:**
1. ✅ Choose appropriate layer (API for speed, UI for user validation, E2E for critical flows)
2. ✅ Reuse existing Page Objects and Controllers - avoid inline locators
3. ✅ Add relevant `@Tag` annotations (`Smoke`, `API`, `UI`, `E2E`)
4. ✅ Use `DataFaker` for test data generation
5. ✅ Clean up created entities in `@AfterEach`
6. ✅ Use `AssertJ` fluent assertions for readability
7. ✅ Add Allure `@Step` annotations for critical actions
8. ✅ Follow naming convention: `shouldDoSomething_WhenCondition_ThenExpectedResult`

**Example:**
```java
@Test
@Tag("API")
@Tag("Smoke")
@DisplayName("Should create task successfully with all required fields")
void shouldCreateTask_WhenValidData_ThenReturnsCreatedTask() {
    // Given
    String taskName = faker.lorem().sentence();
    
    // When
    CreatedTask task = tasksController.createTask(projectId, taskName);
    
    // Then
    assertThat(task.getName()).isEqualTo(taskName);
    assertThat(task.getId()).isNotNull();
}
```

### Test Data Strategy

- **Use `DataFaker`** for dynamic, realistic data
- **Avoid hardcoded values** that may conflict in parallel execution
- **Capture generated IDs** for cleanup and assertions
- **Use template projects** for complex setup (via `TestDataSetupExtension`)

---

## 📖 Quick Reference

### Common Commands

```bash
# Full suite
./gradlew clean test

# Smoke tests (fastest)
./gradlew test -DincludeTags=Smoke

# API only with parallelism
./gradlew test -DincludeTags=API -Pforks=2 -PjunitThreads=6

# UI only, headless
./gradlew test -DincludeTags=UI -Dui.headless=true -Pforks=2 -PjunitThreads=3

# Single test class
./gradlew test --tests "com.freedcamp.tests.api.AuthTests"

# Single test method
./gradlew test --tests "com.freedcamp.tests.ui.TasksTests.verifyLoggingTimeOnTask"

# External credentials + parallel
./gradlew test -PcredsFile=$HOME/secure/creds.properties -Pforks=2 -PjunitThreads=4

# Generate & view Allure report
allure serve build/allure-results
```

### Configuration Overrides

```bash
# Browser configuration
-Dui.browser=chrome|firefox|edge
-Dui.headless=true|false

# Parallel execution
-Pforks=N              # Number of JVM processes
-PjunitThreads=M       # Threads per JVM

# Tag filtering
-DincludeTags=Smoke,API
-DexcludeTags=E2E

# Credentials
-PcredsFile=/path/to/file.properties
```

## 👤 Author

**Oleh Khomik**
- Demonstrates professional QA automation engineering skills
- Showcases enterprise-grade testing framework design
- Real-world SaaS application testing expertise

---

## 🤝 Contributing

This is a portfolio project, but feedback and suggestions are welcome! Feel free to:
- Report issues or bugs you encounter
- Suggest improvements to framework architecture
- Share best practices and patterns
---

**Built with ❤️ for Quality Engineering**
