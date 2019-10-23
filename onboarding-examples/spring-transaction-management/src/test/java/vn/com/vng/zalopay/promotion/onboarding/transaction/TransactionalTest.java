package vn.com.vng.zalopay.promotion.onboarding.transaction;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureJdbc;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTestContextBootstrapper;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.BootstrapWith;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import vn.com.vng.zalopay.promotion.onboarding.transaction.repository.PersonRepository;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.fail;
import static org.springframework.test.annotation.DirtiesContext.ClassMode;

/**
 * @author huyvha
 */
@RunWith(SpringRunner.class)
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
@BootstrapWith(SpringBootTestContextBootstrapper.class)
@AutoConfigureJdbc
@AutoConfigureTestDatabase
@ComponentScan
@TestPropertySource(properties = {
        "logging.level.ROOT=INFO",
        "logging.level.org.springframework.jdbc.core=DEBUG",
        "logging.level.org.springframework.transaction=TRACE"
})
public class TransactionalTest {

    @Autowired
    private TransactionalService transactionalService; // SUT

    @Autowired
    private PersonRepository personRepository;

    @Test
    public void test1() {
        // GIVEN transaction propagation setup with "REQUIRES_NEW" inside "REQUIRED"

        // WHEN
        try {
            transactionalService.executeRequiresNewInsideRequired();
            fail("Exception must be thrown out");
        } catch (Exception e) {
            assertThat(e.getMessage(), is("Error occurs"));
        }

        // THEN both person and another person insertion are rolled back
        assertThat(personRepository.countByName("PersonServiceImpl"), is(0));
        assertThat(personRepository.countByName("AnotherPersonServiceImpl"), is(0));
    }

    @Test
    public void test2() {
        // GIVEN mixed transaction propagation setup

        // WHEN
        try {
            transactionalService.executeMixedPropagation();
            fail("Exception must be thrown out");
        } catch (Exception e) {
            assertThat(e.getMessage(), is("Error occurs"));
        }

        // THEN both person and another person insertion are rolled back
        assertThat(personRepository.countByName("PersonServiceImpl"), is(0));
        assertThat(personRepository.countByName("AnotherPersonServiceImpl"), is(0));
    }

}
