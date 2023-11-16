package com.juneutf.mtg;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import com.juneutf.mtg.controller.APIController;
import com.juneutf.mtg.model.APIChargeModel;
import com.juneutf.mtg.model.APIMessengerModel;
import com.juneutf.mtg.model.APIPurposeModel;
import com.juneutf.mtg.model.JobModel;
import com.juneutf.mtg.service.APIService;
import com.juneutf.mtg.service.PlanService;

/**
 * このクラスはAPIControllerです。
 */
public class APIControllerTest {
	//モックコントローラーテスト
    @InjectMocks
    private APIController apiController;
    //モックインタフェークラAPIServiceスサービス
    @Mock
    private APIService apiService;
  //モックインタフェークラススPlanServiceサービス
    @Mock
    private PlanService planService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * 'getCherge'メソッドをテストします。
     */
    @Test
    public void testGetCherge() {
        APIMessengerModel apiMessengerModel = new APIMessengerModel();
        ArrayList<APIChargeModel> chargeModels = new ArrayList<>();
        chargeModels.add(new APIChargeModel());

        Mockito.when(apiService.selectAPICharge(0)).thenReturn(chargeModels);

        ResponseEntity<?> response = apiController.getCherge(apiMessengerModel);

        assertEquals(200, response.getStatusCodeValue());
        assertTrue(response.getBody() instanceof ArrayList);
    }

    /**
     * 'getCherge'メソッドの例外ケースをテストします。
     */
    @Test
    public void testGetChergeException() {
        APIMessengerModel apiMessengerModel = new APIMessengerModel();

        Mockito.when(apiService.selectAPICharge(0)).thenThrow(new RuntimeException());

        ResponseEntity<?> response = apiController.getCherge(apiMessengerModel);

        assertEquals(400, response.getStatusCodeValue());
        assertTrue(response.getBody() instanceof APIMessengerModel);
        assertEquals("false", apiMessengerModel.getIsData());
    }

    /**
     * 'getPurpose'メソッドをテストします。
     */
    @Test
    public void testGetPurpose() {
        APIMessengerModel apiMessengerModel = new APIMessengerModel();
        ArrayList<APIPurposeModel> purposeModels = new ArrayList<>();
        purposeModels.add(new APIPurposeModel());

        Mockito.when(apiService.selectAPIPurpose(0)).thenReturn(purposeModels);

        ResponseEntity<?> response = apiController.getPurpose(apiMessengerModel);

        assertEquals(200, response.getStatusCodeValue());
        assertTrue(response.getBody() instanceof ArrayList);
    }

    /**
     * 'getPurpose'メソッドの例外ケースをテストします。
     */
    @Test
    public void testGetPurposeException() {
        APIMessengerModel apiMessengerModel = new APIMessengerModel();

        Mockito.when(apiService.selectAPIPurpose(0)).thenThrow(new RuntimeException());

        ResponseEntity<?> response = apiController.getPurpose(apiMessengerModel);

        assertEquals(400, response.getStatusCodeValue());
        assertTrue(response.getBody() instanceof APIMessengerModel);
        assertEquals("false", apiMessengerModel.getIsData());
    }

    /**
     * 'getJob'メソッドをテストします。
     */
    @Test
    public void testGetJob() {
        APIMessengerModel apiMessengerModel = new APIMessengerModel();
        ArrayList<JobModel> jobModels = new ArrayList<>();
        jobModels.add(new JobModel());

        Mockito.when(planService.selectPlan()).thenReturn(jobModels);

        ResponseEntity<?> response = apiController.getJob(apiMessengerModel);

        assertEquals(200, response.getStatusCodeValue());
        assertTrue(response.getBody() instanceof ArrayList);
    }

    /**
     * 'getJob'メソッドの例外ケースをテストします。
     */
    @Test
    public void testGetJobException() {
        APIMessengerModel apiMessengerModel = new APIMessengerModel();

        Mockito.when(planService.selectPlan()).thenThrow(new RuntimeException());

        ResponseEntity<?> response = apiController.getJob(apiMessengerModel);

        assertEquals(400, response.getStatusCodeValue());
        assertTrue(response.getBody() instanceof APIMessengerModel);
        assertEquals("false", apiMessengerModel.getIsData());
    }
}
