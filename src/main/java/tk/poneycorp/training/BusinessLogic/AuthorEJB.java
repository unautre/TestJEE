package tk.poneycorp.training.BusinessLogic;

import tk.poneycorp.training.data.AuthorBean;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by unautre on 28/04/16.
 */
@Stateless
public class AuthorEJB {
    @PersistenceContext(unitName = "jpa_training")
    EntityManager em;

    public AuthorBean register(String nickname, String password){
        AuthorBean author = new AuthorBean(nickname, password);
        em.persist(author);
        return author;
    }

    public AuthorBean login(String nickname, String password){
        TypedQuery<AuthorBean> requete = em.createQuery(
                "SELECT a from AuthorBean a where a.nickname = :name",
                AuthorBean.class
        );
        AuthorBean resultat = requete.setParameter("name", nickname).getSingleResult();
        if(resultat.getPassword().equals(password)){
            return resultat;
        }else{
            return null;
        }
    }
}
