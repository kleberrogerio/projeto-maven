package br.gov.sp.fatec.projetomaven;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

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

        //Salva aluno, professor e trabalho
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
        //Limpa o cache para forçar a execução de select
        manager.clear();
        
        //Busca o aluno pelo ID
        aluno = manager.find(Aluno.class, aluno.getId());
        System.out.println(aluno.getId());
        System.out.println(aluno.getNomeUsuario());  
        for(Trabalho trab: aluno.getTrabalhos()) {
            System.out.println(trab.getTitulo());
        }
        //Limpa o cache para forçar a execução de select
        manager.clear();

        //Busca o trabalho pelo ID
        trabalho = manager.find(Trabalho.class,trabalho.getId());
        System.out.println(trabalho.getTitulo());
        for(Aluno al: trabalho.getAlunos()){
            System.out.println(al.getNomeUsuario());
        }
        trabalho.setTitulo("Novo Trabalho 2 - JPA");
        
        try{
            manager.getTransaction().begin();
            manager.merge(trabalho);
            manager.getTransaction().commit();
        }
        catch(PersistenceException e){
            e.printStackTrace();
            manager.getTransaction().rollback();
        }

        String queryString = "select t from Trabalho t where t.titulo like :titulo";
        TypedQuery<Trabalho> query = manager.createQuery(queryString,Trabalho.class);
        query.setParameter("titulo","%JPA%");

        List<Trabalho> resultados = query.getResultList();
        for(Trabalho trab:resultados){
            System.out.println("Titulo: "+trab.getTitulo());
        }

        //Apaga registros (permite re-execução)
        try{
            manager.getTransaction().begin();
            professor = trabalho.getAvaliador();
            trabalho.setAvaliador(null);
            Set<Aluno> alunos = trabalho.getAlunos();
            trabalho.setAlunos(null);
            manager.remove(trabalho);
            manager.remove(professor);
            for(Aluno al:alunos){
                manager.remove(al);
            }
            manager.getTransaction().rollback();
        }
        catch(PersistenceException e){
            e.printStackTrace();
            manager.getTransaction().rollback();
        }
        manager.close();    	
    }
}
