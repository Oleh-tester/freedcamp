# 🔍 Freedcamp Automation Framework

**Author:** Oleh Khomik  
**Tech Stack:** Java 17 · Gradle · JUnit 5 · Selenide · Rest-Assured · Allure · Owner · Lombok · AssertJ · DataFaker · Logback  
**Status:** 🚀 Evolving (API + UI smoke/core coverage)

---
## 🧭 Overview
This repository demonstrates a layered, maintainable automated testing framework for the real SaaS product [Freedcamp](https://freedcamp.com/). It blends fast API validation with higher‑value UI flows while maximizing reuse, readability, and CI friendliness.

Key design goals:
- Fast feedback via API tests (login, users, tasks, projects, events)
- Stable, pre‑authenticated UI flows (session cookie injection) to skip fragile login UIs
- Clean separation of concerns (config, auth, data setup, page objects, assertions, steps)
- Deterministic, tagged execution for selective pipelines (smoke, api, ui, e2e)
- Rich reporting & diagnosability (Allure + structured logging + request/response capture)
- Extensible utilities (HMAC signing, dynamic request specs, data factories)

---
## 🗂 Project Structure
```
src/
 ├── main/java/com.freedcamp/
 │   ├── api/                # Controllers & auth helpers
 │   │    ├── auth/          # Session + HMAC helpers (AuthHelper, AuthSignatureUtil)
 │   │    ├── controllers/   # REST endpoints aggregation
 │   │    └── models/        # POJOs / DTOs (if any)
 │   ├── testdata/           # Domain objects & builders for test entities
 │   ├── utils/              # Config (Owner), request specs, waiters, logging
 │   │    └── logging/       # Log filters + JUnit extension (MDC enrichers)
 │   └── common/             # (Future shared abstractions)
 ├── test/java/com/freedcamp/
 │   ├── tests/api/          # API test classes (BaseApiTest)
 │   ├── tests/ui/           # UI test classes (BaseUiTest)
 │   ├── pages/              # Selenide Page Objects & Components
 │   ├── steps/              # Higher-level reusable business actions
 │   ├── assertions/         # Custom assertion helpers
 │   └── testdata/utils/     # JUnit extensions (TestDataSetupExtension, etc.)
 └── test/resources/
      ├── freedcamp.properties.example  # Template (copy -> freedcamp.properties)
      ├── junit-platform.properties     # JUnit 5 platform config
      ├── logback-test.xml              # Logging pattern + MDC keys
      └── freedcamp.properties          # (Local ONLY; gitignored)
```

---
## 📦 Dependencies (Highlights)
| Library | Purpose |
|---------|---------|
| Selenide 7.x | Simplified Selenium wrapper & conditions |
| Rest-Assured 5.x | Fluent HTTP client for API tests |
| JUnit 5 | Modern test engine + extensions model |
| Allure | Unified reporting of API + UI actions |
| Owner | Typed runtime configuration resolution |
| Lombok | Boilerplate reduction |
| AssertJ | Fluent assertions |
| DataFaker | Randomized but controlled data generation |
| Awaitility | Async polling for eventual consistency |

---
## ⚙️ Configuration Layer
Managed by `Owner` via `FreedcampConfig` interface.

Sources resolution order (earlier overrides later):
1. System properties (`-DbaseUrl=... -Downer.email=...`)
2. External file passed by Gradle property `-PcredsFile=path/to/file.properties`
3. Classpath resource `freedcamp.properties`

`FreedcampConfig` keys:
```
baseUrl=https://your-host
ui.browser=chrome
ui.headless=true|false
owner.email=...
owner.password=...
testUserId=...
projectTemplateId=...
```

Sample `freedcamp.properties` (NEVER commit secrets):
```
baseUrl=https://freedcamp.com
ui.browser=chrome
ui.headless=false
owner.email=your_user@domain.com
owner.password=secret
```

Passing an external credentials file (e.g., Jenkins secured file credential):
```
./gradlew test -PcredsFile=/tmp/secured_creds.properties
```

---
## 🔐 Authentication & Session Handling
`AuthHelper` performs a login once per logical identity and caches the session cookies with a TTL (8 hours). UI tests inject this session directly:
1. Open base URL to initialize driver
2. Clear cookies
3. Add cached session cookies
4. Navigate directly to an authenticated page (`/dashboard/home`)

Benefits:
- Avoid repeated UI logins → faster suite
- Lower flakiness surface

If a test must exercise the login UI, annotate it with a custom opt‑out annotation (e.g., `@SkipSessionInjection`).

---
## 🌐 API Layer
`RequestSpecFactory` centralizes Rest-Assured specs:
- Authenticated spec with session cookies
- Unauthenticated spec for negative/login tests
- Optional cookie injection method `getSpecWithCookies(Map<String,String>)`

Logging & Allure integration applied via filters (`LogRequestFilter`, `AllureRestAssured`).

---
## 💻 UI Layer
`BaseUiTest` sets Selenide configuration (browser, headless, timeouts) from `FreedcampConfig`. Allure listener captures:
- Screenshots (on every failure & optionally on steps)
- Page source

Page Objects reside under `pages/` and use concise XPaths / semantic locators; components (drawers, sidebars) extracted for reuse.

Session cookie injection drastically shortens test runtime; ensure any stateful UI preconditions (e.g., created project) are prepared via API helpers or `TestDataSetupExtension`.

---
## 🏷 Tagging Strategy
JUnit `@Tag` values in the suite:
- `smoke` – High-value quick subset
- `e2e` – Cross-layer extended flows

Running by tag (Preferred JUnit 5 syntax):
```
./gradlew test -Djunit.jupiter.tags=smoke
```
(Previous `-Dtags=` placeholder in earlier README is replaced by official JUnit property.)

Exclude tags:
```
./gradlew test -Djunit.jupiter.exclude.tags=e2e
```

Run a single test class:
```
./gradlew test --tests "com.freedcamp.tests.api.AuthTests"
```

Run a single method:
```
./gradlew test --tests "com.freedcamp.tests.ui.TasksTests.verifyLoggingTimeOnTask"
```
---
## ✅ Running the Suite
Full clean run:
```
./gradlew clean test
```
API only:
```
./gradlew test --tests "com.freedcamp.tests.api"
```
UI:
```
./gradlew test --tests "com.freedcamp.tests.ui"
```
Headless mode (override at runtime):
```
./gradlew test -Dui.headless=true
```
Different browser:
```
./gradlew test -Dui.browser=firefox
```

---
## 📊 Allure Reporting
Generate dynamic (serves locally):
```
allure serve build/allure-results
```
Gradle task (if configured):
```
./gradlew allureReport
open build/reports/allure-report/index.html
```
Artifacts captured:
- Request/response (API)
- Steps + screenshots (UI)
- Attachments on failure

---
## 🧪 Test Data & Extensions
`TestDataSetupExtension` can provision seed entities (e.g., project from template) and inject them as parameters (ensure a matching `ParameterResolver` exists). Domain objects under `testdata/` represent created entities (Project, Task, TimeRecord, etc.).

Use `DataFaker` (`faker`) for randomized but bounded inputs; avoid randomness in identifiers that must be asserted later unless captured/stored.

---
## 🧾 Logging & Observability
- `logback-test.xml` provides enriched pattern with MDC keys: testName / user / corrId
- `LoggingExtension` populates MDC per test lifecycle
- `LogRequestFilter` logs HTTP method + URI + status (and can be extended for bodies / correlation IDs)

Sample log line:
```
INFO  [AuthTests#login_valid][owner@freedcamp][1b2c3d] c.f.api.controllers.LoginController - POST /iapi/auth/login 200
```

---
## 🛠 Jenkins Pipeline
`Jenkinsfile` stages:
1. Checkout & workspace clean
2. Secure creds injection (`withCredentials` file) ➜ passed as `-PcredsFile`
3. Run targeted tests (`--tests "com.freedcamp.tests.ui*"` sample)
4. Publish JUnit + conditionally Allure

Adapting for tags in Jenkins:
```
env.TAGS = 'smoke'
sh "./gradlew clean test -Djunit.jupiter.tags=${TAGS} -PcredsFile=\"${CREDS_FILE}\""
```

---
## 🧩 Adding New Tests (Checklist)
1. Decide layer (API vs UI vs e2e) and appropriate package
2. Reuse or extend existing controller / page object; avoid inline locators in tests
3. Add a suitable `@Tag`
4. Use `AssertJ` for fluent assertions
5. Attach any custom artifacts via Allure step or attachment when valuable
6. Keep test method names descriptive (`verb_condition_expectedResult` pattern encouraged)

---
## 🚧 Common Pitfalls & Troubleshooting
| Issue | Cause | Resolution |
|-------|-------|------------|
| 401 / unauthorized API | Missing/incorrect creds | Verify `freedcamp.properties` or `-PcredsFile` path |
| UI test opens login page instead of dashboard | Session injection failed | Check cookie names; ensure auth endpoint still stable |
| `No tests found for given includes` | Wrong `--tests` pattern | Use fully-qualified or wildcard like `*TasksTests*` |
| Allure report empty | No results produced | Ensure `allure.results.directory` system property set by Gradle (already configured) |
| Stale element / timing | SPA hydration delays | Add or extend wait helpers (`awaitDomAndHydration`, custom network idle wait) |

---
## 🧪 Quality & Future Enhancements
Planned / Suggested:
- Add contract tests with schema validation
- Introduce retry logic for known transient UI actions
- Integrate Visual regression (baseline screenshots) for critical views
- Dockerized Selenium grid for scaled parallel UI runs
- Report analytics trend (historical stability badges)
---
## 🧠 Quick Reference (Cheat Sheet)
```
# Smoke subset
./gradlew test -Djunit.jupiter.tags=smoke

# Single test method
./gradlew test --tests "com.freedcamp.tests.api.AuthTests.login_valid"

# Headless UI
./gradlew test -Djunit.jupiter.tags=ui -Dui.headless=true

# Parallel (after enabling in junit-platform.properties)
./gradlew test -Djunit.jupiter.tags=api -DforkCount=1
```

---
Feedback & ideas welcome – iterate fast, keep the feedback loop short, and prioritize reliability over breadth.