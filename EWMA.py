import pandas
import sys

from pandas import np
from pandas_datareader.data import DataReader

def ewma_volatility(sym, days):
    try:
        quotes = DataReader(sym, 'google')['Close'][-days:]
    except Exception, e:
        print "Error getting data for symbol '{}'.\n".format(sym), e
        return None, None
    
    logreturns = np.log(quotes / quotes.shift(1))
    
    l = 0.94 #lambda
    temp = 1-l

    weight = []
    
    for logreturn in logreturns:
        logreturn = logreturn*logreturn*temp
        weight.append(logreturn)
        temp = temp*l

    ewma = pandas.Series(weight, logreturns.index)
    return np.sqrt(252*ewma.sum())

if __name__ == "__main__":
    print ewma_volatility('GOOG',int(sys.argv[1]))*100
