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
import walkingkooka.reflect.ClassTesting;
import walkingkooka.reflect.JavaVisibility;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

public final class RouterCollectionTest implements RouterTesting2<RouterCollection<String, Integer>, String, Integer>,
    ClassTesting<RouterCollection<String, Integer>> {

    // with.............................................................................................................

    @Test
    public void testWithNullFails() {
        assertThrows(
            NullPointerException.class,
            () -> RouterCollection.with(null)
        );
    }

    @Test
    public void testWithEmptyFails() {
        assertThrows(
            IllegalArgumentException.class,
            () -> RouterCollection.with(
                Lists.empty()
            )
        );
    }

    @Test
    public void testWithOneRouter() {
        final Router<String, Integer> router = Routers.fake();

        assertSame(
            RouterCollection.with(
                Lists.of(router)
            ),
            router
        );
    }

    @Test
    public void testWithMany() {
        final Router<String, Integer> router1 = Routers.fake();
        final Router<String, Integer> router2 = Routers.fake();

        final RouterCollection<String, Integer> routerCollection = Cast.to(
            RouterCollection.with(
                Lists.of(
                    router1,
                    router2
                )
            )
        );

        this.checkEquals(
            Lists.of(
                router1,
                router2
            ),
            routerCollection.routers
        );
    }

    @Override
    public RouterCollection<String, Integer> createRouter() {
        return Cast.to(
            RouterCollection.with(
                Lists.of(
                    new FakeRouter<>() {

                        @Override
                        public String toString() {
                            return "Router1";
                        }
                    },
                    new FakeRouter<>() {

                        @Override
                        public String toString() {
                            return "Router2";
                        }
                    }
                )
            )
        );
    }

    // toString.........................................................................................................

    @Test
    public void testToString() {
        this.toStringAndCheck(
            this.createRouter(),
            "[Router1, Router2]"
        );
    }

    // class............................................................................................................

    @Override
    public Class<RouterCollection<String, Integer>> type() {
        return Cast.to(RouterCollection.class);
    }

    @Override
    public JavaVisibility typeVisibility() {
        return JavaVisibility.PACKAGE_PRIVATE;
    }

    @Override
    public void testTypeNaming() {
        throw new UnsupportedOperationException();
    }
}
