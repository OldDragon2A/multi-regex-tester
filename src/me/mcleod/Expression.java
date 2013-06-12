package me.mcleod;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Expression {
  private String pattern;
  private String replacement;
  
  private Pattern p;
  
  /**
   * @return the pattern
   */
  public String getPattern() {
    return pattern;
  }
  /**
   * @param pattern the pattern to set
   */
  public void setPattern(String pattern) {
    this.pattern = pattern;
    this.p = Pattern.compile(this.pattern);
  }
  
  /**
   * @return the replacement
   */
  public String getReplacement() {
    return replacement;
  }
  /**
   * @param replacement the replacement to set
   */
  public void setReplacement(String replacement) {
    this.replacement = replacement;
  }
  
  public Boolean test(String text) {
    return this.p.matcher(text).matches();
  }
  public String execute(String text) {
    Matcher m = this.p.matcher(text);
    return m.replaceAll(this.replacement);
  }
}