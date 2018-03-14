import tester.Tester;

// To represent a picture
interface IPicture {
  // Return width of this picture
  int getWidth(); 
  
  // Count the number of shapes in this picture
  int countShapes(); 
  
  // Return the combo depth of this picture
  int comboDepth(); 
  
  // Return the string representation of this picture at the given depth 
  String pictureRecipe(int depth); 
}

// To represent a shape picture
class Shape implements IPicture {
  String kind; 
  int size; 
  
  Shape(String kind, int size) {
    this.kind = kind;
    this.size = size; 
  }
  /*
   Fields:
   ...this.kind...                  -- String
   ...this.size...                  -- int
   
   Methods:
   ...this.getWidth()...            -- int
   ...this.countShapes()...         -- int
   ...this.comboDepth()...          -- int
   ...this.pictureRecipe(int)...    -- String
   
   Methods on Fields:
   
   */
  
  // Return the width of this shape
  public int getWidth() {
    return this.size; 
  }
  
  // Count the number of shapes in this shape
  public int countShapes() {
    return 1; 
  }
  
  // Return the combo depth of this shape
  public int comboDepth() {
    return 0; 
  }
  
  /*
   Parameters:
   ...depth...  -- int
   Methods on Parameters:
   */
  //Return the string representation of this shape at the given depth 
  public String pictureRecipe(int depth) {
    return this.kind; 
  }
}

// To represent a combination of shapes in a picture
class Combo implements IPicture {
  String name;
  IOperation operation; 
  
  Combo(String name, IOperation operation) {
    this.name = name;
    this.operation = operation; 
  }
  /*
  Fields:
  ...this.name...                  -- String
  ...this.op...                    -- IOperation
  
  Methods:
  ...this.getWidth()...            -- int
  ...this.countShapes()...         -- int
  ...this.comboDepth()...          -- int
  ...this.pictureRecipe(int)...    -- String
  
  Methods on Fields:
  ...this.op.getWidth()...          -- int
  ...this.op.countShapes()...       -- int
  ...this.op.comboDepth()...        -- int
  ...this.op.pictureRecipe(int)...  -- String
  
  */
  
  // Return the width of this combo
  public int getWidth() {
    return this.operation.getWidth(); 
  }
  
  // Count the number of shapes in this combo
  public int countShapes() {
    return this.operation.countShapes(); 
  }
  
  // Return the combo depth of this combo
  public int comboDepth() {
    return this.operation.comboDepth(); 
  }
  
  /*
  Parameters:
  ...depth...  -- int
  Methods on Parameters:
  */
  //Return the string representation of this combo at the given depth
  public String pictureRecipe(int depth) {
    if (depth > 0) {
      return this.operation.pictureRecipe(depth);
    } 
    else {
      return this.name; 
    }
  }
}

// To represent an operation on a picture
interface IOperation {
  // To represent the width of this operation
  int getWidth(); 
  
  // Count the number of shapes in this operation
  int countShapes(); 
  
  // Return the combo depth of this operation
  int comboDepth(); 
  
  //Return the string representation of this operation at the given depth
  String pictureRecipe(int depth); 
}

// To represent a scaled picture
class Scale implements IOperation {
  IPicture picture; 
  
  Scale(IPicture picture) {
    this.picture = picture; 
  } 
  /*
  Fields:
  ...this.pic...                     -- IPicture
  
  Methods:
  ...this.getWidth()...              -- int
  ...this.countShapes()...           -- int
  ...this.comboDepth()...            -- int
  ...this.pictureRecipe(int)...      -- String
  
  Methods on Fields:
  ...this.pic.getWidth()...          -- int
  ...this.pic.countShapes()...       -- int
  ...this.pic.comboDepth()...        -- int
  ...this.pic.pictureRecipe(int)...  -- String
  
  */
  
  // Return the width of this scaled picture
  public int getWidth() {
    return 2 * this.picture.getWidth(); 
  }
  
  // Count the number of shapes in this scaled picture
  public int countShapes() {
    return this.picture.countShapes(); 
  }
  
  // Return the combo depth of this scaled picture
  public int comboDepth() {
    return 1 + this.picture.comboDepth(); 
  }
  
  /*
  Parameters:
  ...depth...  -- int
  Methods on Parameters:
  */
  //Return the string representation of this scaled picture at the given depth
  public String pictureRecipe(int depth) {
    if (depth > 0) {
      return "scale" + "(" + this.picture.pictureRecipe(depth - 1) + ")"; 
    } 
    else {
      return this.picture.pictureRecipe(depth); 
    }
  }
  
}


// To represent two pictures besides each other
class Beside implements IOperation {
  IPicture picture1;
  IPicture picture2; 
  
  Beside(IPicture picture1, IPicture picture2) {
    this.picture1 = picture1;
    this.picture2 = picture2; 
  }
  /*
  Fields:
  ...this.picture1...                  -- IPicture
  ...this.picture2...                    -- IPicture
  
  Methods:
  ...this.getWidth()...            -- int
  ...this.countShapes()...         -- int
  ...this.comboDepth()...          -- int
  ...this.pictureRecipe(int)...    -- String
  
  Methods on Fields:
  ...this.picture1.getWidth()...          -- int
  ...this.picture1.countShapes()...       -- int
  ...this.picture1.comboDepth()...        -- int
  ...this.picture1.pictureRecipe(int)...  -- String
  ...this.picture2.getWidth()...          -- int
  ...this.picture2.countShapes()...       -- int
  ...this.picture2.comboDepth()...        -- int
  ...this.picture2.pictureRecipe(int)...  -- String
  
  */
  
