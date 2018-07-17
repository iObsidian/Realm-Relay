package oryx2D.util.hint;

import javax.swing.*;
import java.awt.*;

/**
 * Text field with hint text
 * <p>
 * from https://stackoverflow.com/a/24571681
 *
 * @Author Adam Gawne-Cain
 */
public class HintTextField extends JTextField {

	private final String hint;

	public HintTextField(String hint) {
		this.hint = hint;
		setEnabled(true);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		if (getText().length() == 0) {
			int h = getHeight();
			((Graphics2D) g).setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
					RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
			Insets ins = getInsets();
			FontMetrics fm = g.getFontMetrics();
			int c0 = getBackground().getRGB();
			int c1 = getForeground().getRGB();
			int m = 0xfefefefe;
			int c2 = ((c0 & m) >>> 1) + ((c1 & m) >>> 1);
			g.setColor(new Color(c2, true));
			g.drawString(hint, ins.left, h / 2 + fm.getAscent() / 2 - 2);
		}
	}

}

