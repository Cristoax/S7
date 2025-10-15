package com.empresix.gestion.repositorio;

import com.empresix.gestion.entity.Cargo;
import jakarta.persistence.*;

import java.util.List;
import java.util.Optional;

public class CargoRepositorioJPA implements CargoRepositorio {

    private final EntityManagerFactory emf;

    public CargoRepositorioJPA(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public Cargo crear(Cargo cargo) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(cargo);
            tx.commit();
            return cargo;
        } catch (RuntimeException ex) {
            if (tx.isActive()) tx.rollback();
            throw ex;
        } finally {
            em.close();
        }
    }

    @Override
    public Cargo actualizar(Cargo cargo) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Cargo actualizado = em.merge(cargo);
            tx.commit();
            return actualizado;
        } catch (RuntimeException ex) {
            if (tx.isActive()) tx.rollback();
            throw ex;
        } finally {
            em.close();
        }
    }

    @Override
    public void eliminar(Long id) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Cargo cargo = em.find(Cargo.class, id);
            if (cargo != null) {
                em.remove(cargo);
            }
            tx.commit();
        } catch (RuntimeException ex) {
            if (tx.isActive()) tx.rollback();
            throw ex;
        } finally {
            em.close();
        }
    }

    @Override
    public Optional<Cargo> buscarPorId(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            return Optional.ofNullable(em.find(Cargo.class, id));
        } finally {
            em.close();
        }
    }

    @Override
    public Optional<Cargo> buscarPorCodigo(String codigo) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Cargo> query = em.createQuery(
                    "SELECT c FROM Cargo c WHERE c.codigo = :codigo", Cargo.class);
            query.setParameter("codigo", codigo);
            return query.getResultStream().findFirst();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Cargo> listarTodos() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT c FROM Cargo c", Cargo.class).getResultList();
        } finally {
            em.close();
        }
    }
}
