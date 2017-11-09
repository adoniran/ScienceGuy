/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package papa;

import Entidades.Admin;
import Entidades.Area;
import Entidades.Arquivos;

import Entidades.Conta;
import Entidades.Endereco;
import Entidades.Log;
import Entidades.Necessidade;
import Entidades.Projetos;
import Entidades.Situacao;
import Entidades.Usuario;
import java.util.Date;


import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.inject.New;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.validation.ConstraintViolationException;

/**
 *
 * @author adoniran
 */
public class TesteValidation {

    private static EntityManagerFactory emf;
    private static final Logger logger = Logger.getGlobal();
    private EntityManager em;
    private EntityTransaction et;

    public TesteValidation() {
    }

    @BeforeClass
    public static void setUpClass() {
        logger.setLevel(Level.SEVERE);
        emf = Persistence.createEntityManagerFactory("SC_exercicioPU");
//        DbUnitUtil.inserirDados();
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
        try {
            et.commit();
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
            et.rollback();
            fail(ex.getMessage());
        }
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

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void T01_valida_Usuario() {

        Usuario user = new Usuario();
        Conta conta = new Conta();
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        user.setEmail("Userteste.com");//invalido
        user.setNome("serteste");//invalido
        user.setLogin("Usertest");
        user.setSenha("Userteste00");//invalido

        Endereco end = new Endereco();
        end.setBairro("jardim");
        end.setCep("53300-03");//invalido
        end.setCidade("recife");
        end.setEstado("turic");//invalido
        end.setLogradouro("rua dos bobos");
        end.setNumero(751);
        user.setEndereco(end);

        Set<ConstraintViolation<Usuario>> constraintViolations = validator.validate(user);

        if (logger.isLoggable(Level.INFO)) {
            for (ConstraintViolation violation : constraintViolations) {
                Logger.getGlobal().log(Level.INFO, "{0}.{1}: {2}", new Object[]{violation.getRootBeanClass(), violation.getPropertyPath(), violation.getMessage()});
            }
        }

        assertEquals(6, constraintViolations.size());

    }

    @Test
    public void T02_Valida_Conta() {

        Usuario user = new Usuario();
        Conta conta = new Conta();
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        //valido
        user.setEmail("User@teste.com");
        user.setNome("Userteste");
        user.setLogin("Userteste00");
        user.setSenha("User.teste00");
        //valido
        Endereco end = new Endereco();
        end.setBairro("jardim");
        end.setCep("53300-030");
        end.setCidade("recife");
        end.setEstado("PE");
        end.setLogradouro("rua dos bobos");
        end.setNumero(751);
        user.setEndereco(end);

        conta.setAgencia("12341111");//invalido
        //conta.setNumero("1"); sem numero conta
        conta.setTipo("popanca");//invalido
        user.setConta(conta);

        Set<ConstraintViolation<Usuario>> constraintViolations = validator.validate(user);

        if (logger.isLoggable(Level.INFO)) {
            for (ConstraintViolation violation : constraintViolations) {
                Logger.getGlobal().log(Level.INFO, "{0}.{1}: {2}", new Object[]{violation.getRootBeanClass(), violation.getPropertyPath(), violation.getMessage()});
            }
        }

        assertEquals(3, constraintViolations.size());

    }

//    @Test
//    public void T03_atualiza_invalida() {
//        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
//        TypedQuery<Usuario> query
//                = em.createQuery("select u from Usuario u where u.login = :login ", Usuario.class);
//        query.setParameter("login", "percy");
//        Usuario u = query.getSingleResult();
//        u.setEmail("cocorico");
//        u.setSenha("olimpo");
//        Set<ConstraintViolation<Usuario>> constraintViolations = validator.validate(u);
//
//        if (logger.isLoggable(Level.INFO)) {
//            for (ConstraintViolation violation : constraintViolations) {
//                Logger.getGlobal().log(Level.INFO, "{0}.{1}: {2}", new Object[]{violation.getRootBeanClass(), violation.getPropertyPath(), violation.getMessage()});
//            }
//        }
//
//        assertEquals(3, constraintViolations.size());
//
//    }

    @Test
    public void T04_Insere_User_conta_Valido() {
        Usuario user = new Usuario();
        Conta conta = new Conta();
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        //valido
        user.setEmail("User@teste.com");
        user.setNome("Userteste");
        user.setLogin("Userteste00");
        user.setSenha("User.teste00");
        //valido
        Endereco end = new Endereco();
        end.setBairro("jardim");
        end.setCep("53300-030");
        end.setCidade("recife");
        end.setEstado("PE");
        end.setLogradouro("rua dos bobos");
        end.setNumero(751);
        user.setEndereco(end);

        conta.setAgencia("12341");
        conta.setNumero("000567844");
        conta.setTipo("CONTA CORRENTE");
        user.setConta(conta);
        em.persist(user);

        Set<ConstraintViolation<Usuario>> constraintViolations = validator.validate(user);
        if (logger.isLoggable(Level.INFO)) {
            for (ConstraintViolation violation : constraintViolations) {
                Logger.getGlobal().log(Level.INFO, "{0}.{1}: {2}", new Object[]{violation.getRootBeanClass(), violation.getPropertyPath(), violation.getMessage()});
            }
        }

        assertEquals(0, constraintViolations.size());
    }

    @Test
    public void T05_Insere_Proj_Arquivo_Valido() {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Projetos projeto = new Projetos();
        projeto.setNome("Estrela");
        projeto.setArea(Area.TI);
        projeto.setDescricao("Estrela da morte");
        projeto.setNecessidade(Necessidade.FINANCEIRO);
        projeto.setMotivacaoNecessidade("materiais");
        
        projeto.setSituacao(Situacao.PARADO);
        Arquivos arq = new Arquivos();
        arq.setNome("requisitos");
        arq.setTipo("pdf");
        arq.setURL("bit.ly/arquivo");

        projeto.addArquivo(arq);
        
            em.persist(projeto);
            assertNotNull(projeto.getId());
       
            Set<ConstraintViolation<Projetos>> constraintViolations = validator.validate(projeto);
            

            if (logger.isLoggable(Level.INFO)) {
                for (ConstraintViolation violation : constraintViolations) {
                    Logger.getGlobal().log(Level.INFO, "{0}.{1}: {2}", new Object[]{violation.getRootBeanClass(), violation.getPropertyPath(), violation.getMessage()});
                }
            }

           
        

            
    }
    
    @Test
    public void T06_Proj_Arquivo_InValido() {
        Projetos projeto = new Projetos();
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        projeto.setArea(Area.TI);
        projeto.setDescricao(null);//invalido
        projeto.setNecessidade(Necessidade.FINANCEIRO);
        projeto.setMotivacaoNecessidade("materiais");
        projeto.setNome("estrela da morte que mata a morte matada de morte morrida daqueles que não morrem");//2X invalido
        projeto.setSituacao(Situacao.PARADO);
        Arquivos arq = new Arquivos();
        arq.setNome("requisitos");
        arq.setTipo("pdffgp.889-epub.mobbb.securyti");//invalido
        //sem url

        projeto.addArquivo(arq);
        
            

            Set<ConstraintViolation<Projetos>> constraintViolations = validator.validate(projeto);
        if (logger.isLoggable(Level.INFO)) {
            for (ConstraintViolation violation : constraintViolations) {
                Logger.getGlobal().log(Level.INFO, "{0}.{1}: {2}", new Object[]{violation.getRootBeanClass(), violation.getPropertyPath(), violation.getMessage()});
            }
        }

            assertEquals(3, constraintViolations.size());
            assertNull(projeto.getId());

            
    }
    
    @Test
    public void T07_Admin_Log_InValido() {
    Admin a = new Admin();
    Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
    a.setEmail("Adminteste.com");//invalido
        a.setNome("Userteste");
        a.setLogin("Userteste00");
        a.setSenha("User.teste00");
        a.setConfirmacao("12.45#!");//invalido
     
    
     Endereco end = new Endereco();
        end.setBairro("jardim");
        end.setCep("53300-030");
        end.setCidade("recife");
        end.setEstado("PE");
        end.setLogradouro("rua dos bobos");
        end.setNumero(751);
        a.setEndereco(end);
        
        Log log = new Log();
        log.setCategoria("loucura");//invalido
        log.setOperacao(logger.getResourceBundleName());
//        log.setDataDeOp(new Date());
        
        a.addLog(log);
        Set<ConstraintViolation<Admin>> constraintViolations = validator.validate(a);

        if (logger.isLoggable(Level.INFO)) {
            for (ConstraintViolation violation : constraintViolations) {
                Logger.getGlobal().log(Level.INFO, "{0}.{1}: {2}", new Object[]{violation.getRootBeanClass(), violation.getPropertyPath(), violation.getMessage()});
            }
        }

        assertEquals(5, constraintViolations.size());
    }
    
    @Test
    public void T08_Admin_Log_Valido() {
    Admin a = new Admin();
    Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
    a.setEmail("AdminSuper@teste.com");
        a.setNome("Userteste");
        a.setLogin("Userteste00");
        a.setSenha("User.teste00");
        a.setConfirmacao("1263456");
     
    
     Endereco end = new Endereco();
        end.setBairro("jardim");
        end.setCep("53300-030");
        end.setCidade("recife");
        end.setEstado("PE");
        end.setLogradouro("rua dos bobos");
        end.setNumero(751);
        a.setEndereco(end);
        
        Log log = new Log();
        log.setCategoria("MANUTENÇÃO");
        log.setOperacao("Troca De servidores");
        log.setDataDeOp(new Date()); 
        
        a.addLog(log);
        em.persist(a);
        Set<ConstraintViolation<Admin>> constraintViolations = validator.validate(a);

        if (logger.isLoggable(Level.INFO)) {
            for (ConstraintViolation violation : constraintViolations) {
                Logger.getGlobal().log(Level.INFO, "{0}.{1}: {2}", new Object[]{violation.getRootBeanClass(), violation.getPropertyPath(), violation.getMessage()});
            }
        }

        assertEquals(0, constraintViolations.size());
    }
        
        
    

}
