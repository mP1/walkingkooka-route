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

import walkingkooka.collect.list.Lists;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * Creates a collection of {@link Router}, which will be tried in the given order when {@link #route(Map)} is invoked.
 */
final class RouterCollection<K,V> implements Router<K, V> {
    
    static <K, V> Router<K, V> with(final List<Router<K, V>> routers) {
        Objects.requireNonNull(routers, "routers");

        final List<Router<K, V>> copy = Lists.immutable(routers);

        Router<K, V> router;
        final int count = copy.size();

        switch (count) {
            case 0:
                throw new IllegalArgumentException("Expected at least 1 converter but got 0");
            case 1:
                router = copy.get(0);
                break;
            default:
                router = new RouterCollection<>(copy);
                break;
        }

        return router;
    }
    
    private RouterCollection(final Collection<Router<K, V>> routers) {
        this.routers = routers;
    }

    // Router...........................................................................................................

    @Override
    public Optional<V> route(final Map<K, Object> parameters) {
        Objects.requireNonNull(parameters, "parameters");

        Optional<V> routed = Optional.empty();

        for (final Router<K, V> router : this.routers) {
            routed = router.route(parameters);
            if(routed.isPresent()) {
                break;
            }
        }

        return routed;
    }

    // @VisibleForTesting
    final Collection<Router<K, V>> routers;

    // Object...........................................................................................................

    @Override
    public String toString() {
        return this.routers.toString();
    }
}
