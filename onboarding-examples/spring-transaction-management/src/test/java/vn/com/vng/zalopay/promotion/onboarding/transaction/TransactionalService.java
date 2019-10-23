package vn.com.vng.zalopay.promotion.onboarding.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.com.vng.zalopay.promotion.onboarding.transaction.entity.Person;
import vn.com.vng.zalopay.promotion.onboarding.transaction.repository.PersonRepository;
import vn.com.vng.zalopay.promotion.onboarding.transaction.service.AnotherPersonService;
import vn.com.vng.zalopay.promotion.onboarding.transaction.service.PersonService;

/**
 * @author huyvha
 */
@Service
@Transactional
public class TransactionalService {

    @Autowired
    private PersonService personService;
    @Autowired
    private AnotherPersonService anotherPersonService;

    @Autowired
    private PersonRepository personRepository;

    public void executeRequiresNewInsideRequired() {
        personRepository.createPerson(new Person(this.getClass().getSimpleName()));
        anotherPersonService.createAnotherPerson(true);
    }

    public void executeMixedPropagation() {
        personService.createPerson(false);
        anotherPersonService.createAnotherPerson(true);
    }

}
