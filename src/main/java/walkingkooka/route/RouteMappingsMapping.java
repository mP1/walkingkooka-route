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

import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

/**
 * Represents a single mapping with one or more key to predicates and the target that is returned when all are true.
 */
final class RouteMappingsMapping<K, T> implements Comparable<RouteMappingsMapping<K, T>>,
        Predicate<Map<K, Object>> {

    static <K, T> RouteMappingsMapping<K, T> with(final Map<? extends K, Predicate<?>> keyToPredicates,
                                                  final T target) {
        Objects.requireNonNull(keyToPredicates, "keyToPredicates");
        Objects.requireNonNull(target, "target");

        return new RouteMappingsMapping<>(Maps.immutable(keyToPredicates), target);
    }

    private RouteMappingsMapping(final Map<? extends K, Predicate<?>> keyToPredicates,
                                 final T target) {
        super();
        this.keyToPredicates = keyToPredicates;
        this.target = target;
    }

    private final Map<? extends K, Predicate<?>> keyToPredicates;

    T target() {
        return this.target;
    }

    private final T target;

    // Object...........................................................................................................

    @Test
    public int hashCode() {
        return this.target.hashCode();
    }

    public boolean equals(final Object other) {
        return this == other || (other instanceof RouteMappingsMapping && this.equals0(Cast.to(other)));
    }

    private boolean equals0(final RouteMappingsMapping<?, ?> other) {
        return this.keyToPredicates.equals(other.keyToPredicates) && this.target.equals(other.target);
    }

    @Override
    public String toString() {
        return this.keyToPredicates + "->" + this.target;
    }

    // Comparable.......................................................................................................

    /**
     * Sorts mappings with more {@link #keyToPredicates} before those with fewer.
     */
    @Override
    public int compareTo(final RouteMappingsMapping<K, T> other) {
        return other.keyToPredicates.size() - this.keyToPredicates.size();
    }

    // Predicate........................................................................................................

    /**
     * Tests if this mapping has all key to {@link Predicate} satisfied.
     */
    @Override
    public boolean test(final Map<K, Object> parameters) {
        return this.keyToPredicates.entrySet()
                .stream()
                .allMatch(e -> e.getValue().test(Cast.to(parameters.get(e.getKey()))));
    }
}
