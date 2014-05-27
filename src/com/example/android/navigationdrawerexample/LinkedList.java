package com.example.android.navigationdrawerexample;

import android.graphics.Bitmap;

/**
 * Created with IntelliJ IDEA.
 * User: DarthDesktop
 * Date: 5/25/14
 * Time: 8:20 PM
 * To change this template use File | Settings | File Templates.
 */
public class LinkedList {
    private CardNode head;
    private CardNode current;
    private CardNode tail;
    private int count;

    LinkedList() {
        current = null;
        head = null;
        count = 0;
    }

    public void push(Bitmap bitSave) {
        if(head == null) {
            head = new CardNode(bitSave);
            tail = head;
        } else {
            CardNode add = new CardNode(bitSave);
            add.prev = head;
            head.next = add;
            head = add;
            current = head;

            if(tail.prev == null) {
                tail.prev = add;
            }
        }

        if(count <= 10) {
            count++;
        } else {
            deleteLast();
            count--;
        }
    }

    private void deleteLast() {
        CardNode hold = tail.prev.prev;
        tail = tail.prev;
        tail.prev = hold;
    }

    public Bitmap getPrevious() {
        if(current != null && current.prev != null && current != tail) {
            current = current.prev;
            return current.bits;
        }
        return null;
    }

    public Bitmap getNext() {
        if(current != null && current.next != null) {
            current = current.next;
            return current.bits;
        }
        return null;
    }
}
