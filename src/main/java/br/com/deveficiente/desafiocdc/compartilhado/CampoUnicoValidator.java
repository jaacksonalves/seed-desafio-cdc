package br.com.deveficiente.desafiocdc.compartilhado;

import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CampoUnicoValidator implements ConstraintValidator<CampoUnico, Object> {

    private String atributo;
    private Class<?> classeDominio;
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void initialize(CampoUnico parametros) {
        atributo = parametros.campo();
        classeDominio = parametros.classeDominio();
    }

    @Override
    public boolean isValid(Object valor, ConstraintValidatorContext context) {
        var lista = entityManager
                .createQuery("SELECT 1 FROM " + classeDominio.getName() + " WHERE " + atributo + "=?1")
                .setParameter(1, valor)
                .getResultList();

        Assert.state(lista.size() <= 1, "Temos um possível bug, pois foi encontrado mais de um (a) "
                + classeDominio
                + " com o atributo "
                + atributo);

        return lista.isEmpty();
    }
}
