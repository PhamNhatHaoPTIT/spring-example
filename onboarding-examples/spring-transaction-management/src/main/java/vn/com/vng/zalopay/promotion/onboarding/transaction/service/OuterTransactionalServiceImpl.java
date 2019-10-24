package vn.com.vng.zalopay.promotion.onboarding.transaction.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.com.vng.zalopay.promotion.onboarding.transaction.entity.Person;
import vn.com.vng.zalopay.promotion.onboarding.transaction.repository.PersonRepository;

/**
 * @author huyvha
 */
@Service
@Transactional
public class OuterTransactionalServiceImpl implements OuterTransactionalService {

    @Autowired
    private InnerRequiredService innerRequiredService;
    @Autowired
    private InnerRequiresNewService innerRequiresNewService;

    @Autowired
    private PersonRepository personRepository;

    @Override
    public void executeRequiresNewInsideRequired() {
        personRepository.save(new Person(this.getClass().getSimpleName()));
        innerRequiresNewService.save(true);
    }

    @Override
    public void executeMixedPropagation() {
        innerRequiredService.save(false);
        innerRequiresNewService.save(true);
    }

    @Override
    public void executeSuccessInInnerTransaction() {
        innerRequiresNewService.save(false);
        innerRequiredService.save(true);
    }

}
