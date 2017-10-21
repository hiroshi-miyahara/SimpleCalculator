import jp.zousoft.calc.*;
import java.text.Normalizer;

public class T
{
	public static void main(String[] args)
	{
		new T(8);
	}

	public T(int cCol)
	{
		Calculator	aCal = new SmplCalc(cCol);
		doIt(aCal, "1");
		doIt(aCal, "2");
		doIt(aCal, "3");
		doIt(aCal, "=");
		doIt(aCal, "=");

		doIt(aCal, "CLR");
		doIt(aCal, "1");
		doIt(aCal, "2");
		doIt(aCal, "*");
		doIt(aCal, "6");
		doIt(aCal, "/");
		doIt(aCal, "DUMMY");
		doIt(aCal, "2");
		doIt(aCal, ".");
		doIt(aCal, "0");
		doIt(aCal, "=");

		doIt(aCal, "/");
		doIt(aCal, "3");
		doIt(aCal, "*");
		doIt(aCal, "SGN");
		doIt(aCal, "3");
		doIt(aCal, "=");
	}

	private void doIt(Calculator cCal, String cOp)
	{
		cCal.input(cOp);
		System.out.println("<"+cOp+"> -> ["+cCal.stat()+"]");
	}
}
