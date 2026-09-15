import deque.ArrayDeque61B;

import deque.Deque61B;
import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class ArrayDeque61BTest {

     @Test
     @DisplayName("ArrayDeque61B has no fields besides backing array and primitives")
     void noNonTrivialFields() {
         List<Field> badFields = Reflection.getFields(ArrayDeque61B.class)
                 .filter(f -> !(f.getType().isPrimitive() || f.getType().equals(Object[].class) || f.isSynthetic()))
                 .toList();

         assertWithMessage("Found fields that are not array or primitives").that(badFields).isEmpty();
     }

    @Test
    public void addFirstAndAddLastTest() {
        Deque61B<Integer> deque = new ArrayDeque61B<>();

        deque.addFirst(2);
        deque.addFirst(1);
        deque.addLast(3);
        deque.addLast(4);

        assertThat(deque.toList())
                .containsExactly(1, 2, 3, 4)
                .inOrder();

        assertThat(deque.size()).isEqualTo(4);
        assertThat(deque.isEmpty()).isFalse();
    }

    @Test
    public void removeFirstAndRemoveLastTest() {
        Deque61B<Integer> deque = new ArrayDeque61B<>();

        deque.addLast(1);
        deque.addLast(2);
        deque.addLast(3);

        assertThat(deque.removeFirst()).isEqualTo(1);
        assertThat(deque.toList()).containsExactly(2, 3).inOrder();

        assertThat(deque.removeLast()).isEqualTo(3);
        assertThat(deque.toList()).containsExactly(2).inOrder();

        assertThat(deque.removeFirst()).isEqualTo(2);
        assertThat(deque.toList()).isEmpty();

        assertThat(deque.isEmpty()).isTrue();
        assertThat(deque.size()).isEqualTo(0);

        assertThat(deque.removeFirst()).isNull();
        assertThat(deque.removeLast()).isNull();
    }

    @Test
    public void getTest() {
        Deque61B<String> deque = new ArrayDeque61B<>();

        deque.addLast("a");
        deque.addLast("b");
        deque.addLast("c");

        assertThat(deque.get(0)).isEqualTo("a");
        assertThat(deque.get(1)).isEqualTo("b");
        assertThat(deque.get(2)).isEqualTo("c");

        assertThat(deque.get(-1)).isNull();
        assertThat(deque.get(3)).isNull();
        assertThat(deque.get(100)).isNull();
    }

    @Test
    public void toListAndWrapAroundTest() {
        Deque61B<Integer> deque = new ArrayDeque61B<>();

        for (int i = 0; i < 6; i++) {
            deque.addLast(i);
        }

        for (int i = 0; i < 4; i++) {
            deque.removeFirst();
        }

        deque.addLast(6);
        deque.addLast(7);
        deque.addLast(8);
        deque.addLast(9);

        assertThat(deque.toList())
                .containsExactly(4, 5, 6, 7, 8, 9)
                .inOrder();
    }

    @Test
    public void resizeTest() {
        Deque61B<Integer> deque = new ArrayDeque61B<>();

        for (int i = 0; i < 32; i++) {
            deque.addLast(i);
        }

        assertThat(deque.size()).isEqualTo(32);

        for (int i = 0; i < 25; i++) {
            assertThat(deque.removeFirst()).isEqualTo(i);
        }

        assertThat(deque.toList())
                .containsExactly(25, 26, 27, 28, 29, 30, 31)
                .inOrder();
    }

    @Test
    public void addAfterRemoveToEmptyTest() {
        Deque61B<Integer> deque = new ArrayDeque61B<>();

        deque.addLast(1);
        deque.removeFirst();

        deque.addFirst(2);
        deque.addLast(3);

        assertThat(deque.toList())
                .containsExactly(2, 3)
                .inOrder();
    }

    @Test
    public void objectMethodsTest() {
        Deque61B<String> deque1 = new ArrayDeque61B<>();
        Deque61B<String> deque2 = new ArrayDeque61B<>();

        deque1.addLast("a");
        deque1.addLast("b");

        deque2.addLast("a");
        deque2.addLast("b");

        assertThat(deque1).isEqualTo(deque2);
        assertThat(deque1.toString()).isEqualTo("[a, b]");


    }
}
