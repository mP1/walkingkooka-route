/*
 * Copyright © 2020 Miroslav Pokorny
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
 */
package test;


import com.google.j2cl.junit.apt.J2clTestInput;
import org.junit.Assert;
import org.junit.Test;

import walkingkooka.collect.map.Maps;
import walkingkooka.predicate.Predicates;
import walkingkooka.route.RouteMappings;
import walkingkooka.route.Router;

import java.util.Optional;
import java.util.function.Predicate;

@J2clTestInput(JunitTest.class)
public final class JunitTest {

    // Mostly copied from walkingkooka.route.sample.Sample
    @Test
    public void testBasicHttpRouter() {
        final RouteMappings<String, Integer> mappings = RouteMappings.<String, Integer>empty()
            .add(Maps.of("protocol", Predicates.is("http"), "host", Predicate.isEqual("example.com")), 12)
            .add(Maps.of("protocol", Predicates.is("http"), "host", Predicate.isEqual("example2.com")), 34)
            .add(Maps.of("protocol", Predicates.is("https")), 56);
        final Router<String, Integer> router = mappings.router();

        final Optional<Integer> https = router.route(Maps.of("protocol", "https")); // should return 56
        final Optional<Integer> http = router.route(Maps.of("protocol", "http")); // 12 and 34 both match returns nothing.
        final Optional<Integer> httpExample = router.route(Maps.of("protocol", "http", "host", "example.com")); // should return 12

        Assert.assertEquals(Optional.of(56), https);
        Assert.assertEquals(Optional.empty(), http);
        Assert.assertEquals(Optional.of(12), httpExample);
    }
}
