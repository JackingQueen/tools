package cn.xkyln.newfeature;

import cn.xkyln.bean.Person;
import cn.xkyln.thread.ErrHandler;
import cn.xkyln.thread.ErrThread;

import java.beans.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.MessageFormat;
import java.util.*;

import static java.lang.Math.abs;

public class Jdk5 {
    public static void main(String[] args) {
        autoBoxing();
        foreachLoop();
        staticImport();
        varArgs(1,2,3);
        enumClass();
        formatPrint();
        generic("param");
        introspector();
        uncaughtException();

        Long end_create_time = 1572537600L;
        Long aLong = Optional.ofNullable(end_create_time).map(endTime -> endTime + 86399).orElse(end_create_time);
        System.out.println(aLong);
    }

    /**
     * 自动拆箱装箱
     */
    private static void autoBoxing() {
        int a = 1;
        Integer b = a;
        System.out.print("自动拆箱装箱:");
        System.out.println(++b);
    }

    /**
     * foreach 循环
     */
    private static void foreachLoop() {
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5);
        for (Integer integer : integers) {
            System.out.print(integer+"\t");
        }
        System.out.println();
    }

    /**
     * 静态导入
     */
    private static void staticImport() {
        int a = abs(-1);
        System.out.print("静态导入:");
        System.out.println(a);
    }

    /**
     * 可变参数
     * @param b
     */
    private static void varArgs(int ... b) {
        System.out.print("可变参数:");
        for (int i : b) {
            System.out.print(i + "\t");
        }
        System.out.println();
    }

    /**
     * 枚举，可以实现单例、多例模式
     */
    private static void enumClass() {
        SexEnum sex = SexEnum.MALE;

        switch (sex) {
            case MALE:
                System.out.println(sex);
            case FEMALE:
                System.out.println(sex.name() + " --- " + sex.ordinal());
        }
        System.out.println(SexEnum.valueOf("FEMALE"));
        System.out.println(Arrays.toString(SexEnum.values()));

        EnumMap<SexEnum, String> enumMap = new EnumMap(SexEnum.class);
        enumMap.put(SexEnum.MALE, SexEnum.MALE.name());
        enumMap.put(SexEnum.FEMALE, SexEnum.FEMALE.name());
        System.out.println(enumMap);

        EnumSet<SexEnum> enumSet = EnumSet.of(SexEnum.MALE, SexEnum.FEMALE);
        System.out.println(enumSet);
    }

    /**
     * 格式化输出
     */
    private static void formatPrint() {
        String strFormat = "%d * %d = %d";
        String strFormt = String.format(strFormat, 2, 3, 2*3);
        System.out.println(strFormt);
        System.out.printf("%d * %d = %d", 3, 4, 3*4);

        String msgFormat = "我是{0},来自{1}";
        String str = MessageFormat.format(msgFormat, "李白", "唐朝");
        System.out.println(str);
    }

    /**
     * 泛型，泛型类、泛型接口、泛型方法（在返回值之前必须有<T>才是方形方法）、泛型通配符和上下边界
     */
    private static <T> void generic(T param) {
        List<Integer> iList = new ArrayList<Integer>();
        iList.add(1);
        iList.add(2);
        iList.add(3);
        System.out.println(iList);

        Class<?> clazz = String.class;
        System.out.println(clazz.getName());
        clazz = Integer.class;
        System.out.println(clazz.getTypeName());

        // 必须继承与Number，或者是 Number
        Class<? extends Number> numCls = Integer.class;
        // 必须是 Integer 的超类或者是 Integer
        Class<? super Integer> iCls = Number.class;

        System.out.println(param);
    }

    /**
     * 内省
     */
    private static void introspector() {
        try {
            PropertyDescriptor propertyDescriptor = new PropertyDescriptor("name", Person.class);
            String propertyClss = propertyDescriptor.getPropertyType().getCanonicalName();
            System.out.println("属性类型：" + propertyClss);

            Person person = new Person();
            Method writeMethod = propertyDescriptor.getWriteMethod();
            Method readMethod = propertyDescriptor.getReadMethod();

            writeMethod.invoke(person, "Jack");
            String name = (String)readMethod.invoke(person);
            System.out.println(name);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }

        try {
            Person person = Person.class.newInstance();
            BeanInfo beanInfo = Introspector.getBeanInfo(Person.class);
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
                if (propertyDescriptor.getName().equals("name")) {
                    Method writeMethod = propertyDescriptor.getWriteMethod();
                    writeMethod.invoke(person, "Queen");
                    break;
                }
            }
            System.out.println(person);
        } catch (IntrospectionException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * 未捕获异常
     */
    private static void uncaughtException() {
        ErrThread thread = new ErrThread();
        thread.setUncaughtExceptionHandler(new ErrHandler());
        thread.start();
    }

    /**
     * JUC 并发库
     */
    private static void juc() {

    }
}
