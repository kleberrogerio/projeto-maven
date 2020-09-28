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
        Aluno aluno = new Aluno();
        aluno.setNomeUsuario("alunofatec");
        aluno.setSenha("senha");
        aluno.setRa(1234567891011L);

        Professor professor = new Professor();
        professor.setNomeUsuario("professorlabiv");
        professor.setSenha("senhaForte");

        Trabalho trabalho = new Trabalho();
        trabalho.setTitulo("Trabalho 2 Lab IV");
        trabalho.setDataHoraEntrega(new Date());
        trabalho.setLocalArquivo("trabalhos");
        trabalho.setAvaliador(professor);
        trabalho.setAlunos(new HashSet<Aluno>());
        trabalho.getAlunos().add(aluno);

        try{
            manager.getTransaction().begin();
            manager.persist(aluno);
            manager.persist(professor);
            manager.persist(trabalho);
            manager.getTransaction().commit();
            }
        catch(PersistenceException e){
            e.printStackTrace();
            manager.getTransaction().rollback();
        }
        manager.clear();

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
        
        aluno = manager.find(Aluno.class, aluno.getId);
        System.out.println(aluno.getId());
        System.out.println(aluno.getNomeUsuario());  
        for(Trabalho trab: aluno.getTrabalhos()) {
            System.out.println(trab.getTitulo());
        }
        manager.clear();
        trabalho = manager.find(Trabalho.class,trabalho.getId);
        System.out.println(trabalho.getTitulo());
        for(Aluno al: trabalho.getAlunos()){
            System.out.println(al.getNomeUsuario());
        }


        manager.close();    	
    }
}
