package tk.poneycorp.training.BusinessLogic;

import tk.poneycorp.training.data.AuthorBean;
import tk.poneycorp.training.data.MessageBean;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.util.List;

/**
 * Created by unautre on 27/04/16.
 */
//@ManagedBean
//@RequestScoped
@Stateless
public class MessagesEJB implements Serializable {
    MessagesEJB(){}

    @PersistenceContext(unitName = "jpa_training")
    EntityManager em;

    public List<MessageBean> getAllMessages(){
        TypedQuery<MessageBean> query = em.createQuery("Select m from MessageBean m", MessageBean.class);
        return query.getResultList();
    }

    public MessageBean sendMessage(String message, AuthorBean authorBean){
        MessageBean messageBean = new MessageBean(message, authorBean);
        em.persist(messageBean);
        return messageBean;
    }

    public List<MessageBean> getAllSince(long from_id) {
        TypedQuery<MessageBean> query = em.createQuery("Select m from MessageBean m where m.id > :from_id", MessageBean.class);
        return query.setParameter("from_id", from_id).getResultList();
    }
}
