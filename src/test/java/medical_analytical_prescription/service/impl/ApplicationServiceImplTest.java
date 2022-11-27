package medical_analytical_prescription.service.impl;

import medical_analytical_prescription.BaseTest;
import medical_analytical_prescription.entity.Application;
import medical_analytical_prescription.exception.ApplicationNotFoundException;
import medical_analytical_prescription.utils.ApplicationUtil;
import org.junit.Before;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

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
    public void addApplicationTest() {
        //given
        Application createdApplication = applicationUtil.createApplication();

        //when
        Application savedApplication = applicationService.addApplication(createdApplication);

        //then
        assertNotNull(savedApplication.getId());
        assertNotNull(applicationRepository.findById(savedApplication.getId()));
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
