import tester.Tester;

// To represent a course
class Course {
  String name;
  Instructor prof;
  IList<Student> students;

  Course(String name, Instructor prof) {
    this.name = name;
    this.prof = prof;
    
    // A course begins with no students
    this.students = new MtList<Student>();
    
    // As soon as this course is created, it should show up on the Professor's course list
    this.prof.addCourse(this);
  }
  
  /*
   * Fields:
   * ...this.name...     -- String
   * ...this.prof...     -- Instructor
   * ...this.students... -- IList<Student>
   * 
   * Methods:
   * ...this.enroll(Student)... *void*
   * 
   * Methods on Fields:
   * ...this.prof.addCourse(Course)... *void*
   * ...this.prof.dejavu(Student)...  boolean
   */

  /* 
   * Template: all in class +
   * Parameters: 
   * ...that... Student
   * 
   * Methods on Parameters:
   * ...that.enroll(Course)... *void*
   * ...that.classmates(Student)... boolean
   * ...that.enrolledTwo(IList<Course>)... boolean
   */
  // Enroll the given student to this course
  // EFFECT: Adds the given student to the list of students this course has
  void enroll(Student that) {
    this.students = new ConsList<Student>(that, this.students);
  }
}

// To represent an instructor who teaches a course
class Instructor {
  String name;
  IList<Course> courses;

  Instructor(String name) {
    this.name = name;
    
    // When instructors emerge from the womb, they don't have any classes yet
    this.courses = new MtList<Course>();
  }

  /*
   * Fields:
   * ...this.name...    -- String
   * ...this.courses... -- IList<Course>
   * 
   * Methods:
   * ...this.addCourse(Course)...  -- *void*
   * ...this.dejavu(Student)...    -- Boolean
   * 
   * Methods on Fields:
   * ...this.courses.overlap(IList<Courses>)... -- boolean
   * ...this.courses.foundIn(Courses)...        -- boolean
   * ...this.courses.containsNum(IList<Course>, IComparator<Course, Course>, int) -- boolean
   * ...this.courses.contains(Course, IComparator<Course, Course>, int) -- boolean
   */
  
  /* Template: All in class +
   * Parameters:
   * ...that... Course
   * 
   * Methods on Parameters:
   * ...that.enroll(Student)... -- *void*
   * 
   */
  // Enroll the instructor in the newly-created course
  // EFFECT: Adds the given course to the list of courses this instructor is
  // teaching
  void addCourse(Course that) {
    this.courses = new ConsList<Course>(that, this.courses);
  }

  /*
   * Template: Same as class +
   * Parameters:
   * ...that... -- Student
   * 
   * Methods on parameters:
   * ...that.enroll(Course)... *void*
   * ...that.classmates(Student)... boolean
   * ...that.enrolledTwo(IList<Course>)... boolean
   * 
   */
  // Return true if the given student is in more than one of this instructor's
  // classes
  boolean dejavu(Student that) {
    return that.enrolledTwo(this.courses);
  }
}

// To represent a student taking a class
// Students have non-unique names but unique identification numbers.
class Student {
  String name;
  int id;
  IList<Course> courses;

  Student(String name, int id) {
    // When students emerge from the womb, they do have a name and ID number
    this.name = name;
    this.id = id;
    
    // When students emerge from the womb, they don't take classes yet
    this.courses = new MtList<Course>();
  }
  
  /*
   * Fields:
   * ...this.name...    -- String
   * ...this.id...      -- int
   * ...this.courses... -- IList<Course>
   * 
   * Methods:
   * ...this.enroll(Course)...   -- *void*
   * ...this.classmates(Student)... -- boolean
   * ...this.enrolledTwo(IList<Course>)... -- boolean
   * 
   * Methods on fields:
   * ...this.courses.overlap(IList<Courses>)... -- boolean
   * ...this.courses.foundIn(Courses)...        -- boolean
   * ...this.courses.containsNum(IList<Course>, IComparator<Course, Course>, int) -- boolean
   * ...this.courses.contains(Course, IComparator<Course, Course>, int) -- boolean
   * 
   */
  

