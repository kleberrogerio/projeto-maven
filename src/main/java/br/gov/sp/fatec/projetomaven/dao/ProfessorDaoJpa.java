package br.gov.sp.fatec.projetomaven.dao;

import javax.persistence.EntityManager;

import br.gov.sp.fatec.projetomaven.entity.PersistenceManager;
import br.gov.sp.fatec.projetomaven.entity.Professor;

public class ProfessorDaoJpa implements ProfessorDao {
    private EntityManager em;

    public ProfessorDaoJpa(){
        this(PersistenceManager.getInstance().getEntityManager());
    }

    public ProfessorDaoJpa(EntityManager em){
        this.em=em;
    }

    @Override
    public Professor cadastrarProfessor(String nomeUsuario, String senha, String titulo) {
        Professor professor = new Professor();
        professor.setNomeUsuario(nomeUsuario);
        professor.setSenha(senha);
        professor.setTitulo(titulo);
       return salvarProfessor(professor);
    }
     @Override
    public Professor salvarAluno(Professor professor){
        try{
            em.getTransaction().begin();
            if(professor.getId()==null){
                em.persist(professor);
            }
            else{
                em.merge(professor);
            }
            em.getTransaction().commit();
            return professor;
        }
        catch(PersistenceException e){
            e.printStackTrace();
            em.getTransaction().rollback();
            throw new RuntimeException("Erro ao salvar Professor!", e);
        }    
    }   

    @Override
    public Aluno buscarProfessor(String nomeUsuario) {
        String jpql = "select a from Professor a where a.ra = :ra";
        TypedQuery<Professor> query = em.createQuery(jpql, Professor.class);
        query.setParameter("ra",ra);
        return query.getSingleResult();
    }

    @Override
	public void removerProfessor(String nomeUsuario) {
        Professor professor = buscarProfessor(nomeUsuario);
        if(aluno==null){
            throw new RuntimeException("Professor não cadastrado");
        }
        em.getTransaction().begin();
        em.remove(aluno);
        em.getTransaction().commit();        
    }

}