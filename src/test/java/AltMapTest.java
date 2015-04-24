import org.junit.Before;
import org.junit.Test;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.*;

public class AltMapTest {

    private Map<Integer, Double> map;


    public void setUp() throws Exception {
        map = new AltMap<>();
        map.put(1, 11.11);
        map.put(2, 22.22);
        map.put(3, 33.33);
        map.put(4, 44.44);
        map.put(5, 55.55);

    }

    @Test
    public void testSize() throws Exception {
        setUp();
        assertEquals(map.size(), 5);

        map.put(6, 66.66);

        assertEquals(map.size(), 6);

        map.remove(1);
        map.remove(2);

        assertEquals(map.size(), 4);
    }

    @Test
    public void testIsEmpty() throws Exception {
        setUp();
        assertEquals(map.isEmpty(), false);
        map.clear();
        assertEquals(map.isEmpty(), true);

        setUp();
        map.remove(1);
        assertEquals(map.isEmpty(), false);
        map.remove(2);
        assertEquals(map.isEmpty(), false);
        map.remove(3);
        assertEquals(map.isEmpty(), false);
        map.remove(4);
        assertEquals(map.isEmpty(), false);
        map.remove(5);
        assertEquals(map.isEmpty(), true);


    }

    @Test
    public void testContainsKey() throws Exception {
        setUp();
        assertTrue(map.containsKey(1));
        assertTrue(map.containsKey(5));
        assertTrue(!map.containsKey(6));

    }

    @Test
    public void testContainsValue() throws Exception {
        setUp();
        assertTrue(map.containsValue(11.11));
        assertTrue(map.containsValue(55.55));
        assertTrue(!map.containsValue(66.66));
    }

    @Test
    public void testGet() throws Exception {
        setUp();
        assertTrue(map.get(1) == 11.11);
        assertTrue(map.get(5) == 55.55);
        assertTrue(map.get(6) == null);

    }

    @Test
    public void testPut() throws Exception {
        setUp();
        map.put(6, 66.66);
        assertTrue(map.get(6) == 66.66);
        map.put(6, -66.66);
        assertTrue(map.get(6) == -66.66);
        map.put(3, -33.33);
        assertTrue(map.get(3) == -33.33);

    }

    @Test
    public void testRemove() throws Exception {
        setUp();
        assertTrue(map.remove(3) == 33.33);
        assertTrue(map.remove(3) == null);

    }

    @Test
    public void testPutAll() throws Exception {
        setUp();
        HashMap<Integer, Double> alt = new HashMap<>();
        alt.put(1, -11.11);
        alt.put(3, -33.33);
        alt.put(5, -55.55);
        alt.put(6, -66.66);

        map.putAll(alt);
        assertTrue(map.get(1) == -11.11);
        assertTrue(map.get(2) == 22.22);
        assertTrue(map.get(3) == -33.33);
        assertTrue(map.get(5) == -55.55);
        assertTrue(map.get(6) == -66.66);



    }

    @Test
    public void testClear() throws Exception {
        setUp();
        assertTrue(map.size() > 0);
        assertTrue(!map.isEmpty());
        map.clear();
        assertTrue(map.size() == 0);
        assertTrue(map.isEmpty());

    }

    @Test
    public void testKeySet() throws Exception {
        setUp();
        Set<Integer> set = map.keySet();
        assertTrue(set.contains(1));
        assertTrue(set.contains(2));
        assertTrue(set.contains(3));
        assertTrue(set.contains(4));
        assertTrue(set.contains(5));
    }

    @Test
    public void testValues() throws Exception {
        setUp();
        Collection<Double> collection = map.values();

        assertTrue(collection.size() > 0);
        for(Double d : collection){
            assertTrue(map.containsValue(d));
        }


    }


    @Test
    public void testEntrySet() throws Exception {
        setUp();
        Set<Map.Entry<Integer, Double>> set = map.entrySet();

        assertTrue(set.size() == map.size());
        set.forEach( e -> assertTrue( map.get(e.getKey()) == e.getValue()));

    }
}