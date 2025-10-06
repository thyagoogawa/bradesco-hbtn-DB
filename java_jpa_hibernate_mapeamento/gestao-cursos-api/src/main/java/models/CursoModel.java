package models;

import entities.Curso;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class CursoModel {

    public void create(Curso curso) {
        EntityManager em = getEntityManager();

        try {
            System.out.println("Iniciando a transação");
            em.getTransaction().begin();
            em.persist(curso);
            em.getTransaction().commit();
            System.out.println("Curso criado com sucesso !!!");
        } catch (Exception e) {
            em.close();
            System.err.println("Erro ao criar um curso !!!" + e.getMessage());
        } finally {
            em.close();
            System.out.println("Finalizando a transação");
        }
    }
    
    public Curso findById(Long id) {
        EntityManager em = getEntityManager();

        try {
            System.out.println("Iniciando a busca por ID");
            em.getTransaction().begin();
            Curso curso = em.find(Curso.class, id);
            em.getTransaction().commit();
            System.out.println("Curso encontrado com sucesso !!!");
            return curso;

        } catch (Exception e) {
            em.close();
            System.err.println("Erro ao buscar um curso !!!" + e.getMessage());

        } finally {
            em.close();
            System.out.println("Finalizando a busca por ID");
        }

        return null;
    }

    public List<Curso> findAll() {
        EntityManager em = getEntityManager();

        try {
            System.out.println("Iniciando a busca de todos os cursos");
            em.getTransaction().begin();
            List<Curso> cursos = em.createQuery("FROM Curso", Curso.class).getResultList();
            em.getTransaction().commit();
            System.out.println("Cursos encontrados com sucesso !!!");
            return cursos;

        } catch (Exception e) {
            em.close();
            System.err.println("Erro ao buscar todos os cursos !!!" + e.getMessage());

        } finally {
            em.close();
            System.out.println("Finalizando a busca de todos os cursos");
        }
        return null;
    }

    public void update(Curso curso) {
        EntityManager em = getEntityManager();

        try {
            System.out.println("Iniciando a atualizacao do curso");
            em.getTransaction().begin();
            em.merge(curso);
            em.getTransaction().commit();
            System.out.println("Curso atualizado com sucesso !!!");

        } catch (Exception e) {
            em.close();
            System.err.println("Erro ao atualizar o curso !!!" + e.getMessage());

        } finally {
            em.close();
            System.out.println("Finalizando a atualizacao do curso");
        }
    }

    /**
     *
     * @param curso Deve ser um curso previamente buscado no banco de dados
     */
    public void delete(Curso curso) {
        EntityManager em = getEntityManager();

        try {
            System.out.println("Iniciando a delecao do curso");
            em.getTransaction().begin();
            Curso cursoManaged = em.merge(curso);
            em.remove(cursoManaged);
            em.getTransaction().commit();
            System.out.println("Curso deletado com sucesso !!!");

        } catch (Exception e) {
            em.close();
            System.err.println("Erro ao deletar o curso !!!" + e.getMessage());

        } finally {
            em.close();
            System.out.println("Finalizando a delecao do curso");
        }
    }

    private static EntityManager getEntityManager() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestao-cursos-jpa");
        return emf.createEntityManager();
    }
    
}
