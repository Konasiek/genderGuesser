# Gender Guesser app

### JAR creation:
	mvn clean package
	cd target
	java -jar gender-guesser-1.0.jar
	
### API:
#### 1. localhost:8080/api/guess-gender
    params: "name", "guessVariant"
    
    example: 
    name: "Konrad Ewa Dąbrowski"
    guessVariant: "single"
    return: "MALE" (because only Konrad was used to check gender)
    
    example:
    name: "Konrad Ewa Dąbrowski"
    guessVariant: "multple"
    return: "INCONCLUSIVE" (Konrad is male and Ewa is female, Dąbrowski is ignored.)
    
#### 2. localhost:8080/api/get-tokens
    (pagination was natural choice for over 20k flat files records)
    (names are ordered in .csv by most usage)
    params: "gender", "page", "size"
    
    example: 
    gender: "male"
    page: "0"
    size: "5"
    return: "PIOTR", "ANDRZEJ", "PAWEŁ", "MICHAŁ", "JAKUB"

### Algorithm versions for checkmultipleName():

    commit: fef9e75e3287774c8db0d44c85c5eea622067798
    dummy method checks only 4 given names with counter male/female occurance
    
    commit: b4d3be702f50d6cd1d51f25488861bb21d981dd9
    attaching male.csv flat file from dane.gov.pl to the method, now method look for names in DB
    
    commit: 070986147e56eadfa4588b613ebf5b8da0d5958c
    adding female implementation as previous for male and female.csv
    
    commit: ef4be92d677f53d8a903aaa8ab93269761236099
    considering that name ending letter is valid information in polish names
    
### Algorithm versions for getGenderTokens():

    commit 64ad9b98b0ec2023d02dc73bf54e95c077d8a74f
    method is returning over 20k names in one request
    
    commit: 88935e833138ccc034d58332975185aafb6fb2de
    method is now returning only limited data depending on requested page and pagesize
    
### log of fixed bugs:

    - keeping .csv file lenght in in for loop -> while loop (.csv files may be swaped without changing manualy property in app)
    
