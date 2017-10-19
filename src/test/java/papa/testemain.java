/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package papa;

import Entidades.Conta;
import Entidades.Projetos;
import Entidades.Usuario;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import org.hibernate.metamodel.source.annotations.entity.EntityClass;

/**
 *
 * @author adoniran
 */
public class testemain {

    public static void main(String[] args) {
        Usuario user = new Usuario();
        Usuario user2 = new Usuario();
        Projetos proj = new Projetos();
        Conta conta = new Conta(); 
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SC_exercicioPU");

        user.setEmail("User@teste.com");
        user.setNome("Userteste");
        user.setLogin("Userteste00");
        user.setSenha("Userteste00");

        user2.setEmail("User2@teste.com");
        user2.setNome("User2teste");
        user2.setLogin("User2teste02");
        user2.setSenha("User2teste02");

       
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();//            
        tx.begin();
        em.persist(user);
        tx.commit();em.close();
        
        proj.setNome("ProjTeste00");
        proj.setDescricao("Projeto Teste 00");
        proj.adicionar(user2);
        proj.adicionar(user);

        em = emf.createEntityManager();
        tx = em.getTransaction();//            
        tx.begin();
        em.persist(proj);
        tx.commit();   em.close();     

        conta.setAgencia("123456");
        conta.setNumero("123456");
        conta.setTipo("Poupança");
        conta.setDonoConta(user);
        user.setConta(conta);

        em = emf.createEntityManager();
        tx = em.getTransaction();//            
        tx.begin();
        em.persist(conta);
        tx.commit();
        em.close();
        

    }

    public void S(Entity AlgumaCoisa) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SC_exercicioPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();//            
        tx.begin();
        em.persist(AlgumaCoisa);
        tx.commit();
        //garbage colector
//        em = null;
//        emf = null;
//        tx = null;

    }

}
