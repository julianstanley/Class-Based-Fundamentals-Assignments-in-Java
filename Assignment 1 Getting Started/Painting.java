class Painting {
  String artist;
  String title;
  int year;
  double height;
  boolean restored;
  
  Painting(String artist, String title, int year, double height, boolean restored) {
    this.artist = artist;
    this.title = title;
    this.year = year;
    this.height = height;
    this.restored = restored;
  }
}

class ExamplesPaintings {
  Painting monaLisa = new Painting("Leonardo da Vinci","Mona Lisa",1506,30.315,true);
  Painting starryNight = new Painting("Vincent van Gogh","The Starry Night",1889,29.016,false);
  Painting nightWatch = new Painting("Rembrandt Harmenszoon Van Rijn","The Night Watch",
      1630,28.5,false);
}
