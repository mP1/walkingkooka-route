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

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * A {@link Router} that tries all mappings until all {@link java.util.function.Predicate} are met and returns that target.
 */
final class RouteMappingsRouter<K, T> implements Router<K, T> {

    static <K, T> RouteMappingsRouter<K, T> with(final List<RouteMappingsMapping<K, T>> mappings) {
        if (mappings.isEmpty()) {
            throw new IllegalStateException("Mappings empty");
        }
        return new RouteMappingsRouter<>(mappings);
    }

    private RouteMappingsRouter(final List<RouteMappingsMapping<K, T>> mappings) {
        super();

        final List<RouteMappingsMapping<K, T>> copy = Lists.array();
        copy.addAll(mappings);
        copy.sort(Comparator.naturalOrder());

        this.mappings = copy;
    }

    // Router...........................................................................................................

    @Override
    public Optional<T> route(final Map<K, Object> parameters) {
        return this.mappings.stream()
            .filter(m -> m.test(parameters))
            .map(RouteMappingsMapping::target)
            .findFirst();
    }

    /**
     * All mappings sorted by mapping with most {@link java.util.function.Predicate} to the least.
     */
    private final List<RouteMappingsMapping<K, T>> mappings;

    // ToString.........................................................................................................

    @Override
    public String toString() {
        return this.mappings.toString();
    }
}
