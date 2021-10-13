# Far-Away-front

| **URI** | **Method** | **Auth?** | **Action** |
| --- | --- | --- | --- |
| suits | GET | Non | READ ALL : retourne tous les scaphandres de la db. |
| suits/{id} | GET | non | READ ONE : retourne un scaphandre |
| suits?sort=value&amp;type=value&amp;min=value&amp;max=value | GET | Non | READ ALL : retourne tous les scaphandres triés par prix croissant ou décroissant ou par type ou par prix minimum, maximum ou encore plusieurs en même temps. |
| suits | POST | Oui : admin | CREATE ONE : ajoute un scaphandre dans la db avec les données passées dans la requête. |
| suits/{id} | DELETE | Oui : admin | DELETE ONE : supprime un scaphandre donné dans la db. |
| suits/{id} | UPDATE | Oui : admin | UPDATE ONE : modifie un scaphandre donné dans la db. |
| me | GET | oui : utilisateur | READ ONE : retourne les informations de la session utilisateur |
| users | GET | oui : admin | READ ALL : retourne tous les utilisateurs |
| authentification/login | POST | non | LOGIN : connecte un utilisateur. |
| authentification/register | POST | non | CREATE ONE : enregistre un nouvel utilisateur |
| users/{id} | DELETE | oui : utilisateur | DELETE ONE : supprime un utilisateur |
| users/{id} | UPDATE | oui : utilisateur | UPDATE ONE : modifie un utilisateur |
| users/{mail} | GET | Oui : admin | READ ONE : retourne un utilisateur en fonction de son mail. |
| baskets/{id\_user}A voir | GET | Oui : utilisateur | READ SEVERAL : retourne les paniers en fonction de l&#39;id de l&#39;utilisateur à qui appartient le panier |
| baskets/{id}/{id\_article} | DELETE | Oui : utilisateur | DELETE ONE : permet de supprimer un article du panier choisi |
| baskets/{id}/{id\_article} | POST | Oui : utilisateur | CREATE ONE : permet de rajouter un article dans le panier choisi |
| baskets/{id}/{id\_article} | UPDATE | Oui : utilisateur | UPDATE ONE : permet de modifier la quantité d&#39;un article donné dans le panier choisi |