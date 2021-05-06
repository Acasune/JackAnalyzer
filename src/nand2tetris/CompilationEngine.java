package nand2tetris;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CompilationEngine {

  private static final Set<String> classVarDecSet = Stream.of("static","field").collect(Collectors.toSet());
  private static final Set<String> subroutineDecSet = Stream.of("constructor","function","method","void").collect(Collectors.toSet());

  JackTokenizer jt;
  BufferedWriter writer;
  private int indentDepth;
  CompilationEngine() {
    this.indentDepth=2;
  }


  CompilationEngine setUp(JackTokenizer jt,BufferedWriter writer){
    this.jt = jt;
    this.writer = writer;
    return this;
  }

  public void compileClass() throws Exception {
    this.jt.advance();
    preTermWriter("class"); // <class>
    nonTermWriter(jt.getTagType(),jt.getKeyword()); // <keyword> class <keyword>
    nonTermWriter(jt.getTagType(),jt.getIdentifier()); // <identifier> $className <identifier>
    nonTermWriter(jt.getTagType(),jt.getSymbol()); // <symbol> { </symbol>

    // Variable declaration
    if (classVarDecSet.contains(jt.getKeyword())) {
      compileClassVarDec();
    }

    // class subroutine declaration
//    while (subroutineDecSet.contains(jt.getKeyword())) {
//      compileSubroutine();
//    }
//
//    nonTermWriter(jt.getTagType(),jt.getSymbol()); // <symbol> } </symbol>
//    postTermWriter("class"); // </class>

  }
  public void compileClassVarDec() {}
  public void compileSubroutine() {}
  public void compileParameterList() {}
  public void compileVarDec() {}
  public void compileStatements(){}
  public void compileDo() {}
  public void compileLet() {}
  public void compileWhile() {}
  public void compileReturn() {}
  public void compileIf() {}
  public void compileExpression() {}
  public void compileTerm() {}
  public void compileExpressionList() {}

  private void lineWriter(String line) throws IOException{
    StringBuilder sb=new StringBuilder();
    for (int i=0;i<this.indentDepth;i++) {
      sb.append(" ");
    }
    sb.append(line).append("\n");
    writer.write(sb.toString());
  }

  private void nonTermWriter(String tag, String value) throws IOException {
    String line = "<"+tag+">" + " value " +"</"+ tag +">";
    lineWriter(line);
    this.jt.advance();
  }

  private void preTermWriter(String tag) throws IOException{
    String line = String.format("<%s>",tag);
    lineWriter(line);
    this.indentDepth+=2;
  }
  private void postTermWriter(String tag) throws  IOException{
    this.indentDepth=-2;
    String line = String.format("</%s>",tag);
    lineWriter(line);
  }


}
