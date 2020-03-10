[![Build Status](https://travis-ci.com/mP1/walkingkooka-route.svg?branch=master)](https://travis-ci.com/mP1/walkingkooka-route.svg?branch=master)
[![Coverage Status](https://coveralls.io/repos/github/mP1/walkingkooka-route/badge.svg?branch=master)](https://coveralls.io/github/mP1/walkingkooka-route?branch=master)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
[![Language grade: Java](https://img.shields.io/lgtm/grade/java/g/mP1/walkingkooka-route.svg?logo=lgtm&logoWidth=18)](https://lgtm.com/projects/g/mP1/walkingkooka-route/context:java)
[![Total alerts](https://img.shields.io/lgtm/alerts/g/mP1/walkingkooka-route.svg?logo=lgtm&logoWidth=18)](https://lgtm.com/projects/g/mP1/walkingkooka-route/alerts/)



# Basic Project

The basic building blocks for assembling required components for individual routes. Smarts are also included to
group routes sharing equivalent components. Components are given to the router as key/values within a `Map`.

Web routing specific `Router` is available in another github project at [walkingkooka-net](https://github.com/mP1/walkingkooka-net).
All interesting components of a Http request become individual key/values in a `Map`
- the security scheme: HTTP or HTTPS.
- the method
- individual header values
- individual cookies
- paths are separated into path components and named path-0, path-1, path-2...
- query parameters

The interface `HttpRequest` includes a default method `routerParameter()` that may be used to fetch the above mentioned,
parameters in a map ready for `Router`.

## Dependencies

- [https://github.com/mP1/walkingkooka](https://github.com/mP1/walkingkooka)
- junit

No actual releases are available, instead the latest snapshot may be referenced in a Maven POM.xml using
[https://jitpack.io](https://jitpack.io) repository.

```xml
    <repositories>
        <repository>
            <id>jitpack.io</id>
            <url>https://jitpack.io</url>
        </repository>
    </repositories>

    <dependencies>
       <dependency>
            <groupId>com.github.mP1</groupId>
            <artifactId>walkingkooka</artifactId>
            <version>master-SNAPSHOT</version>
        </dependency>
    </dependencies>
```

## Getting the source

You can either download the source using the "ZIP" button at the top
of the github page, or you can make a clone using git:

```
git clone git://github.com/mP1/walkingkooka-route.git
```
