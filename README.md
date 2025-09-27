# WeatherBuddy Webservice

## Tests

- Run all tests:

```bash
mvn test
```

- Run a specific test class:

```bash
mvn -Dtest=com.home.weatherbuddy.controller.OutboundControllerTest test
```

## Code coverage (JaCoCo)

Coverage is generated automatically on `mvn verify`.

- Generate coverage and build:

```bash
mvn verify
```

- Open the HTML report:

`target/site/jacoco/index.html`

If you need to regenerate without running verify, you can also run:

```bash
mvn jacoco:report
```
