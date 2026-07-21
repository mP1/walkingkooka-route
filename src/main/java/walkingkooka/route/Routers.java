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

import walkingkooka.reflect.PublicStaticHelper;

import java.util.List;

public final class Routers implements PublicStaticHelper {

    /**
     * {@link RouterCollection}
     */
    public static <K, V> Router<K, V> collection(final List<Router<K, V>> routers) {
        return RouterCollection.with(routers);
    }

    /**
     * {@link FakeRouter}
     */
    public static <K, T> FakeRouter<K, T> fake() {
        return new FakeRouter<>();
    }

    private Routers() {
        throw new UnsupportedOperationException();
    }
}
