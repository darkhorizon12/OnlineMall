package org.juon.jpashop.domain.item;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("AB")
public class Album extends Item{
	private String artist;
	private String composer;
	public String getArtist() {
		return artist;
	}
	public void setArtist(String artist) {
		this.artist = artist;
	}
	public String getComposer() {
		return composer;
	}
	public void setComposer(String composer) {
		this.composer = composer;
	}
	@Override
	public String toString() {
		return "Album [artist=" + artist + ", composer=" + composer + "]";
	}
}
