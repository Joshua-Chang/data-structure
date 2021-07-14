package com.data.linkedlist;

import java.util.HashMap;
import java.util.Map;

public class LRUBaseArray<T> {
    private static final int DEFAULT_CAPACITY = (1 << 3);

    private int capacity;

    private int count;

    private T[] data;

    private Map<T, Integer> holder;

    public LRUBaseArray() {
        this(DEFAULT_CAPACITY);
    }

    public LRUBaseArray(int capacity) {
        this.capacity = capacity;
        data = (T[]) new Object[capacity];
        count = 0;
        holder = new HashMap<T, Integer>(capacity);
    }

    public void add(T obj) {
        if (obj == null) throw new IllegalArgumentException();
        Integer index = holder.get(obj);
        if (index == null) {/*未缓存过*/
            if (count == capacity) {/*满了，先删末尾*/
                T key = data[--count];
                holder.remove(key);
            }
            /*未满，直接插到首位*/
            for (int i = count-1; i >=0; i--) {/*往后移动*/
                data[i+1]= data[i];
                holder.put(data[i],i+1);
            }
            data[0]=obj;
            holder.put(obj,0);
            count++;
        } else {/*缓存过*/
            /*先把原位置删除*/
            T target = data[index];
            for (int i = index-1; i >=0; i--) {/*插入位置前面的元素往后移动，覆盖掉要删除的位置，后变得不必再往前移动*/
                data[i+1]= data[i];
                holder.put(data[i],i+1);
            }
            /*再插入到首位*/
            data[0]=target;
            holder.put(target,0);
        }
    }
}