  /*
   * Template: Same as class +
   * Parameters:
   * ...that...    -- Course
   * 
   * Methods on parameters:
   * ..that.enroll(Student)... -- *void*
   * 
   */
  // Enroll the student to the given course
  // EFFECT: Adds the given course to the list of courses this student is taking
  // ~EFFECT: Dispatches to Course class to add student to course list.
  void enroll(Course that) {
    this.courses = new ConsList<Course>(that, this.courses);
    that.enroll(this);
  }
  
  /*
   * Template: Same as class + Parameters: ...that... -- Student
   * 
   * Methods on parameters: ...that.enroll(Course)... *void*
   * ...that.classmates(Student)... boolean
   * ...that.enrolledTwo(IList<Course>)... boolean
   * 
   */
  // Is the given student in any of the same classes as this student?
  boolean classmates(Student that) {
    return this.courses.overlap(that.courses);
  }

  /*
   * Template: Same as class +
   * 
   * Parameters: ...that... -- IList<Course>
   * 
   * Methods on parameters: ...that.overlap(IList<Courses>)... -- boolean
   * ...that.foundIn(Courses)... -- boolean ...that.containsNum(IList<Course>,
   * IComparator<Course, Course>, int) -- boolean ...that.contains(Course,
   * IComparator<Course, Course>, int) -- boolean
   */
  // Does this student have two items in the given list?
  boolean enrolledTwo(IList<Course> that) {
    return that.containsNum(this.courses, new SameCourse(), 2);
  }
}

// To represent a generic list
interface IList<T> {
  
  // Template: same as class +
  /*
   * Parameters
   * ...that...   -- IList<T>
   * Methods on Parameters:
   *  ...that.overlap(IList<T>)... -- boolean
   * ...that.foundIn(Courses)...        -- boolean
   * ...that.containsNum(IList<T>, IComparator<T, T>, int) -- boolean
   * ...that.contains(Course, IComparator<T, T>, int) -- boolean
   */
  // Does this list overlap with the given list of courses?
  boolean overlap(IList<T> that);

  /*
   * Template: Same as class +
   * 
   * Parameters: 
   * ...that... T
   */
  // Is the given item found in this list?
  boolean foundIn(T that);

  /*
   * Template: Same as class +
   * Parameters:
   * ...that...    -- IList<T>
   * ...comp...    -- IComparator<T,T>
   * ...min...     -- int
   * 
   * Methods on parameters:
   *  ...that.overlap(IList<T>)... -- boolean
   * ...that.foundIn(Courses)...        -- boolean
   * ...that.containsNum(IList<T>, IComparator<T, T>, int) -- boolean
   * ...that.contains(Course, IComparator<T, T>, int) -- boolean
   * ...comp.apply(T, T)...                           -- int
   */
  // Does the given list contain at least the given number of items in this
  // list?
  // Equality determined by the given comparator
  boolean containsNum(IList<T> that, IComparator<T, T> comp, int min);

  
  /*
   * Template: Same as class +
   * Parameters:
   * ...that...    -- IList<T>
   * ...comp...    -- IComparator<T,T>
   * 
   * Methods on parameters:
   *  ...that.overlap(IList<T>)... -- boolean
   * ...that.foundIn(Courses)...        -- boolean
   * ...that.containsNum(IList<T>, IComparator<T, T>, int) -- boolean
   * ...that.contains(Course, IComparator<T, T>, int) -- boolean
   * ...comp.apply(T, T)...                           -- int
   */
  // Does this list contain the given item?
  // *Equality determined by the given comparator
  boolean contains(T that, IComparator<T, T> comp);

}

// To represent a non-empty generic list
class ConsList<T> implements IList<T> {
  T first;
  IList<T> rest;

  ConsList(T first, IList<T> rest) {
    this.first = first;
    this.rest = rest;
  }

  // Template: same as class +
  /*
   * Parameters
   * ...that...   -- IList<T>
   * Methods on Parameters:
   *  ...that.overlap(IList<T>)... -- boolean
   * ...that.foundIn(Courses)...        -- boolean
   * ...that.containsNum(IList<T>, IComparator<T, T>, int) -- boolean
   * ...that.contains(Course, IComparator<T, T>, int) -- boolean
   */
  // Does this non-empty list overlap with the given list?
  public boolean overlap(IList<T> that) {
    return that.foundIn(this.first) || this.rest.overlap(that);
  }

