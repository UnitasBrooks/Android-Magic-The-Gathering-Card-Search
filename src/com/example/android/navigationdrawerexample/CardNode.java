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
    protected String name = null;
    protected Bitmap bits = null;
    protected String id = null;


    public CardNode(String id, String name, String url) {
        this.id = id;
        this.name = name;
        this.url = url;
    }

    public void setBits(Bitmap bits) {
        this.bits = bits;
    }

    public CardNode(Bitmap bits) {
        this.bits = bits;
    }

}
