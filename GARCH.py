import numpy as np
import pyflux as pf
import sys
import pandas as pd

from pandas_datareader.data import DataReader

jpm = DataReader('GOOG',  'google')
returns = pd.DataFrame(np.diff(np.log(jpm['Close'].values)))
returns.index = jpm.index.values[1:jpm.index.values.shape[0]]
returns.columns = ['Google Returns']

model = pf.GARCH(returns,p=1,q=1)
x = model.fit()
#x.summary()


print np.sqrt(np.abs(returns.head(int(sys.argv[1]))))*100

#model.predict(h=10)