  /*
   * Template: Same as class +
   * 
   * Parameters: 
   * ...that... T
   */
  // Is the given item found in this non-empty list?
  public boolean foundIn(T that) {
    return (this.first == that) || (this.rest.foundIn(that));
  }


  /*
   * Template: Same as class +
   * Parameters:
   * ...that...    -- IList<T>
   * ...comp...    -- IComparator<T,T>
   * ...min...     -- int
   * 
   * Methods on parameters:
   *  ...that.overlap(IList<T>)... -- boolean
   * ...that.foundIn(Courses)...        -- boolean
   * ...that.containsNum(IList<T>, IComparator<T, T>, int) -- boolean
   * ...that.contains(Course, IComparator<T, T>, int) -- boolean
   * ...comp.apply(T, T)...                           -- int
   */
  // Does the given list contain at least the given number of items in this
  // non-empty list?
  public boolean containsNum(IList<T> that, IComparator<T, T> comp, int min) {
    if (min < 1) {
      return true;
    }
    else if (that.contains(this.first, comp)) {
      return this.rest.containsNum(that, comp, min - 1);
    }

    else {
      return this.rest.containsNum(that, comp, min);
    }
  }

  
  /*
   * Template: Same as class +
   * Parameters:
   * ...that...    -- IList<T>
   * ...comp...    -- IComparator<T,T>
   * 
   * Methods on parameters:
   *  ...that.overlap(IList<T>)... -- boolean
   * ...that.foundIn(Courses)...        -- boolean
   * ...that.containsNum(IList<T>, IComparator<T, T>, int) -- boolean
   * ...that.contains(Course, IComparator<T, T>, int) -- boolean
   * ...comp.apply(T, T)...                           -- int
   */
  // Does this non-empty list contain the given item?
  public boolean contains(T that, IComparator<T, T> comp) {
    return (comp.apply(this.first, that) == 0) || this.rest.contains(that, comp);
  }
}

// To represent an empty generic list
class MtList<T> implements IList<T> {

  // Template: same as class +
  /*
   * Parameters
   * ...that...   -- IList<T>
   * Methods on Parameters:
   *  ...that.overlap(IList<T>)... -- boolean
   * ...that.foundIn(Courses)...        -- boolean
   * ...that.containsNum(IList<T>, IComparator<T, T>, int) -- boolean
   * ...that.contains(Course, IComparator<T, T>, int) -- boolean
   */
  // Does this empty list overlap with the given list?
  public boolean overlap(IList<T> that) {
    return false;
  }

  /*
   * Template: Same as class +
   * 
   * Parameters: 
   * ...that... T
   */
  // Is the given item found in this empty list?
  public boolean foundIn(T that) {
    return false;
  }

  /*
   * Template: Same as class +
   * Parameters:
   * ...that...    -- IList<T>
   * ...comp...    -- IComparator<T,T>
   * ...min...     -- int
   * 
   * Methods on parameters:
   *  ...that.overlap(IList<T>)... -- boolean
   * ...that.foundIn(Courses)...        -- boolean
   * ...that.containsNum(IList<T>, IComparator<T, T>, int) -- boolean
   * ...that.contains(Course, IComparator<T, T>, int) -- boolean
   * ...comp.apply(T, T)...                           -- int
   */
  // Does the given list contain at least the given number of items in this
  // empty list?
  public boolean containsNum(IList<T> that, IComparator<T, T> comp, int min) {
    return min < 1;
  }

  
  /*
   * Template: Same as class +
   * Parameters:
   * ...that...    -- IList<T>
   * ...comp...    -- IComparator<T,T>
   * 
   * Methods on parameters:
   *  ...that.overlap(IList<T>)... -- boolean
   * ...that.foundIn(Courses)...        -- boolean
   * ...that.containsNum(IList<T>, IComparator<T, T>, int) -- boolean
   * ...that.contains(Course, IComparator<T, T>, int) -- boolean
   * ...comp.apply(T, T)...                           -- int
   */
  // Does this empty list contain the given item?
  public boolean contains(T that, IComparator<T, T> comp) {
    return false;
  }
}

