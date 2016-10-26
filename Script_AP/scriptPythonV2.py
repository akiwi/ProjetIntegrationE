import socket
import os
import time
from datetime import datetime
import MySQLdb

BUFFER_SIZE = 1024
#connection DB
db = MySQLdb.connect(host="127.0.0.1",   
                     user="root",         
                     passwd="",  
                     db="")        


# cursor
cur = db.cursor()

# Query SQL
cur.execute("SELECT `Alarme`.`port`,`Alarme`.`heure`,`Alarme`.`minute`,`Alarme`.`raison`FROM `test`.`Alarme`ORDER BY `Alarme`.`port`")
tabAlarmes=cur.fetchall()
while 1:

	#On recupere l heure actuelle
	t = datetime.now().time()
	
	#J affiche l heure pour debug
	print (t.hour+2)%24
	print t.minute
	
	#Affichage de la table des alarmes
	#for row in  tabAlarmes:
	#	print row[0]
	#	for data in row:
	#		print data
	
	#parcours de la table des alarmes
	for i in range(len( tabAlarmes)):
		#si l heure actuelle correspond a une alarme
		if (t.hour+2)%24 ==  tabAlarmes[i][1] and t.minute ==  tabAlarmes[i][2]:	
			
			#j affiche l heure pour debug
			print (t.hour+2)%24
			print t.minute 
			
			#j affiche le port et la raison de l alarme pour debug
			print str( tabAlarmes[i][0]);
			print str( tabAlarmes[i][3]);
			
			#Demarrage serveur
			s = socket.socket(socket.AF_INET,socket.SOCK_STREAM)
			TCP_IP = '192.168.1.41';
			TCP_PORT = int( tabAlarmes[i][0]);
			s.bind((TCP_IP,TCP_PORT))
			s.listen(1)
			conn,addr = s.accept()
			data = conn.recv(BUFFER_SIZE)
			if not data: break
			print "received data:", data
			if data  == "repas":
				#Envoi de la raison de l alarme
				conn.send(str( tabAlarmes[i][3]))
				#tabAlarmes.pop(i)
			conn.close()
			s.close()
			#time.sleep(50)
	time.sleep(10)
			