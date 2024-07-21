package thread.forkjoin;

import java.util.Arrays;
import java.util.concurrent.RecursiveAction;

public class SortArrayTask extends RecursiveAction {

    private static final int THRESHOLD = 1000;

    private final int[] array;

    private final int low;

    private final int high;

    public SortArrayTask(int[] array) {
        this(array, 0, array.length - 1);
    }

    SortArrayTask(int[] array, int low, int high) {
        this.array = array;
        this.low = low;
        this.high = high;
    }

    /**
     * 自定义compute方法，在其中实现排序任务的分治逻辑：
     * <p>1. 定义最小执行单元：满足条件，可以不用继续拆分（fork），直接进行计算</p>
     * <p>2. 定义任务的拆分逻辑：不满足条件时，如何拆分任务</p>
     * <p>3. 定义合并逻辑（如果需要的话）：合并子任务的执行合并</p>
     */
    @Override
    protected void compute() {
        // 满足最小执行单元，直接进行排序
        if (high - low < THRESHOLD) {
            sortArray(array, low, high + 1);
        } else {
            // 拆分数组，分别进行排序
            int mid = (high + low) / 2;
            SortArrayTask leftTask = new SortArrayTask(array, low, mid);
            SortArrayTask rightTask = new SortArrayTask(array, mid + 1, high);
            invokeAll(leftTask, rightTask);
            // 合并排序结果,参考RecursiveAction类的注释，实现的一种减少空间和时间复杂度的、巧妙的合并方法
            mergeArray(array, low, mid, high);
        }
    }

    public void mergeArray(int[] array, int low, int mid, int high) {
        // 先将左半部分复制到临时数组
        int[] temp = Arrays.copyOfRange(array, low, mid + 1);
        // 将临时数组与右半部分比较，实现数组合并
        for (int k = 0, right = mid + 1, j = low; k < temp.length; k++) {
            // 合并temp[k]的情况：① 右半部分没有了元素；② 右半部分有元素，但数值较大
            array[j++] = (right > high || temp[k] < array[right]) ? temp[k] : array[right++];
        }
    }

    // public void mergeArray(int[] array, int low1, int high1, int low2, int hig2) {
    //     int[] temp = new int[hig2 - low1 + 1];
    //     int k = 0;
    //     int left1 = low1;
    //     int left2 = low2;
    //     while (left1 <= high1 && left2 <= hig2) {
    //         temp[k++] = array[left1] < array[left2] ? array[left1++] : array[left2++];
    //     }
    //     // 合并剩余元素
    //     while (left1 <= high1) {
    //         temp[k++] = array[left1++];
    //     }
    //     while (left2 <= hig2) {
    //         temp[k++] = array[left2++];
    //     }
    //     // 将临时数组复制到当前数组
    //     k = 0;
    //     while (low1 <= hig2) {
    //         array[low1++] = temp[k++];
    //     }
    // }

    public void sortArray(int[] arr, int start, int end) {
        System.out.printf("%s -- array sort,[%d,%d)\n", Thread.currentThread().getName(), start, end);
        Arrays.sort(arr, start, end); // Arrays.sort()不包含end元素
    }
}

