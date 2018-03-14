interface IResource {
  
}

class Coin implements IResource {
  String material; // of gold or silver
  int value; // monetary value
  
  Coin(String material, int value) {
    this.material = material;
    this.value = value; 
  }
}

class VictoryToken implements IResource {
  String type; 
  int value; // monetary value and number of victory points
  
  VictoryToken(String type, int value) {
    this.type = type;
    this.value = value;
  }
}

class Card implements IResource {
  String instructions;
  boolean grantsBonusAction;
  int value;
  
  Card(String instructions, boolean grantsBonusAction) {
    this.instructions = instructions;
    this.grantsBonusAction = grantsBonusAction;
    this.value = 1;
  }
}

interface IAction {
  
}

class Purchase implements IAction {
  int cost;
  IResource item;
  
  Purchase(int cost, IResource item) {
    this.cost = cost;
    this.item = item;
  }
}

class Swap implements IAction {
  IResource consumed;
  IResource received;
  
  Swap(IResource consumed, IResource received) {
    this.consumed = consumed;
    this.received = received; 
  }
}

class ExamplesGame {
  IResource ducat = new Coin("bronze",5);
  IResource galleon = new Coin("magic",89);
  
  IResource fortress = new VictoryToken("Fortress",2);
  IResource tank = new VictoryToken("Tank",1);
    
  IResource mason = new Card("Play only if you have at least 3 victory points",true);
  IResource sorcerer = new Card("Play only if you have at least 238723 victory points",false);
  
  IAction purchase1 = new Purchase(2,fortress);
  IAction purchase2 = new Purchase(1,tank);
  
  IAction swap1 = new Swap(fortress,tank);
  IAction swap2 = new Swap(ducat,fortress); 
  
}