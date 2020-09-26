package br.gov.sp.fatec.projetomaven;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

import br.gov.sp.fatec.projetomaven.entity.Aluno;
import br.gov.sp.fatec.projetomaven.entity.Professor;
import br.gov.sp.fatec.projetomaven.entity.Trabalho;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("avaliacao");
        EntityManager manager = factory.createEntityManager();
        /*
        Aluno aluno = new Aluno();
        aluno.setNomeUsuario("aluno");
        aluno.setSenha("senha");
        aluno.setRa(1234567891011L);

        Professor professor = new Professor();
        professor.setNomeUsuario("mineda");
        professor.setSenha("senhaForte");
        try{
            manager.getTransaction().begin();
            manager.persist(aluno);
            manager.persist(professor);
            manager.getTransaction().commit();
            }
        catch(PersistenceException e){
            e.printStackTrace();
            manager.getTransaction().rollback();
        }
        */
        Aluno aluno = manager.find(Aluno.class,1L);
        System.out.println(aluno.getId());
        System.out.println(aluno.getNomeUsuario());  
        for(Trabalho trabalho: aluno.getTrabalhos()) {
            System.out.println(trabalho.getTitulo());
        }
        manager.close();    	
    }
}
