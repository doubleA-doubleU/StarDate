from datetime import datetime
from time import sleep
from os import system, name

def clear():
    if (name=='nt'):
        system('cls')
    else:
        system('clear')

try:
    while True:
        t = str(datetime.now().time())
        (h,m,s) = t.split(':')
        sec = float(h)*3600 + float(m)*60 + float(s)
        dec = sec/0.864
        dh = int(dec/10000)
        dm = int((dec - dh*10000)/100)
        ds = int(dec-dh*10000-dm*100)
        print('The Current Decimal Time is '+str(dh)+':'+f'{dm:02d}'+':'+f'{ds:02d}')
        print('Press Ctrl+C to Exit')
        sleep(0.864/10)
        clear()  
except KeyboardInterrupt: 
    pass