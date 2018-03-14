/*
+--------->  Winter Outfit +------------+
|              +                        |
|              |                        |
|  +-----------v----------+         +---+----------+
|  | Covering           |         | BaseLayer    |
+----- WinterOutfit inner |         | - String name|
   |   String description |         +-+------------+
   +---------------------
*/


interface IWinterOutfit {
  
}

class BaseLayer implements IWinterOutfit {
  String name;
  
  BaseLayer(String name) {
    this.name = name;
  }
}

class Covering implements IWinterOutfit {
  IWinterOutfit inner;
  String description;
  
  Covering(IWinterOutfit inner, String description) {
    this.inner = inner;
    this.description = description;
  }
}


class ExamplesWinterOutfit {
  IWinterOutfit skiing = new Covering(
      new Covering(
          new Covering(
              new BaseLayer("leggings"),"sweatpants"),"ski pants"),"boots");
  IWinterOutfit formalwear = new Covering(
      new Covering(
          new Covering(
              new Covering(
                  new BaseLayer("shirt"),"vest"),"suit jacket"),"top coat"),"scarf");
      
}