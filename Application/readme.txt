Bonjour,

Il faudra cr�er un nouvau projet dans android et l'appeler CareWatch, dans domain name mettez groupe3.ephec.be

pour avoir un package du type be.ephec.groupe3.carewatch

Cela �vitera � tout le monde d'avoir des probl�mes pour le package.

Pour afficher les informations en d�bug, il faudra passer la variable debug de MainActivity � true.

pour se connecter � la base de donn�es, changer l'URL_CONNEXION (attention que android �mule en priv� donc ce ne sera pas localhost si vous travaillez sur votre machine, mais l'ip de la machine sur votre r�seau (192.168.0.X)
chez moi j'ai rajout� le port (:8080) mais il sera possible que �a soit autre chose pour vous ou alors inutile si du 80.

Pour le nom de champs (Base de donn�es) :
Changer la ligne 64 et 65 de TaskConnexion se trouvant dans CareWatch\app\src\main\java\be\ephec\groupe3\carewatch\Task\TaskConnexion.java
en mettant le nom de votre base de donn�e locale.