package models;

import entities.Aluno;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
/*
 * aluno com telefone OK
 * aluno com endereços OK
 * aluno com cursos OK
 * atualizar aluno OK
 * deletar aluno OK
 */
public class AlunoModel {

    public void create(Aluno aluno) {
        EntityManager em = getEntityManager();

        try {
            System.out.println("Iniciando a transação");
            em.getTransaction().begin();
            em.persist(aluno);
            em.getTransaction().commit();
            System.out.println("Aluno criado com sucesso !!!");
        } catch (Exception e) {
            em.close();
            System.err.println("Erro ao criar um aluno !!!" + e.getMessage());
        } finally {
            em.close();
            System.out.println("Finalizando a transação");
        }
    }

    public Aluno findById(Long id) {
        EntityManager em = getEntityManager();

        try {
            System.out.println("Iniciando a busca por ID");
            em.getTransaction().begin();
            Aluno aluno = em.find(Aluno.class, id);
            em.getTransaction().commit();
            System.out.println("Aluno encontrado com sucesso !!!");
            return aluno;

        } catch (Exception e) {
            em.close();
            System.err.println("Erro ao buscar um aluno !!!" + e.getMessage());

        } finally {
            em.close();
            System.out.println("Finalizando a busca por ID");
        }

        return null;
    }

    public List<Aluno> findAll() {
        EntityManager em = getEntityManager();

        try {
            System.out.println("Iniciando a busca de todos os alunos");
            em.getTransaction().begin();
            List<Aluno> alunos = em.createQuery("FROM Aluno", Aluno.class).getResultList();
            em.getTransaction().commit();
            System.out.println("Alunos encontrados com sucesso !!!");
            return alunos;

        } catch (Exception e) {
            em.close();
            System.err.println("Erro ao buscar todos os alunos !!!" + e.getMessage());

        } finally {
            em.close();
            System.out.println("Finalizando a busca de todos os alunos");
        }
        return null;
    }

    public void update(Aluno aluno) {
        EntityManager em = getEntityManager();

        try {
            System.out.println("Iniciando a atualizacao do aluno");
            em.getTransaction().begin();
            em.merge(aluno);
            em.getTransaction().commit();
            System.out.println("Aluno atualizado com sucesso !!!");

        } catch (Exception e) {
            em.close();
            System.err.println("Erro ao atualizar o aluno !!!" + e.getMessage());

        } finally {
            em.close();
            System.out.println("Finalizando a atualizacao do aluno");
        }
    }

    /**
     *
     * @param aluno Deve ser um aluno previamente buscado no banco de dados
     */
    public void delete(Aluno aluno) {
        EntityManager em = getEntityManager();

        try {
            System.out.println("Iniciando a delecao do aluno");
            em.getTransaction().begin();
            Aluno alunoManaged = em.merge(aluno);
            em.remove(alunoManaged);
            em.getTransaction().commit();
            System.out.println("Aluno deletado com sucesso !!!");

        } catch (Exception e) {
            em.close();
            System.err.println("Erro ao deletar o aluno !!!" + e.getMessage());

        } finally {
            em.close();
            System.out.println("Finalizando a delecao do aluno");
        }
    }

    private static EntityManager getEntityManager() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestao-cursos-jpa");
        return emf.createEntityManager();
    }

}
