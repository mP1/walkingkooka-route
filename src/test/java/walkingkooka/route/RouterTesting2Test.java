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
import org.opentest4j.AssertionFailedError;
import walkingkooka.collect.map.Maps;
import walkingkooka.route.RouterTesting2Test.TestRouter;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

public final class RouterTesting2Test implements RouterTesting2<TestRouter, Void, RouterTesting2Test> {

    private final static Map<Void, Object> PARAMETERS = Maps.fake();

    @Test
    public void testRouteNullParametersFails() {
        new TestRouterTesting2() {

            @Override
            public TestRouter createRouter() {
                return new TestRouter(null);
            }
        }.testRouteNullParametersFails();
    }

    @Test
    public void testRouteNullParametersFailsFails() {
        assertThrows(AssertionFailedError.class, () -> new TestRouterTesting2() {

            @Override
            public TestRouter createRouter() {
                return new TestRouter(null) {
                    public Optional<RouterTesting2Test> route(final Map<Void, Object> parameters) throws RouteException {
                        return Optional.empty();
                    }
                };
            }
        }.testRouteNullParametersFails());
    }

    @Test
    public void testRouteAndCheckParametersTarget() {
        final RouterTesting2Test TARGET = new RouterTesting2Test();
        new TestRouterTesting2() {

            @Override
            public TestRouter createRouter() {
                return new TestRouter(TARGET);
            }
        }.routeAndCheck(PARAMETERS, TARGET);
    }

    @Test
    public void testRouteAndCheckParametersTargetFails() {
        final RouterTesting2Test TARGET = new RouterTesting2Test();

        assertThrows(AssertionFailedError.class, () -> new TestRouterTesting2() {

            @Override
            public TestRouter createRouter() {
                return new TestRouter(null);
            }
        }.routeAndCheck(PARAMETERS, TARGET));
    }

    @Test
    public void testRouteFailsParameters() {
        new TestRouterTesting2() {

            @Override
            public TestRouter createRouter() {
                return new TestRouter(null);
            }
        }.routeFails(PARAMETERS);
    }

    @Test
    public void testRouteFailsParametersFails() {
        final RouterTesting2Test TARGET = new RouterTesting2Test();

        assertThrows(AssertionFailedError.class, () -> new TestRouterTesting2() {

            @Override
            public TestRouter createRouter() {
                return new TestRouter(TARGET);
            }
        }.routeFails(PARAMETERS));
    }

    @Override
    public TestRouter createRouter() {
        return new TestRouter(this);
    }

    @Override
    public Class<TestRouter> type() {
        return TestRouter.class;
    }

    static class TestRouter implements Router<Void, RouterTesting2Test> {

        private TestRouter(final RouterTesting2Test result) {
            this.result = result;
        }

        @Override
        public Optional<RouterTesting2Test> route(final Map<Void, Object> parameters) throws RouteException {
            Objects.requireNonNull(parameters, "parameters");
            assertSame(PARAMETERS, parameters, "parameters");
            return Optional.ofNullable(this.result);
        }

        private final RouterTesting2Test result;

        @Override
        public final String toString() {
            return this.getClass().getSimpleName();
        }
    }

    static abstract class TestRouterTesting2 implements RouterTesting2<TestRouter, Void, RouterTesting2Test> {

        @Override
        public final Class<TestRouter> type() {
            return TestRouter.class;
        }
    }
}
