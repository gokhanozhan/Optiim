package Runnerpage;

public class Runnerpagee {
	import org.junit.runner.RunWith;




	@RunWith(Cucumber.class)
	@CucumberOptions(  
		    features = "src/main/java/com/keytorcteknoloji/uitestautomation/features", 
		    glue="com.keytorcteknoloji.uitestautomation.steps",
		    junit = "--step-notifications",
//		    tags= "",
		    strict=true,
		    monochrome=true,
		    plugin= {"pretty","html:target/cucumber","json:target/cucumber.json","junit:target/cukes.xml"})
}
}
