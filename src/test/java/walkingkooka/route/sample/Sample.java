/*
 * Copyright 2019 Miroslav Pokorny (github.com/mP1)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package walkingkooka.route.sample;

import org.junit.jupiter.api.Assertions;
import walkingkooka.collect.map.Maps;
import walkingkooka.predicate.Predicates;
import walkingkooka.route.RouteMappings;
import walkingkooka.route.Router;

import java.util.Optional;
import java.util.function.Predicate;

public final class Sample {
    public static void main(final String[] args) {
        final RouteMappings<String, Integer> mappings = RouteMappings.<String, Integer>empty()
            .add(Maps.of("protocol", Predicates.is("http"), "host", Predicate.isEqual("example.com")), 12)
            .add(Maps.of("protocol", Predicates.is("http"), "host", Predicate.isEqual("example2.com")), 34)
            .add(Maps.of("protocol", Predicates.is("https")), 56);
        final Router<String, Integer> router = mappings.router();

        final Optional<Integer> https = router.route(Maps.of("protocol", "https")); // should return 56
        final Optional<Integer> http = router.route(Maps.of("protocol", "http")); // 12 and 34 both match returns nothing.
        final Optional<Integer> httpExample = router.route(Maps.of("protocol", "http", "host", "example.com")); // should return 12

        Assertions.assertEquals(Optional.of(56), https);
        Assertions.assertEquals(Optional.empty(), http);
        Assertions.assertEquals(Optional.of(12), httpExample);
    }
}
