/**
**	Calc
**
**	ŠÈ’PŒvŽZ‹@
*/

import javax.swing.JTextField;
import javax.swing.JComponent;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.*;
import jp.zousoft.calc.Calculator;
import jp.zousoft.calc.SmplCalc;

public class Calc extends CPanel
{
	public static void main(String[] args)
	{
		new Calc();
	}

	public Calc()
	{
		super("Simple Calculator");

		setBounds(0, 0, kWidth, kHeight);
		setVisible(true);
	}

	private final static int	kColumn	= 12;	// ‚Æ‚è‚ ‚¦‚¸12Œ…
	private final static int	kWidth	= 300;
	private final static int	kHeight	= 300;

	protected Calculator newCalculator() { return new SmplCalc(kColumn); }

	protected ActionListener newActionListener(String cOp)
	{
		return new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				mCalc.input(cOp);
				mText.setText(mCalc.stat());
			}
		};
	}

	private JTextField	mText;

	protected JComponent createFigs()
	{
		mText = new JTextField(mCalc.stat()) {{
			setHorizontalAlignment(JTextField.RIGHT);
			setEditable(false);
			Font F = readFont("DSEG7Classic-BoldItalic.ttf", 24.0f);
			if(null != F) setFont(F);
		}};

		return mText;
	}

	private Font readFont(String cFile, float cSize)
	{
		try {
			InputStream is = getClass().getClassLoader().getResource(cFile).openStream();
			Font F = Font.createFont(Font.TRUETYPE_FONT, is);
			is.close();
			GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(F);
			return F.deriveFont(24.0f);
		}
		catch(FontFormatException e) { return null; }
		catch(IOException e) { return null; }
	}
}
