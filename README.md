# Gender Guesser app

### JAR creation:
	mvn clean package
	cd target
	java -jar gender-guesser-1.0.jar
	
### API:
#### 1. localhost:8080/api/guess-gender
    path variables: "/name"/"guessVariant"  (variable guessVariant in no required, default value for guessVariant = "SINGLE")
  
    example: localhost:8080/api/guess-gender/Konrad Ewa Dąbrowski
    name: "Konrad Ewa Dąbrowski"
    guessVariant: "SINGLE"
    -> "MALE" (because only Konrad was used to check gender.)
    return: {
                "name": "Konrad Ewa Dąbrowski",
                "gender": "MALE",
                "guessVariant": "SINGLE",
                "_links": {
                    "self": {
                        "href": "http://localhost:8080/api/guess-gender/Konrad%20Ewa%20D%C4%85browski/SINGLE"
                    }
                }
            }
    
    example: localhost:8080/api/guess-gender/Konrad Ewa Dąbrowski/MULTIPLE
    name: "Konrad Ewa Dąbrowski"
    guessVariant: "MULTIPLE"
    -> "INCONCLUSIVE" (Konrad is male and Ewa is female, Dąbrowski is ignored.)
    RESTful return: {
                        "name": "Konrad Ewa Dąbrowski",
                        "gender": "INCONCLUSIVE",
                        "guessVariant": "MULTIPLE",
                        "_links": {
                            "self": {
                                "href": "http://localhost:8080/api/guess-gender/Konrad%20Ewa%20D%C4%85browski/MULTIPLE"
                            }
                        }
                    }
     
    
#### 2. localhost:8080/api/get-tokens
    (pagination was natural choice for over 20k flat files records)
    (names are ordered in .csv by most usage)
    
    path variable: "/gender"
    params: "page", "size"
    
    example: localhost:8080/api/get-tokens/FEMALE?page=0&size=5
    gender: "FEMALE"
    page: "0"
    size: "5"
    RESTful return: {
                        "content": [
                            {
                                "name": "ANNA"
                            },
                            {
                                "name": "KATARZYNA"
                            },
                            {
                                "name": "AGNIESZKA"
                            },
                            {
                                "name": "EWA"
                            },
                            {
                                "name": "MAGDALENA"
                            }
                        ],
                        "pageable": {
                            "sort": {
                                "sorted": false,
                                "unsorted": true,
                                "empty": true
                            },
                            "offset": 0,
                            "pageSize": 5,
                            "pageNumber": 0,
                            "unpaged": false,
                            "paged": true
                        },
                        "last": false,
                        "totalPages": 3476,
                        "totalElements": 17377,
                        "number": 0,
                        "size": 5,
                        "sort": {
                            "sorted": false,
                            "unsorted": true,
                            "empty": true
                        },
                        "first": true,
                        "numberOfElements": 5,
                        "empty": false,
                        "_links": {
                            "self": {
                                "href": "http://localhost:8080/api/get-tokens/FEMALE?page=0&size=5"
                            }
                        }
                    }

### versions for guess-gender:

    commit: fef9e75e3287774c8db0d44c85c5eea622067798
    dummy method checks only 4 given names with counter male/female occurance
    
    commit: b4d3be702f50d6cd1d51f25488861bb21d981dd9
    attaching male.csv flat file from dane.gov.pl to the method, now method look for names in DB
    
    commit: 070986147e56eadfa4588b613ebf5b8da0d5958c
    adding female implementation as previous for male and female.csv
    
    commit: ef4be92d677f53d8a903aaa8ab93269761236099
    considering that name ending letter is valid information in polish names
    
    *after feedback:
    commit: 65eb0b83adad27c48068835ac5d8b56365f0bc93
    fixing 'Kuba problem', now algorithm checks both male and female DBs and then letter -a 
    
    commit: e054518b419d34f3ca2dedaa99d335c6f7a76509
    changing name separators
    
    commit: 2afd7510ddf9f3ecc1bebaead6ee980e7ac17bf1
    now method return RESTful response
    
### versions for get-tokens:

    commit 64ad9b98b0ec2023d02dc73bf54e95c077d8a74f
    method is returning over 20k names in one request
    
    commit: 88935e833138ccc034d58332975185aafb6fb2de
    method is now returning only limited data depending on requested page and pagesize
    
    *after feedback:
    commit: 43869ef565c826af514b214072da9ddec74007f0
    now method return RESTful response
    
### list of improvement after feedback:

    - depending on .csv file lenght in for loop -> while loop (.csv files may be swaped without necessity of changing property in app)
    - using Enums insted of simple Strings
    - changing implementation of checkMultipleName and checkSingleName methods (solving 'Kuba problem')
    - name separators in given names are now: ",.;` !'*+_-" witout quote marks
    - making endopoints RESTful
    
    
