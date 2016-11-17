package levelp.model;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Использование библиотеки AssertJ (альтернатива JUnit):
 * http://joel-costigliola.github.io/assertj/
 */
public class AssertJDemo {

    @Test
    public void test() {
        assertThat("Test".equals("T" + "est"));
        assertThat("Test").isEqualTo("T" + "est");

        List<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");
        assertThat(list).isNotNull().hasSize(3).contains("B");

        assertNotNull(list);
        assertEquals(3, list.size());
        assertTrue(list.contains("B"));
    }
}
