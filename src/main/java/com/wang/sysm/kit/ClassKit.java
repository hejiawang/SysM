package com.wang.sysm.kit;

import com.jfinal.kit.StrKit;

import java.io.File;
import java.io.FileFilter;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * @auther HeJiawang
 * @date 2017/12/27
 */
public class ClassKit {

    /**
     * 获取类加载器
     * @return
     */
    public static ClassLoader getClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }

    /**
     * 加载类
     * @param className
     * @param isInitialized
     * @return
     */
    public static Class<?> loadClass( String className, boolean isInitialized ) {
        Class<?> cls;
        try {
            cls = Class.forName(className, isInitialized, getClassLoader());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return cls;
    }

    /**
     * 获取指定包名下的所有类
     * @param packageName
     * @return
     */
    public static Set<Class<?>> getClassSet(String packageName ) {
        Set<Class<?>> classSet = new HashSet<Class<?>>();

        try {
            Enumeration<URL> urls = getClassLoader().getResources(packageName.replace(".", "/"));

            while( urls.hasMoreElements() ) {
                URL url = urls.nextElement();
                if(url == null) continue;

                String protocol = url.getProtocol();
                if( protocol.equals("file") ) {
                    String packagePath = url.getPath().replaceAll("%20", " ");
                    addClass(classSet, packagePath, packageName);
                } else if( protocol.equals("jar") ) {
                    JarURLConnection jarURLConnection = (JarURLConnection)url.openConnection();
                    if( jarURLConnection == null ) continue;

                    JarFile jarFile = jarURLConnection.getJarFile();
                    if( jarFile == null ) continue;

                    Enumeration<JarEntry> jarEntries = jarFile.entries();
                    while( jarEntries.hasMoreElements() ) {
                        String jarEntryName = jarEntries.nextElement().getName();
                        if( jarEntryName.endsWith(".class") ) {
                            String className = jarEntryName.substring(0, jarEntryName.lastIndexOf(".")).replace("/", ".");
                            doAddClass(classSet, className);
                        }
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return classSet;
    }

    private static void addClass( Set<Class<?>> classSet, String packagePath, String packageName ) {
        File[] files = new File(packagePath).listFiles(new FileFilter() {

            @Override
            public boolean accept(File pathname) {
                return ( pathname.isFile() && pathname.getName().endsWith(".class") ) || pathname.isDirectory();
            }
        });

        for( File file : files ) {
            String fileName = file.getName();
            if( file.isFile() ) {
                String className = fileName.substring(0, fileName.lastIndexOf("."));

                if( !StrKit.isBlank(packageName) ) className = packageName + "." + className;
                doAddClass(classSet, className);
            } else {
                String subPackagePath = fileName;
                if( !StrKit.isBlank(packagePath) ) subPackagePath = packagePath + "/" + subPackagePath;

                String subPackageName = fileName;
                if( !StrKit.isBlank(packageName) ) subPackageName = packageName + "." + subPackageName;

                addClass(classSet, subPackagePath, subPackageName);
            }
        }
    }

    private static void doAddClass( Set<Class<?>> classSet, String className ) {
        Class<?> cls = loadClass(className, false);
        classSet.add(cls);
    }
}
