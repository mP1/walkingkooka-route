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

package walkingkooka.routing;

import org.junit.jupiter.api.Test;
import walkingkooka.test.ToStringTesting;
import walkingkooka.test.TypeNameTesting;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertThrows;

public interface RouterTesting2<R extends Router<K, T>, K, T> extends RouterTesting,
        ToStringTesting<R>,
        TypeNameTesting<R> {

    @Test
    default void testRouteNullParametersFails() {
        assertThrows(NullPointerException.class, () -> {
            this.createRouter().route(null);
        });
    }

    R createRouter();

    default void routeAndCheck(final Map<K, Object> parameters, final T target) {
        this.routeAndCheck(this.createRouter(),
                parameters,
                target);
    }

    default void routeFails(final Map<K, Object> parameters) {
        this.routeFails(this.createRouter(),
                parameters);
    }

    // TypeNameTesting .................................................................................................

    @Override
    default String typeNamePrefix() {
        return "";
    }

    @Override
    default String typeNameSuffix() {
        return Router.class.getSimpleName();
    }
}

