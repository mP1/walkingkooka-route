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

package walkingkooka.route;

import org.junit.jupiter.api.Test;
import walkingkooka.Cast;
import walkingkooka.collect.map.Maps;
import walkingkooka.compare.ComparableTesting2;
import walkingkooka.predicate.Predicates;

import java.util.Map;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.assertThrows;

public final class RouteMappingsMappingTest extends RouteMappingsTestCase2<RouteMappingsMapping<String, Integer>>
        implements ComparableTesting2<RouteMappingsMapping<String, Integer>> {

    @Test
    public void testWithNullKeyToPredicatesFails() {
        assertThrows(NullPointerException.class, () -> {
            RouteMappingsMapping.with(null, this.target());
        });
    }

    @Test
    public void testWithNullTargetFails() {
        assertThrows(NullPointerException.class, () -> {
            RouteMappingsMapping.with(this.keyToPredicates(), null);
        });
    }

    // Comparable.......................................................................................................

    @Test
    public void testLess() {
        this.compareToAndCheckLess(RouteMappingsMapping.with(Maps.of("key-1a", Predicates.is(1)), 98));
    }

    @Test
    public void testMore() {
        this.compareToAndCheckMore(RouteMappingsMapping.with(Maps.of("key-1a", Predicates.is(1),
                "key-2b", Predicates.is(2),
                "key-3c", Predicates.is(3)), 98));
    }

    @Override
    public RouteMappingsMapping<String, Integer> createComparable() {
        return this.mapping();
    }

    // toString.........................................................................................................

    @Test
    public void testToString() {
        this.toStringAndCheck(this.mapping(), this.keyToPredicates() + "->" + this.target());
    }

    // helper...........................................................................................................

    private RouteMappingsMapping<String, Integer> mapping() {
        return RouteMappingsMapping.with(this.keyToPredicates(), this.target());
    }

    final Map<String, Predicate<?>> keyToPredicates() {
        return Maps.of("key-1a", Predicates.is(1), "key-2b", Predicates.is(99));
    }

    final Integer target() {
        return 100;
    }

    // ClassTesting.....................................................................................................

    @Override
    public Class<RouteMappingsMapping<String, Integer>> type() {
        return Cast.to(RouteMappingsMapping.class);
    }

    // TypeNameTesting..................................................................................................

    @Override
    public String typeNameSuffix() {
        return "Mapping";
    }
}
