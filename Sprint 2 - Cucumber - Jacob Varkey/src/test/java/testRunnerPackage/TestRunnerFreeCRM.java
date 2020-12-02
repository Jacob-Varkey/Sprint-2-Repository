package testRunnerPackage;



import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;


@CucumberOptions(
features = "Features"
,glue={"stepDefinitionPackage"}
,monochrome = true
,dryRun = false 
,plugin = { "html:target/Destination/FreeCRMTest"}
)

public class TestRunnerFreeCRM extends AbstractTestNGCucumberTests{

}
