package com.empresix.gestion.repositorio;

import com.empresix.gestion.entity.Empleado;
import jakarta.persistence.*;

import java.util.List;
import java.util.Optional;

public class EmpleadoRepositorioJPA implements EmpleadoRepositorio {

    private final EntityManagerFactory emf;

    public EmpleadoRepositorioJPA(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public Empleado crear(Empleado empleado) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(empleado);
            tx.commit();
            return empleado;
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public Optional<Empleado> buscarPorId(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            return Optional.ofNullable(em.find(Empleado.class, id));
        } finally {
            em.close();
        }
    }

    @Override
    public Optional<Empleado> buscarPorCodigo(String codigo) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Empleado> query = em.createQuery(
                    "SELECT e FROM Empleado e WHERE e.codigo = :codigo", Empleado.class);
            query.setParameter("codigo", codigo);
            return query.getResultStream().findFirst();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Empleado> listarTodos() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT e FROM Empleado e", Empleado.class).getResultList();
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
            Empleado empleado = em.find(Empleado.class, id);
            if (empleado != null) {
                em.remove(empleado);
            }
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        } finally {
            em.close();
        }
    }
}
