package other;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class InsertionSpeedComparison {
    public static void main(String[] args) {
        int numElements = 1000000; // 插入的元素数量

        // 使用ArrayList进行插入操作
        List<Integer> arrayList = new ArrayList<>();
        long arrayListStartTime = System.currentTimeMillis();
        for (int i = 0; i < numElements; i++) {
            arrayList.add(i);
        }
        long arrayListEndTime = System.currentTimeMillis();
        long arrayListInsertionTime = arrayListEndTime - arrayListStartTime;
        System.out.println("ArrayList插入时间：" + arrayListInsertionTime + "毫秒");

        // 使用LinkedList进行插入操作
        List<Integer> linkedList = new LinkedList<>();
        long linkedListStartTime = System.currentTimeMillis();
        for (int i = 0; i < numElements; i++) {
            linkedList.add(i);
        }
        long linkedListEndTime = System.currentTimeMillis();
        long linkedListInsertionTime = linkedListEndTime - linkedListStartTime;
        System.out.println("LinkedList插入时间：" + linkedListInsertionTime + "毫秒");
    }
}
