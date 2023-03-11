package a_complexity;

import org.junit.Test;
import z_util.TimeTool;

import java.sql.ResultSet;
import java.util.Map;

/**
 * 斐波那契数列
 */
public class Fibonacci {

    @Test
    public void test() {
        int n = 12;

        TimeTool.check("fib1", new TimeTool.Task() {
            @Override
            public void execute() {;
                System.out.println(n + " 结果：" + fib(n));
            }
        });

        TimeTool.check("fib2", new TimeTool.Task() {
            @Override
            public void execute() {
                System.out.println(n +  " 结果：" + fib2(n));
            }
        });
        TimeTool.check("fib3", new TimeTool.Task() {
            @Override
            public void execute() {
                System.out.println(n +  " 结果：" + fib3(n));
            }
        });
    }


    private int fib(int n) {
        if (n <= 1) {
            return n;
        }
        return fib(n - 1) + fib(n - 2);
    }

    private int fib2(int n) {
        if (n <= 1) {
            return n;
        }
        int f1 = 0;
        int f2 = 1;
        while (n-- > 1) {
            f2 += f1;
            f1 = f2 - f1;
        }
        return f2;
    }

    private int fib3(int n){
        double c = Math.sqrt(5);
        return (int)((Math.pow((1 + c) / 2, n) - Math.pow((1 - c) / 2, n)) / c);
    }
}
