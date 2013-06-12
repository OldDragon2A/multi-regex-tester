package me.mcleod;

import java.util.List;

public class Tester {
  private List<Expression> expressions;
  private List<Test> tests;
  
  public Tester() {} 
  
  /**
   * @return the expressions
   */
  public List<Expression> getExpressions() {
    return expressions;
  }
  /**
   * @param expressions the expressions to set
   */
  public void setExpressions(List<Expression> expressions) {
    this.expressions = expressions;
  }

  /**
   * @return the tests
   */
  public List<Test> getTests() {
    return tests;
  }
  /**
   * @param tests the tests to set
   */
  public void setTests(List<Test> tests) {
    this.tests = tests;
  }
  
  public String run() {
    StringBuilder results = new StringBuilder();
    for (Test t : this.tests) {
      boolean matched = false;
      String value = t.getValue();
      for (Expression e : this.expressions) {
        if (e.test(value)) {
          String result = e.execute(value);
          results.append(value + "\t" + result + "\t" + (t.getTest().equals(result) ? "Pass" : "Fail" ));
          matched = true;
          break;
        }
      }
      if (!matched) {
        results.append(value + "\tNo Match");
      }
      results.append("\r\n");
    }
    return results.toString();
  }
}