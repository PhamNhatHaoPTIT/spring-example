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
public class InnerRequiredServiceImpl implements InnerRequiredService {

    @Autowired
    private PersonRepository personRepository;

    @Override
    public void save(boolean raiseError) {
        personRepository.save(new Person(this.getClass().getSimpleName()));

        if (raiseError) {
            throw new RuntimeException("Error occurs");
        }
    }
}
