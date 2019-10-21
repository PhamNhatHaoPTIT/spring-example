package com.haopn.transactional.service;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
public class InnerBean {

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void testRequired() {
        throw new RuntimeException("Rollback this transaction!");
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public void testRequiresNew() {
        throw new RuntimeException("Rollback this transaction!");
    }

}
