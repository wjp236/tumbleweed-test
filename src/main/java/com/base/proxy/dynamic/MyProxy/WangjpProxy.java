package com.base.proxy.dynamic.myProxy;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 描述:个人代理类
 *
 * @author: mylover
 * @Time: 15/02/2017.
 */
public class WangjpProxy {

    private static String rt = "\r\n";

    public static Object createProxyInstance(ClassLoader classLoader, Class clazz, WangjpInvocationHandler handler) {
        try {
            Method[] methods = clazz.getMethods();

            //创建一个java文件,用流创建一个java文件
            StringBuffer proxyClass = new StringBuffer("package com.base.proxy.dynamic.myProxy;").append(rt);
            proxyClass.append("import java.lang.reflect.Method;").append(rt);
            //定义类名
            proxyClass.append("public class $WjpProxy implements ").append(clazz.getName()).append("{").append(rt);
            proxyClass.append("WangjpInvocationHandler h;").append(rt);
            //创建构造方法
            proxyClass.append("public $WjpProxy(WangjpInvocationHandler h) {").append(rt);
            proxyClass.append("this.h = h;").append(rt);
            proxyClass.append("}").append(rt);
            //构建所有方法
            proxyClass.append(getMethodString(methods, clazz)).append(rt);
            proxyClass.append("}");

            //生成文件
            String fileName = "/Users/mylover/git/tumbleweed-test/src/main/java/com/base/proxy/dynamic/myProxy/$WjpProxy.java";
            File file = new File(fileName);

            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(proxyClass.toString());
            fileWriter.flush();
            fileWriter.close();

            //编译java文件
            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
            StandardJavaFileManager sjfm = compiler.getStandardFileManager(null, null, null);
            Iterable iterable = sjfm.getJavaFileObjects(fileName);
            JavaCompiler.CompilationTask compilationTask = compiler.getTask(null, sjfm, null, null, null, iterable);
            compilationTask.call();
            sjfm.close();

            //把编译的文件加载到内存中
            WangjpClassLoader loader = new WangjpClassLoader("/Users/mylover/git/tumbleweed-test/src/main/java/com/base/proxy/dynamic/myProxy/");
            Class wjpProxy = loader.findClass("$WjpProxy");

            File classFile = new File("/Users/mylover/git/tumbleweed-test/src/main/java/com/base/proxy/dynamic/myProxy/$WjpProxy.class");
            if (classFile.exists() && file.exists()) {
                Thread.sleep(10000);
                file.delete();
            }

            Constructor m = wjpProxy.getConstructor(WangjpInvocationHandler.class);
            Object obj = m.newInstance(handler);
            return obj;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return null;
    }

    private static String getMethodString(Method[] methods, Class clazz) {
        StringBuffer proxyMethod = new StringBuffer();
        for (Method method : methods) {
            proxyMethod.append("public void " + method.getName() + "() throws Throwable {").append(rt);
            proxyMethod.append("Method md = ").append(clazz.getName()).append(".class.getMethod(\"").append(method.getName())
                    .append("\",new Class[]{});").append(rt);
            proxyMethod.append("this.h.invoke(this,md,null);").append(rt);
            proxyMethod.append("}").append(rt);
        }
        return proxyMethod.toString();
    }

}
