# library
[![License](https://img.shields.io/badge/license-Apache%202.0-blue.svg)](LICENSE.txt)

[![Build & test](https://github.com/IQPlay/library/actions/workflows/gradle-build-test.yml/badge.svg?branch=main)](https://github.com/IQPlay/library/actions/workflows/gradle-build-test.yml)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=IQPlay_library&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=IQPlay_library)
<br/>
[![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=IQPlay_library&metric=reliability_rating)](https://sonarcloud.io/summary/new_code?id=IQPlay_library)
[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=IQPlay_library&metric=security_rating)](https://sonarcloud.io/summary/new_code?id=IQPlay_library)
[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=IQPlay_library&metric=bugs)](https://sonarcloud.io/summary/new_code?id=IQPlay_library)
[![Technical Debt](https://sonarcloud.io/api/project_badges/measure?project=IQPlay_library&metric=sqale_index)](https://sonarcloud.io/summary/new_code?id=IQPlay_library)
[![Lines of Code](https://sonarcloud.io/api/project_badges/measure?project=IQPlay_library&metric=ncloc)](https://sonarcloud.io/summary/new_code?id=IQPlay_library)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=IQPlay_library&metric=coverage)](https://sonarcloud.io/summary/new_code?id=IQPlay_library)
[![codecov](https://codecov.io/github/IQPlay/library/graph/badge.svg?token=vT0cGItXEP)](https://codecov.io/github/IQPlay/library)

## Getting Started

### Prerequisites

Ensure that the following are installed on your system:

- Java 21 or higher
- Gradle 8.10.2 or higher 

### 1. Configure Github Credentials

The package requires authentication, Add your GitHub credentials in a `gradle.properties` file located in your user directory `~/.gradle/gradle.properties`:

```bash
gpr.user=YourGitHubUsername
gpr.key=YourGitHubToken
```

Note: Ensure that your GitHub token has the `read:packages` permission to access the packages.

### 2. Add the Dependency
In your `build.gradle`, add the dependency for [IQPlayLib](https://github.com/IQPlay/library/packages/2304124):

```gradle
dependencies {
    implementation 'fr.parisnanterre:iqplaylib:{version}' // Replace {version} with the latest available version
}
```

### 3. Run the Application
Use the Gradle Wrapper to build the project and verify everything is set up correctly:
```bash
./gradlew clean build
```

## Example Usage
To create a custom quiz game using IQPlayLib, you can start by extending the `AbstractGame` class. Here’s a basic example:

```java

import fr.parisNanterre.iqplaylib.core.*;

public class CustomGame extends AbstractGame {

    public CustomGame(String name) {
        super(name);
    }

    @Override
    protected IGameSession createGameSession() {
        return new CustomGameSession(this);
    }
}
```

