package rotmg.ui;

import alde.flash.utils.Vector;
import flash.display.GraphicsPath;
import flash.display.GraphicsSolidFill;
import flash.display.GraphicsStroke;
import flash.display.IGraphicsData;
import rotmg.core.service.GoogleAnalytics;
import rotmg.text.view.stringBuilder.TextFieldDisplayConcrete;

/*
Not fully implemented
 */
public class Frame {

	private static final double INDENT = 17;

	public TextFieldDisplayConcrete titleText;

	public DeprecatedClickableText leftButton;

	public DeprecatedClickableText rightButton;

	public String analyticsPageName;

	public Vector<TextInputField> textInputFields;

	public Vector<DeprecatedClickableText> navigationLinks;

	public int w = 288;

	public int h = 100;

	private GoogleAnalytics googleAnalytics;

	private GraphicsSolidFill titleFill;

	private GraphicsSolidFill backgroundFill;

	private GraphicsSolidFill outlineFill;

	private GraphicsStroke lineStyle;

	private GraphicsPath path1;

	private GraphicsPath path2;

	private Vector<IGraphicsData> graphicsData;

}
