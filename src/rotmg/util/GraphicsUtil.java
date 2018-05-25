package rotmg.util
{
//   import flash.display.CapsStyle;
//   import flash.display.GraphicsEndFill;
//   import flash.display.GraphicsPath;
//   import flash.display.GraphicsPathCommand;
//   import flash.display.GraphicsSolidFill;
//   import flash.display.GraphicsStroke;
//   import flash.display.JointStyle;
//   import flash.display.LineScaleMode;
//   import flash.geom.Matrix;
   
   public class GraphicsUtil
   {
      
      public static final GraphicsEndFill END_FILL = new GraphicsEndFill();
      
      public static final QUAD_COMMANDS:List = new Integer[]{GraphicsPathCommand.MOVE_TO,GraphicsPathCommand.LINE_TO,GraphicsPathCommand.LINE_TO,GraphicsPathCommand.LINE_TO};
      
      public static final GraphicsStroke DEBUG_STROKE = new GraphicsStroke(1,false,LineScaleMode.NORMAL,CapsStyle.NONE,JointStyle.ROUND,3,new GraphicsSolidFill(16711680));
      
      public static final GraphicsStroke END_STROKE = new GraphicsStroke();
      
      private static final double TWO_PI = 2 * Math.PI;
      
      public static final Array ALL_CUTS = [true,true,true,true];
       
      
public  GraphicsUtil()  
      {
         super();
      }
      
public void clearPath(GraphicsPath graphicsPath)  
      {
         graphicsPath.commands.length = 0;
         graphicsPath.data.length = 0;
      }
      
public GraphicsPath getRectPath(int x, int y, int width, int height)  
      {
         return new GraphicsPath(QUAD_COMMANDS,new Double[]{x,y,x + width,y,x + width,y + height,x,y + height});
      }
      
      public static function getGradientMatrix(width:Number, height:Number, rotation:double = 0.0, tx:double = 0.0, ty:double = 0.0) : Matrix
      {
          Matrix m = new Matrix();
         m.createGradientBox(width,height,rotation,tx,ty);
         return m;
      }
      
public void drawRect(int x, int y, int width, int height, GraphicsPath path)  
      {
         path.moveTo(x,y);
         path.lineTo(x + width,y);
         path.lineTo(x + width,y + height);
         path.lineTo(x,y + height);
      }
      
      public static function drawCircle(centerX:Number, centerY:Number, radius:Number, path:GraphicsPath, numPoints:int = 8) : void
      {
          double th = NaN;
          double thm = NaN;
          double px = NaN;
          double py = NaN;
          double hx = NaN;
          double hy = NaN;
          double curve = 1 + 1 / (numPoints * 1.75);
         path.moveTo(centerX + radius,centerY);
         for( int i = 1; i <= numPoints; i++)
         {
            th = TWO_PI * i / numPoints;
            thm = TWO_PI * (i - 0.5) / numPoints;
            px = centerX + radius * Math.cos(th);
            py = centerY + radius * Math.sin(th);
            hx = centerX + radius * curve * Math.cos(thm);
            hy = centerY + radius * curve * Math.sin(thm);
            path.curveTo(hx,hy,px,py);
         }
      }
      
public void drawCutEdgeRect(int x, int y, int width, int height, int cutLen, Array cuts, GraphicsPath path)  
      {
         if(cuts[0] != 0)
         {
            path.moveTo(x,y + cutLen);
            path.lineTo(x + cutLen,y);
         }
         else
         {
            path.moveTo(x,y);
         }
         if(cuts[1] != 0)
         {
            path.lineTo(x + width - cutLen,y);
            path.lineTo(x + width,y + cutLen);
         }
         else
         {
            path.lineTo(x + width,y);
         }
         if(cuts[2] != 0)
         {
            path.lineTo(x + width,y + height - cutLen);
            path.lineTo(x + width - cutLen,y + height);
         }
         else
         {
            path.lineTo(x + width,y + height);
         }
         if(cuts[3] != 0)
         {
            path.lineTo(x + cutLen,y + height);
            path.lineTo(x,y + height - cutLen);
         }
         else
         {
            path.lineTo(x,y + height);
         }
         if(cuts[0] != 0)
         {
            path.lineTo(x,y + cutLen);
         }
         else
         {
            path.lineTo(x,y);
         }
      }
      
public void drawDiamond(Number x, Number y, Number radius, GraphicsPath path)  
      {
         path.moveTo(x,y - radius);
         path.lineTo(x + radius,y);
         path.lineTo(x,y + radius);
         path.lineTo(x - radius,y);
      }
   }
}
