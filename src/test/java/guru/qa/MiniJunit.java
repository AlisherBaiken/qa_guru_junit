package guru.qa;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

public class MiniJunit {
    public static void main(String[] args) throws Exception{
        Method[] methods = DemoTest.class.getDeclaredMethods();
        for(Method method : methods){
            Test anotation = method.getAnnotation(Test.class);
            if (anotation != null){
                DemoTest instance = DemoTest.class.getConstructor().newInstance();
               try {
                   method.invoke(instance);
               } catch (Exception e){
                   System.out.println("TEST FAILD");
               }
                System.out.println("TEST PASSED");
            }
        }
    }
}
