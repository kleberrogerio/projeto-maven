package br.gov.sp.fatec.projetomaven.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import br.gov.sp.fatec.projetomaven.entity.Aluno;
import br.gov.sp.fatec.projetomaven.entity.PersistenceManager;

public class AlunoDaoJpa implements AlunoDao {
    
    private EntityManager em;

    public AlunoDaoJpa(){
        this(PersistenceManager.getInstance().getEntityManager());
    }

    public AlunoDaoJpa(EntityManager em){
        this.em=em;
    }

    @Override
    public Aluno cadastrarAluno(String nomeUsuario, String senha, Long ra) {
        Aluno aluno = new Aluno();
        aluno.setNomeUsuario(nomeUsuario);
        aluno.setSenha(senha);
        aluno.setRa(ra);
        try{
            em.getTransaction().begin();
            em.persist(aluno);
            em.getTransaction().commit();
            return aluno;
        }
        catch(PersistenceException e){
            e.printStackTrace();
            em.getTransaction().rollback();
        }
        return null;
    }

    @Override
    public Aluno buscarAlunoPorRa(Long ra) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
	public void removerAluno(Long ra) {
		// TODO Auto-generated method stub
		
    }
}