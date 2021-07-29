package com.design.gen;

import java.util.ArrayList;
import java.util.List;

public class Test {
    //    Java 中的泛型是不型变的，这意味着 List<String> 并不是 List<Object> 的子类型
    List<String> strings = new ArrayList<String>();
    //    List<Object>objects=strings;//报错
    List<? extends Object> objects = strings;//不报错
    //可以安全地从该集合中读取 E，但不能写入， 因为不知道什么对象符合那个未知的 E 的子类型

    public static void main(String[] args) {
        Collect<Collect.SupPar> superParents=new Collect();
        Collect<Collect.Par> parents=new Collect();
        Collect<Collect.Chi> child=new Collect();
        child.add1(new Collect.Chi());

        parents.addAll2(superParents);
        parents.addAll3(child);

        child.addAll2(superParents);
        superParents.addAll3(child);

    }
}
