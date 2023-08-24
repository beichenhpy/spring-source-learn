package cn.beichenhpy.event;

import java.util.EventListener;
import java.util.EventObject;
import java.util.Observable;
import java.util.Observer;

/**
 * 事件/监听器模式 demo
 */
public class ObserverDemo {

    public static void main(String[] args) {
        EventObservable eventObservable = new EventObservable();
        //给事件发布添加监听器
        eventObservable.addObserver(new EventObServer());
        eventObservable.notifyObservers("hello world");
    }


    /**
     * 事件发布服务
     */
    static class EventObservable extends Observable {

        @Override
        public void notifyObservers(Object arg) {
            setChanged();
            super.notifyObservers(new EventObject(arg));
            clearChanged();
        }
    }

    /**
     * 监听器
     */
    static class EventObServer implements Observer, EventListener {

        @Override
        public void update(Observable o, Object arg) {
            EventObject eventObject = (EventObject) arg;
            System.out.println(eventObject);
        }
    }
}
