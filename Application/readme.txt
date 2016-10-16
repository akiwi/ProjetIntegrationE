Bonjour,

Il faudra créer un nouvau projet dans android et l'appeler CareWatch, dans domain name mettez groupe3.ephec.be

pour avoir un package du type be.ephec.groupe3.carewatch

Cela évitera à tout le monde d'avoir des problèmes pour le package.

Pour afficher les informations en débug, il faudra passer la variable debug de MainActivity à true.

pour se connecter à la base de données, changer l'URL_CONNEXION (attention que android émule en privé donc ce ne sera pas localhost si vous travaillez sur votre machine, mais l'ip de la machine sur votre réseau (192.168.0.X)
chez moi j'ai rajouté le port (:8080) mais il sera possible que ça soit autre chose pour vous ou alors inutile si du 80.

Pour le nom de champs (Base de données) :
Changer la ligne 64 et 65 de TaskConnexion se trouvant dans CareWatch\app\src\main\java\be\ephec\groupe3\carewatch\Task\TaskConnexion.java
en mettant le nom de votre base de donnée locale.

------ V1.3 ------
ATTENTION il faut absolument respecter le content value put "url" pour plus tard utiliser la TaskConnect.
Ajout d'une interface pour la phase de test. Pour les démos aux clients.
