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

import static org.junit.jupiter.api.Assertions.assertThrows;

public final class RouteMappingsRouterTest extends RouteMappingsTestCase2<RouteMappingsRouter<String, Integer>> {

    @Test
    public void testEmptyMappingsFails() {
        assertThrows(IllegalStateException.class, () -> {
            RouteMappingsRouter.with(Lists.empty());
        });
    }

    // toString.........................................................................................................

    @Test
    public void testToString() {
        this.toStringAndCheck(this.router(), "[{key-1a=1, key-2b=2, key-3c=3}->1333, {key-1a=1, key-2b=2}->1000, {key-1a=1}->1022]");
    }

    // helper...........................................................................................................

    private RouteMappingsRouter<String, Integer> router() {
        return RouteMappingsRouter.with(Lists.of(this.mapping1(), this.mapping2(), this.mapping3()));
    }

    private RouteMappingsMapping<String, Integer> mapping1() {
        return RouteMappingsMapping.with(Maps.of("key-1a", Predicates.is(1), "key-2b", Predicates.is(2)),
                1000);
    }

    private RouteMappingsMapping<String, Integer> mapping2() {
        return RouteMappingsMapping.with(Maps.of("key-1a", Predicates.is(1)),
                1022);
    }

    private RouteMappingsMapping<String, Integer> mapping3() {
        return RouteMappingsMapping.with(Maps.of("key-1a", Predicates.is(1), "key-2b", Predicates.is(2), "key-3c", Predicates.is(3)),
                1333);
    }

    // ClassTesting.....................................................................................................

    @Override
    public Class<RouteMappingsRouter<String, Integer>> type() {
        return Cast.to(RouteMappingsRouter.class);
    }

    // TypeNameTesting..................................................................................................

    @Override
    public String typeNameSuffix() {
        return Router.class.getSimpleName();
    }
}
