/**
**	StatZero
**
**	初期状態
**	状態遷移：
**		数値：数値をセットし次の状態に移動
**		小数点：小数点をセットし次の状態に移動
**		その他：何もしない
*/
package jp.zousoft.calc;

public class StatZero extends Stat
{
	public StatZero(ValueContainer cDisp, ValueContainer cMem, Params cParam)
	{
		super(cDisp, cMem, cParam);
	}

	public Stat next(Keys.Type cType, int cValue)
	{
		switch(cType)
		{
		case Value:
			mDisp.add(cValue);
			return mNext;
 		case Point:
			mDisp.point();
			return mNext;
		}

		return this;
	}
}
