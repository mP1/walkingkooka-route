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
 * A {@link Router} that combines two {@link Router routers} trying the first and only if nothing is returned then the second.
 */
final class ChainRouter<K, V> implements Router<K, V> {

    static <K, V> Router<K, V> with(final Router<K, V> first,
                                    final Router<K, V> second) {
        return first.equals(second) ?
                first :
                new ChainRouter<>(first, second);
    }

    private ChainRouter(final Router<K, V> first,
                        final Router<K, V> second) {
        super();
        this.first = first;
        this.second = second;
    }

    // Router...........................................................................................................

    @Override
    public Optional<V> route(final Map<K, Object> parameters) throws RouteException {
        final Optional<V> result = this.first.route(parameters);
        return result.isPresent() ?
                result :
                this.second.route(parameters);
    }

    // Object...........................................................................................................

    @Override
    public int hashCode() {
        return Objects.hash(this.first, this.second);
    }

    @Override
    public boolean equals(final Object other) {
        return this == other ||
                other instanceof ChainRouter &&
                        this.equals0((ChainRouter<?, ?>) other);
    }

    private boolean equals0(final ChainRouter<?, ?> other) {
        return this.first.equals(other.first) &&
                this.second.equals(other.second);
    }

    private final Router<K, V> first;
    private final Router<K, V> second;

    @Override
    public String toString() {
        return this.first + " > " + this.second;
    }
}
