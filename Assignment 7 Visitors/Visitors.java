// To represent an arithmetic statement
interface IArith {
  <R> R accept(IArithVisitor<R> visitor);
}

// To represent an arithmetic constant
class Const implements IArith {
  double num;

  Const(double num) {
    this.num = num;
  }

  // Accepts an IArithVisitor
  public <R> R accept(IArithVisitor<R> visitor) {
    return visitor.visitConst(this);
  }
}

// To represent an arithmetic formula
class Formula implements IArith {
  IFunc2<Double, Double, Double> fun;
  String name;
  IArith left;
  IArith right;

  Formula(IFunc2<Double, Double, Double> fun, String name, IArith left, IArith right) {
    this.fun = fun;
    this.name = name;
    this.left = left;
    this.right = right;
  }

  // Accepts an IArithVisitor
  public <R> R accept(IArithVisitor<R> visitor) {
    return visitor.visitFormula(this);
  }
}

// To represent a function that takes in two arguments
interface IFunc2<T, U, R> {

  // Apply this function
  R apply(T t, U u);
}

// To represent a visitor that visits an arithmetic statement
interface IArithVisitor<R> {

  // Apply this visitor
  R apply(IArith that);

  // Visits that IArith
  R visit(IArith that);

  // Visit that Const
  R visitConst(Const c);

  // Visits that Formula
  R visitFormula(Formula f);
}

// Represents a visitor to IArith that evaluates the expression to a double
class EvalVisitor implements IArithVisitor<Double> {

  // Apply this visitor
  public Double apply(IArith that) {
    return this.visit(that);
  }

  // Visits that IArith
  public Double visit(IArith that) {
    return that.accept(this);
  }

  // Visits that Const
  public Double visitConst(Const that) {
    return that.num;
  }

  // Visits that Formula
  public Double visitFormula(Formula that) {
    return that.fun.apply(that.left.accept(this), that.right.accept(this));
  }
}

// Represents a visitor to IArith that prints the arithmetic expression
class PrintVisitor implements IArithVisitor<String> {

  // Apply this visitor
  public String apply(IArith that) {
    return this.visit(that);
  }

  // Visits that IArith
  public String visit(IArith that) {
    return that.accept(this);
  }

  // Visits that Const
  public String visitConst(Const that) {
    return Double.toString(that.num);
  }

  // Visits that Formula
  public String visitFormula(Formula that) {
    return "(" + that.name + " " + that.left.accept(this) + " " + that.right.accept(this) + ")";
  }
}

// Represents a visitor to IArith that returns an IArith where every const has
// been doubled
class DoublerVisitor implements IArithVisitor<IArith> {

  // Apply this visitor
  public IArith apply(IArith that) {
    return this.visit(that);
  }

  // Visits that IArith
  public IArith visit(IArith that) {
    return that.accept(this);
  }

  // Visits that Const
  public IArith visitConst(Const that) {
    return new Const(that.num * 2);
  }

  // Visits that Formula
  public IArith visitFormula(Formula that) {
    return new Formula(that.fun, that.name, that.left.accept(this), that.right.accept(this));
  }
}

// Represents a visitor that determines whether every const in the tree is
// less than 10
class AllSmallVisitor implements IArithVisitor<Boolean> {

  // Apply this visitor
  public Boolean apply(IArith that) {
    return this.visit(that);
  }

  // Visits that IArith
  public Boolean visit(IArith that) {
    return that.accept(this);
  }

  // Visits that Const
  public Boolean visitConst(Const that) {
    return that.num < 10;
  }

  // Visits that Formula
  public Boolean visitFormula(Formula that) {
    return that.left.accept(this) && that.right.accept(this);
  }
}

// Represents a visitor that determines whether the formula tries to divide by
// zero
class NoDivBy0 implements IArithVisitor<Boolean> {

  // Apply this visitor
  public Boolean apply(IArith that) {
    return this.visit(that);
  }

  // Visits that IArith
  public Boolean visit(IArith that) {
    return that.accept(this);
  }

  // Is this const trying to divide by zero?
  public Boolean visitConst(Const that) {
    return true; 
  }

  // Is this formula both named "div" and not trying to divide by zero?
  public Boolean visitFormula(Formula that) {
    return (that.name.equals("div") && that.right.accept(this)
        && that.right.accept(new EvalVisitor()) > 0.0001)
        || (!that.name.equals("div") && that.right.accept(this))
        || (!that.name.equals("div") && that.left.accept(this));
  }
}
