NOTES :
    
    MapUserInput handles Input
    
    GameSprite handles map drawing
    Map handles object drawing
        
    Player handles camera rotation

TODO : 

    Reorganise files to match client
    Add 'get' awareness in README (replaced get value() with getValue())
    Add placeholder for graphic stuff (Player, GameObject)
    
    
    
    
-------------------
    
Most changes are automatically made by the AS3 to Java converter AS3toCSharp.

see AldeCommons' *AS3ToJava.java*

Good news, most of the manual changes are related to Arrays and Dictionary.


Strings
--------

   **AS3**
   
   this.name.length;
   
   
   **Java**
   
    this.name.length();


Integers
------

   Large int to double

   **AS3**
    
    4294967295
    
   **Java**

    4294967295.0


Methods and fields
-------------

   Remove the '_' by convention.
   
   Java declares the type before the name (opposite for AS3, see bellow).
   In AS3 the return type is after the method name
   
   Java uses the @Override annotation while ActionScript uses a keyword.
   
   **AS3**
   
    public var portrait_:BitmapData;
    
    override public function getPortrait() : BitmapData

   **Java**
   
    public BitmapData portrait;
   
    @Override
    public BitmapData getPortrait()
    
    
Dictionary
---------------

   Java has a Dictionary class, but it's not exactly like AS3's.
   
   Use the utility class Dictionary instead.
    
    Key, Value
    
   **AS3**
   
    Dictionary textures = new Dictionary();
    texturingCache[0] = new Texture();
    
   **Java**
   
    Dictionary<Integer, Texture> textures = new Dictionary<>();
    textures.put(0, new Texture());

    
Number
-----------

   Java doesn't have the 'Number' object. Use 'double' instead.

    3.0
    
   **AS3**
       
    Number num = 3.0;
    
   **Java**
   
    double num = 3.0;


Reflection
-----------

   **AS3**
   
    Class.newInstance();
    
    
   **Java**
   
    Class toGenerate = ...;
   
    Class[] cArg = new Class[2]; //Our constructor has 2 arguments
    cArg[0] = XML.class; //First argument
    cArg[1] = Integer.TYPE; // use .TYPE for primitive integer
    
    try {
    	return (GameObject) typeClass.getDeclaredConstructor(cArg).newInstance(objectXML, objectType);
    } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
    	| InvocationTargetException | NoSuchMethodException | SecurityException e) {
    	e.printStackTrace();
    }
    

Casting
-------

   **AS3**

    int(equipment.length)
    
    Object as Projectile


   **Java**
   
    (int) equipment.length

    (Projectile) Object


Freelist
------

   We allocated objects from memory directly

   **AS3**
    
       FreeList.newObject(Projectile) as Projectile;
       
   **Java**

       new Projectile();


Arrays
-----------

   Arrays in AS3 are weird. There is lists, vectors and arrays.
   We try to use lists for Messages (packets) as much as possible.
   For the rest, we use the utility class Vector (see bellow).
   

Vectors
---------------

   Use the utility class Vector.
   
   **AS3**

	public static const STARS:Vector.<int> = new <int>[20, 150, 400, 800, 2000];

   **Java**

	public static final Vector<Integer> STARS = new Vector<Integer>(20, 150, 400, 800, 2000);
   

XML handling
--------------

   Java doesn't have XML handling built in. Use the utility class XML.java instead.

    <Element attribute="value">value</Element>

   **AS3**
   
    XML.Element 
    XML.@attribute
    
   **Java**
   
    XML.getValue("Element");
    XML.getAttribute("attribute");
    
   Getting child elements as XML objects
   
   **AS3**
   
    loc2 = XML(param1.Guild);
    
   **Java**
   
    param1.getChild("Guild");

   Take a look at XML.java for more XML-related tasks
   


    


    
    

    
    

   
   
Signals
-----------

   RotMG uses the Signal class to send messages between the classes. 
   I have implemented a version of it.
   
   Add a method : add(Consumer<Class>)
   Call the method : dispatch(Class c)
   
   See Signal for more information
   
   **AS3**
      
       public class Portrait extends Signal
       
       public function Portrait() {
            super(Boolean)  
       }
       
   **JAVA**
   
       public class Portrait extends Signal<Boolean>
   
   
Injector
-------

   AS3 uses injector.getInstance to get static instances of classes...
   I have personally opted for the Singleton pattern instead
   
   
   
   