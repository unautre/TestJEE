package tk.poneycorp.training.data;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by unautre on 24/04/16.
 */
@Entity @XmlRootElement(name = "author")
public class AuthorBean {
    @Id //@XmlID
    private String nickname;

    private String password;

    @OneToMany(mappedBy = "author")
    private List<MessageBean> msgs = new ArrayList<>();

    public AuthorBean() { }
    public AuthorBean(String nickname, String password) {
        this.nickname = nickname;
        this.password = password;
    }

    @XmlID @XmlAttribute
    public String getNickname() {
        return nickname;
    }
    public void setNickname(String id) {
        this.nickname = id;
    }

    @XmlTransient
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public List<MessageBean> getMsgs() {
        return msgs;
    }
    public void setMsgs(List<MessageBean> msgs) {
        this.msgs = msgs;
    }

    @Override
    public String toString() {
        return "AuthorBean{" +
                "nickname='" + nickname + '\'' +
                ", password='" + password + '\'' +
                ", msgs=" + msgs +
                '}';
    }
}
