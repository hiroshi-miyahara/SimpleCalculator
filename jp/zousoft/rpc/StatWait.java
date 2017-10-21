/**
**	StatWait
**
**	数値入力待ち状態
**	状態遷移：
**		数値：数値をセット
**		小数点：小数点をセット
**		符号変換：符号変換を実行(sign)
**		演算子,＝：演算子を設定し次の状態に移動
**		クリア：数値を消去し次の状態に移動
**		AC：数値の消去
*/
package jp.zousoft.rpc;

import jp.zousoft.calc.ValueContainer;
import jp.zousoft.calc.Params;
import jp.zousoft.calc.Keys;

class StatWait extends Stat
{
	public StatWait(ValueContainer cValue, Works cWork)
	{
		super(cValue, cWork);
	}

	public Stat next(Keys.Type cType, int cValue)
	{
		if(Keys.Type.All == cType)
		{
			mWork.doit(cType);
			return mNext;
		}
		if(mValue.isError()) return this;

		switch(cType)
		{
		case Value:
			mWork.doit(Keys.Type.Equal);
			mValue.add(cValue);
			return mNext;
		case Point:
			mWork.doit(Keys.Type.Equal);
			mValue.point();
			return mNext;
		case Sign:
			mValue.sign();
			break;
		case Clear:
			mWork.doit(Keys.Type.Equal);
			mValue.setValue(0);
			return mNext;
		case All:
			mWork.doit(Keys.Type.All);
			break;
		case Equal:
			mWork.doit(cType);
			break;
		case Plus:
		case Minus:
		case Times:
		case Div:
			mWork.doit(cType);
			break;
		}

		return this;
	}
}
