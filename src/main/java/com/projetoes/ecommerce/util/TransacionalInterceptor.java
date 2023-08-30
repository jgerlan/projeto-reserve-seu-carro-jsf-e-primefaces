package com.projetoes.ecommerce.util;

import java.io.Serializable;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

@Interceptor
@Transacional
@Priority(Interceptor.Priority.APPLICATION)
public class TransacionalInterceptor implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private EntityManager manager;

    @AroundInvoke
    public Object invoke(InvocationContext context) throws Exception {
        EntityTransaction et = manager.getTransaction();
        boolean criador = false;

        try {
            if (!et.isActive()) {
                //Evita commits em operações sem transações
                et.begin();
                et.rollback();

                //Início transação
                et.begin();

                criador = true;
            }

            return context.proceed();
        } catch (Exception e) {
            if (et != null && criador) {
                et.rollback();
            }

            throw e;
        } finally {
            if (et != null && et.isActive() && criador) {
                et.commit();
            }
        }
    }

}
