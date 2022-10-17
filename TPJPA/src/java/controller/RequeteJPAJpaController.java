/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.exceptions.NonexistentEntityException;
import entity.RequeteJPA;
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
public class RequeteJPAJpaController implements Serializable {

    public RequeteJPAJpaController() {
        this.emf = Persistence.createEntityManagerFactory("jpalocalhost");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(RequeteJPA requeteJPA) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(requeteJPA);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(RequeteJPA requeteJPA) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            requeteJPA = em.merge(requeteJPA);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = requeteJPA.getId();
                if (findRequeteJPA(id) == null) {
                    throw new NonexistentEntityException("The requeteJPA with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            RequeteJPA requeteJPA;
            try {
                requeteJPA = em.getReference(RequeteJPA.class, id);
                requeteJPA.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The requeteJPA with id " + id + " no longer exists.", enfe);
            }
            em.remove(requeteJPA);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<RequeteJPA> findRequeteJPAEntities() {
        return findRequeteJPAEntities(true, -1, -1);
    }

    public List<RequeteJPA> findRequeteJPAEntities(int maxResults, int firstResult) {
        return findRequeteJPAEntities(false, maxResults, firstResult);
    }

    private List<RequeteJPA> findRequeteJPAEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(RequeteJPA.class));
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

    public RequeteJPA findRequeteJPA(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(RequeteJPA.class, id);
        } finally {
            em.close();
        }
    }

    public int getRequeteJPACount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<RequeteJPA> rt = cq.from(RequeteJPA.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