  // Return the width of these side-by-side picture 
  public int getWidth() {
    return this.picture1.getWidth() + this.picture2.getWidth(); 
  }
  
  // Count the number of shapes in this side-by-side picture
  public int countShapes() {
    return this.picture1.countShapes() + this.picture2.countShapes(); 
  }
  
  // Return the combo depth of this side-by-side picture
  public int comboDepth() {
    return 1 + Math.max(this.picture1.comboDepth(), this.picture2.comboDepth()); 
  }
  
  /*
  Parameters:
  ...depth...  -- int
  Methods on Parameters:
  */
  //Return the string representation of this side-by-side picture at the given depth
  public String pictureRecipe(int depth) {
    if (depth > 0) {
      return "beside" + "(" +  this.picture1.pictureRecipe(depth - 1) + ", " 
          + this.picture2.pictureRecipe(depth - 1) + ")"; 
    }
    else {
      return this.picture1.pictureRecipe(depth) + ", " + this.picture2.pictureRecipe(depth); 
    }
  }
}

// To represent two pictures overlain on eachother
class Overlay implements IOperation {
  IPicture topPicture;
  IPicture bottomPicture;
  
  Overlay(IPicture topPicture, IPicture bottomPicture) {
    this.topPicture = topPicture;
    this.bottomPicture = bottomPicture;
  }
  /*
  Fields:
  ...this.topPicture...                        -- IPicture
  ...this.bottomPicture...                     -- IPicture
  
  Methods:
  ...this.getWidth()...                        -- int
  ...this.countShapes()...                     -- int
  ...this.comboDepth()...                      -- int
  ...this.pictureRecipe(int)...                -- String
  
  Methods on Fields:
  ...this.topPicture.getWidth()...             -- int
  ...this.topPicture.countShapes()...          -- int
  ...this.topPicture.comboDepth()...           -- int
  ...this.topPicture.pictureRecipe(int)...     -- String
  ...this.bottomPicture.getWidth()...          -- int
  ...this.bottomPicture.countShapes()...       -- int
  ...this.bottomPicture.comboDepth()...        -- int
  ...this.bottomPicture.pictureRecipe(int)...  -- String
  
  */
  
  // Return the width of these overlain pictures
  public int getWidth() { 
    return Math.max(this.topPicture.getWidth(), this.bottomPicture.getWidth()); 
  }
  
  // Return the number of shapes in these overlain pictures
  public int countShapes() {
    return this.topPicture.countShapes() + this.bottomPicture.countShapes(); 
  }
  
  // Return the combo depth of these overlain pictures
  public int comboDepth() {
    return 1 + Math.max(this.topPicture.comboDepth(), this.bottomPicture.comboDepth()); 
  }
  
  /*
  Parameters:
  ...depth...  -- int
  Methods on Parameters:
  */
  //Return the string representation of these overlain pictures at the given depth
  public String pictureRecipe(int depth) {
    if (depth > 0) {
      return "overlay" + "(" +  this.topPicture.pictureRecipe(depth - 1) + ", " 
          + this.bottomPicture.pictureRecipe(depth - 1) + ")"; 
    }
    else {
      return this.topPicture.pictureRecipe(depth) + ", " + this.bottomPicture.pictureRecipe(depth); 
    }
  }
  
}

class ExamplesPicture {
  IPicture circle = new Shape("circle", 20);
  IPicture square = new Shape("square", 30);
  IPicture bigCircle = new Combo("big circle", new Scale(circle)); 
  IPicture bigSquare = new Combo("big square", new Scale(square)); 
  IPicture squareOnCircle = new Combo("square on circle", new Overlay(square, bigCircle)); 
  IPicture circleOnSquare = new Combo("circle on square", new Overlay(circle, bigSquare)); 
  IPicture doubledSquareOnCircle = new Combo("doubled square on circle",
      new Beside(squareOnCircle, squareOnCircle));
  IPicture doubledCircleOnSquare = new Combo("doubled circle on square",
      new Beside(circleOnSquare, circleOnSquare)); 
  
  boolean testPictures(Tester t) {
    return t.checkExpect(circle.getWidth(), 20)
        && t.checkExpect(bigCircle.getWidth(), 40)
        && t.checkExpect(squareOnCircle.getWidth(), 40)
        && t.checkExpect(doubledSquareOnCircle.getWidth(), 80)
        
        && t.checkExpect(circle.countShapes(), 1)
        && t.checkExpect(bigCircle.countShapes(), 1)
        && t.checkExpect(squareOnCircle.countShapes(), 2)
        && t.checkExpect(doubledSquareOnCircle.countShapes(), 4)
        
        && t.checkExpect(circle.comboDepth(), 0)
        && t.checkExpect(bigCircle.comboDepth(), 1)
        && t.checkExpect(squareOnCircle.comboDepth(), 2)
        && t.checkExpect(doubledSquareOnCircle.comboDepth(), 3)
        
        && t.checkExpect(doubledSquareOnCircle.pictureRecipe(0), "doubled square on circle")
        && t.checkExpect(doubledSquareOnCircle.pictureRecipe(2), 
            "beside(overlay(square, big circle), overlay(square, big circle))")
        && t.checkExpect(doubledSquareOnCircle.pictureRecipe(3), 
            "beside(overlay(square, scale(circle)), overlay(square, scale(circle)))"); 
  }
}
