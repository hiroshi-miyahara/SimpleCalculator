/**
**	StatStay
**
**	演算子入力後の待ち状態
**	状態遷移：
**		数値：数値をセットし次の状態に移動
**		小数点：小数点をセットし次の状態に移動
**		演算子：演算子を設定する
**		符号変換：符号変換を実行(sign)
**		＝：＝を設定する
**		AC：数値をクリアし初期状態に戻る
*/
package jp.zousoft.calc;

class StatStay extends Stat
{
	public StatStay(ValueContainer cDisp, ValueContainer cMem, Params cParam)
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
		if(Keys.Type.All == cType)
		{
			mDisp.clear();
			mMem .clear();
			return mZero;
		}
		if(mDisp.isError()) return this;

		switch(cType)
		{
		case Value:
			mMem .setValue(mDisp.getValue());
			mDisp.clear();
			mDisp.add(cValue);
			return mNext;
		case Point:
			mMem .setValue(mDisp.getValue());
			mDisp.clear();
			mDisp.point();
			return mNext;
		case Plus:
		case Minus:
		case Times:
		case Div:
		case Equal:
			mParam.setParam(cType);
			break;
		case Sign:
			mDisp.sign();
			break;
		}

		return this;
	}
}
