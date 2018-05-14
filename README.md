TODO : 

    Reorganise files to match client
    Add 'get' awareness in README (replaced get value() with getValue())


Vectors
---------------

   We try to use arrays as much as possible
   
   **AS3**

	public static const STARS:Vector.<int> = new <int>[20, 150, 400, 800, 2000];

   **Java**

	public static final int[] STARS = new int[]{20, 150, 400, 800, 2000};


Bitmap Handling
---------

   We use the non-native to Java class Bitmap (wrapper for BufferedImage)
   
   .width gets replaced with .width()
   

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
   

Number
-----------

   Java doesn't have the 'Number' object. Use 'double' instead.

    3.0
    
   **AS3**
       
    Number num = 3.0;
    
   **Java**
   
    double num = 3.0;
    

Dictionary
---------------

   Java has a Dictionary class, but HashMap matches more closely.
    
    Key, Value
    
   **AS3**
   
    Dictionary textures = new Dictionary();
    texturingCache[0] = new Texture();
    
   **Java**
   
    HashMap<Integer, Texture> textures = new Map();
    textures.put(0, new Texture());
    
    
Methods and fields
-------------

   Remove the '_' by convention.
   
   Java declares the type before the name (opposite for AS3)
   
   Java uses the @Override anotation while ActionScript uses the override keyword
   
   AS3 return type is after the method name

   **AS3**
   
    public var portrait_:BitmapData;
    
    override public function getPortrait() : BitmapData

   **Java**
   
    public BitmapData portrait;
   
    @Override
    public BitmapData getPortrait()
    
    
    
Arrays
-----------

   Arrays in AS3 are weird. There is lists, vectors and arrays.
   We try to use lists for Messages (packets) as much as possible.
   For the rest, we use arrays.
   
   
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
   
   
   
   