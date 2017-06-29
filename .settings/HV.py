#/usr/bin/env python

from pandas import np
from pandas_datareader.data import DataReader

def historical_volatility(sym, days):
    "Return the annualized stddev of daily log returns of `sym`."
    try:
        quotes = DataReader(sym, 'google')['Close'][-days:]
    except Exception, e:
        print "Error getting data for symbol '{}'.\n".format(sym), e
        return None, None
    logreturns = np.log(quotes / quotes.shift(1))

    print quotes
    print logreturns
    
    return np.sqrt(252*logreturns.var())

if __name__ == "__main__":
    print historical_volatility('GOOG', 20)*100

