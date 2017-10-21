/**
**	ValueContainer
**
**	数値の保持
**		桁数指定
**		実数部と小数点部を別持ち
**		数値の入力(add,point)
**		特殊コマンド(clear,sign)
*/
package jp.zousoft.calc;

public final class ValueContainer
{
	public ValueContainer(int cCol)
	{
		mCol = (short)cCol;
		clear();
	}

	private short	mCol;
	private short	mSign;
	private long	mInt;
	private long	mFlt;
	private short	mZero;
	private boolean	mErr;

	// 表示テキストの取り出し
	public final String getDisp()
	{
		if(mErr) return "N/A";

		String	S = (mSign>0)?"":"-";
		if(mFlt < 0) return S+mInt;
		if(0 == mFlt) return S+mInt+"."+zero();
		return S+mInt+"."+zero()+mFlt;
	}

	// 値のダイレクト設定
	public final void setValue(double cVal)
	{
		if(mErr) return;

		if(cVal < 0)
		{
			mSign = -1;
			mInt  = (long)-cVal;
			setFloat(-cVal-mInt);
		}
		else
		{
			mSign = 1;
			mInt  = (long)cVal;
			setFloat(cVal-mInt);
		}

		mErr = col(mInt) > mCol;
	}

	// 値の取り出し
	public final double getValue()
	{
		if(mFlt <= 0) return mSign*mInt;

		double	D = mFlt;
		while(D > 1.0) D /= 10.0;
		for(int i=0 ; i<mZero ; ++i) D /= 10.0;
		return mSign * (mInt+D);
	}

	public final boolean isError() { return mErr; }

	private final String zero() { return "0000000000".substring(0, mZero); }

	// 小数点部の設定
	private final void setFloat(double cFlt)
	{
		if(0 == cFlt)
		{
			mFlt = -1;
			return;
		}

		mZero = (short)(mCol - col(mInt));
		long	aTen = 1;
		for(int i=0 ; i<mZero ; ++i) aTen *= 10;
		mFlt = (long)Math.rint(cFlt*aTen);
		if(mFlt == aTen)
		{
			mFlt = -1;
			++mInt;
		} else
		{
			while(0 == (mFlt%10)) { mFlt /= 10; --mZero; }
			mZero -= col(mFlt);
		}
	}

	// 数値の桁数の算出
	private final int col(long cVal)
	{
		if(		cVal < 10) return 1;
		else if(cVal < 100) return 2;
		else if(cVal < 1000) return 3;
		else {
			int	aCol = 0;
			for( ; 0!=cVal ; cVal/=10) ++aCol;
			return aCol;
		}
	}

	// 内容のクリア
	public final void clear()
	{
		mSign = 1;
		mInt  = 0;
		mFlt  = -1;
		mZero = 0;
		mErr  = false;
	}

	// 数字(１桁)の入力
	public final void add(int cVal)
	{
		if(mErr) return;

		if((cVal<0) || (cVal>9)) return;
		if(mFlt < 0)
		{
			if(col(mInt) < mCol)
			{
				if(0 == mInt) mInt = cVal;
				else mInt = 10*mInt+cVal;
			}
		} else
		{
			if(col(mInt)+col(mFlt)+mZero < mCol)
			{
				if((0==mFlt) && (0==cVal)) ++mZero;
				mFlt = 10*mFlt + cVal;
			}
		}
	}

	// 小数点入力
	public final void point()
	{
		if(mErr) return;

		if(mFlt < 0)
		{
			mFlt  = 0;
			mZero = 0;
		}
	}

	// 符号変換
	public final void sign()
	{
		if(mErr) return;

		if((0==mInt) && (mFlt<=0)) return;
		mSign = (short)-mSign;
	}
}
