package com.data.array;
/*
* 数组挪位
* data[i]=data[i-1]  和 data[i+1]=data[i] 均表示数组上的元素都，往后挪1位
* data[i]=data[i+1]  和 data[i-1]=data[i] 均表示数组上的元素都，往前挪1为
* 要根据挪位方向，往前还是往后，决定fori循环的遍历顺序(i递增还是递减)。如往后挪位从尾部最先挪，插入位置最后挪，反过来会覆盖掉元素。
* 同时保证的i取极限值时不会数组越界；且完全覆盖要遍历的目标范围。来选择合适的表示方式。
* */
public class Array {
    public char data[];
    private int length;/*/长度/容量*/
    private int count;/*实际元素数量*/
    public Array(int capacity){/*初始化数组*/
        this.data=new char[capacity];
        this.length =capacity;
        this.count=0;
    }
    /*随机访问*/
    public int find (int index){
        if (index< 0||index>=count/*=也算越界*/) return -1;
        return data[index];
    }
    /*插入*/
    public boolean insert(int index,char value){
//        if (index==count&&count==0) {
//            data[index]=value;
//            ++count;
//            return true;
//        }
        if (count==length) {
            System.out.println("array is full");
            return false;
        }
        if (index<0||index>count){/*数组连续存储，插入不许有空位，且不许越界*/
            System.out.println("index err");
            return false;
        }
        //假设数组容量10，在索引0-8上共9个元素，先在要在索引为6处插入x。
        //i初始为9，每次自减1。--i：先由容量变为索引数8
        //从索引8开始，只要还大于插入位置，就循环一次并减1
        /* 数组满了就报错，因此count<length。i最大时是count,最小是index+1,不会越界；i最大是count，i-1最小是index，全部覆盖*/
        for (int i = count; i > index; --i) {/*从尾到首遍历，找到插入位置：尾部先移动，插入点先移动会覆盖元素*/
            data[i]=data[i-1];/*当前位置存上一位置的数据，即依次往后挪一位*/
            //第1次循环 i=9 i>6 data[9]=data[8]
            //第2次循环 i=8 i>6 data[8]=data[7]
            //第3次循环 i=7 i>6 data[7]=data[6] 把插入位置的原始数据移到下一位置
        }
        /*在有容量上限时，即count≠length时，i永远小于length，这两种遍历方法都可以。但在动态扩容中，有可能count=length，防止越界要-1*/
//        for (int i = count - 1; i >= index; i--) {//i最大为count-1，不会越界。i+1最大是count，i最小是index，全部覆盖
//            data[i + 1] = data[i];
//        }
        data[index]=value;//data[6]=x
        ++count;
        return true;
    }
    public boolean delete(int index){
        if (index<0 || index >=count) return false;
        //从删除位置开始，将后面的元素向前移动一位

//        for (int i = index; i <count; ++i) {
//            data[i]=data[i+1];//虽然也是往前移动一位。但i的上限为count-1，即索引最大值，i+1时会下标越界
//        }
        for (int i = index+1; i < count; ++i) {/*插入点先移动，尾部先移动会覆盖元素*/
            data[i-1]=data[i];//即不会越界，又能覆盖应该的索引位置。
        }
        //删除数组末尾元素  这段代码不需要也可以
        /*int[] arr = new int[count-1];
        for (int i=0; i<count-1;i++){
            arr[i] = data[i];
        }
        this.data = arr;*/

        count--;
        return true;
    }
    public void printAll() {
        for (int i = 0; i < count; ++i) {
            System.out.print("["+data[i] + "] ");
        }
        System.out.println();
    }
    public static void main(String[] args) {
        Array array = new Array(10);
        array.printAll();
        array.insert(0, 'i');
        array.insert(0, 'h');
        array.insert(0, 'g');
        array.insert(0, 'f');
        array.insert(0, 'e');
        array.insert(0, 'd');
        array.insert(0, 'c');
        array.insert(0, 'b');
        array.insert(0, 'a');
        array.printAll();
        array.insert(6,'X');
        array.printAll();
    }

//    var i = 0;
//    var a = i++;
//    a = 0;
//    i  = 1 ;
//
//    var i =0;
//    var a = ++i;
//    a = 1;
//    i = 1;

//    在for循环里 i++/i-- 和++i/--i没区别，都是执行完代码区再加/减。
    //老的C语言的程序员可能习惯于写成++i，
    //因为i++会产生一个临时变量，而++i不会，但先在已经被优化了，没有这个性能问题。
}
