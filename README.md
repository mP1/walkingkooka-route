[![Build Status](https://github.com/mP1/walkingkooka-route/actions/workflows/build.yaml/badge.svg)](https://github.com/mP1/walkingkooka-route/actions/workflows/build.yaml/badge.svg)
[![Coverage Status](https://coveralls.io/repos/github/mP1/walkingkooka-route/badge.svg?branch=master)](https://coveralls.io/github/mP1/walkingkooka-route?branch=master)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
[![Language grade: Java](https://img.shields.io/lgtm/grade/java/g/mP1/walkingkooka-route.svg?logo=lgtm&logoWidth=18)](https://lgtm.com/projects/g/mP1/walkingkooka-route/context:java)
[![Total alerts](https://img.shields.io/lgtm/alerts/g/mP1/walkingkooka-route.svg?logo=lgtm&logoWidth=18)](https://lgtm.com/projects/g/mP1/walkingkooka-route/alerts/)
![](https://tokei.rs/b1/github/mP1/walkingkooka-router)
[![J2CL compatible](https://img.shields.io/badge/J2CL-compatible-brightgreen.svg)](https://github.com/mP1/j2cl-central)



# Router

The following example builds a very simple, totally useless router that dispatches urls. This examples constructs a router
where all some components of a URL have been decomposed into components, and several paths have all their required components
and predicates defined. The router will then return the target value only if only a single rule is entirely matched.

```java
final RouteMappings<String, Integer> mappings = RouteMappings.<String, Integer>empty()
        .add(Maps.of("protocol", Predicates.is("http"), "host", Predicate.isEqual("example.com")), 12)
        .add(Maps.of("protocol", Predicates.is("http"), "host", Predicate.isEqual("example2.com")), 34)
        .add(Maps.of("protocol", Predicates.is("https")), 56);
final Router<String, Integer> router = mappings.router();

final Optional<Integer> https = router.route(Maps.of("protocol", "https")); // should return 56
final Optional<Integer> http = router.route(Maps.of("protocol", "http")); // 12 and 34 both match returns nothing.
final Optional<Integer> httpExample = router.route(Maps.of("protocol", "http", "host", "example.com")); // should return 12

assertEquals(Optional.of(56), https);
assertEquals(Optional.empty(), http); // matches two rules, there no value
assertEquals(Optional.of(12), httpExample);
```


## [walkingkooka-net](https://github.com/mP1/walkingkooka-net)

This projects takes this project to the next level, supporting expressing rules about all components within a http request.