interface IComparator<T, U> {
  
  /*
   * Template: Same as class +
   * ...t...  -- T
   * ...u...  -- U
   */
  // Applies this comparator to the given data
  // Returns 0 for the same data
  int apply(T t, U u);
}

// SameCourse is just a boolean comparator, since courses do not have an order.
// Made an IComparator since I thought it would make it more expandable in the
// future.
class SameCourse implements IComparator<Course, Course> {
  
  /*
   * Template: Same as class +
   * Parameters:
   * ...c1...   -- Course
   * ...c2...   -- Course
   * 
   * Methods on parameters:
   * ...c1.enroll(Student)...  --*void*
   * ...c2.enroll(Student)...  --*void*
   * 
   */
  // Applies this course comparison to the given courses 
  public int apply(Course c1, Course c2) {
    if (c1 == c2) {
      return 0;
    }

    else {
      return 1;
    }
  }
}

// To hold examples and tests
class ExamplesCourses {

  // Empty Lists
  IList<Student> mtS = new MtList<Student>();
  IList<Course> mtC = new MtList<Course>();

  // Instructors
  Instructor tuck = new Instructor("Tuck");
  Instructor lerner = new Instructor("Lerner");
  Instructor bunce = new Instructor("Bunce");
  Instructor watts = new Instructor("Watts");
  Instructor savant = new Instructor("Savant");

  // Courses
  Course fundies1 = new Course("Fundies 1", tuck);
  Course fundies2 = new Course("Fundies 2", lerner);
  Course biopsych = new Course("Biological Psychology", bunce);
  Course war = new Course("Anthropology of War", watts);
  Course systems = new Course("Computer Systems", tuck);
  Course stat = new Course("Statistics", savant);
  Course geo = new Course("Differential Geometry", savant);
  Course top = new Course("Algebraic Topology", savant);
  Course spaces = new Course("Function Spaces", savant);

  // Students
  Student julian = new Student("Julian", 001650560);
  Student marianne = new Student("Marianne", 000000001);
  Student bill = new Student("Bill", 000000002);
  Student ava = new Student("Ava", 000000003);
  Student latisha = new Student("Latisha", 000000004);
  Student ava2 = new Student("ava", 00000005);

  // Some filled lists
  IList<Course> cs = new ConsList<Course>(fundies1,
      new ConsList<Course>(fundies2, new ConsList<Course>(systems, new MtList<Course>())));
  IList<Course> sci = new ConsList<Course>(biopsych,
      new ConsList<Course>(war, new MtList<Course>()));
  IList<Course> hum = new ConsList<Course>(war, new MtList<Course>());
  IList<Course> math = new ConsList<Course>(stat, new ConsList<Course>(geo,
      new ConsList<Course>(top, new ConsList<Course>(spaces, new MtList<Course>()))));
  IList<Course> all = new ConsList<Course>(fundies1,
      new ConsList<Course>(fundies2, new ConsList<Course>(biopsych, new ConsList<Course>(war,
          new ConsList<Course>(systems, new ConsList<Course>(stat, new ConsList<Course>(geo,
              new ConsList<Course>(top, new ConsList<Course>(spaces, new MtList<Course>())))))))));

  // A comparator
  IComparator<Course, Course> comp = new SameCourse(); 
  
  // Initalizes the data
  void init() {
    this.fundies1.students = mtS;
    this.fundies2.students = mtS;
    this.biopsych.students = mtS;
    this.war.students = mtS;
    this.systems.students = mtS;
    this.stat.students = mtS;
    this.geo.students = mtS;
    this.top.students = mtS;
    this.spaces.students = mtS;

    this.julian.courses = mtC;
    this.marianne.courses = mtC;
    this.bill.courses = mtC;
    this.ava.courses = mtC;
    this.latisha.courses = mtC;
    this.ava2.courses = mtC; 
  }

