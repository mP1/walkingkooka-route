package test;

import com.google.gwt.junit.client.GWTTestCase;

import walkingkooka.collect.map.Maps;
import walkingkooka.predicate.Predicates;
import walkingkooka.route.RouteMappings;
import walkingkooka.route.Router;

import java.util.Optional;
import java.util.function.Predicate;

public class TestGwtTest extends GWTTestCase {

    @Override
    public String getModuleName() {
        return "test.Test";
    }

    public void testAssertEquals() {
        assertEquals(
                1,
                1
        );
    }

    public void testPatch() {
        final RouteMappings<String, Integer> mappings = RouteMappings.<String, Integer>empty()
                .add(Maps.of("protocol", Predicates.is("http"), "host", Predicate.isEqual("example.com")), 12)
                .add(Maps.of("protocol", Predicates.is("http"), "host", Predicate.isEqual("example2.com")), 34)
                .add(Maps.of("protocol", Predicates.is("https")), 56);
        final Router<String, Integer> router = mappings.router();

        final Optional<Integer> https = router.route(Maps.of("protocol", "https")); // should return 56
        final Optional<Integer> http = router.route(Maps.of("protocol", "http")); // 12 and 34 both match returns nothing.
        final Optional<Integer> httpExample = router.route(Maps.of("protocol", "http", "host", "example.com")); // should return 12

        assertEquals(Optional.of(56), https);
        assertEquals(Optional.empty(), http);
        assertEquals(Optional.of(12), httpExample);
    }
}
