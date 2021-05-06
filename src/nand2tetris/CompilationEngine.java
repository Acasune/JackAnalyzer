package nand2tetris;

import java.io.BufferedWriter;
import java.io.IOException;

public class CompilationEngine {
  JackTokenizer jt;
  BufferedWriter writer;

  CompilationEngine setUp(JackTokenizer jt,BufferedWriter writer){
    this.jt = jt;
    this.writer = writer;
    return this;
  }

  public void compileClass() throws IOException{
      writer.write("hoge\n");
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


}
