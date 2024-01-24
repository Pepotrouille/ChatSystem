# Chat System

## Notre objectif

L'objectif de cette application est de proposer une application de conversation en milieu professionel, avec l'utilisation d'une base de données centralisées pour l'accès au messages. Il vous sera donc possible de vous connecter, connaître les personnes en lignes, et discuter avec celles-ci.

## Utilisation

Avant toute utilisation de notre application, il faut s'assurer que 

- Vous possédez la bonne version java,
- Vous êtes connectés sur le réseau INSA, en local ou à travers un VPN, afin d'avoir accès à la base de données.


Pour télécharger notre application, la première étape est de la récupérer depuis le notre GitHub, en rentrant la commande suivante dans un terminal :

```bash
git@github.com:insa-4ir-chatsystem/chatsystem-brunetto-nguyen.git
```

À partir de là, vous devriez trouver un fichier nommé ChatSytem.jar qui vous permettra de lancer l'application avec la commande ;


```bash
java -jar ChatSytem.jar
```

Vous devriez maintenant avoir accès à notre application. A partir d'ici, vous pouvez :

- Vous connecter avec le compte admin (login : admin, mot de passe : admin) afin de tester la modération. Il s'agit originellement du seul compte créé, et seul lui peut créer des comptes utilisateurs. Avec le compte admin, vous aurez également l'occasion de rafraîchir les bases de données
- Envoyer des messages avec d'autres utilisateurs connectés à travers les fenêtres de clavardage. Vous pouvez également vous envoyer des messages avec vous-même pour tester.
- Utiliser le contact discovery en observant les utilisateurs en ligne ainsi que les changements de pseudo, connexions ou déconnexions.


## Credits

Projet réalisé en 2023 dans le cadre des cours de Programmation avancée en Java, Conception Orientée Objet et Processus de Développement de Logiciel Automatisé / Conduite de Projet au sein de l'INSA Toulouse par 
- Brunetto Marie
- NGuyen Le Tuong Lan
