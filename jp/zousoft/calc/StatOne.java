/**
**	StatOne
**
**	初期数値入力状態
**	状態遷移：
**		数値：数値を追加(add)
**		小数点：小数点を追加(point)
**		＝：なにもしない
**		符号変換：符号の変換(sign)
**		演算子：演算子を設定し次の状態に移動
**		クリア,AC：数値をクリアし初期状態に戻る
*/
package jp.zousoft.calc;

public class StatOne extends Stat
{
	public StatOne(ValueContainer cDisp, ValueContainer cMem, Params cParam)
	{
		super(cDisp, cMem, cParam);
		mZero = null;
	}

	private Stat	mZero;

	public void setStat(Stat cStat)
	{
		if(null == mZero) mZero = cStat;
		else mNext = cStat;
	}

	public Stat next(Keys.Type cType, int cValue)
	{
		switch(cType)
		{
		case Value:
			mDisp.add(cValue);
			break;
		case Point:
			mDisp.point();
			break;
		case Equal:
			break;
		case Sign:
			mDisp.sign();
			break;
		case All:
		case Clear:
			mDisp.clear();
			return mZero;
		default:
			mParam.setParam(cType);
			return mNext;
		}

		return this;
	}
}
