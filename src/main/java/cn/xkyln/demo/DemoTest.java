package cn.xkyln.demo;

public class DemoTest {
    public static void main(String[] args) {
        Class<?> stringClass = String.class;
        System.out.println("TypeName:" + stringClass.getTypeName());
        System.out.println("CanonicalName:" + stringClass.getCanonicalName());;
        System.out.println("Name:" + stringClass.getName());
    }
}
