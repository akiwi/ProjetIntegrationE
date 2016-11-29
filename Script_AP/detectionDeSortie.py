import socket
import os
import time
from datetime import datetime
import MySQLdb
import sys

def changerValeurElementListe(liste, indice,nouvelleValeur):
        liste= list(liste)
        liste[indice]=nouvelleValeur
        liste=tuple(liste)
        return liste

def pingerChaqueIp(db):
        cur = db.cursor()
        cur.execute("SELECT `patient`.`ip`,`patient`.`estPresent`,`patient`.`port` FROM `CareWatch`.`patient` WHERE `patient`.`ip` IS NOT NULL;");
        tabDesIp=list(cur.fetchall())
        sortie = 0
        print str(tabDesIp)
        while 1:
                time.sleep(5)
                for i in range(len(tabDesIp)):
                        a=str(tabDesIp[i][0])
                        print a
                        b= ",()"
                        for char in b:
                                        a = a.replace(char,"")
                        response = os.system("ping -c 1 " + a)
                        print a
                        print str(tabDesIp[i][2])
                        if response == 0:
                                        print a, 'is up!'
                                        if tabDesIp[i][1] :
                                                        print 'est deja present dans la db'
                                        else :
                                                        updateStmnt = "UPDATE `CareWatch`.`patient` SET `patient`.`estPresent` =%s WHERE `patient`.`port`=%s" % (1,int(tabDesIp[i][2]))
                                                        print updateStmnt
                                                        cur.execute (updateStmnt)
                                                        db.commit()
                                                        tabDesIp[i]=list(tabDesIp[i])
                                                        tabDesIp[i][1]=1
                                                        tabDesIp[i]=tuple(tabDesIp[i])
                        else:
                                        print a, 'is down!'
                                        if  not tabDesIp[i][1] :
                                                        print 'est deja absent dans la db'
                                        else:
                                                        cur.execute ("UPDATE `CareWatch`.`patient` SET `patient`.`estPresent` =%s WHERE `patient`.`port`=%s" ,(0,str(tabDesIp[i][2])))
                                                        print str(tabDesIp[i][2])
                                                        db.commit()
														#os.system("python /home/pi/scriptPython/majAlarmes.py " + str(tabDesIp[i][2]))
                                                        tabDesIp[i]=list(tabDesIp[i])
                                                        tabDesIp[i][1]=0
                                                        tabDesIp[i]=tuple(tabDesIp[i])
                if sortie == 1:
                        os.system("python /home/pi/scriptPython/detectionSortie.py")
                        return 0
				sortie+=1

#connection DB
db = MySQLdb.connect(host="127.0.0.1",
                     user="root",
                     passwd="raspberry",
                     db="CareWatch")


pingerChaqueIp(db)


#hostname = "192.168.42.12"
