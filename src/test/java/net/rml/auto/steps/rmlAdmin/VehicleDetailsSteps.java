package net.rml.auto.steps.rmlAdmin;

import io.cucumber.java.en.Then;
import net.rml.auto.website.rml.pages.VehicleDetailsPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

/**
 * Vehicle Details definition class.
 */
public class VehicleDetailsSteps {
    private static final Logger LOGGER = LogManager.getLogger(VehicleDetailsSteps.class.getSimpleName());
    private VehicleDetailsPage vehicleDetailsPage;

    public VehicleDetailsSteps() {
        vehicleDetailsPage = new VehicleDetailsPage();
    }

    @Then("^Status code of VehicleDetailsPage should be \"([^\"]*)\"$")
    public void statusCodeOfVehicleDetailsPageShouldBe(final int statusCode) {

        Assert.assertEquals(vehicleDetailsPage.getStatusCode(), statusCode, String.format("Wrong status code of VehicleDetailsPage %s",vehicleDetailsPage.getStatusCode()));
    }

    @Then("^There should not be error message \"([^\"]*)\" on VehicleDetailsPage$")
    public void shouldNotBeErrorMsgOnfVehicleDetailsPageShouldBe(final String errorMsg) {
        vehicleDetailsPage.hasErrorMessage(errorMsg);

        Assert.assertFalse(vehicleDetailsPage.hasErrorMessage(errorMsg), String.format("Error message is exist %s on VehicleDetailsPage", errorMsg));
    }
}
