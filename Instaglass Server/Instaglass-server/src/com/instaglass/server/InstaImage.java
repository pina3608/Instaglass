package com.instaglass.server;

import com.google.appengine.api.datastore.Blob;
import com.google.appengine.api.datastore.Key;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class InstaImage {
    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;

    @Persistent
    private String name;

    @Persistent
    Blob image;

    public InstaImage(String name, Blob image) {
        this.name = name; 
        this.image = image;
    }

    public Blob getImage()              { return image; }
    public void setImage(Blob image)    { this.image = image; }
    public String getName() {
		return name;
	}
}
