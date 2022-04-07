package me.wawwior.utils.common;

import sun.reflect.ReflectionFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

public class CloneFactory {

    public static <T> T clone(T t) throws IllegalAccessException {

        Class<T> clazz = (Class<T>) t.getClass();

        Field[] fields = clazz.getFields();

        T clone = create(clazz);

        for (Field f : fields) {
            boolean accessible = f.canAccess(t);
            f.setAccessible(true);
            f.set(clone, f.get(t));
            f.setAccessible(accessible);
        }

        return t;
    }

    public static <T> T create(Class<T> clazz) {
        try {
            ReflectionFactory rf = ReflectionFactory.getReflectionFactory();

            Constructor<? super T> objDef = ((Class<? super T>) Object.class).getDeclaredConstructor();
            Constructor<?> intConstr = rf.newConstructorForSerialization(clazz, objDef);

            return clazz.cast(intConstr.newInstance());
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new IllegalStateException("Cannot create object", e);
        }
    }

}
