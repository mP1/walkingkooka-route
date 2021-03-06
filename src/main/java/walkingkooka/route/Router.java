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

import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * Represents a container of routes, ready to accept parameters with a goal of returning the matching target processing
 * the rules for each route.
 */
public interface Router<K, T> {

    /**
     * Accepts some parameters and attempt to locate the only target.
     */
    Optional<T> route(final Map<K, Object> parameters) throws RouteException;

    /**
     * Returns a {@link Router} which chain tests against this {@link Router} followed by the router parameter..
     */
    default Router<K, T> then(final Router<K, T> router) {
        Objects.requireNonNull(router, "router");

        return ChainRouter.with(this, router);
    }
}
