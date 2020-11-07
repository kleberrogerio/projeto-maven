package br.gov.sp.fatec.projetomaven.dao;

public interface AlunoDao{
    public Aluno cadastrarAluno(String nomeUsuario, String senha, Long ra);

    public Aluno buscarAlunoPorRa(Long ra);

    public void removerAluno(Long ra); 
    
}