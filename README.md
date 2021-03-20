# Gender Guesser app

### JAR creation:
	mvn clean package
	cd target
	java -jar gender-guesser-1.0.jar
	
### API:
#### 1. localhost:8080/api/guess-gender
    params: "name", "guessVariant"  (default value for guessVariant = "SINGLE")
    
    example: 
    name: "Konrad Ewa Dąbrowski"
    guessVariant: "SINGLE"
    return: "MALE" (because only Konrad was used to check gender)
    
    example:
    name: "Konrad Ewa Dąbrowski"
    guessVariant: "MULTIPLE"
    return: "INCONCLUSIVE" (Konrad is male and Ewa is female, Dąbrowski is ignored.)
    
#### 2. localhost:8080/api/get-tokens
    (pagination was natural choice for over 20k flat files records)
    (names are ordered in .csv by most usage)
    params: "gender", "page", "size"
    
    example: 
    gender: "MALE"
    page: "0"
    size: "5"
    return: "PIOTR", "ANDRZEJ", "PAWEŁ", "MICHAŁ", "JAKUB"

### Algorithm versions for checkMultipleName():

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
    
### Algorithm versions for getGenderTokens():

    commit 64ad9b98b0ec2023d02dc73bf54e95c077d8a74f
    method is returning over 20k names in one request
    
    commit: 88935e833138ccc034d58332975185aafb6fb2de
    method is now returning only limited data depending on requested page and pagesize
    
### list of fixed bugs:

    - depending on .csv file lenght in for loop -> while loop (.csv files may be swaped without necessity of changing property in app)
    - using Enums insted of simple Strings
    - changing implementation of checkMultipleName and checkSingleName methods (solving 'Kuba problem')
    - name separators in given names are now: ",.;` !'*+_/-" witout quote marks
    
    
