package me.mcleod;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.yaml.snakeyaml.TypeDescription;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

public class MultiRegexTester {
  

  /**
   * @param args
   */
  public static void main(String[] args) {
    CommandLineParser parser = new GnuParser();
    Options options = new Options();
    options.addOption("f", "file", true, "Specify the YAML file containing tests.");
    try {
      CommandLine line = parser.parse(options,  args);
      if (line.hasOption("file")) {
        process(line.getOptionValue("file"));
      }
    } catch (ParseException e) {
      System.out.println("Unexpected exception: " + e.getMessage());
    } catch (FileNotFoundException e) {
      System.out.println("File Not Found: " +  e.getMessage());
    }
  }
  
  public static void process(String file) throws FileNotFoundException {
    Constructor constructor = new Constructor(Tester.class);
    TypeDescription td_expression = new TypeDescription(Expression.class);
    TypeDescription td_test = new TypeDescription(Test.class);
    TypeDescription td_tester = new TypeDescription(Tester.class);
    td_tester.putListPropertyType("expressions", Expression.class);
    td_tester.putListPropertyType("tests", Test.class);
    constructor.addTypeDescription(td_expression);
    constructor.addTypeDescription(td_test);
    constructor.addTypeDescription(td_tester);
    Yaml yaml = new Yaml(constructor);
    InputStream is = new FileInputStream(new File(file));
    Tester tester = (Tester)yaml.load(is);
    System.out.print(tester.run());
  }
}
