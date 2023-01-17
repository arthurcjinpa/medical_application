package medical_analytical_prescription.service.impl;

import medical_analytical_prescription.BaseTest;
import medical_analytical_prescription.entity.Application;
import medical_analytical_prescription.exception.ApplicationNotFoundException;
import medical_analytical_prescription.utils.ApplicationUtil;
import org.junit.Before;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static medical_analytical_prescription.enums.ApplicationStatus.IN_PROGRESS;
import static org.junit.jupiter.api.Assertions.*;

@Transactional
public class ApplicationServiceImplTest extends BaseTest {

    private ApplicationUtil applicationUtil;

    @Before
    public void before() {
        applicationUtil = new ApplicationUtil();
    }

    @Test
    public void showAllApplicationsTest() {
        //given
        int repositorySize = applicationRepository.findAll().size();

        //when
        int serviceSize = applicationService.showAllApplications().size();

        //then
        assertEquals(repositorySize, serviceSize);
    }

    @Test
    public void getApplicationByIdTest() {
        //given
        Application createdApplication = applicationUtil.createApplication();
        Application savedApplication = applicationService.addApplication(createdApplication);

        //when
        Application foundedApplication = applicationService.getApplicationById(savedApplication.getId());

        //then
        assertEquals(savedApplication.getApplicant(), foundedApplication.getApplicant());
        assertEquals(savedApplication.getSymptoms(), foundedApplication.getSymptoms());
        assertEquals(savedApplication.getContext(), foundedApplication.getContext());
        assertEquals(savedApplication.getStatus(), foundedApplication.getStatus());
        assertEquals(savedApplication.getCreateDate(), foundedApplication.getCreateDate());
    }

    @Test
    public void findApplicationsByUserIdTest() {
        //given
        Application createdApplication = applicationUtil.createApplication();
        Application savedApplication = applicationService.addApplication(createdApplication);

        Application createdAnotherApplication = applicationUtil.createAnotherApplication(savedApplication.getApplicant());
        Application savedAnotherApplication = applicationService.addApplication(createdAnotherApplication);

        //when
        List<Application> foundedApplication =
                applicationService.findApplicationsByUserId(savedApplication.getApplicant().getId());

        //then
        assertEquals(2, foundedApplication.size());

        assertEquals(savedApplication.getApplicant(), foundedApplication.get(0).getApplicant());
        assertEquals(savedAnotherApplication.getApplicant(), foundedApplication.get(1).getApplicant());

        assertEquals(savedApplication.getId(), foundedApplication.get(0).getId());
        assertEquals(savedAnotherApplication.getId(), foundedApplication.get(1).getId());

        assertEquals(savedApplication.getSymptoms(), foundedApplication.get(0).getSymptoms());
        assertEquals(savedAnotherApplication.getSymptoms(), foundedApplication.get(1).getSymptoms());

        assertEquals(savedApplication.getStatus(), foundedApplication.get(0).getStatus());
        assertEquals(savedAnotherApplication.getStatus(), foundedApplication.get(1).getStatus());
    }

    @Test
    public void addApplicationTest() {
        //given
        Application createdApplication = applicationUtil.createApplication();

        //when
        Application savedApplication = applicationService.addApplication(createdApplication);

        //then
        assertNotNull(savedApplication.getId());
        assertNotNull(applicationService.getApplicationById(savedApplication.getId()));
        assertEquals(savedApplication.getStatus(), IN_PROGRESS);
        assertEquals(savedApplication.getContext(), createdApplication.getContext());
    }

    @Test(expected = ApplicationNotFoundException.class)
    public void deleteApplicationByIdTest() {
        //given
        Application createdApplication = applicationUtil.createApplication();
        Application savedApplication = applicationService.addApplication(createdApplication);

        //when
        applicationService.deleteApplicationById(savedApplication.getId());

        //then
        assertNotNull(userService.getUserById(savedApplication.getApplicant().getId()));
        assertNull(applicationService.getApplicationById((savedApplication.getId())));
    }

}
