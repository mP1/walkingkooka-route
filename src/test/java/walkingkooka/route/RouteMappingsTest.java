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
import walkingkooka.collect.list.Lists;
import walkingkooka.collect.map.Maps;
import walkingkooka.predicate.Predicates;
import walkingkooka.type.JavaVisibility;

import java.util.Map;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class RouteMappingsTest extends RouteMappingsTestCase<RouteMappings<String, Long>>
        implements RouterTesting {

    // add..............................................................................................................

    @Test
    public void testAdd() {
        final RouteMappings<String, Long> mappings = RouteMappings.empty();
        this.checkMappings(mappings);

        final RouteMappings<String, Long> mappings2 = mappings.add(this.keyToPredicates1(), TARGET1);
        this.checkMappings(mappings2, this.mapping1());
        this.checkMappings(mappings);
    }

    @Test
    public void testAdd2() {
        final RouteMappings<String, Long> mappings = RouteMappings.empty();
        this.checkMappings(mappings);

        final RouteMappings<String, Long> mappings2 = mappings.add(this.keyToPredicates1(), TARGET1);
        this.checkMappings(mappings2, this.mapping1());
        this.checkMappings(mappings);

        final RouteMappings<String, Long> mappings3 = mappings2.add(this.keyToPredicates2(), TARGET2);
        this.checkMappings(mappings3, this.mapping1(), this.mapping2());
        this.checkMappings(mappings2, this.mapping1());
        this.checkMappings(mappings);
    }


    private void checkMappings(final RouteMappings<String, Long> mappings,
                               final RouteMappingsMapping<String, Long>... mapping) {
        assertEquals(Lists.of(mapping), mappings.mappings, "mappings");
    }

    // router...........................................................................................................

    @Test
    public void testRouteNone() {
        this.routeFails(RouteMappings.<String, Long>empty()
                        .add(this.keyToPredicates1(), TARGET1)
                        .add(this.keyToPredicates1(), TARGET2)
                        .router(),
                Maps.of(KEY3C, VALUE3));
    }

    @Test
    public void testRouteNone2() {
        this.routeFails(RouteMappings.<String, Long>empty()
                        .add(this.keyToPredicates1(), TARGET1)
                        .add(this.keyToPredicates1(), TARGET2)
                        .add(this.keyToPredicates3(), TARGET3)
                        .router(),
                Maps.of(KEY3C, VALUE3));
    }

    @Test
    public void testRouteFirst() {
        this.routeAndCheck(RouteMappings.<String, Long>empty()
                        .add(this.keyToPredicates1(), TARGET1)
                        .add(this.keyToPredicates2(), TARGET2)
                        .router(),
                Maps.of(KEY1A, VALUE1A, KEY2B, VALUE2),
                TARGET1);
    }

    @Test
    public void testRouteBest() {
        this.routeAndCheck(RouteMappings.<String, Long>empty()
                        .add(this.keyToPredicates1(), TARGET1)
                        .add(this.keyToPredicates2(), TARGET2)
                        .add(this.keyToPredicates3(), TARGET3)
                        .router(),
                Maps.of(KEY1A, VALUE1A, KEY2B, VALUE2, KEY3C, VALUE3),
                TARGET3);
    }

    @Test
    public void testRouteLast() {
        this.routeAndCheck(RouteMappings.<String, Long>empty()
                        .add(this.keyToPredicates3(), TARGET3)
                        .add(this.keyToPredicates1(), TARGET1)
                        .router(),
                Maps.of(KEY1A, VALUE1A, KEY2B, VALUE2),
                TARGET1);
    }

    // toString.........................................................................................................

    @Test
    public void testToString() {
        this.toStringAndCheck(this.mappings(), Lists.of(this.keyToPredicates1() + "->" + TARGET1).toString());
    }

    // helper...........................................................................................................

    private RouteMappings<String, Long> mappings() {
        return RouteMappings.<String, Long>empty()
                .add(this.keyToPredicates1(), TARGET1);
    }

    private RouteMappingsMapping<String, Long> mapping1() {
        return RouteMappingsMapping.with(this.keyToPredicates1(), TARGET1);
    }

    private final static String KEY1A = "key-1a";
    private final static String KEY2B = "key-2b";
    private final static String KEY3C = "key-3c";

    private final static int VALUE1A = 1;
    private final static int VALUE2 = 99;
    private final static int VALUE3 = 888;
    private final static int VALUE1B = 98;

    private final static Long TARGET1 = 100L;
    private final static Long TARGET2 = 200L;
    private final static Long TARGET3 = 300L;

    final Map<String, Predicate<?>> keyToPredicates1() {
        return Maps.of(KEY1A, Predicates.is(VALUE1A), KEY2B, Predicates.is(VALUE2));
    }

    private RouteMappingsMapping<String, Long> mapping2() {
        return RouteMappingsMapping.with(this.keyToPredicates2(), TARGET2);
    }

    final Map<String, Predicate<?>> keyToPredicates2() {
        return Maps.of(KEY1A, Predicates.is(VALUE1B), KEY2B, Predicates.is(VALUE2));
    }

    private RouteMappingsMapping<String, Long> mapping3() {
        return RouteMappingsMapping.with(this.keyToPredicates3(), TARGET3);
    }

    final Map<String, Predicate<?>> keyToPredicates3() {
        return Maps.of(KEY1A, Predicates.is(VALUE1A), KEY2B, Predicates.is(VALUE2), KEY3C, Predicates.is(VALUE3));
    }

    // ClassTesting.....................................................................................................

    @Override
    public Class<RouteMappings<String, Long>> type() {
        return Cast.to(RouteMappings.class);
    }

    @Override
    public final JavaVisibility typeVisibility() {
        return JavaVisibility.PUBLIC;
    }

    // TypeNameTesting..................................................................................................

    @Override
    public String typeNameSuffix() {
        return "";
    }
}
