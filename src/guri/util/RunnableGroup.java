package guri.util;

import java.util.Collection;
import java.util.LinkedList;

public class RunnableGroup implements Runnable {
    final private LinkedList<Runnable> runnables;

    public RunnableGroup(Collection<Runnable> runnables) {
        this.runnables = new LinkedList<Runnable>(runnables);
//        synchronized (this.runnables) {
//            Iterator i = this.runnables.iterator();
//            while (i.hasNext()) {//confirm all member are runnable
//                Runnable r = (Runnable) (i.next());
//                r = null;
//            }
//        }
    }

    public void add(Runnable r) {
        synchronized (runnables) {
            runnables.add(r);
        }
    }

    public boolean remove(Runnable r) {
        synchronized (runnables) {
            return runnables.remove(r);
        }
    }

    public void run() {
        Object[] r;
        synchronized (runnables) {
            r = runnables.toArray();
        }
        int i;
        for (i = 0; i < r.length; i++) {
            ((Runnable) r[i]).run();
        }
    }
}