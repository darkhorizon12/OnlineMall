package org.juon.jpashop.domain.item;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@DiscriminatorValue("MV")
public class Movie extends Item {

    private String director;
    private String actor;
    
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date releaseDate;

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getActor() {
		return actor;
	}

	public void setActor(String actor) {
		this.actor = actor;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	@Override
	public String toString() {
		return "Movie [director=" + director + ", actor=" + actor + ", releaseDate=" + releaseDate + "]";
	}
}

