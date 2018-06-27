/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.utils;

/**
 *
 * @author namdv
 */
public class CounterSynchronized {

    private static int c = 0;

    public static synchronized int increment() {
       return c++;
    }

    public static synchronized void decrement() {
        c--;
    }

    public synchronized int value() {
        return c;
    }
}
