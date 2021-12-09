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

import walkingkooka.test.Testing;

import java.util.Map;
import java.util.Optional;

public interface RouterTesting extends Testing  {

    default <K, T> void routeAndCheck(final Router<K, T> router, final Map<K, Object> parameters, final T target) {
        this.checkEquals(
                Optional.of(target),
                router.route(parameters),
                () -> "Routing of parameters=" + parameters + " failed"
        );
    }

    default <K, T> void routeFails(final Router<K, T> router, final Map<K, Object> parameters) {
        this.checkEquals(
                Optional.empty(),
                router.route(parameters),
                () -> "Routing of parameters=" + parameters + " should have failed"
        );
    }
}

