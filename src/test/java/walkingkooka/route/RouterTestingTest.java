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

import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

public final class RouterTestingTest implements RouterTesting {

    private final static Map<Void, Object> PARAMETERS = Maps.fake();

    @Test
    public void testRouteAndCheck() {
        final RouterTestingTest target = this;

        this.routeAndCheck(this.router(target),
                PARAMETERS,
                target);
    }

    @Test
    public void testRouteAndCheckFails() {
        final RouterTestingTest target = this;

        assertThrows(AssertionFailedError.class, () -> {
            this.routeAndCheck(this.router(null),
                    PARAMETERS,
                    target);
        });
    }

    @Test
    public void testRouteFails() {
        this.routeFails(this.router(null), PARAMETERS);
    }

    @Test
    public void testRouteFailsFails() {
        assertThrows(AssertionFailedError.class, () -> {
            this.routeFails(this.router(this), PARAMETERS);
        });
    }

    private Router<Void, RouterTestingTest> router(final RouterTestingTest result) {
        return new Router<>() {
            @Override
            public Optional<RouterTestingTest> route(final Map<Void, Object> parameters) throws RouteException {
                assertSame(PARAMETERS, parameters, "parameters");
                return Optional.ofNullable(result);
            }
        };
    }
}
