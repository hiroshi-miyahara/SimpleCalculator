/**
**	ValueContainer
**
**	���l�̕ێ�
**		�����w��
**		�������Ə����_����ʎ���
**		���l�̓���(add,point)
**		����R�}���h(clear,sign)
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

	// �\���e�L�X�g�̎��o��
	public final String getDisp()
	{
		if(mErr) return "N/A";

		String	S = (mSign>0)?"":"-";
		if(mFlt < 0) return S+mInt;
		if(0 == mFlt) return S+mInt+"."+zero();
		return S+mInt+"."+zero()+mFlt;
	}

	// �l�̃_�C���N�g�ݒ�
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

	// �l�̎��o��
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

	// �����_���̐ݒ�
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

	// ���l�̌����̎Z�o
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

	// ���e�̃N���A
	public final void clear()
	{
		mSign = 1;
		mInt  = 0;
		mFlt  = -1;
		mZero = 0;
		mErr  = false;
	}

	// ����(�P��)�̓���
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

	// �����_����
	public final void point()
	{
		if(mErr) return;

		if(mFlt < 0)
		{
			mFlt  = 0;
			mZero = 0;
		}
	}

	// �����ϊ�
	public final void sign()
	{
		if(mErr) return;

		if((0==mInt) && (mFlt<=0)) return;
		mSign = (short)-mSign;
	}
}