  // Testing the enroll method
  void testEnroll(Tester t) {
    // Initialize data
    init();

    // Make sure data is as we expect it to be
    t.checkExpect(tuck.courses,
        new ConsList<Course>(systems, new ConsList<Course>(fundies1, new MtList<Course>())));
    t.checkExpect(lerner.courses, new ConsList<Course>(fundies2, new MtList<Course>()));
    t.checkExpect(bunce.courses, new ConsList<Course>(biopsych, new MtList<Course>()));
    t.checkExpect(watts.courses, new ConsList<Course>(war, new MtList<Course>()));
    t.checkExpect(savant.courses, new ConsList<Course>(spaces, new ConsList<Course>(top,
        new ConsList<Course>(geo, new ConsList<Course>(stat, new MtList<Course>())))));
    t.checkExpect(julian.courses, mtC);
    t.checkExpect(marianne.courses, mtC);
    t.checkExpect(bill.courses, mtC);
    t.checkExpect(ava.courses, mtC);
    t.checkExpect(latisha.courses, mtC);
    t.checkExpect(fundies1.students, mtS);
    t.checkExpect(fundies2.students, mtS);
    t.checkExpect(biopsych.students, mtS);
    t.checkExpect(war.students, mtS);
    t.checkExpect(stat.students, mtS);
    t.checkExpect(geo.students, mtS);
    t.checkExpect(top.students, mtS);
    t.checkExpect(spaces.students, mtS);

    // Test the enroll method
    julian.enroll(fundies1);
    t.checkExpect(fundies1.students, new ConsList<Student>(julian, new MtList<Student>()));
    latisha.enroll(fundies1);
    t.checkExpect(fundies1.students,
        new ConsList<Student>(latisha, new ConsList<Student>(julian, new MtList<Student>())));
  }

  // Testing the addCourse method
  void testAddCourse(Tester t) {
 // Initialize data
    init();

    // Make sure data is as we expect it to be
    t.checkExpect(tuck.courses,
        new ConsList<Course>(systems, new ConsList<Course>(fundies1, new MtList<Course>())));
    t.checkExpect(lerner.courses, new ConsList<Course>(fundies2, new MtList<Course>()));
    t.checkExpect(bunce.courses, new ConsList<Course>(biopsych, new MtList<Course>()));
    t.checkExpect(watts.courses, new ConsList<Course>(war, new MtList<Course>()));
    t.checkExpect(savant.courses, new ConsList<Course>(spaces, new ConsList<Course>(top,
        new ConsList<Course>(geo, new ConsList<Course>(stat, new MtList<Course>())))));
    t.checkExpect(julian.courses, mtC);
    t.checkExpect(marianne.courses, mtC);
    t.checkExpect(bill.courses, mtC);
    t.checkExpect(ava.courses, mtC);
    t.checkExpect(latisha.courses, mtC);
    t.checkExpect(fundies1.students, mtS);
    t.checkExpect(fundies2.students, mtS);
    t.checkExpect(biopsych.students, mtS);
    t.checkExpect(war.students, mtS);
    t.checkExpect(stat.students, mtS);
    t.checkExpect(geo.students, mtS);
    t.checkExpect(top.students, mtS);
    t.checkExpect(spaces.students, mtS);

    // Test the addCourse method
    // Note: addCourse is not meant to be used in this way. With this design,
    // Courses are created with instructors, but instructors cannot by themselves 
    // Add courses to their list. This is why addCourse, while located in the professor
    // class, is only ever called from the Course class.
    lerner.addCourse(fundies1);
    t.checkExpect(lerner.courses, new ConsList<Course>(fundies1, 
        new ConsList<Course>(fundies2, new MtList<Course>())));
    bunce.addCourse(war);
    t.checkExpect(bunce.courses, new ConsList<Course>(war, 
        new ConsList<Course>(biopsych, new MtList<Course>())));
  }
  
