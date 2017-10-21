/**
**	StatNum
**
**	２つ目以降の数値入力待ち
**	状態遷移：
**		数値：数値を追加(add)
**		小数点：小数点を追加(point)
**		符号変換：符号の変換(sign)
**		演算子,＝：演算を実行し、現在の演算子を設定し次の状態に移動
**		AC：数値をクリアし初期状態に戻る
**		クリア：数値をクリアする
*/
package jp.zousoft.calc;

class StatNum extends Stat
{
	public StatNum(ValueContainer cDisp, ValueContainer cMem, Params cParam)
	{
		super(cDisp, cMem, cParam);
		mZero  = null;
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
		case Sign:
			mDisp.sign();
			break;
		case Plus:
		case Minus:
		case Times:
		case Div:
		case Equal:
			switch(mParam.getParam())
			{
			case Plus:
				mDisp.setValue(mMem.getValue()+mDisp.getValue());
				break;
			case Minus:
				mDisp.setValue(mMem.getValue()-mDisp.getValue());
				break;
			case Times:
				mDisp.setValue(mMem.getValue()*mDisp.getValue());
				break;
			case Div:
				mDisp.setValue(mMem.getValue()/mDisp.getValue());
				break;
			}
			mParam.setParam(cType);
			return mNext;
		case All:
			mDisp.clear();
			mMem .clear();
			return mZero;
		case Clear:
			mDisp.clear();
			break;
		}

		return this;
	}
}
