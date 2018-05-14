package za.ac.unisa.myadmin.service.studyquotation.date.impl;

import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import za.ac.unisa.myadmin.service.studyquotation.date.dao.StudyQuoteDateRepository;

@Ignore
@PropertySource("classpath:application.properties")
@RunWith(SpringRunner.class)
@ComponentScan(basePackageClasses = {StudyQuoteDateRepository.class})
@DataJpaTest
public class StudyQuoteDateServiceImplTest {

}
