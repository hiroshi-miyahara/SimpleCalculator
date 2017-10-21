/**
**	RPCalc
**
**	逆ポーランド電卓エンジン
*/
package jp.zousoft.rpc;

import java.util.Vector;
import jp.zousoft.calc.Keys;
import jp.zousoft.calc.Params;
import jp.zousoft.calc.ValueContainer;
import jp.zousoft.calc.Calculator;

public class RPCalc implements Calculator
{
	public RPCalc(int cCol)
	{
		ValueContainer	aValue = new ValueContainer(cCol);
		mStack = new Vector<ValueContainer>();
		mStack.add(aValue);
		Works	aWork = new Works() {
			public void doit(Keys.Type cType) {
				switch(cType)
				{
				case Equal:
					mStack.add(new ValueContainer(cCol));
					for(int i=mStack.size()-1 ; i>0 ; --i) {
						mStack.get(i).setValue(mStack.get(i-1).getValue());
					}
					mStack.get(0).clear();
					break;
				case Plus:
					if(mStack.size() >= 2) {
						mStack.get(1).setValue(mStack.get(1).getValue()+mStack.get(0).getValue());
						delFirst();
					}
					break;
				case Minus:
					if(mStack.size() >= 2) {
						mStack.get(1).setValue(mStack.get(1).getValue()-mStack.get(0).getValue());
						delFirst();
					}
					break;
				case Times:
					if(mStack.size() >= 2) {
						mStack.get(1).setValue(mStack.get(1).getValue()*mStack.get(0).getValue());
						delFirst();
					}
					break;
				case Div:
					if(mStack.size() >= 2) {
						mStack.get(1).setValue(mStack.get(1).getValue()/mStack.get(0).getValue());
						delFirst();
					}
					break;
				case All:
					delFirst();
					break;
				}
			}
			public int count() { return mStack.size(); }
		};

		mList = new Stat[2];
		mList[0] = new StatZero(aValue, aWork);
		mList[1] = new StatWait(aValue, aWork);
		mList[0].setStat(mList[1]);
		mList[1].setStat(mList[0]);

		mCol  = cCol;
		mKey  = new Keys();
		mStat = mList[0];
//System.out.println(mStat.getClass().getName());
	}

	private void delFirst()
	{
		for(int i=0 ; i<mStack.size()-1 ; ++i) {
			mStack.get(i).setValue(mStack.get(i+1).getValue());
		}
		mStack.remove(mStack.size()-1);
	}

	private Vector<ValueContainer>	mStack;

	private int		mCol;
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
		return mStack.get(0).getDisp();
	}

	// for Stack Display
	public int countStat() { return mStack.size(); }
	public String getStat(int cIdx) { return mStack.get(cIdx).getDisp(); }
}
