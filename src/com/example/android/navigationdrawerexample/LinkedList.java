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
        tail = null;
        count = 0;
    }

    public void push(CardNode card) {
        if(head == null) {
            head = card;
            tail = head;
            current = head;
        } else {
            CardNode add = card;
            add.prev = head;
            head.next = add;
            head = add;
            current = head;

            if(tail.next == null) {
                tail.next = add;
            }
        }

        if(count <= 10) {
            count++;
        } else {
            deleteLast();
        }
    }

    private void deleteLast() {
        tail = tail.next;
        tail.prev = null;
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

    public CardNode getCurrent() {
        return current;
    }
}
