package zt_test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class ATest {
    public static final Logger logger = Logger.getLogger(ATest.class.getName());

    public static void main(String[] args) {
        LinkedList<A> list = new LinkedList<>();
        list.add(new A("公司"));
        list.add(new A("班组"));
        list.add(new A("工区"));
        List<A> collect = list.stream().sorted(Comparator.comparing(A::getName).reversed()).
                collect(Collectors.toList());
        logger.log(Level.INFO, "{0}", new Object[]{collect});
    }
}


@Data
@AllArgsConstructor
@NoArgsConstructor
class A {
    private String name;

    @Override
    public String toString() {
        return "zt_test.A{" +
                "name='" + this.name + '\'' +
                '}';
    }
}

