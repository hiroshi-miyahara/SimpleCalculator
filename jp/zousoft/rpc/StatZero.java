/**
**	StatZero
**
**	数値入力待ち状態
**	状態遷移：
**		数値：数値をセット
**		小数点：小数点をセット
**		符号変換：符号の変換(sign)
**		＝：数値を設定する
**		演算子：演算を実施し次の状態に移動
**		クリア：数値のクリア
**		AC：数値を削除し次の状態に移動
*/
package jp.zousoft.rpc;

import jp.zousoft.calc.ValueContainer;
import jp.zousoft.calc.Params;
import jp.zousoft.calc.Keys;

public class StatZero extends Stat
{
	public StatZero(ValueContainer cValue, Works cWork)
	{
		super(cValue, cWork);
	}

	public Stat next(Keys.Type cType, int cValue)
	{
		if(Keys.Type.All == cType)
		{
			mWork.doit(cType);
			return this;
		}
		if(mValue.isError()) return this;

		switch(cType)
		{
		case Value:
			mValue.add(cValue);
			break;
 		case Point:
			mValue.point();
			break;
 		case Sign:
			mValue.sign();
			break;
		case Equal:
			mWork.doit(Keys.Type.Equal);
			break;
		case Plus:
		case Minus:
		case Times:
		case Div:
		case All:
			mWork.doit(cType);
			return mNext;
		case Clear:
			mValue.setValue(0);
			break;
		}

		return this;
	}
}
