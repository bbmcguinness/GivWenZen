package bdd.steps;

import java.util.HashMap;
import java.util.Map;

import org.givwenzen.GivWenZen;
import org.givwenzen.IgnoreAllTestsGivWenZenException;
import org.givwenzen.IgnoreScriptTestGivWenZenException;
import org.givwenzen.annotations.DomainStep;
import org.givwenzen.annotations.DomainSteps;

@DomainSteps
public class SimpleTestSteps {

  private GivWenZen gwz;
  private Map<String, Boolean> stepCalls = new HashMap<String, Boolean>();
  private Object paramValue;

  public SimpleTestSteps(GivWenZen gwz) {
    this.gwz = gwz;
  }

  @DomainStep("(?s)a step annotated with '(.*)' (:?.*)")
  public void verifyStepExists(String step) throws Exception {
    gwz.given(step);
  }

  @DomainStep("(?s)the step '(.*)' is called")
  public void callStep(String stepToCall) throws Exception {
    gwz.when(stepToCall);
  }

  @DomainStep("the step '(.*)' executes successfully")
  public boolean stepExecutedSuccessfully(String executedStep) {
    return stepCalls.get(executedStep).equals(true);
  }

  @DomainStep("simple no parameter step")
  public void simpleNoParamStep() {
    stepCalls.put("simple no parameter step", true);
  }

  @DomainStep("simple step with int parameter (\\d+)")
  public void simpleIntParamTest(int intParam) {
    stepCalls.put("simple step with int parameter " + intParam, true);
    paramValue = intParam;
  }

  @DomainStep("simple step with custom type parameter (.*)")
  public void simpleCustomTypeParamTest(CustomType customType) {
    stepCalls.put("simple step with custom type parameter CustomType", true);
    paramValue = customType;
  }

  @DomainStep("the int value (\\d+) is passed as a parameter")
  public Boolean verifyParamValue(int paramValue) {
    return this.paramValue.equals(paramValue);
  }

  @DomainStep("the custom value (.*) is passed as a parameter")
  public boolean veryifyCustomTypeParam(CustomType customType) {
    return customType.equals(paramValue);
  }

  @DomainStep("the CustomType has a CustomTypeEditor in the same package as the CustomType")
  public void checkTheCustomTypeHasAPropertyEditor() {
    assert(CustomType.class.getPackage().equals(CustomTypeEditor.class.getPackage()));
  }

  @DomainStep("(?s)simple step with hash table parameter (.*)")
  public void simpleHashTableParameterStep(Map <String, String> data) {
    paramValue = data;
  }

  @DomainStep("the hash table is passed as a parameter")
  public boolean verifyHashTableParamValue() {
    return paramValue instanceof Map;
  }

  @DomainStep("simple step that throws an exception that should stop the test")
  public boolean throwException() {
    throw new StopTestExceptionForTesting("stop test example");
  }

  @DomainStep("a simple step returns true")
  public boolean returnTrue(){
    return true;
  }

  @DomainStep("another simple step that throws an exception that should ignore the rest of that test")
  public String throwIgnoreException() throws IgnoreScriptTestGivWenZenException {
    throw new IgnoreScriptTestGivWenZenException("Ignoring test example");
  }

  @DomainStep("another simple step that throws an exception that should ignore all subsequent tests")
  public String throwIgnoreAllException() throws IgnoreAllTestsGivWenZenException {
    throw new IgnoreAllTestsGivWenZenException("Ignoring all tests example");
  }


}
