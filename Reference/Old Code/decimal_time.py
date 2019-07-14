try:
    while True:
        go=0
        while go==0:
            try:
                print('Enter the Sexagesimal Time')
                h = int(input('Hours   '))
                if not (0<=h<=23):
                    print('Invalid Entry')
                    continue
                m = int(input('Minutes '))
                if not (0<=m<=59):
                    print('Invalid Entry')
                    continue
                s = int(input('Seconds '))
                if not (0<=s<=59):
                    print('Invalid Entry')
                    continue
                go = 1
            except ValueError:
                print('Invalid Entry')
        sec = h*3600 + m*60 + s
        dec = sec/0.864
        dh = int(dec/10000)
        dm = int((dec - dh*10000)/100)
        ds = int(round(dec-dh*10000-dm*100))
        print('')
        print('In Decimal Time '+str(h)+':'+f'{m:02d}'+':'+f'{s:02d}'+' is '+str(dh)+':'+f'{dm:02d}'+':'+f'{ds:02d}')
        print('')
except KeyboardInterrupt: 
    pass