  // Testing the classmates method
  void testClassmates(Tester t) {
    // Initialize data
    init();

    // Make sure data is as we expect it to be
    t.checkExpect(tuck.courses,
        new ConsList<Course>(systems, new ConsList<Course>(fundies1, new MtList<Course>())));
    t.checkExpect(lerner.courses, new ConsList<Course>(fundies2, new MtList<Course>()));
    t.checkExpect(bunce.courses, new ConsList<Course>(biopsych, new MtList<Course>()));
    t.checkExpect(watts.courses, new ConsList<Course>(war, new MtList<Course>()));
    t.checkExpect(savant.courses, new ConsList<Course>(spaces, new ConsList<Course>(top,
        new ConsList<Course>(geo, new ConsList<Course>(stat, new MtList<Course>())))));
    t.checkExpect(julian.courses, mtC);
    t.checkExpect(marianne.courses, mtC);
    t.checkExpect(bill.courses, mtC);
    t.checkExpect(ava.courses, mtC);
    t.checkExpect(latisha.courses, mtC);
    t.checkExpect(fundies1.students, mtS);
    t.checkExpect(fundies2.students, mtS);
    t.checkExpect(biopsych.students, mtS);
    t.checkExpect(war.students, mtS);
    t.checkExpect(stat.students, mtS);
    t.checkExpect(geo.students, mtS);
    t.checkExpect(top.students, mtS);
    t.checkExpect(spaces.students, mtS);

    // Ava, Marianne, and bill should not be classmates
    t.checkExpect(marianne.classmates(ava), false);
    t.checkExpect(marianne.classmates(bill), false);
    t.checkExpect(ava.classmates(bill), false);

    // Enroll Ava and Marianne in the same class, and Bill in a different class
    ava.enroll(war);
    marianne.enroll(war);
    bill.enroll(fundies2);

    // Now Ava and Marianne should be classmates, but not Ava and Bill nor the other Ava
    t.checkExpect(marianne.classmates(ava), true);
    t.checkExpect(ava.classmates(marianne), true);
    t.checkExpect(ava2.classmates(marianne), false);
    t.checkExpect(marianne.classmates(bill), false);
    t.checkExpect(ava.classmates(bill), false);
  }

  // Testing the dejavu method
  void testDejavu(Tester t) {
    // Initialize data
    init();

    // Make sure data is as we expect it to be
    t.checkExpect(tuck.courses,
        new ConsList<Course>(systems, new ConsList<Course>(fundies1, new MtList<Course>())));
    t.checkExpect(lerner.courses, new ConsList<Course>(fundies2, new MtList<Course>()));
    t.checkExpect(bunce.courses, new ConsList<Course>(biopsych, new MtList<Course>()));
    t.checkExpect(watts.courses, new ConsList<Course>(war, new MtList<Course>()));
    t.checkExpect(savant.courses, new ConsList<Course>(spaces, new ConsList<Course>(top,
        new ConsList<Course>(geo, new ConsList<Course>(stat, new MtList<Course>())))));
    t.checkExpect(julian.courses, mtC);
    t.checkExpect(marianne.courses, mtC);
    t.checkExpect(bill.courses, mtC);
    t.checkExpect(ava.courses, mtC);
    t.checkExpect(latisha.courses, mtC);
    t.checkExpect(fundies1.students, mtS);
    t.checkExpect(fundies2.students, mtS);
    t.checkExpect(biopsych.students, mtS);
    t.checkExpect(war.students, mtS);
    t.checkExpect(stat.students, mtS);
    t.checkExpect(geo.students, mtS);
    t.checkExpect(top.students, mtS);
    t.checkExpect(spaces.students, mtS);

    // Nat Tuck should not be getting dejavu about Julian
    t.checkExpect(tuck.dejavu(julian), false);

    // But let's say that Julian makes a mistake and enrolls in both fundies 1
    julian.enroll(fundies1);

    // Tuck should still not be getting dejavu
    t.checkExpect(tuck.dejavu(julian), false);

    // But if Julian enrolls in systems too:
    julian.enroll(systems);

    // Now Tuck should be gettin some dejavu
    t.checkExpect(tuck.dejavu(julian), true);

    // And it's not person specific: we can do the same thing with Marianne and
    // Savant
    t.checkExpect(savant.dejavu(marianne), false);
    marianne.enroll(spaces);
    marianne.enroll(biopsych);
    marianne.enroll(top);
    t.checkExpect(savant.dejavu(marianne), true);
    // But Julian is unaffected
    t.checkExpect(savant.dejavu(julian), false);
  }

