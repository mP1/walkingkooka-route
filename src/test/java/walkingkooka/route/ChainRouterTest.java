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
import walkingkooka.HashCodeEqualsDefinedTesting2;
import walkingkooka.ToStringTesting;
import walkingkooka.collect.map.Maps;

import java.util.Optional;

public final class ChainRouterTest implements RouterTesting2<ChainRouter<String, Integer>, String, Integer>,
    HashCodeEqualsDefinedTesting2<ChainRouter<String, Integer>>,
    ToStringTesting<ChainRouter<String, Integer>> {

    private final static String PARAMETER = "parameter";

    private final static String FIRST_TEST = "first";
    private final static Integer FIRST_RESULT = 100;

    private final static Router<String, Integer> FIRST = (p) -> Optional.ofNullable(FIRST_TEST.equals(p.get(PARAMETER)) ? FIRST_RESULT : null);

    private final static String SECOND_TEST = "second";
    private final static Integer SECOND_RESULT = 200;

    private final static Router<String, Integer> SECOND = (p) -> Optional.ofNullable(SECOND_TEST.equals(p.get(PARAMETER)) ? SECOND_RESULT : null);

    @Test
    public void testRouteFirst() {
        this.routeAndCheck(Maps.of(PARAMETER, FIRST_TEST), FIRST_RESULT);
    }

    @Test
    public void testRouteSecond() {
        this.routeAndCheck(Maps.of(PARAMETER, SECOND_TEST), SECOND_RESULT);
    }

    @Test
    public void testRouteMatchesNone() {
        this.routeFails(Maps.of(PARAMETER, "neither"));
    }

    @Test
    public void testDifferentRouter() {
        this.checkNotEquals(FIRST);
    }

    @Test
    public void testDifferentFirstAndSecond() {
        this.checkNotEquals(ChainRouter.with(SECOND, FIRST));
    }

    @Test
    public void testToString() {
        this.toStringAndCheck(this.createRouter(), FIRST + " > " + SECOND);
    }

    @Override
    public ChainRouter<String, Integer> createRouter() {
        return Cast.to(ChainRouter.with(FIRST, SECOND));
    }

    @Override
    public Class<ChainRouter<String, Integer>> type() {
        return Cast.to(ChainRouter.class);
    }

    @Override
    public ChainRouter<String, Integer> createObject() {
        return this.createRouter();
    }
}
