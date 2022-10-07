package thread.forkjoin;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.concurrent.ForkJoinPool;

public class SortArrayTaskTest {
    public static void main(String[] args) {
        // 初始化待排序数组数组
        int[] array = SortArrayTaskTest.generateIntArray(5000);

        // 通过ForkJoinPool提交并执行ForkJoinTask
        // 使用ForkJoinPool提供的common线程池，未自定义ForkJoinPool
        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
        // 直接执行任务，invoke()方法会在任务执行结束后返回结果
        forkJoinPool.invoke(new SortArrayTask(array));
        // 打印数组的首尾元素
        System.out.println("排序后，数组前10个元素");
        for (int i = 0; i < 10; i++) {
            System.out.printf(array[i] + " ");
        }
        System.out.println("\n排序后，数组后10个元素");
        for (int i = array.length - 11; i < array.length; i++) {
            System.out.printf(array[i] + " ");
        }
    }

    public static int[] generateIntArray(int length) {
        int[] array = new int[length];
        for (int i = 0; i < length; i++) {
            array[i] = Integer.parseInt(RandomStringUtils.random(4, false, true));
        }
        return array;
    }
}
