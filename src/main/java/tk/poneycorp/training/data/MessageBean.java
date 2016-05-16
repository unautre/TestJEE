package tk.poneycorp.training.data;

import javax.persistence.*;
import javax.xml.bind.annotation.*;

/**
 * Created by unautre on 24/04/16.
 */
@Entity @XmlRootElement(name = "message")
public class MessageBean {
    @Id @GeneratedValue
    private long id;

    private String content;

    @ManyToOne // @XmlIDREF
    private AuthorBean author;

    public MessageBean() {

    }
    public MessageBean(String content, AuthorBean author) {
        this.content = content;
        this.author = author;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }

    public AuthorBean getAuthor() {
        return author;
    }
    public void setAuthor(AuthorBean author) {
        this.author = author;
    }
}