  // Testing the student.enrolledTwo(list of courses) method
  void testEnrolledTwo(Tester t) {
    // Initialize data
    init();

    // Make sure data is as we expect it to be
    t.checkExpect(tuck.courses,
        new ConsList<Course>(systems, new ConsList<Course>(fundies1, new MtList<Course>())));
    t.checkExpect(lerner.courses, new ConsList<Course>(fundies2, new MtList<Course>()));
    t.checkExpect(bunce.courses, new ConsList<Course>(biopsych, new MtList<Course>()));
    t.checkExpect(watts.courses, new ConsList<Course>(war, new MtList<Course>()));
    t.checkExpect(savant.courses, new ConsList<Course>(spaces, new ConsList<Course>(top,
        new ConsList<Course>(geo, new ConsList<Course>(stat, new MtList<Course>())))));
    t.checkExpect(julian.courses, mtC);
    t.checkExpect(marianne.courses, mtC);
    t.checkExpect(bill.courses, mtC);
    t.checkExpect(ava.courses, mtC);
    t.checkExpect(latisha.courses, mtC);
    t.checkExpect(fundies1.students, mtS);
    t.checkExpect(fundies2.students, mtS);
    t.checkExpect(biopsych.students, mtS);
    t.checkExpect(war.students, mtS);
    t.checkExpect(stat.students, mtS);
    t.checkExpect(geo.students, mtS);
    t.checkExpect(top.students, mtS);
    t.checkExpect(spaces.students, mtS);

    // Latisha should not be enrolled in two of the total classes. Nor is she enrolled in two
    // of the empty list of classes, because that doesn't even make sense
    t.checkExpect(latisha.enrolledTwo(all), false);
    t.checkExpect(latisha.enrolledTwo(mtC), false);
    
    // Nothing changes if we enroll her in one course
    latisha.enroll(biopsych);
    t.checkExpect(latisha.enrolledTwo(all), false);
    
    // But it changes if we enroll her in one more
    latisha.enroll(systems);
    t.checkExpect(latisha.enrolledTwo(all), true);
    
    // But it is specific to the list type
    t.checkExpect(latisha.enrolledTwo(sci), false);
  }

  // Testing the list.overlap(list) method
  void testOverlap(Tester t) {
    // Two empty lists do not overlap. A list an empty do not overlap. 
    // Two lists with nothing in common do not overlap, but two lists with something
    // in common do overlap
    t.checkExpect(mtC.overlap(mtC), false);
    t.checkExpect(mtC.overlap(all), false);
    t.checkExpect(sci.overlap(cs), false);
    t.checkExpect(sci.overlap(hum), true);
  }

  // Testing the list.foundin(that) method
  void testFoundIn(Tester t) {
    t.checkExpect(mtC.foundIn(biopsych), false);
    t.checkExpect(sci.foundIn(biopsych), true);
    t.checkExpect(all.foundIn(biopsych), true);
    t.checkExpect(hum.foundIn(biopsych), false);
  }
  
  // Testing the list.containsNum(list, comparator, minimum) method
  void testContainsNum(Tester t) {
    t.checkExpect(mtC.containsNum(mtC, comp, 0), true);
    t.checkExpect(mtC.containsNum(mtC, comp, 1), false);
    t.checkExpect(hum.containsNum(sci, comp, 1), true);
    t.checkExpect(hum.containsNum(sci, comp, 2), false);
    t.checkExpect(all.containsNum(all, comp, 7), true);
  }
  
  // Testing the list.contains(item, comparator) method
  void testContains(Tester t) {
    t.checkExpect(mtC.contains(fundies1, comp), false);
    t.checkExpect(sci.contains(fundies1, comp), false);
    t.checkExpect(cs.contains(fundies1, comp), true);
    t.checkExpect(sci.contains(biopsych, comp), true);
    t.checkExpect(all.contains(geo, comp), true);
  }
}
