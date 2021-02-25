# API Lab Discussion
# Collections API Discussion

## Names and NetIDs
Jessica Yang (JQY2)
Rachel Luria (rl213)
Montana Lee (mal115)
Livia Seibert (las120)

### What is the purpose of each interface implemented by LinkedList?
List<> and Deque<>. The LinkedList class inherits the iterator() method from Deque<>, and the containsAll(), equals(), hashCode(), isEmpty(), iterator(), listIterator(), removeAll(), replaceAll(), retainAll(), sort(), and subList() methods from List<>. Deque<> is particularly needed in order to insert, remove, and inspect items from the LinkedList from both ends.

### What is the purpose of each superclass of HashMap?
HashMap extends AbstractMap and implements Serializable, Cloneable, and Map. It inherits the equals(), hashCode(), and toString() methods from AbstractMap, as well as the equals() and hashCode() methods from Map. The Map interface describes the basic implementation for any map, while the AbstractMap class is a basic implementation of that Map interface. HashMap therefore implements a more specific Map object that provides constant time get and put operations.

### How many different implementations are there for a Set?

HashSet, TreeSet, LinkedHashSet are the three different implementations of a set. HashSet is backed by a HashTable and runs basic operations in constant time. TreeSet is based on a TreeMap and guarantees log(n) time for basic operations. LinkedHashSet has predictable ordering because a doubly linked list runs through its entries. It has constant time for basic operations if there aren’t too many entries with the same hash.

### What methods are common to all collections?
add(), addAll(), clear(), contains(), containsAll(), equals(), hashCode(), isEmpty(), iterator(), parallelStream(), remove(), removeAll(), removeIf(), retainAll(), size(), spliterator(), stream(), toArray()

### What methods are common to all Queues?
Element() is inherited from java.util.Queue and common to all the queues. addAll(), containsAll(), equals(), hashCode(), isEmpty() are inherited from Java.util.collections and across all the queues.

### What is the purpose of the collection utility classes?

The purpose of the collection utility classes are to provide functionality for creating and operating on different types of data structures, as different types of data work better in different collection types. The methods provided are often polymorphic and work well on many different types of collections.
