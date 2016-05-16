package tk.poneycorp.training;

import java.util.Enumeration;
import java.util.Iterator;

/**
 * Created by unautre on 24/04/16.
 */
public class MyUtils {
    private MyUtils(){}

    public static <T> Iterable<T> iterableFromEnumeration(final Enumeration<T> enumeration){
        return new Iterable<T>() {
            public Iterator<T> iterator() {
                return new Iterator<T>() {
                    @Override
                    public void remove() {
                        throw new UnsupportedOperationException();
                    }
                    public boolean hasNext() {
                        return enumeration.hasMoreElements();
                    }
                    public T next() {
                        return enumeration.nextElement();
                    }
                };
            }
        };
    }
}
