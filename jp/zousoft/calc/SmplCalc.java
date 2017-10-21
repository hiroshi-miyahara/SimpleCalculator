/**
**	SmplCalc
**
**	ノーマル電卓の実装
**	0-9	数値
**	.	小数点
**	+-* /=	演算子
**	AC	オールクリア
**	CLR	現在入力中の数値をクリア
**	SGN	符号変換
*/
package jp.zousoft.calc;

public class SmplCalc implements Calculator
{
	public SmplCalc(int cCol)
	{
		mDisp = new ValueContainer(cCol);
		mMem  = new ValueContainer(cCol);
		Params	aParam = new Params() {
			private Keys.Type mType;
			public void setParam(Keys.Type cType) { mType = cType; }
			public Keys.Type getParam() { return mType; }
		};

		mList = new Stat[4];
		mList[0] = new StatZero(mDisp, mMem, aParam);
		mList[1] = new StatOne (mDisp, mMem, aParam);
		mList[2] = new StatStay(mDisp, mMem, aParam);
		mList[3] = new StatNum (mDisp, mMem, aParam);
		mList[0].setStat(mList[1]);
		mList[1].setStat(mList[0]);
		mList[1].setStat(mList[2]);
		mList[2].setStat(mList[0]);
		mList[2].setStat(mList[3]);
		mList[3].setStat(mList[0]);
		mList[3].setStat(mList[2]);

		mKey  = new Keys();
		mStat = mList[0];
//System.out.println(mStat.getClass().getName());
	}

	private ValueContainer	mDisp;	// 表示中の値
	private ValueContainer	mMem;	// 計算待ちの値(１つ前の値)

	private Keys	mKey;
	private Stat	mStat;	// 現在の状態
	private Stat[]	mList;	// 全状態リスト

	// キー入力
	public void input(String cText)
	{
		Keys.Type	aType = mKey.getType(cText);
		if(Keys.Type.Error == aType) return;

		mStat = mStat.next(aType, mKey.getValue());
//System.out.println(mStat.getClass().getName());
	}

	// 現在の状態の取り出し(表示文字列)
	public String stat()
	{
		return mDisp.getDisp();
	}
}
