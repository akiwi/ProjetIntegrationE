import socket
import os
import time
#from datetime import datetime
import MySQLdb
import sys

BUFFER_SIZE = 1024

#connection DB
db = MySQLdb.connect(host="127.0.0.1",   
                     user="root",         
                     passwd="",  
                     db="")        


# curseur pour executer le query sql
cur = db.cursor()

if __name__ == '__main__':
	# Query SQL
	cur.execute("SELECT `Alarme`.`heure`,`Alarme`.`minute`,`Alarme`.`raison` FROM `test`.`Alarme` WHERE `Alarme`.`port` =" + str(sys.argv[1]))
	tabAlarmes=cur.fetchall()
	aList = list(tabAlarmes)
	if(len(sys.argv)==6):
		cur.execute("SELECT `Alarme`.`heure`,`Alarme`.`minute`,`Alarme`.`raison` FROM `test`.`Alarme` WHERE `Alarme`.`port` =" + str(sys.argv[1]) + " AND `Alarme`.`idAlarme` =" + str(sys.argv[5]))
		tabADel = cur.fetchall()
		bList = list(tabADel)
		aList.remove(bList[0])  
	#result = [ str(row).split(',', 1 ) for row in cur.fetchall()]
	b = "[]('"     #caracteres a supprimer dans la chaine
	arg4 = sys.argv[4].replace('%',' ') 
	bList = (sys.argv[2],sys.argv[3],arg4)
	aList.append(bList)
	a = str(aList)
	print a
	for char in b:
		a = a.replace(char,"")
	print a
	
	Demarrage serveur
	s = socket.socket(socket.AF_INET,socket.SOCK_STREAM)
	TCP_IP = '192.168.1.41'
	TCP_PORT =int(sys.argv[1])
	s.bind((TCP_IP,TCP_PORT))
	s.listen(1)
	conn,addr = s.accept()
	while 1:
		data = conn.recv(BUFFER_SIZE)
		if not data: break
		print "received data:", data
		if data  == "pret":
			#Envoi de la liste des alarmes sous forme de chaine de caracteres
			conn.send(a)
	conn.close()
	s.close()
