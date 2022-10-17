/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import entity.UserJPA;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author extra
 */
public class UserJPAJpaController implements Serializable {

    public UserJPAJpaController() {
        this.emf = Persistence.createEntityManagerFactory("jpalocalhost");
    }
    
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UserJPA userJPA) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(userJPA);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findUserJPA(userJPA.getEmail()) != null) {
                throw new PreexistingEntityException("UserJPA " + userJPA + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UserJPA userJPA) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            userJPA = em.merge(userJPA);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = userJPA.getEmail();
                if (findUserJPA(id) == null) {
                    throw new NonexistentEntityException("The userJPA with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UserJPA userJPA;
            try {
                userJPA = em.getReference(UserJPA.class, id);
                userJPA.getEmail();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The userJPA with id " + id + " no longer exists.", enfe);
            }
            em.remove(userJPA);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UserJPA> findUserJPAEntities() {
        return findUserJPAEntities(true, -1, -1);
    }

    public List<UserJPA> findUserJPAEntities(int maxResults, int firstResult) {
        return findUserJPAEntities(false, maxResults, firstResult);
    }

    private List<UserJPA> findUserJPAEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UserJPA.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public UserJPA findUserJPA(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UserJPA.class, id);
        } finally {
            em.close();
        }
    }

    public int getUserJPACount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UserJPA> rt = cq.from(UserJPA.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
