/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package papa;

import Entidades.Area;
import Entidades.Arquivos;
import Entidades.Conta;
import Entidades.Endereco;
import Entidades.Necessidade;
import Entidades.Projetos;
import Entidades.Situacao;
import Entidades.Usuario;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

/**
 *
 * @author adoniran
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SuppressWarnings("JPQLValidation")
public class TestesManeger {
    private static EntityManagerFactory emf;
    private static final Logger logger = Logger.getGlobal();
    private EntityManager em;
    private EntityTransaction et;
    public TestesManeger() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        logger.setLevel(Level.SEVERE);
        emf = Persistence.createEntityManagerFactory("SC_exercicioPU");
        DbUnitUtil.inserirDados();

    }
     
    @AfterClass
    public static void tearDownClass() {
        emf.close();
    }

    @Before
    public void setUp() {
        
        em = emf.createEntityManager();
        beginTransaction();
        
    }

    @After
    public void tearDown() {
        commitTransaction();
        em.close();
    }

    private void beginTransaction() {
        et = em.getTransaction();
        et.begin();
    }

    private void commitTransaction() {
        try {
            et.commit();
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
            et.rollback();
            fail(ex.getMessage());
        }
}

    @Test
    public void t01_inserir_userEconta(){
        Usuario user = new Usuario();
        Conta conta = new Conta(); 
        user.setEmail("User@teste.com");
        user.setNome("Userteste");
        user.setLogin("Userteste00");
        user.setSenha("Userteste00");
        
        Endereco end=new Endereco();
        end.setBairro("jardim");
        end.setCep("53300-003");
        end.setCidade("recife");
        end.setEstado("PE");
        end.setLogradouro("rua dos bobos");
        end.setNumero(751);
        user.setEndereco(end);
        
        conta.setAgencia("123456");
        conta.setNumero("123456");
        conta.setTipo("Poupança");
        user.setConta(conta);
        em.persist(user);
        assertNotNull(conta.getId());
        assertNotNull(user.getId());
    }
    
    @Test
    public void t02_inserir_userEtelefone(){
        
        Usuario user2 = new Usuario();
        user2.setEmail("User2@teste.com");
        user2.setNome("User2teste");
        user2.setLogin("User2teste02");
        user2.setSenha("User2teste02");
        user2.addTelefone("20900-45654");
        user2.addTelefone("4003-4154");
        user2.addTelefone("5465-1111");
        Endereco end=new Endereco();
        end.setBairro("jardim");
        end.setCep("53300-003");
        end.setCidade("recife");
        end.setEstado("PE");
        end.setLogradouro("rua dos bobos");
        end.setNumero(751);
        user2.setEndereco(end);
        
        em.persist(user2);
        
        assertEquals(3, user2.getTelefones().size());
    
    
    }
    @Test
    public void t03_inserir_projeto(){
        Projetos projeto=new Projetos();
        projeto.setArea(Area.TI);
        projeto.setDescricao("estrela da morte");
        projeto.setNecessidade(Necessidade.FINANCEIRO);
        projeto.setMotivacaoNecessidade("materiais");
        projeto.setNome("estrela da morte");
        projeto.setSituacao(Situacao.PARADO);
        Arquivos arq =new Arquivos();
        arq.setNome("requisitos");
        arq.setTipo("pdf");
        arq.setURL("bit.ly/arquivo");
        
        projeto.addArquivo(arq);
        em.persist(projeto);
        assertNotNull(projeto.getId());
        assertNotNull(arq.getId());
    }
    @Test
    public void t04_Update_Usuarios_ao_projeto(){
        long id=1;
        Projetos projetos= em.find(Projetos.class, id);
        
        Usuario user = new Usuario();
        
        user.setEmail("User@teste.com");
        user.setNome("Userteste");
        user.setLogin("Userteste00");
        user.setSenha("Userteste00");
        
        Endereco end=new Endereco();
        end.setBairro("jardim");
        end.setCep("53300-003");
        end.setCidade("recife");
        end.setEstado("PE");
        end.setLogradouro("rua dos bobos");
        end.setNumero(751);
        user.setEndereco(end);
        
        projetos.addUsuario(user);
        em.merge(projetos);
    }
    @Test
    public void t05_Deletar_Arquivo(){
        long id=3;
    Projetos projetos;
   projetos= em.find(Projetos.class, id);
    Arquivos arq =em.find(Arquivos.class, projetos.getArquivos().get(0).getId());
            
    em.remove(arq);
    assertNull(projetos.getArquivos().get(0));
    
    }
    
    @Test
    public void t06_Deletar_Usuario(){
    long id=4;
    Usuario user=em.find(Usuario.class, id);
    em.remove(user);
        assertNull(user.getId());
    
    }
    @Test
    public void t07_Update_Usuario(){
        long id=3;
    Usuario user=em.find(Usuario.class, id);
    user.setSenha("alohomora");
    user.setEmail("email@email.com");
        em.merge(user);
        em.clear();
         user=em.find(Usuario.class, id);
        assertEquals("alohomora",user.getSenha());
        assertEquals("email@email.com",user.getEmail());
    
    }
    @Test
    public void t08_Update_Situacao(){
        long id=2;
   Projetos projetos= em.find(Projetos.class, id);
   projetos.setSituacao(Situacao.CONCLUIDO);
   projetos.setNecessidade(Necessidade.NENHUMA);
   projetos.setMotivacaoNecessidade("");
   em.merge(projetos);
   em.clear();
   projetos=null;
   projetos=em.find(Projetos.class, id);
        assertEquals(projetos.getSituacao(), Situacao.CONCLUIDO);
        assertEquals(projetos.getNecessidade(),Necessidade.NENHUMA);
        assertEquals(projetos.getMotivacaoNecessidade(),"");
    
    }
    
    
    
    
    
    
    
}
