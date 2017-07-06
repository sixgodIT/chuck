package com.readystatesoftware.chuck;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.HttpCookie;

/**
 * @author Created by xzz
 * @date Created at 下午7:42 on 2017/6/8
 * @description
 */
public class SerializableHttpCookie implements Serializable {
    private static final long serialVersionUID = 1L;

    private transient final HttpCookie mCookie;
    private transient HttpCookie mHttpCookie;

    public SerializableHttpCookie(HttpCookie cookie) {
        this.mCookie = cookie;
    }

    public HttpCookie getCookie() {
        HttpCookie bestCookie = mCookie;
        if (mHttpCookie != null) {
            bestCookie = mHttpCookie;
        }
        return bestCookie;
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.writeObject(mCookie.getName());
        out.writeObject(mCookie.getValue());
        out.writeObject(mCookie.getComment());
        out.writeObject(mCookie.getCommentURL());
        out.writeObject(mCookie.getDomain());
        out.writeLong(mCookie.getMaxAge());
        out.writeObject(mCookie.getPath());
        out.writeObject(mCookie.getPortlist());
        out.writeInt(mCookie.getVersion());
        out.writeBoolean(mCookie.getSecure());
        out.writeBoolean(mCookie.getDiscard());
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        String name = (String) in.readObject();
        String value = (String) in.readObject();
        mHttpCookie = new HttpCookie(name, value);
        mHttpCookie.setComment((String) in.readObject());
        mHttpCookie.setCommentURL((String) in.readObject());
        mHttpCookie.setDomain((String) in.readObject());
        mHttpCookie.setMaxAge(in.readLong());
        mHttpCookie.setPath((String) in.readObject());
        mHttpCookie.setPortlist((String) in.readObject());
        mHttpCookie.setVersion(in.readInt());
        mHttpCookie.setSecure(in.readBoolean());
        mHttpCookie.setDiscard(in.readBoolean());
    }
}

