# ecom_back_end

##Requete public 'METHODE GET'
pour accéder aux requetes publiques depuis postman (methode GET)

    - http://localhost:8080/api/public/hello (permet d'accéder à une page de vérification)

    - http://localhost:8082/api/public/produit/liste (permet d'obtenir la liste des produits dans la BDD)

    
##Requete pour Obtenir Token 'METHODE POST'
pour recevoir un token depuis postman (methode POST avec le JSON)

    - http://localhost:8080/api/public/login

        il faut y ajouter au format JSON un Utilisateur existant dans la BDD (uniquement l'email et le mot de passe)
        `{"email": "minou33@gmail.com",
          "password": "pass"
        }`


##Requete pour Ajouter un utilisateur 'METHODE POST'

    - http://localhost:8080/api/public/adduser 
     pour ajouter un utilisateur dans la BDD (methode POST avec le JSON)





##Requete privé 'METHODE GET'(avec Token)

     pour accéder aux requetes privées depuis postman (methode GET avec le token)
    - http://localhost:8080/api/bye (permet d'accéder à une page privé de vérification)
        il faut y inclure le token dans "Bearer" 


##Requete privé 'METHODE POST'(avec Token)

    - http://localhost:8082/api/public/produit/create (permet d'ajouter un produit dans la BDD)



##Requete privé 'METHODE DELETE'(avec Token)
    - http://localhost:8082/api/produit/delete/{id} (permet de supprimer un produit depuis son Id)


##Requete privé 'METHODE UPDATE'(avecToken)
    - http://localhost:8082/api/produit/update/{id} (permet de modifier un produit depuis son Id)

