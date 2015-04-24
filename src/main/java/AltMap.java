import java.util.*;
import java.util.function.BiConsumer;

public class AltMap<K, V> implements Map<K, V>{

  private Node<K, V> start = null;
  private int size = 0;

  private static class Node<K, V> implements Entry<K, V> {
    private Node<K, V> next;
    private K key;
    private V value;

    private Node(){}
    private Node(K key, V value) {
      this.key = key;
      this.value = value;
    }

    @Override
    public K getKey() {
      return key;
    }

    @Override
    public V getValue() {
      return value;
    }

    @Override
    public V setValue(V value) {
      V oldValue = this.value;
      this.value = value;
      return oldValue;
    }
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public boolean isEmpty() {
    return size == 0;
  }

  @Override
  public boolean containsKey(Object key) {
    Node<K, V> node = start;
    while(node != null){
      if(node.key.equals(key)){
        return true;
      }
      node = node.next;
    }
    return false;
  }

  @Override
  public boolean containsValue(Object value) {
    Node<K, V> node = start;
    while (node != null){
      if(node.value.equals(value)){
        return true;
      }
      node = node.next;
    }
    return false;
  }


  private Entry<K,V> getEntry(Object key){
    Node<K, V> node = start;
    while (node != null){
      if(node.key.equals(key)){
        return node;
      }
      node = node.next;
    }
    return null;
  }

  @Override
  public V get(Object key) {
    Entry<K, V> e = getEntry(key);
    return e == null ? null : e.getValue();
  }

  @Override
  public V put(K key, V value) {
    Entry<K, V> e = getEntry(key);
    if(e != null){
      return e.setValue(value);
    }
    Node<K,V> node = new Node<>();
    node.key = key;
    node.value = value;
    node.next = start;
    start = node;
    size++;
    return null;
  }

  @Override
  public V remove(Object key) {
    Node<K, V> current = start;
    Node<K, V> last = null;
    while (current != null){
      if(current.key.equals(key)){
        if(last == null){
          start = current.next;
        }else{
          last.next = current.next;
        }
        size--;
        return current.value;
      }
      last = current;
      current = current.next;
    }
    return null;
  }

  @Override
  public void putAll(Map<? extends K, ? extends V> m) {
    if(m == null){
      return;
    }
    m.forEach(this::put);
  }

  @Override
  public void clear() {
    start = null;
    size = 0;
  }

  @Override
  public Set<K> keySet() {
    Set<K> set = new HashSet<>();
    forEach((k, v) -> set.add(k));
    return set;
  }

  @Override
  public Collection<V> values() {
    Collection<V> collection = new LinkedList<>();
    forEach((k, v) -> collection.add(v));
    return collection;
  }

  @Override
  public Set<Entry<K, V>> entrySet() {
    Set<Entry<K, V>> set = new HashSet<>();
    forEach( (k, v) -> set.add( new Node<>(k, v) ));
    return set;
  }

  // Java 8 shit not necessary for functionality but makes other code prettier
  @Override
  public void forEach(BiConsumer<? super K, ? super V> action) {
    Node<K, V> node = start;
    while (node != null){
      action.accept(node.key, node.value);
      node = node.next;
    }
  }
}