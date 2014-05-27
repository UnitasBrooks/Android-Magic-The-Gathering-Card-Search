package com.example.android.navigationdrawerexample;

import android.graphics.Bitmap;

/**
 * Created with IntelliJ IDEA.
 * User: DarthDesktop
 * Date: 5/25/14
 * Time: 8:20 PM
 * To change this template use File | Settings | File Templates.
 */
public class CardNode {
    protected CardNode next = null;
    protected CardNode prev = null;
    protected String url = null;
    protected Bitmap bits = null;

    public CardNode(String url) {
        this.url = url;
    }

    public CardNode(Bitmap bits) {
        this.bits = bits;
    }

    public CardNode() {
        this.next = null;
        this.prev = null;
        this.url = null;
    }


}
