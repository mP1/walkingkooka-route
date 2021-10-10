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

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

/**
 * Holds zero or more mappings and can be used to build a {@link Router}.
 */
public final class RouteMappings<K, T> {

    /**
     * An empty {@link RouteMappings}.
     */
    public static <K, V> RouteMappings<K, V> empty() {
        return INSTANCE;
    }

    private final static RouteMappings INSTANCE = new RouteMappings(Lists.empty());

    private RouteMappings(final List<RouteMappingsMapping<K, T>> mappings) {
        super();
        this.mappings = mappings;
    }

    /**
     * Adds another mapping.
     */
    public RouteMappings<K, T> add(final Map<? extends K, Predicate<?>> keyToPredicates,
                                   final T target) {
        List<RouteMappingsMapping<K, T>> copy = Lists.array();
        copy.addAll(this.mappings);
        copy.add(RouteMappingsMapping.with(keyToPredicates, target));

        return new RouteMappings<>(copy);
    }

    /**
     * Returns a {@link Router} for all the given mappings.
     */
    public Router<K, T> router() {
        return RouteMappingsRouter.with(this.mappings);
    }

    /**
     * A {@link List} of mappings in add order which may or may not have duplicate mappings.
     */
    final List<RouteMappingsMapping<K, T>> mappings;

    // Object...........................................................................................................

    @Override
    public String toString() {
        return this.mappings.toString();
    }
}
