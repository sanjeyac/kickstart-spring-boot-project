package eu.sanprojects.kickstart.model;

import javax.persistence.*;

/**
 * Created by sanjeya on 21/02/15.
 */
@Entity
@Table(name = "bookmarks")
public class Bookmark {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    Long id;

    String name;
    String url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
